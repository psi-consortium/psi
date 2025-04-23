// Copyright 2025 THE PSI CONSORTIUM
//
// SPDX-License-Identifier: Apache-2.0

package database

import (
	"context"
	"log"
	"log/slog"
	"time"

	// "github.com/psi-consortium/oda/mission-management/mission/config"
	"github.com/sv-tools/mongoifc"
	"go.mongodb.org/mongo-driver/mongo/options"
)

type MongoDb struct {
	db mongoifc.Client
}

func (mdb *MongoDb) GetClient() interface{} {
	return mdb.GetMongoClient()
}

func (mdb *MongoDb) GetMongoClient() mongoifc.Client {
	return mdb.db
}

func (mdb *MongoDb) Disconnect() error {
	return mdb.db.Disconnect(context.Background())
}

func (mdb *MongoDb) Healthy() bool {
	ctx, cancel := context.WithTimeout(context.Background(), 1*time.Second)
	defer cancel()

	err := mdb.db.Ping(ctx, nil)
	if err != nil {
		log.Fatalf("db down: %v", err)
	}
	return true
}

// func NewMongoDb(config config.Configuration) DB {
func NewMongoDb(scheme string, host string, port string, user string, password string, authSource string) DB {

	// e.g. "mongodb://localhost:27017"
	dbUrl := scheme + "://" + host + ":" + port
	slog.Debug("Server Init", "DbUrl", dbUrl)

	credential := options.Credential{
		Username:   user,
		Password:   password,
		AuthSource: authSource,
	}
	clientOpts := options.Client().ApplyURI(dbUrl).SetAuth(credential)

	client, err := mongoifc.Connect(context.Background(), clientOpts)
	if err != nil {
		panic(err)
	}
	return &MongoDb{
		db: client,
	}
}
