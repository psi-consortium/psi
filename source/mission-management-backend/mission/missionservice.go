// Copyright 2025 THE PSI CONSORTIUM
//
// SPDX-License-Identifier: Apache-2.0

package mission

import (
	"context"
	"encoding/json"
	"errors"
	"log/slog"
	"time"

	"github.com/google/uuid"
	"github.com/psi-consortium/oda/mission-management/internal/token"
	"github.com/psi-consortium/oda/mission-management/mission/config"
	"github.com/psi-consortium/oda/mission-management/mission/openapi"
)

const providerRole = "Provider"

// The MissionService represents the business logic for the Mission utilizing the [mission.MissionRepository].
type MissionService interface {
	Create(ctx context.Context, mission *openapi.MissionCreate) (*openapi.Mission, error)
	Patch(ctx context.Context, id string, patch *openapi.MissionUpdate) (*openapi.Mission, error)
	CountAll(ctx context.Context) (int64, error)
	List(ctx context.Context, offset int64, limit int64, sorting []string) ([]*openapi.Mission, error)
	GetById(ctx context.Context, id string) (*openapi.Mission, error)
	Delete(ctx context.Context, id string) (int64, error)
}

type missionService struct {
	missionRepository MissionRepository
	config            *config.Configuration
}

// NewMissionService initializes a new [mission.MissionService] instance based on the provided [config.Configuration] and [mission.MissionRepository].
func NewMissionService(config *config.Configuration, repo MissionRepository) MissionService {
	return &missionService{
		missionRepository: repo,
		config:            config,
	}
}

// Generic error, if a given ID is already in use.
var ErrIdInUse = errors.New("ID is already in use")

func (ms *missionService) Create(ctx context.Context, missionCreate *openapi.MissionCreate) (*openapi.Mission, error) {

	slog.DebugContext(ctx, "Create Mission: ", "Mission", missionCreate)

	mission, err := missionCleansingForCreate(missionCreate, token.GetPartyId(ctx), ms.config)
	if err != nil {
		return nil, err
	}

	result, err := ms.missionRepository.Add(ctx, mission)
	if err != nil {
		slog.ErrorContext(ctx, "missionService Create - Storing", "err", err, "mission", mission)
		return nil, errors.New("storing of Mission in DB failed")
	}
	return result, nil
}

func (ms *missionService) Patch(ctx context.Context, id string, patch *openapi.MissionUpdate) (*openapi.Mission, error) {

	// Read original Mission
	slog.DebugContext(ctx, "missionHandler Patch - Params", "id", id)
	mission, err := ms.missionRepository.GetById(ctx, id)
	if err != nil {
		return nil, err
	}

	// Patch Mission
	slog.DebugContext(ctx, "Patch Mission: ", "Mission", patch)
	patchMission(mission, patch)
	mission.SetLastUpdate(time.Now())

	// TODO: Check/fix RelatedParty list

	// Persisting adjusted Mission
	err = ms.missionRepository.Save(ctx, mission)
	if err != nil {
		slog.ErrorContext(ctx, "missionHandler Patch - Storing", "err", err, "id", id, "mission", mission)
		return nil, errors.New("storing of patched Mission failed")
	}

	// Loading updated Mission:
	result, err := ms.missionRepository.GetById(ctx, id)
	if err != nil {
		slog.ErrorContext(ctx, "missionHandler Patch - Reloading", "err", err)
		return nil, errors.New("reloading of just stored Mission object failed")
	}
	return result, nil
}

func (ms *missionService) CountAll(ctx context.Context) (int64, error) {
	return ms.missionRepository.CountAll(ctx)
}

func (ms *missionService) List(ctx context.Context, offset int64, limit int64, sorting []string) ([]*openapi.Mission, error) {
	return ms.missionRepository.List(ctx, offset, limit, sorting)
}

func (ms *missionService) GetById(ctx context.Context, id string) (*openapi.Mission, error) {
	return ms.missionRepository.GetById(ctx, id)
}

func (ms *missionService) Delete(ctx context.Context, id string) (int64, error) {
	return ms.missionRepository.Delete(ctx, id)
}

func patchMission(mission *openapi.Mission, patchMission *openapi.MissionUpdate) *openapi.Mission {
	if patchMission.GetName() != "" {
		mission.SetName(patchMission.GetName())
	}
	if patchMission.HasDescription() {
		mission.SetDescription(patchMission.GetDescription())
	}
	if patchMission.HasCategory() {
		mission.SetCategory(patchMission.GetCategory())
	}
	if patchMission.HasTimeframe() {
		mission.SetTimeframe(patchMission.GetTimeframe())
	}
	if patchMission.HasStatus() {
		mission.SetStatus(patchMission.GetStatus())
	}
	if patchMission.HasCharacteristic() {
		mission.SetCharacteristic(patchMission.GetCharacteristic())
	}
	if patchMission.HasPlace() {
		mission.SetPlace(patchMission.GetPlace())
	}
	if patchMission.HasAsset() {
		mission.SetAsset(patchMission.GetAsset())
	}
	if patchMission.HasRelatedParty() {
		mission.SetRelatedParty(patchMission.GetRelatedParty())
	}
	if patchMission.HasBaseType() {
		mission.SetBaseType(patchMission.GetBaseType())
	}
	if patchMission.HasSchemaLocation() {
		mission.SetSchemaLocation(patchMission.GetSchemaLocation())
	}
	if patchMission.HasType() {
		mission.SetType(patchMission.GetType())
	}
	if patchMission.HasMissionRelationship() {
		mission.SetMissionRelationship(patchMission.GetMissionRelationship())
	}
	return mission
}

func missionCleansingForCreate(
	missionCreate *openapi.MissionCreate,
	providerId string,
	config *config.Configuration,
) (*openapi.Mission, error) {

	mission, err := mapToMission(missionCreate)
	if err != nil {
		return nil, err
	}

	mission.SetId(uuid.New().String())
	mission.SetLastUpdate(time.Now())
	mission.SetHref("/" + config.App.PathPrefix + "/" + mission.Id)

	if !mission.HasStatus() {
		mission.SetStatus(openapi.DRAFT)
	}

	// Should we remove any existing but invalid Provider references (e.g. Role)?

	if providerId != "" && findProviderRef(mission.RelatedParty, providerId) == nil {
		mission.RelatedParty = append(mission.RelatedParty, createProviderRef(providerId))
	}
	return mission, nil
}

func createProviderRef(providerId string) openapi.RelatedParty {
	provRole := providerRole
	return openapi.RelatedParty{
		Id:   providerId,
		Role: &provRole,
	}
}

func findProviderRef(parties []openapi.RelatedParty, providerId string) *openapi.RelatedParty {
	if providerId == "" {
		return nil
	}

	for i := range parties {
		if *(parties[i].Role) == providerRole && parties[i].Id == providerId {
			return &parties[i]
		}
	}
	return nil
}

func mapToMission(missionCreate *openapi.MissionCreate) (*openapi.Mission, error) {

	// TODO: Investigate in a data mapping tool, like:
	//   * https://github.com/jinzhu/copier or
	//   * https://github.com/ulule/deepcopier

	data, err := json.Marshal(missionCreate)
	if err != nil {
		return nil, errors.New("error while converting the MissionCreate object to a Mission - Step 1")
	}
	var mission openapi.Mission
	err = json.Unmarshal(data, &mission)
	if err != nil {
		return nil, errors.New("error while converting the MissionCreate object to a Mission - Step 2")
	}
	return &mission, nil
}
