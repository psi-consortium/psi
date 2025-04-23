// Copyright 2025 THE PSI CONSORTIUM
//
// SPDX-License-Identifier: Apache-2.0

package mission

import (
	"fmt"
	"log/slog"
	"net/http"
	"path/filepath"

	"github.com/gin-contrib/cors"
	"github.com/gin-gonic/gin"
	"github.com/psi-consortium/oda/mission-management/internal/database"
	"github.com/psi-consortium/oda/mission-management/mission/config"
	"github.com/psi-consortium/oda/mission-management/mission/middleware"
)

// NewRouter creates a new [gin-gonic/gin] router instance utilizing the [mission.MissionHandler] and the [config.Configuration].
// This includes also the integration of the middleware.
func NewRouter(handler MissionHandler, db database.DB, config config.Configuration) *gin.Engine {
	router := gin.Default()

	router.GET("/health", func(c *gin.Context) { healthCheck(c, db) })

	apiGroup := router.Group("/" + config.App.PathPrefix)
	{
		useMiddleWare(apiGroup, &config)

		apiGroup.POST("", handler.Create)
		apiGroup.GET("", handler.List)
		apiGroup.GET("/:id", handler.GetById)
		apiGroup.PATCH("/:id", handler.Patch)
		apiGroup.DELETE("/:id", handler.Delete)
	}

	return router
}

func healthCheck(c *gin.Context, db database.DB) {
	if !db.Healthy() {
		c.IndentedJSON(http.StatusOK, gin.H{"status": "No DB"})
	}
	c.IndentedJSON(http.StatusOK, gin.H{"status": "UP"})
}

func useMiddleWare(group *gin.RouterGroup, config *config.Configuration) {

	slog.Debug("Gin Middleware", "config.App.Cors", config.App.Cors)
	if config.App.Cors.AllowOrigins != "" {
		corsConfig := cors.DefaultConfig()
		if config.App.Cors.AllowOrigins != "" {
			corsConfig.AllowOrigins = []string{config.App.Cors.AllowOrigins}
		}
		if config.App.Cors.AllowHeaders != "" {
			corsConfig.AllowHeaders = []string{config.App.Cors.AllowHeaders}
		}

		slog.Debug(fmt.Sprintf("ORS Middleware cors - corsConfig: %+v", corsConfig))

		group.Use(cors.New(corsConfig))
	}

	group.Use(middleware.CorrelationMiddleware(config.App.ExtCorrelationKeys))

	if config.App.Security.OpenIdConnect.PublicKeyEndpoint != "" {
		slog.Debug("NewRouter - OpenIdConnect Config:", "config.App.Security", config.App.Security)
		group.Use(middleware.JwtAuthMiddleware(config.App.Security.OpenIdConnect))
	}

	if config.App.OpenApi.Validation {
		openApiFilename := config.App.OpenApi.Definition
		if openApiFilename == "" {
			openApiFilename, _ = filepath.Abs("./internal/api/PSID002-MissionManagement_v2.oas.json")
		}
		group.Use(middleware.OpenApiValidationMiddleware(openApiFilename, config.App.OpenApi.IncludeResponseValidation))
	}
}
