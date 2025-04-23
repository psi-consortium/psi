// Copyright 2025 THE PSI CONSORTIUM
//
// SPDX-License-Identifier: Apache-2.0

package mission

import (
	"context"
	"errors"
	"testing"

	mockeryMocks "github.com/sv-tools/mongoifc/mocks/mockery"
	"go.mongodb.org/mongo-driver/bson"
	"go.mongodb.org/mongo-driver/bson/primitive"
	"go.mongodb.org/mongo-driver/mongo"
	"go.mongodb.org/mongo-driver/mongo/options"

	"github.com/psi-consortium/oda/mission-management/internal/token"
	"github.com/psi-consortium/oda/mission-management/mission/config"
	"github.com/psi-consortium/oda/mission-management/mission/openapi"
	"github.com/stretchr/testify/assert"
	"github.com/stretchr/testify/mock"
)

const DbName = "DB-Setting-Name"
const DbCollection = "DB-Setting-Collection"
const DbHost = "db"
const DbPort = "12345"

func getRepositoryConfiguration() *config.Configuration {
	return &config.Configuration{
		Database: config.DatabaseSetting{
			Name:       DbName,
			Collection: DbCollection,
			Host:       DbHost,
			Port:       DbPort,
		},
	}
}

func TestRepAdd(t *testing.T) {

	t.Run("Successful", func(t *testing.T) {
		ctx := context.Background()
		missionId := "12345678"
		mission := openapi.Mission{
			Id:   missionId,
			Name: "Name for Creation",
		}
		oId := primitive.NewObjectID()

		col := &mockeryMocks.Collection{}
		defer col.AssertExpectations(t)
		col.EXPECT().InsertOne(ctx, mock.Anything).
			Return(&mongo.InsertOneResult{InsertedID: oId}, nil).Once()

		singleResult := &mockeryMocks.SingleResult{}
		defer singleResult.AssertExpectations(t)
		var filter bson.D
		col.EXPECT().FindOne(ctx, mock.Anything).Run(
			func(ctx context.Context, arg2 interface{}, opts ...*options.FindOneOptions) {
				filter = arg2.(bson.D)
			},
		).Return(singleResult).Once()
		singleResult.EXPECT().Decode(mock.Anything).
			Run(
				func(arg interface{}) {
					mis := arg.(*openapi.Mission)
					mis.SetId(missionId)
					mis.SetName("Result of getByOId")
				}).
			Return(nil).Once()

		missionRepository := createMissionRepository(getRepositoryConfiguration(), col)
		result, err := missionRepository.Add(ctx, &mission)

		assert.NoError(t, err)
		assert.NotSame(t, &mission, result)
		assert.NotEqual(t, &mission, result)
		assert.Equal(t, mission.Id, result.Id)
		assert.NotEqual(t, mission.Name, result.Name)

		expectedFilter := bson.D{bson.E{Key: "_id", Value: oId}}
		assert.Equal(t, expectedFilter, filter)
	})

	t.Run("Not Successful", func(t *testing.T) {

		t.Run("InsertOne failed", func(t *testing.T) {

			ctx := context.Background()
			mission := openapi.Mission{
				Id: "123456",
			}
			errAddFailed := errors.New("failed to add a Mission")

			col := &mockeryMocks.Collection{}
			defer col.AssertExpectations(t)
			col.EXPECT().InsertOne(ctx, mock.Anything).Return(nil, errAddFailed).Once()

			missionRepository := createMissionRepository(getRepositoryConfiguration(), col)
			result, err := missionRepository.Add(ctx, &mission)

			assert.Error(t, err)
			assert.ErrorIs(t, err, errAddFailed)
			assert.Nil(t, result)
		})

		t.Run("InsertOne result invalid", func(t *testing.T) {

			ctx := context.Background()
			mission := openapi.Mission{
				Id: "123456",
			}

			col := &mockeryMocks.Collection{}
			defer col.AssertExpectations(t)
			col.EXPECT().InsertOne(ctx, mock.Anything).
				Return(&mongo.InsertOneResult{InsertedID: "invalid OId"}, nil).Once()

			missionRepository := createMissionRepository(getRepositoryConfiguration(), col)
			result, err := missionRepository.Add(ctx, &mission)

			assert.Error(t, err)
			assert.ErrorContains(t, err, "invalid")
			assert.Nil(t, result)
		})
	})
}

