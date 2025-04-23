// Copyright 2025 THE PSI CONSORTIUM
//
// SPDX-License-Identifier: Apache-2.0

package mission

import (
	"context"
	"errors"
	"fmt"
	"log/slog"
	"strings"

	"github.com/psi-consortium/oda/mission-management/internal/database"
	"github.com/psi-consortium/oda/mission-management/internal/token"
	"github.com/psi-consortium/oda/mission-management/mission/config"
	"github.com/psi-consortium/oda/mission-management/mission/openapi"
	"github.com/sv-tools/mongoifc"
	"go.mongodb.org/mongo-driver/bson"
	"go.mongodb.org/mongo-driver/bson/primitive"
	"go.mongodb.org/mongo-driver/mongo/options"
)

// The MissionRepository is the interface between the [mission.MissionService] and the [database.MongoDB].
type MissionRepository interface {
	Add(ctx context.Context, mission *openapi.Mission) (*openapi.Mission, error)
	Save(ctx context.Context, mission *openapi.Mission) error
	List(ctx context.Context, size int64, page int64, sorting []string) ([]*openapi.Mission, error)
	GetById(ctx context.Context, id string) (*openapi.Mission, error)
	Delete(ctx context.Context, id string) (int64, error)
	CountAll(ctx context.Context) (int64, error)
	Exists(ctx context.Context, id string) (bool, error)
}

type missionRepository struct {
	collection mongoifc.Collection
	config     *config.Configuration
}

// NewMissionRepository initializes a new [mission.MissionRepository] instance based on the provided [config.Configuration] and [database.MongoDB].
func NewMissionRepository(config *config.Configuration, db database.DB) MissionRepository {
	client := db.(*database.MongoDb).GetMongoClient()
	collection := client.Database(config.Database.Name).Collection(config.Database.Collection)

	return createMissionRepository(config, collection)
}

func createMissionRepository(config *config.Configuration, collection mongoifc.Collection) MissionRepository {
	return &missionRepository{config: config, collection: collection}
}

func (rep *missionRepository) Add(
	ctx context.Context,
	mission *openapi.Mission,
) (*openapi.Mission, error) {

	insertResult, err := rep.collection.InsertOne(ctx, *mission)
	if err != nil {
		return nil, err
	}
	slog.DebugContext(ctx, "MissionRepository Add", "result", insertResult)

	if oidResult, ok := insertResult.InsertedID.(primitive.ObjectID); ok {
		slog.DebugContext(ctx, "MissionRepository - Add", "oidResult", oidResult)
		return rep.getByOId(ctx, oidResult)
	} else {
		slog.ErrorContext(ctx, "MissionRepository - Add, cannot access ObjectId", "insertResult", insertResult)
		return nil, errors.New("the result of the db insert try is invalid")
	}
}

func (rep *missionRepository) getByOId(
	ctx context.Context,
	oId primitive.ObjectID,
) (*openapi.Mission, error) {

	filter := bson.D{bson.E{Key: "_id", Value: oId}}
	var mission openapi.Mission
	rep.collection.FindOne(ctx, filter).Decode(&mission)

	return &mission, nil
}

func (rep *missionRepository) Save(
	ctx context.Context,
	mission *openapi.Mission,
) error {
	filter := bson.D{bson.E{Key: "id", Value: mission.GetId()}}
	result, err := rep.collection.ReplaceOne(ctx, filter, mission)
	if err != nil {
		return err
	}
	slog.DebugContext(ctx, "MissionRepository Save", "result", result)
	return nil
}

func createProviderFilter(providerId string) bson.E {
	return bson.E{
		Key: "relatedparty",
		Value: bson.D{
			{
				Key: "$elemMatch", Value: bson.D{
					{Key: "id", Value: providerId},
					{Key: "role", Value: "Provider"},
				},
			},
		},
	}
}

func (rep *missionRepository) List(
	ctx context.Context,
	offset int64,
	size int64,
	sorting []string,
) ([]*openapi.Mission, error) {

	slog.DebugContext(ctx, "missionRepository.List", "size", size, "offset", offset, "sorting", sorting)

	findOptions := options.Find()
	if offset >= 0 {
		findOptions.SetSkip(offset)
	}
	if size >= 0 {
		findOptions.SetLimit(size)
	}
	if len(sorting) > 0 {
		for _, sort := range sorting {
			sortDetails := strings.Split(sort, `,`)
			if len(sortDetails) == 2 {
				fieldname := strings.ToLower(sortDetails[0])
				order := 1
				if sortDetails[1] == "desc" {
					order = -1
				}
				findOptions.SetSort(bson.D{{Key: fieldname, Value: order}})
			}
		}
	}
	slog.DebugContext(ctx, fmt.Sprintf(
		"FindOptions %d, DbName %s, Host %s, Port %s", size, rep.config.Database.Name, rep.config.Database.Host, rep.config.Database.Port))

	filter := bson.D{}
	if providerId := getProviderId(ctx); providerId != "" {
		filter = bson.D{
			createProviderFilter(providerId),
		}
	}

	cursor, err := rep.collection.Find(ctx, filter, findOptions)
	if err != nil {
		slog.ErrorContext(ctx, "Error", "msg", err)
		return nil, err
	}

	var missions []*openapi.Mission
	// Finding multiple documents returns a cursor
	// Iterating through the cursor allows us to decode documents one at a time
	for cursor.Next(ctx) {
		var elem openapi.Mission
		if err := cursor.Decode(&elem); err != nil {
			slog.Any("Error", err)
			return nil, err
		}
		missions = append(missions, &elem)
	}

	cursor.Close(ctx)
	return missions, nil
}

func (rep *missionRepository) CountAll(ctx context.Context) (int64, error) {

	filter := bson.D{}
	if providerId := getProviderId(ctx); providerId != "" {
		filter = bson.D{
			createProviderFilter(providerId),
		}
	}
	opts := options.Count().SetHint("_id_") // performance optimization, to avoid the complete scan of the collection

	return rep.collection.CountDocuments(ctx, filter, opts)
}

func (rep *missionRepository) Exists(ctx context.Context, id string) (bool, error) {
	// TODO search for a solution without mission instantiation.
	mission, err := rep.GetById(ctx, id)
	if err != nil {
		return false, err
	}
	return mission != nil, nil
}

func (rep *missionRepository) GetById(
	ctx context.Context,
	id string,
) (*openapi.Mission, error) {
	if id == "" {
		return nil, errors.New("cannot load a Mission with an empty Id")
	}

	filter := bson.D{{Key: "id", Value: id}}
	if providerId := getProviderId(ctx); providerId != "" {
		filter = append(filter, createProviderFilter(providerId))
	}

	var mission openapi.Mission
	err := rep.collection.FindOne(ctx, filter).Decode(&mission)
	return &mission, err
}

func (req *missionRepository) Delete(
	ctx context.Context,
	id string,
) (int64, error) {
	filter := bson.D{{"id", id}}
	if providerId := getProviderId(ctx); providerId != "" {
		filter = append(filter, createProviderFilter(providerId))
	}

	result, err := req.collection.DeleteOne(ctx, filter)
	return result.DeletedCount, err
}

func getProviderId(ctx context.Context) string {
	return token.GetPartyId(ctx)
}
