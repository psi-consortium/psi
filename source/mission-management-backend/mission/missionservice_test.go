// Copyright 2025 THE PSI CONSORTIUM
//
// SPDX-License-Identifier: Apache-2.0

package mission

import (
	"context"
	"testing"
	"time"

	"github.com/psi-consortium/oda/mission-management/internal/token"
	mockmission "github.com/psi-consortium/oda/mission-management/mission/mocks"
	"github.com/psi-consortium/oda/mission-management/mission/openapi"
	"github.com/stretchr/testify/assert"
	"github.com/stretchr/testify/mock"
)

var EmptyContext = context.Background()

func initMissionService(misRep MissionRepository) MissionService {
	return NewMissionService(
		nil,
		misRep,
	)
}

func getTestContext(providerId string) context.Context {
	return token.WithPsiClaims(context.Background(), token.PsiClaims{PartyId: providerId})
}

func TestMSCreate(t *testing.T) {

	t.Run("Successful", func(t *testing.T) {

		misRep := mockmission.NewMockMissionRepository(t)
		missionService := initMissionService(misRep)

		ctx := EmptyContext
		desc := "TestDescription"
		missionCreate := openapi.MissionCreate{
			Name:        "TestName",
			Description: &desc,
		}
		expectedMission := openapi.Mission{
			Id:          "1234",
			Name:        "TestName",
			Description: &desc,
		}
		misRep.EXPECT().Add(ctx, mock.Anything).Return(&expectedMission, nil).Once()

		actualMission, err := missionService.Create(ctx, &missionCreate)

		assert.NoError(t, err)
		assert.NotNil(t, actualMission)
		assert.IsType(t, &openapi.Mission{}, actualMission)
		assert.Equal(t, &expectedMission, actualMission)

		misRep.AssertExpectations(t)
	})

	t.Run("Successful With Provider", func(t *testing.T) {
		misRep := mockmission.NewMockMissionRepository(t)
		missionService := initMissionService(misRep)

		providerId := "Prov654"
		ctx := getTestContext(providerId)

		buyerRole := "Buyer"
		missionCreate := openapi.MissionCreate{
			RelatedParty: []openapi.RelatedParty{
				{
					Id:   "Customer123",
					Role: &buyerRole,
				},
			},
		}
		misRep.EXPECT().Add(ctx, mock.Anything).
			RunAndReturn(func(ctx context.Context, adjustedMission *openapi.Mission) (*openapi.Mission, error) {
				return adjustedMission, nil
			}).
			Once()

		actualMission, err := missionService.Create(ctx, &missionCreate)

		assert.NoError(t, err)
		assert.NotNil(t, actualMission)
		assert.IsType(t, &openapi.Mission{}, actualMission)

		assert.NotNil(t, actualMission.RelatedParty)
		assert.Len(t, actualMission.RelatedParty, 2)
		assert.Equal(t, providerId, actualMission.RelatedParty[1].Id)
		assert.Equal(t, "Provider", *(actualMission.RelatedParty[1].Role))

		misRep.AssertExpectations(t)
	})
}

func TestPatch(t *testing.T) {
	misRep := mockmission.NewMockMissionRepository(t)
	missionService := initMissionService(misRep)

	ctx := EmptyContext
	missionId := "1234"
	mission := openapi.Mission{Id: missionId}
	adjustedMission := openapi.Mission{Id: "Undefined"}

	misRep.EXPECT().GetById(ctx, missionId).Return(&mission, nil).Once()
	misRep.EXPECT().Save(ctx, mock.Anything).
		RunAndReturn(func(ctx context.Context, mission *openapi.Mission) error {
			adjustedMission = *mission
			return nil
		}).
		Once()
	misRep.EXPECT().GetById(ctx, missionId).Return(&adjustedMission, nil).Once()

	name := "Update-Name"
	category := "Update-Category"
	desc := "Update-Desc"
	status := openapi.ACTIVE
	emptyString := ""
	missionUpdate := openapi.MissionUpdate{
		Name:                &name,
		Description:         &desc,
		Category:            &category,
		Timeframe:           newTimeFrame(time.Now().AddDate(0, 0, 1), time.Now().AddDate(0, 1, 1)),
		Status:              &status,
		Characteristic:      []openapi.Characteristic{},
		Place:               []openapi.PlaceRefOrValue{},
		Asset:               []openapi.MissionAsset{},
		MissionRelationship: []openapi.MissionRelationship{},
		RelatedParty:        []openapi.RelatedParty{},
		BaseType:            &emptyString,
		SchemaLocation:      &emptyString,
		Type:                &emptyString,
	}

	actualMission, err := missionService.Patch(ctx, missionId, &missionUpdate)

	assert.NoError(t, err)
	assert.NotNil(t, actualMission)
	assert.IsType(t, &openapi.Mission{}, actualMission)

	assert.Equal(t, missionId, actualMission.Id)
	assert.Equal(t, name, actualMission.Name)
	assert.Equal(t, desc, *actualMission.Description)
	assert.Equal(t, category, *actualMission.Category)
	assert.Equal(t, status, *actualMission.Status)

	misRep.AssertExpectations(t)
}

func TestCountAll(t *testing.T) {
	misRep := mockmission.NewMockMissionRepository(t)
	missionService := initMissionService(misRep)

	expectedCount := int64(111)
	misRep.EXPECT().CountAll(EmptyContext).Return(expectedCount, nil).Once()

	actualCount, err := missionService.CountAll(context.Background())

	assert.NoError(t, err)
	assert.Equal(t, expectedCount, actualCount)
}

func TestList(t *testing.T) {
	misRep := mockmission.NewMockMissionRepository(t)
	missionService := initMissionService(misRep)

	foundMissions := []*openapi.Mission{}
	misRep.EXPECT().List(EmptyContext, int64(0), int64(10)).Return(foundMissions, nil).Once()

	missions, err := missionService.List(context.Background(), 0, 10)
	assert.NoError(t, err)
	assert.NotNil(t, missions, "MissionService.List returns no mission list: %v", missions)
	assert.IsType(t, []*openapi.Mission{}, missions)
}

func TestGetById(t *testing.T) {
	misRep := mockmission.NewMockMissionRepository(t)
	missionService := initMissionService(misRep)

	id := "1234"
	expectedMission := &openapi.Mission{Id: id}
	misRep.EXPECT().GetById(EmptyContext, id).Return(expectedMission, nil).Once()

	actualMission, err := missionService.GetById(context.Background(), id)

	assert.NoError(t, err)
	assert.NotNil(t, actualMission)
	assert.IsType(t, &openapi.Mission{}, actualMission)
	assert.Equal(t, expectedMission, actualMission)
}

func TestDelete(t *testing.T) {
	misRep := mockmission.NewMockMissionRepository(t)
	missionService := initMissionService(misRep)

	id := "1234"
	expectedCount := int64(1)
	misRep.EXPECT().Delete(EmptyContext, id).Return(expectedCount, nil).Once()

	actualCount, err := missionService.Delete(context.Background(), id)

	assert.NoError(t, err)
	assert.Equal(t, expectedCount, actualCount)
}

func TestCreateProviderRef(t *testing.T) {
	providerId := "TestId"
	providerRole := "Provider"

	result := createProviderRef(providerId)

	if result.Id != providerId {
		t.Errorf(`got "%s", expected "%s"`, result.Id, providerId)
	}
	if *(result.Role) != providerRole {
		t.Fatalf(`got "%s", expected "%s"`, *(result.Role), providerRole)
	}
}
