// Copyright 2025 THE PSI CONSORTIUM
//
// SPDX-License-Identifier: Apache-2.0

// Package server deals with the initialization and starting of the MissionManagement server.
package server

import (
	"fmt"
	"log/slog"

	"github.com/psi-consortium/oda/mission-management/internal/database"
	"github.com/psi-consortium/oda/mission-management/mission"
	"github.com/psi-consortium/oda/mission-management/mission/config"
)

// Start initializes with the help fo the [scheme.Configuration]
//
//  1. the MongoDb
//  2. the MissionRepository
//  3. the MissionService (Mission Business Logic)
//  4. the MissionHandler (interprets the incoming http requests)
//  5. the router (routing incoming requests to the MissionHandler)
//
// and finally starts the http server.
func Start(config config.Configuration) {

	slog.Info(fmt.Sprintf("Application Name %s is starting....", config.App.Name))

	db := database.NewMongoDb(config.Database.Scheme, config.Database.Host, config.Database.Port,
		config.Database.User, config.Database.Password, config.Database.AuthSource)

	defer func() {
		if err := db.Disconnect(); err != nil {
			panic(err)
		}
	}()

	repository := mission.NewMissionRepository(&config, db)
	missionService := mission.NewMissionService(&config, repository)
	handler := mission.NewMissionHandler(&config, missionService)
	router := mission.NewRouter(handler, db, config)

	if config.App.DemoData {
		mission.CreateDemoData(repository, &config)
	}

	router.Run(":" + config.App.Port) // listen on ":<port>" to support TCP4 and TCP6
}