func TestRepSave(t *testing.T) {

	t.Run("Successful", func(t *testing.T) {
		ctx := context.Background()
		mission := openapi.Mission{
			Id: "123456",
		}

		col := &mockeryMocks.Collection{}
		defer col.AssertExpectations(t)
		col.EXPECT().ReplaceOne(ctx, mock.Anything, mock.Anything).
			Return(&mongo.UpdateResult{UpsertedCount: 1}, nil).Once()

		missionRepository := createMissionRepository(getRepositoryConfiguration(), col)
		err := missionRepository.Save(ctx, &mission)

		assert.NoError(t, err)
	})

	t.Run("Not Successful", func(t *testing.T) {
		ctx := context.Background()
		mission := openapi.Mission{
			Id: "123456",
		}
		errReplaceOneFailed := errors.New("failed ReplaceOne")

		col := &mockeryMocks.Collection{}
		defer col.AssertExpectations(t)
		col.EXPECT().ReplaceOne(ctx, mock.Anything, mock.Anything).
			Return(nil, errReplaceOneFailed).Once()

		missionRepository := createMissionRepository(getRepositoryConfiguration(), col)
		err := missionRepository.Save(ctx, &mission)

		assert.Error(t, err)
		assert.ErrorIs(t, err, errReplaceOneFailed)
	})
}

func TestRepListAll(t *testing.T) {

	t.Run("Successful", func(t *testing.T) {

		t.Run("No Data", func(t *testing.T) {
			ctx := context.Background()

			cursor := mockeryMocks.NewCursor(t)
			defer cursor.AssertExpectations(t)
			cursor.EXPECT().Next(ctx).Return(false).Once()
			cursor.EXPECT().Close(ctx).Return(nil).Once()

			col := &mockeryMocks.Collection{}
			defer col.AssertExpectations(t)
			col.EXPECT().Find(ctx, mock.Anything, mock.Anything).
				Return(cursor, nil).Once()

			missionRepository := createMissionRepository(getRepositoryConfiguration(), col)
			missions, err := missionRepository.List(ctx, int64(0), int64(10))

			assert.NoError(t, err)
			assert.Len(t, missions, 0)
			assert.IsType(t, []*openapi.Mission{}, missions)
		})

		t.Run("With Missions and with Provider", func(t *testing.T) {
			providerId := "Prov554"
			limit := int64(20)
			skip := int64(10)
			ctx := token.WithPsiClaims(context.Background(), token.PsiClaims{PartyId: providerId})

			missionId := "12345"
			missionName := "Mission Test Name"

			cursor := mockeryMocks.NewCursor(t)
			defer cursor.AssertExpectations(t)
			cursor.EXPECT().Next(ctx).Return(true).Once()
			cursor.EXPECT().Next(ctx).Return(false).Once()
			cursor.EXPECT().Decode(mock.Anything).Run(
				func(arg interface{}) {
					mis := arg.(*openapi.Mission)
					mis.SetId(missionId)
					mis.SetName(missionName)
				}).Return(nil).Once()
			cursor.EXPECT().Close(ctx).Return(nil).Once()

			col := &mockeryMocks.Collection{}
			defer col.AssertExpectations(t)
			var filter bson.D
			var findOptions *options.FindOptions
			col.EXPECT().Find(ctx, mock.Anything, mock.Anything).
				Run(
					func(ctx context.Context, arg2 interface{}, opts ...*options.FindOptions) {
						filter = arg2.(bson.D)
						findOptions = opts[0]
					}).
				Return(cursor, nil).Once()

			missionRepository := createMissionRepository(getRepositoryConfiguration(), col)
			missions, err := missionRepository.List(ctx, skip, limit)

			assert.NoError(t, err)
			assert.Len(t, missions, 1)
			assert.IsType(t, []*openapi.Mission{}, missions)
			assert.Equal(t, missionId, missions[0].GetId())

			assert.NotNil(t, filter)
			assert.Len(t, filter, 1)
			relatedParty := filter[0]
			assert.Equal(t, "relatedparty", relatedParty.Key)
			assert.Equal(t, createProviderFilter(providerId), relatedParty)

			assert.NotNil(t, findOptions)
			assert.NotNil(t, findOptions.Limit)
			assert.Equal(t, *findOptions.Limit, limit)
			assert.NotNil(t, findOptions.Skip)
			assert.Equal(t, *findOptions.Skip, skip)
		})
	})
}

