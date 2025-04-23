// Copyright 2025 THE PSI CONSORTIUM
//
// SPDX-License-Identifier: Apache-2.0

package mission

import (
	"context"
	"log/slog"
	"strings"
	"time"

	"github.com/google/uuid"
	"github.com/psi-consortium/oda/mission-management/mission/config"
	"github.com/psi-consortium/oda/mission-management/mission/openapi"
	"golang.org/x/text/cases"
	"golang.org/x/text/language"
)

// CreateDemoData creates a demo Mission, if the [config.Configuration] enables demo data creation and no Mission data exists yet.
func CreateDemoData(missionRepository MissionRepository, config *config.Configuration) {

	count, err := missionRepository.CountAll(context.Background())
	if err != nil {
		slog.Error("CreateDemoData - Error", "err", err)
		return
	} else if count > 0 {
		slog.Debug("CreateDemoData - Found Data -> Suppress demo data creation", "count", count)
		return
	}

	entityType := "Mission"
	if strings.Contains(strings.ToLower(config.App.PathPrefix), strings.ToLower("Templ")) {
		entityType = "MissionTemplate"
	}

	slog.Info("Initialization of " + entityType + " demo data...")

	mission := createDemoMission(entityType, config)
	slog.Debug("DemoData", entityType, mission)

	_, err = missionRepository.Add(context.Background(), mission)
	if err != nil {
		slog.Error("Creation of demo Mission data failed", "err", err)
	} else {
		slog.Info("Demo data created: 1 Mission")
	}
}

func createDemoMission(entityType string, config *config.Configuration) *openapi.Mission {
	id := uuid.New().String()
	now := time.Now()

	areaHqId := uuid.New().String()
	areaHq := "HQ"
	areaOfOperationId := uuid.New().String()
	areaOfOperation := "Area of Operation"

	providerId := "584e3cd2-661d-4845-a549-79a5448bfdd4"
	providerName := "Test Provider"
	providerRole := "Provider"

	team1NameId := "16E5128C-C252-40FA-9493-2B331240939B"
	team1Name := "Fire Department A"

	assetName := "Bandwidth 22"
	asset1 := createAssetBandwidth(id, assetName, areaOfOperationId, areaOfOperation, areaHqId, areaHq, entityType == "Mission")

	team1 := openapi.MissionAsset{
		Id:                  strPtr(team1NameId),
		Name:                strPtr(team1Name),
		TargetProductSchema: openapi.NewTargetProductSchema("Team", "https://localhost/psi-api/schemas/Mision/MissionAsset.schema.json"),
	}
	asset1.AssetRelationship = []openapi.MissionAssetRelationship{
		{
			Id:               strPtr(team1NameId),
			Name:             strPtr(team1Name),
			RelationshipType: strPtr("Team"),
		},
	}

	coordinates := [][]float32{
		{6.126349, 49.754578},
		{-20.565922, 43.462067},
	}

	startTime := time.Now().Truncate(time.Minute).AddDate(0, 0, 1)
	return &openapi.Mission{
		Id:          id,
		Href:        strPtr("/" + config.App.PathPrefix + "/" + id),
		Name:        "Demo 1",
		Description: strPtr("A demo " + entityType + " for testing purpose"),
		Category:    strPtr("XF31"),
		Timeframe:   newTimeFrame(startTime, startTime.AddDate(0, 1, 0)),
		Status:      draftStatusPtr(),
		RelatedParty: []openapi.RelatedParty{
			{
				Id:   providerId,
				Name: &providerName,
				Role: &providerRole,
			},
		},
		Characteristic: []openapi.Characteristic{
			newBoolCharacteristic("antiJamming", false),
			newIntCharacteristic("availability", 1234),
			newStringCharacteristic("securityLevel", "CONFIDENTIAL"),
		},
		LastUpdate: &now,
		Asset: []openapi.MissionAsset{
			asset1,
			team1,
		},
		Place: []openapi.PlaceRefOrValue{
			openapi.GeographicLocationAsPlaceRefOrValue(
				newPoint(areaHqId, areaHq, coordinates[0]),
			),
			openapi.GeographicLocationAsPlaceRefOrValue(
				newPoint(areaOfOperationId, areaOfOperation, coordinates[1]),
			),
		},
	}
}

func createAssetBandwidth(id string, name string,
	uplinkAreaId string, uplinkArea string, downlinkAreaId string, downlinkArea string,
	isMission bool) openapi.MissionAsset {

	place := []openapi.RelatedPlaceRefOrValue{}
	if uplinkArea != "" {
		place = append(place,
			openapi.RelatedPlaceRefOrValue{
				Id:           &uplinkAreaId,
				Name:         &uplinkArea,
				Role:         "Uplink Area",
				Type:         strPtr("PlaceRef"),
				ReferredType: strPtr("RelatedPlaceRefOrValue"),
			})
	}
	if downlinkArea != "" {
		place = append(place,
			openapi.RelatedPlaceRefOrValue{
				Id:           &downlinkAreaId,
				Name:         &downlinkArea,
				Role:         "Downlink Area",
				Type:         strPtr("PlaceRef"),
				ReferredType: strPtr("RelatedPlaceRefOrValue"),
			})
	}

	asset := openapi.MissionAsset{
		Id:                  &id,
		Name:                &name,
		TargetProductSchema: openapi.NewTargetProductSchema("Bandwidth", "https://localhost/psi-api/schemas/Mision/MissionAsset.schema.json"),
		// ServicePeriod: &servicePeriod,
		Place: place,
		InquiredProductCharacteristic: []openapi.InquiredProductCharacteristic{
			newDecimalInquiredProductCharacteristic("latency", "ms", openapi.MAXIMUM.Ptr(), 120),
			newStringInquiredProductCharacteristic("frequencyBand", "MILKA"),
			newIntegerInquiredProductCharacteristic("bandwidth", "kHz", openapi.MINIMUM.Ptr(), 2048),
		},
	}

	if isMission {
		asset.ServicePeriod = &openapi.RelativeTimePeriod{
			// StartOffset: strPtr("P0D"),
			StartAnchor: strPtr("mission.startDateTime"),
		}
	}

	return asset
}

