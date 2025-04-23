// Copyright 2025 THE PSI CONSORTIUM
//
// SPDX-License-Identifier: Apache-2.0

package middleware

import (
	"log/slog"
	"net/http"
	"time"

	"github.com/gin-gonic/gin"
	"github.com/psi-consortium/oda/mission-management/internal/token"
	"github.com/psi-consortium/oda/mission-management/mission/config"
)

// JwtAuthMiddleware is a middleware for the gin-gonic router that deals with the authentication via JWT.
//
// The mode this middleware could run in and the way of the configuration is explained in detail in [config.OpenIdConnect].
func JwtAuthMiddleware(openIdConnectConfig config.OpenIdConnect) gin.HandlerFunc {

	tokenHandler := token.Init(openIdConnectConfig.PublicKeyEndpoint, openIdConnectConfig.Claims)

	return func(c *gin.Context) {
		err := tokenHandler.IsValid(c) // Adds determined Claim-Values to the context
		if err != nil {
			slog.Warn("OpenId Connect token validation", "Error", err.Error())
			c.IndentedJSON(http.StatusUnauthorized, gin.H{
				"timestamp": time.Now().UTC().Truncate(time.Millisecond),
				"status":    401,
				"error":     "Unauthorized",
				"message":   "Please authenticate",
			})
			c.Abort()
			return
		}
		c.Next()
	}
}
