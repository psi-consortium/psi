// Copyright 2025 THE PSI CONSORTIUM
//
// SPDX-License-Identifier: Apache-2.0

package middleware

import (
	"crypto/rand"
	"crypto/rsa"
	"crypto/x509"
	"encoding/base64"
	"encoding/json"
	"fmt"
	"log/slog"
	"net/http"
	"net/http/httptest"
	"testing"
	"time"

	"github.com/gin-gonic/gin"
	"github.com/golang-jwt/jwt/v5"
	"github.com/psi-consortium/oda/mission-management/internal/token"
	"github.com/psi-consortium/oda/mission-management/mission/config"
	"github.com/stretchr/testify/assert"
	"github.com/stretchr/testify/require"
)

type AuthData struct {
	PublicKey string `json:"public_key"`
}

func getOidConfig(publicKeyEndpoint string) config.OpenIdConnect {
	return config.OpenIdConnect{
		PublicKeyEndpoint: publicKeyEndpoint,
		Claims: config.ClaimMapping{
			PartyId: "customer_id",
			UserId:  "sub",
			Name:    "name",
		},
	}
}

func TestJwtAuthMiddleware(t *testing.T) {

	t.Run("Successful", func(t *testing.T) {

		// RSA keys:
		privateKey, err := generatePrivateKey(512)
		require.NoError(t, err, "generation of the private key failed")

		// Public Key - PEM encoded PKCS1
		publicKey := x509.MarshalPKCS1PublicKey(&privateKey.PublicKey)
		publicKeyB64 := base64.StdEncoding.EncodeToString(publicKey)

		// Authority Server
		authority := createAuthorityServer([]byte(publicKeyB64))
		defer authority.Close()
		oidConfig := getOidConfig(authority.URL)

		t.Run("No Claim Data", func(t *testing.T) {
			token, err := createToken(privateKey)
			require.NoError(t, err, "creation of Token failed")
			c, _ := getTestGinContext("GET", "/", token)

			ginHandlerFunc := JwtAuthMiddleware(oidConfig)
			ginHandlerFunc(c)

			assert.Equal(t, http.StatusOK, c.Writer.Status())
		})

		t.Run("With Claim Data", func(t *testing.T) {
			token, err := createTokenWithClaims(
				privateKey,
				&token.PsiClaims{PartyId: "P1234", UserId: "User-Id", Name: "User-Name"},
			)
			require.NoError(t, err, "creation of Token failed")
			c, _ := getTestGinContext("GET", "/", token)

			ginHandlerFunc := JwtAuthMiddleware(oidConfig)
			ginHandlerFunc(c)

			assert.Equal(t, http.StatusOK, c.Writer.Status())
		})
	})

	t.Run("Not Successful", func(t *testing.T) {
		privateKey, _ := generatePrivateKey(512)

		authority := createAuthorityServer([]byte("This is an invalid Public Key -> Validation will fail!"))
		defer authority.Close()
		oidConfig := getOidConfig(authority.URL)

		token, _ := createToken(privateKey)
		c, rr := getTestGinContext("GET", "/", token)

		ginHandlerFunc := JwtAuthMiddleware(oidConfig)
		ginHandlerFunc(c)

		assert.Equal(t, http.StatusUnauthorized, rr.Code)
	})
}

func createAuthorityServer(publicKey []byte) *httptest.Server {
	return httptest.NewServer(
		http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
			data := struct {
				PublicKey string `json:"public_key"`
			}{
				PublicKey: string(publicKey),
			}

			bytes, err := json.Marshal(data)
			if err != nil {
				slog.Error("Authority Server - Data processing", "err", err)
			}
			fmt.Fprint(w, string(bytes))
		}),
	)
}

func generatePrivateKey(bitSize int) (*rsa.PrivateKey, error) {
	privateKey, err := rsa.GenerateKey(rand.Reader, bitSize)
	if err != nil {
		return nil, err
	}
	return privateKey, nil
}

func createToken(signKey *rsa.PrivateKey) (string, error) {
	token := jwt.New(jwt.GetSigningMethod("RS256"))
	return token.SignedString(signKey)
}

func createTokenWithClaims(signKey *rsa.PrivateKey, psiClaims *token.PsiClaims) (string, error) {
	token := jwt.New(jwt.GetSigningMethod("RS256"))
	token.Claims = &jwt.MapClaims{
		"iss":         "Issuer",
		"exp":         time.Now().Add(time.Hour).Unix(),
		"customer_id": psiClaims.PartyId,
		"sub":         psiClaims.UserId,
		"name":        psiClaims.Name,
	}
	return token.SignedString(signKey)
}

func getTestGinContext(method string, path string, token string) (*gin.Context, *httptest.ResponseRecorder) {
	gin.SetMode(gin.TestMode)

	w := httptest.NewRecorder()
	c, _ := gin.CreateTestContext(w)

	c.Request, _ = http.NewRequest(method, path, nil)
	c.BindQuery(nil)

	c.Request.Header = make(http.Header)
	c.Request.Header.Set("Authorization", "Bearer "+token)

	return c, w
}