func strPtr(value string) *string {
	return &value
}

func draftStatusPtr() *openapi.MissionStatusType {
	draft := openapi.AllowedMissionStatusTypeEnumValues[0]
	return &draft
}

func newTimeFrame(startDate time.Time, endDate time.Time) *openapi.TimePeriod {
	timePeriod := openapi.TimePeriod{
		StartDateTime: &startDate,
		EndDateTime:   &endDate,
	}
	return &timePeriod
}

func newPoint(id string, name string, coords []float32) *openapi.GeographicLocation {
	geoLoc := openapi.NewGeographicLocation()
	geoLoc.Place = *openapi.NewPlace()
	geoLoc.Place.SetId(id)
	geoLoc.Place.SetName(name)
	geoLoc.Place.SetType("GeographicLocation")

	point := openapi.NewPoint()
	// point.SetCoordinates([]float32{11.11, 22.22})
	point.SetCoordinates([]float32{coords[0], coords[1]})
	point.SetType("Point")
	geoLoc.GeoJson = &openapi.Geometry{
		Point: point,
	}
	geoLoc.Bbox = []float32{11.11, 22.22, 11.11, 22.22}

	return geoLoc
}

func newLineString(id string, name string) *openapi.GeographicLocation {
	geoLoc := openapi.NewGeographicLocation()
	geoLoc.Place = *openapi.NewPlace()
	geoLoc.Place.SetId(id)
	geoLoc.Place.SetName(name)
	geoLoc.Place.SetType("GeographicLocation")

	lineString := openapi.NewLineString()
	lineString.SetCoordinates([][]float32{
		{11., 51},
		{10, 50},
		{13, 50.5},
	})
	lineString.SetType("LineString")
	geoLoc.GeoJson = &openapi.Geometry{
		LineString: lineString,
	}
	geoLoc.Bbox = []float32{10, 50, 13, 51}

	return geoLoc
}

func newCharacteristic(name string, valueType string) openapi.Characteristic {
	return openapi.Characteristic{
		Id:        &name,
		Name:      cases.Title(language.English).String(name),
		ValueType: strPtr(valueType),
	}
}

func newStringCharacteristic(name string, value string) openapi.Characteristic {
	result := newCharacteristic(name, "boolean")
	result.Value = value
	return result
}

func newIntCharacteristic(name string, value int) openapi.Characteristic {
	// v := int32(value)
	result := newCharacteristic(name, "boolean")
	result.Value = value
	return result
}

func newBoolCharacteristic(name string, value bool) openapi.Characteristic {
	result := newCharacteristic(name, "boolean")
	result.Value = value
	return result
}

func newInquiredProductCharacteristic(
	id string,
	valueType string,
	value openapi.InquiredCharacteristicValueSpecification,
) openapi.InquiredProductCharacteristic {
	return openapi.InquiredProductCharacteristic{
		Id:                                 id,
		Name:                               strPtr(cases.Title(language.English).String(id)),
		ValueType:                          strPtr(valueType),
		InquiredProductCharacteristicValue: []openapi.InquiredCharacteristicValueSpecification{value},
	}
}

func newInquiredCharacteristicValueSpecification(
	precedence *openapi.CharacteristicValuePrecedence,
	uom *string,
	value any,
) openapi.InquiredCharacteristicValueSpecification {
	return openapi.InquiredCharacteristicValueSpecification{
		Precedence:    precedence,
		UnitOfMeasure: uom,
		Value:         value,
	}
}

func newDecimalInquiredProductCharacteristic(
	id string,
	uom string,
	precedence *openapi.CharacteristicValuePrecedence,
	value float32,
) openapi.InquiredProductCharacteristic {
	return newInquiredProductCharacteristic(
		id,
		"decimal",
		newInquiredCharacteristicValueSpecification(
			precedence,
			&uom,
			value,
		),
	)
}

func newStringInquiredProductCharacteristic(id string, value string) openapi.InquiredProductCharacteristic {
	return newInquiredProductCharacteristic(
		id,
		"string",
		newInquiredCharacteristicValueSpecification(nil, nil, value),
	)
}

func newIntegerInquiredProductCharacteristic(
	id string,
	uom string,
	precedence *openapi.CharacteristicValuePrecedence,
	value int32,
) openapi.InquiredProductCharacteristic {
	return newInquiredProductCharacteristic(
		id,
		"integer",
		newInquiredCharacteristicValueSpecification(
			precedence,
			&uom,
			value,
		),
	)
}
