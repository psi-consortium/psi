// Copyright 2025 THE PSI CONSORTIUM
//
// SPDX-License-Identifier: Apache-2.0

package config

import (
	"log/slog"
	"os"
	"path/filepath"
	"strings"

	"github.com/spf13/viper"
)

const loggingLevel = "Logging.Level"

// Configuration is used to manage the entire application configuration.
//
// All configurations could be adjusted via ENV variables.
// The naming of the ENV variables is based on the structure of [config.Configuration], e.g. LOGGING_LEVEL, DATABASE_USER, DATABASE_PASSWORD, etc.
//
// Alternatively, the configuration can be carried out via a YML file to be provided.
// This requires the path to the YML file to be defined via the ENV variable "CONFIG_PATH".
type Configuration struct {
	Database DatabaseSetting
	App      Application
	Logging  LogSettings
}

// All configuration parameters for the database come together here.
type DatabaseSetting struct {
	Scheme     string
	Host       string
	Port       string
	User       string
	Password   string
	Name       string
	AuthSource string
	Collection string
}

// All configuration parameters for the MissionManagement web server come together here.
// This includes the possibility to setup [config.OpenApi] and [config.OpenIdConnect] (as part of [config.Security]) usage.
type Application struct {
	Port               string
	PathPrefix         string
	Name               string
	Timeout            int
	ReleaseMode        bool
	DemoData           bool // To enable the creation of demo data
	OpenApi            OpenApi
	Security           Security
	ExtCorrelationKeys []string // A list of optionally available, external Ids that identifies a request. See [middleware.CorrelationMiddleware]
	Cors               CorsConfig
}

// OpenApi struct services the configuration of all OpenApi supporting code.
type OpenApi struct {
	Validation                bool   // Incoming Requests are validated - default should be true
	IncludeResponseValidation bool   // Outgoing Responses are validated - default should be false
	Definition                string // A default OpenApi definition is provided with the web server binary.
}

type CorsConfig struct {
	AllowOrigins string
	AllowHeaders string
}

type ClaimMapping struct {
	UserId  string
	Name    string
	PartyId string
}

// OpenIdConnect provides the option to include an OpenId Provider.
// Three different access levels are supported:
//  1. No access restrictions: PublicKeyEndpoint is empty
//  2. Authentication via OpenIdConnect: Valid PublicKeyEndpoint is defined
//  3. Data access restriction for owner: Valid PublicKeyEndpoint plus a PartyId ClaimMapping
//
// When using options 2 and 3, a valid JWT must be transmitted with every incoming HTTP request.
type OpenIdConnect struct {
	PublicKeyEndpoint string
	Claims            ClaimMapping
}

// Security provides the [config.OpenIdConnect] setup for the web server.
type Security struct {
	OpenIdConnect OpenIdConnect
}

type LogSettings struct {
	Level   string
	Handler string
}

func LoadConfiguration() Configuration {
	return readConfigurationYml(getEnv("CONFIG_PATH", "./config.yml"))
}

func getEnv(key, fallback string) string {
	if value, ok := os.LookupEnv(key); ok {
		return value
	}
	return fallback
}

func readConfigurationYml(configurationPath string) Configuration {

	slog.Info("readConfigurationYml", "configurationPath", configurationPath)

	dir, file := filepath.Split(configurationPath)
	ext := filepath.Ext(configurationPath)

	viper.AddConfigPath(dir)
	viper.SetConfigName(strings.TrimSuffix(file, ext))
	viper.SetConfigType(strings.TrimLeft(ext, "."))

	viper.AutomaticEnv()
	viper.SetEnvKeyReplacer(strings.NewReplacer(".", "_")) // Only one Replacer supported.

	viper.BindEnv("Database.Scheme", "DB_SCHEME")
	viper.BindEnv("Database.Host", "DB_HOST")
	viper.BindEnv("Database.Port", "DB_PORT")
	viper.BindEnv("Database.User", "DB_USER")
	viper.BindEnv("Database.Password", "DB_PASSWORD")
	viper.BindEnv("Database.Name", "DB_NAME")
	viper.BindEnv("Database.AuthSource", "DB_AUTH_SOURCE")
	viper.BindEnv("Database.Collection", "DB_COLLECTION")

	// Fallback for old env names
	viper.BindEnv("App.Security.OpenIdConnect.PublicKeyEndpoint", "OPENID_CONNECT_PUBLIC_KEY_ENDPOINT")
	viper.BindEnv("App.Security.OpenIdConnect.Claims.PartyId", "CLAIM_PARTY_ID")
	viper.BindEnv("App.Security.OpenIdConnect.Claims.UserId", "CLAIM_USER_ID")
	viper.BindEnv("App.Security.OpenIdConnect.Claims.Name", "CLAIM_NAME")

	viper.BindEnv("App.DemoData", "DEMO_DATA")
	viper.BindEnv("App.ReleaseMode", "RELEASE_MODE")

	viper.BindEnv(loggingLevel, "LOG_LEVEL")
	viper.BindEnv("Logging.LogHandler", "LOG_HANDLER")
	if viper.GetString(loggingLevel) != "" {
		viper.Set(loggingLevel, strings.ToUpper(viper.GetString(loggingLevel)))
	}

	if err := viper.ReadInConfig(); err != nil {
		slog.Error("Error reading config file", "error", err)
	}

	var config Configuration
	err := viper.Unmarshal(&config)
	if err != nil {
		slog.Error("Unable to decode into struct", "error", err)
	}
	return config
}
