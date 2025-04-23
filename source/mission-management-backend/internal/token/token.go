// Copyright 2025 THE PSI CONSORTIUM
//
// SPDX-License-Identifier: Apache-2.0

package token

import (
	"encoding/json"
	"errors"
	"fmt"
	"log/slog"
	"net/http"
	"strings"
	"time"

	"github.com/gin-gonic/gin"
	"github.com/golang-jwt/jwt/v5"
	"github.com/patrickmn/go-cache"
	"github.com/psi-consortium/oda/mission-management/mission/config"
)

var myClient = &http.Client{Timeout: 10 * time.Second}
var memCache = cache.New(5*time.Minute, 10*time.Minute)

// TokenHandler stores the PublicKeyEndpoint and the ClaimMapping, which are used for the authentication.
type TokenHandler struct {
	publicKeyEndpoint string
	claimMapping      config.ClaimMapping
}

// Init initializes a new [token.TokenHandler] with the provided PublicKeyEndpoint and ClaimMapping.
func Init(publicKeyEndpoint string, claimMapping config.ClaimMapping) *TokenHandler {
	memCache.Flush()
	return &TokenHandler{publicKeyEndpoint: publicKeyEndpoint, claimMapping: claimMapping}
}

func (tokenHandler *TokenHandler) getPublicKey() []byte {
	const publicKeyKey string = "publicKey"
	if publicKey, found := memCache.Get(publicKeyKey); found {
		slog.Debug("Found 'PublicKey' in local cache", "PublicKey", string(publicKey.([]byte)))
		return publicKey.([]byte)
	}

	type Data struct {
		PublicKey string `json:"public_key"`
	}

	authority := tokenHandler.publicKeyEndpoint // "AUTHORITY"
	slog.Debug("PublicKey retrieval:", "Authority", authority)

	data := Data{}
	getJson(authority, &data)
	slog.Debug("PublicKey retrieval:", "PublicKey", data.PublicKey)

	publicKey := addPemEnvelope(data.PublicKey)
	memCache.Set(publicKeyKey, publicKey, 2*time.Minute)
	return publicKey
}

func addPemEnvelope(publicKey string) []byte {
	return []byte("-----BEGIN Public KEY-----\n" + publicKey + "\n-----END Public KEY-----")
}

// IsValid checks that the provided JWT is valid, else an error is returned.
func (tokenHandler *TokenHandler) IsValid(c *gin.Context) error {

	tokenString := extractTokenFromHeader(c)
	if tokenString == "" {
		return errors.New("no bearer token found in 'Authorization' header")
	}

	token, err := jwt.Parse(
		tokenString,
		func(token *jwt.Token) (interface{}, error) {
			if _, ok := token.Method.(*jwt.SigningMethodRSA); !ok {
				return nil, fmt.Errorf("unexpected signing method: %v", token.Header["alg"])
			}

			rsaPublicKey, err := jwt.ParseRSAPublicKeyFromPEM(tokenHandler.getPublicKey())
			if err != nil {
				slog.Debug("Received PublicKey is invalid.", "Error", err.Error())
				return nil, err
			}
			return rsaPublicKey, nil
		})

	if err != nil || token == nil || !token.Valid {
		slog.Error("JWT Parsing result", "Error", err.Error())
		return err
	}

	if isClaimMappingDefined(&tokenHandler.claimMapping) {
		slog.Debug("tokenHandler.IsValid:", "token.Claims", token.Claims)
		c.Set(ContextClaimsKey, getPsiClaims(token, &tokenHandler.claimMapping))
	}
	return nil
}

func isClaimMappingDefined(claimMapping *config.ClaimMapping) bool {
	return claimMapping.PartyId != "" ||
		claimMapping.UserId != "" ||
		claimMapping.Name != ""
}

func getPsiClaims(token *jwt.Token, claimMapping *config.ClaimMapping) *PsiClaims {
	psiClaims := &PsiClaims{
		PartyId: "",
	}

	if claims, ok := token.Claims.(jwt.MapClaims); ok {
		if value := claims[claimMapping.PartyId]; value != nil && value != "" {
			psiClaims.PartyId = value.(string)
		}

		if value := claims[claimMapping.UserId]; value != nil && value != "" {
			psiClaims.UserId = value.(string)
		}

		if value := claims[claimMapping.Name]; value != nil && value != "" {
			psiClaims.Name = value.(string)
		}
	} else {
		slog.Error("Claim mapping for PartyId defined, but the Claim data is not accessible.")
	}

	slog.Debug("Found Claims", "PsiClaims", psiClaims)
	return psiClaims
}

func extractTokenFromHeader(c *gin.Context) string {
	bearerToken := c.Request.Header.Get("Authorization")
	if len(strings.Split(bearerToken, " ")) == 2 {
		return strings.Split(bearerToken, " ")[1]
	}
	slog.Warn("Token extraction not possible: No Bearer Token found")
	return ""
}

func getJson(url string, target interface{}) error {
	response, err := myClient.Get(url)
	if err != nil {
		return err
	}
	defer response.Body.Close()
	return json.NewDecoder(response.Body).Decode(target)
}
