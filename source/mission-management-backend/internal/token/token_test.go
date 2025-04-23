// Copyright 2025 THE PSI CONSORTIUM
//
// SPDX-License-Identifier: Apache-2.0

package token

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
	"github.com/psi-consortium/oda/mission-management/mission/config"
	"github.com/stretchr/testify/assert"
	"github.com/stretchr/testify/require"
)

type AuthData struct {
	PublicKey string `json:"public_key"`
}

func getClaimMapping() config.ClaimMapping {
	return config.ClaimMapping{
		PartyId: "customer_id",
		UserId:  "sub",
		Name:    "name",
	}
}

func TestIsValid(t *testing.T) {

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

		tokenHandler := Init(authority.URL, getClaimMapping())

		t.Run("No Claim Data", func(t *testing.T) {
			token, err := createToken(privateKey)
			require.NoError(t, err, "creation of Token failed")
			ctx, _ := getTestGinContext("GET", "/", token)

			err = tokenHandler.IsValid(ctx)

			assert.NoError(t, err, "")
			claims, exists := ctx.Get(ContextClaimsKey)
			assert.True(t, !exists || claims == nil || claims.(*PsiClaims).IsEmpty())
		})

		t.Run("With Claim Data", func(t *testing.T) {
			partyId := "p12345"
			userId := "Test User Id"
			userName := "Test User Name"
			token, err := createTokenWithClaims(
				privateKey,
				&PsiClaims{PartyId: partyId, UserId: userId, Name: userName},
			)
			require.NoError(t, err, "creation of Token failed")
			ctx, _ := getTestGinContext("GET", "/", token)

			err = tokenHandler.IsValid(ctx)

			assert.NoError(t, err)
			data, exists := ctx.Get(ContextClaimsKey)
			claims := data.(*PsiClaims)
			assert.True(t, exists && claims != nil && !claims.IsEmpty())
			assert.Equal(t, partyId, claims.PartyId)
			assert.Equal(t, userId, claims.UserId)
			assert.Equal(t, userName, claims.Name)
		})
	})

	t.Run("Not Successful", func(t *testing.T) {

		t.Run("Invalid RSA Public key", func(t *testing.T) {
			privateKey, _ := generatePrivateKey(512)

			authority := createAuthorityServer([]byte("This is an invalid Public Key -> Validation will fail!"))
			defer authority.Close()

			token, _ := createToken(privateKey)

			tokenHandler := Init(authority.URL, getClaimMapping())
			ctx, _ := getTestGinContext("GET", "/", token)

			err := tokenHandler.IsValid(ctx)

			assert.Error(t, err, "RSA public key is invalid -> validation must fail.")
		})

		t.Run("Invalid Token", func(t *testing.T) {
			token := "This_is_an_invalid_token_String_->_validation_should_fail_early"

			ctx, _ := getTestGinContext("GET", "/", token)

			tokenHandler := Init("http://will/not/be/used.org", getClaimMapping())
			err := tokenHandler.IsValid(ctx)

			assert.Error(t, err, "Invalid token (malformed).")
		})

		t.Run("Unexpected Signing Method", func(t *testing.T) {
			var simpleSigningKey = []byte("AllYourBase")
			token := jwt.New(jwt.SigningMethodHS256)
			signedString, _ := token.SignedString(simpleSigningKey)

			ctx, _ := getTestGinContext("GET", "/", signedString)

			tokenHandler := Init("not used, fails before", getClaimMapping())
			err := tokenHandler.IsValid(ctx)

			assert.Error(t, err, "Invalid signing method, 'jwt.SigningMethodRSA' expected.")
			assert.Regexp(t, "unexpected signing method: HS256", err.Error())
		})

		t.Run("No Authorization Bearer Found", func(t *testing.T) {
			gin.SetMode(gin.TestMode)
			ctx, _ := gin.CreateTestContext(httptest.NewRecorder())
			ctx.Request, _ = http.NewRequest("GET", "/", nil)

			tokenHandler := Init("not used, fails before", getClaimMapping())
			err := tokenHandler.IsValid(ctx)

			assert.Error(t, err, "Invalid signing method, 'jwt.SigningMethodRSA' expected.")
			assert.Regexp(t, "no bearer", err.Error())
		})
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

func createTokenWithClaims(signKey *rsa.PrivateKey, psiClaims *PsiClaims) (string, error) {
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
