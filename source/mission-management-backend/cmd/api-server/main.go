// Copyright 2025 THE PSI CONSORTIUM
//
// SPDX-License-Identifier: Apache-2.0

// Package main is used as the starting point for the applications, like the MissionManagement api server.
package main

import (
	"log/slog"

	"github.com/psi-consortium/oda/mission-management/mission/config"
	"github.com/psi-consortium/oda/mission-management/server"
)

func main() {
	config := config.LoadConfiguration()
	server.InitLogger(config.Logging)
	slog.Debug("Config: ", "config", config)

	server.Start(config)
}
