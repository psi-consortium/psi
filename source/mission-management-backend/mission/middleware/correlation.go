// Copyright 2025 THE PSI CONSORTIUM
//
// SPDX-License-Identifier: Apache-2.0

package middleware

import (
	"log/slog"
	"net/http"

	"github.com/gin-gonic/gin"
	"github.com/google/uuid"
	"github.com/psi-consortium/oda/mission-management/mission/config"
)

const CorrelationId = "CorrelationId"

// CorrelationMiddleware is a middleware for the gin-gonic router that handles Request Identifiers.
//
// With the help of [config.Configuration], a list of 'ExtCorrelationKeys' could be defined.
// If available, these request or correlation Ids are taken from the incoming requests, in order to be used for logging and any subsequent requests.
// In the case that no request Ids are provided, a new CorrelationId is generated for use.
func CorrelationMiddleware(extHeaderKeys []string) gin.HandlerFunc {

	return func(c *gin.Context) {

		extIds := getExternalIds(c.Request.Header, extHeaderKeys)
		if len(extIds) == 0 {
			corrId, _ := uuid.NewRandom()
			extIds[CorrelationId] = corrId.String()
		}

		attrs := []slog.Attr{}

		for key, value := range extIds {
			// c.Request.Header.Set(key, value)  // TODO: Of any help?
			attrs = append(attrs, slog.String(key, value))
		}
		c.Set(string(config.SlogFields), attrs)

		c.Next()
	}
}

func getExternalIds(header http.Header, extHeaderKeys []string) map[string]string {
	slog.Debug("getExternalIds - Checking for external request Ids", "extHeaderKeys", extHeaderKeys)

	extIds := make(map[string]string, len(extHeaderKeys))
	for _, key := range extHeaderKeys {
		v := header.Get(key)
		if v != "" {
			extIds[key] = v
		}
	}
	slog.Info("getExternalIds - External request Ids found:", "extIds", extIds)
	return extIds
}