func TestRepCountAll(t *testing.T) {

	t.Run("Successful", func(t *testing.T) {

		t.Run("No Provider", func(t *testing.T) {
			ctx := context.Background()

			col := &mockeryMocks.Collection{}
			defer col.AssertExpectations(t)
			col.EXPECT().CountDocuments(ctx, mock.Anything, mock.Anything).Return(10, nil).Once()

			missionRepository := createMissionRepository(getRepositoryConfiguration(), col)
			count, err := missionRepository.CountAll(ctx)

			assert.NoError(t, err)
			assert.NotNil(t, count)
			assert.Equal(t, int64(10), count)
		})

		t.Run("With Provider", func(t *testing.T) {
			providerId := "Prov554"
			ctx := token.WithPsiClaims(context.Background(), token.PsiClaims{PartyId: providerId})

			col := &mockeryMocks.Collection{}
			defer col.AssertExpectations(t)
			var filter bson.D
			col.EXPECT().CountDocuments(ctx, mock.Anything, mock.Anything).Run(
				func(ctx context.Context, arg2 interface{}, opts ...*options.CountOptions) {
					filter = arg2.(bson.D)
				},
			).Return(10, nil).Once()

			missionRepository := createMissionRepository(getRepositoryConfiguration(), col)
			count, err := missionRepository.CountAll(ctx)

			assert.NoError(t, err)
			assert.NotNil(t, count)
			assert.Equal(t, int64(10), count)

			assert.NotNil(t, filter)
			assert.Len(t, filter, 1)
			relatedParty := filter[0]
			assert.Equal(t, "relatedparty", relatedParty.Key)
			assert.Equal(t, createProviderFilter(providerId), relatedParty)

		})
	})
}

func TestRepGetById(t *testing.T) {

	t.Run("Successful", func(t *testing.T) {

		t.Run("No Provider", func(t *testing.T) {

			missionId := "12345678"
			ctx := context.Background()
			mission := openapi.Mission{
				Id:          missionId,
				Name:        "Test-Name",
				Description: strPtr("Test-Description"),
			}

			singleResult := &mockeryMocks.SingleResult{}
			defer singleResult.AssertExpectations(t)
			singleResult.EXPECT().Decode(mock.Anything).Run(
				func(arg interface{}) {
					mis := arg.(*openapi.Mission)
					mis.SetId(missionId)
					mis.SetName(mission.Name)
				}).Return(nil).Once()

			col := &mockeryMocks.Collection{}
			defer col.AssertExpectations(t)
			col.EXPECT().FindOne(ctx, mock.Anything).Return(singleResult).Once()

			missionRepository := createMissionRepository(getRepositoryConfiguration(), col)
			result, err := missionRepository.GetById(ctx, missionId)

			assert.NoError(t, err)
			assert.NotNil(t, result)
			assert.IsType(t, &openapi.Mission{}, result)
			assert.Equal(t, mission.Id, result.Id)
		})

		t.Run("With Provider", func(t *testing.T) {
			missionId := "12345678"
			providerId := "Prov554"
			ctx := token.WithPsiClaims(context.Background(), token.PsiClaims{PartyId: providerId})
			mission := openapi.Mission{
				Id:          missionId,
				Name:        "Test-Name",
				Description: strPtr("Test-Description"),
			}

			singleResult := &mockeryMocks.SingleResult{}
			defer singleResult.AssertExpectations(t)
			singleResult.EXPECT().Decode(mock.Anything).Run(
				func(arg interface{}) {
					mis := arg.(*openapi.Mission)
					mis.SetId(missionId)
					mis.SetName(mission.Name)
				}).Return(nil).Once()

			col := &mockeryMocks.Collection{}
			defer col.AssertExpectations(t)
			var filter bson.D
			col.EXPECT().FindOne(ctx, mock.Anything).Run(
				func(ctx context.Context, arg2 interface{}, opts ...*options.FindOneOptions) {
					filter = arg2.(bson.D)
				},
			).Return(singleResult).Once()

			missionRepository := createMissionRepository(getRepositoryConfiguration(), col)
			result, err := missionRepository.GetById(ctx, missionId)

			assert.NoError(t, err)
			assert.NotNil(t, result)
			assert.IsType(t, &openapi.Mission{}, result)
			assert.Equal(t, mission.Id, result.Id)
			checkFilter(t, filter, missionId, providerId)
		})
	})

	t.Run("Not Successful", func(t *testing.T) {

		t.Run("No Id", func(t *testing.T) {
			ctx := context.Background()
			col := &mockeryMocks.Collection{}

			missionRepository := createMissionRepository(getRepositoryConfiguration(), col)
			result, err := missionRepository.GetById(ctx, "")

			assert.Error(t, err)
			assert.Regexp(t, "empty Id", err.Error())
			assert.Nil(t, result)
		})

		t.Run("No document found", func(t *testing.T) {

			missionId := "12345678"
			ctx := context.Background()

			singleResult := &mockeryMocks.SingleResult{}
			defer singleResult.AssertExpectations(t)
			singleResult.EXPECT().Decode(mock.Anything).Return(mongo.ErrNoDocuments).Once()

			col := &mockeryMocks.Collection{}
			defer col.AssertExpectations(t)
			col.EXPECT().FindOne(ctx, mock.Anything).Return(singleResult).Once()

			missionRepository := createMissionRepository(getRepositoryConfiguration(), col)
			_, err := missionRepository.GetById(ctx, missionId)

			assert.Error(t, err)
			assert.Equal(t, mongo.ErrNoDocuments, err)
		})
	})
}

func TestRepExists(t *testing.T) {

	t.Run("Successful", func(t *testing.T) {
		missionId := "12345678"
		ctx := context.Background()

		singleResult := &mockeryMocks.SingleResult{}
		defer singleResult.AssertExpectations(t)
		singleResult.EXPECT().Decode(mock.Anything).Run(
			func(arg interface{}) {
				mis := arg.(*openapi.Mission)
				mis.SetId(missionId)
			}).Return(nil).Once()

		col := &mockeryMocks.Collection{}
		defer col.AssertExpectations(t)
		col.EXPECT().FindOne(ctx, mock.Anything).Return(singleResult).Once()

		missionRepository := createMissionRepository(getRepositoryConfiguration(), col)
		result, err := missionRepository.Exists(ctx, missionId)

		assert.NoError(t, err)
		assert.NotNil(t, result)
		assert.True(t, result)
	})

	t.Run("Not Successful", func(t *testing.T) {
		ctx := context.Background()
		col := &mockeryMocks.Collection{}

		missionRepository := createMissionRepository(getRepositoryConfiguration(), col)
		result, err := missionRepository.Exists(ctx, "")

		assert.False(t, result)
		assert.Error(t, err)
		assert.Regexp(t, "empty Id", err.Error())
	})
}

func TestRepDelete(t *testing.T) {

	t.Run("Successful", func(t *testing.T) {

		t.Run("No Provider", func(t *testing.T) {
			missionId := "12345678"
			ctx := context.Background()

			col := &mockeryMocks.Collection{}
			defer col.AssertExpectations(t)
			col.EXPECT().DeleteOne(ctx, mock.Anything).
				Return(&mongo.DeleteResult{DeletedCount: 1}, nil).Once()

			missionRepository := createMissionRepository(getRepositoryConfiguration(), col)
			result, err := missionRepository.Delete(ctx, missionId)

			assert.NoError(t, err)
			assert.Equal(t, int64(1), result)
		})

		t.Run("With Provider", func(t *testing.T) {
			missionId := "12345678"
			providerId := "Prov123"
			ctx := token.WithPsiClaims(context.Background(), token.PsiClaims{PartyId: providerId})

			col := &mockeryMocks.Collection{}
			defer col.AssertExpectations(t)
			var filter bson.D
			col.EXPECT().DeleteOne(ctx, mock.Anything).Run(
				func(ctx context.Context, arg2 interface{}, opts ...*options.DeleteOptions) {
					filter = arg2.(bson.D)
				},
			).Return(&mongo.DeleteResult{DeletedCount: 1}, nil).Once()

			missionRepository := createMissionRepository(getRepositoryConfiguration(), col)
			result, err := missionRepository.Delete(ctx, missionId)

			assert.NoError(t, err)
			assert.Equal(t, int64(1), result)
			checkFilter(t, filter, missionId, providerId)
		})
	})

	t.Run("Not Successful", func(t *testing.T) {
		ctx := context.Background()
		anyErrorText := "any error"

		col := &mockeryMocks.Collection{}
		defer col.AssertExpectations(t)
		col.EXPECT().DeleteOne(ctx, mock.Anything).
			Return(&mongo.DeleteResult{DeletedCount: 0}, errors.New(anyErrorText)).Once()

		missionRepository := createMissionRepository(getRepositoryConfiguration(), col)
		_, err := missionRepository.Delete(ctx, "doesn't matter")

		assert.Error(t, err)
		assert.Equal(t, anyErrorText, err.Error())
	})
}

func checkFilter(t *testing.T, filter bson.D, missionId string, providerId string) {
	assert.NotNil(t, filter)
	assert.Len(t, filter, 2)
	assert.Equal(t, bson.E{"id", missionId}, filter[0])
	assert.Equal(t, "relatedparty", filter[1].Key)
	assert.Equal(t, createProviderFilter(providerId), filter[1])
}
