// Copyright 2025 THE PSI CONSORTIUM
//
// SPDX-License-Identifier: Apache-2.0

package mission

import (
	"encoding/json"
	"testing"

	"github.com/psi-consortium/oda/mission-management/mission/openapi"
	"github.com/stretchr/testify/assert"
)

func TestCreateDemoMission(t *testing.T) {
	mission := createDemoMission("Mission")

	assert.NotNil(t, mission, "Created DemoMission should not be nil")
	assert.NotNil(t, mission.Timeframe, "Created DemoMission should have a Timeframe")
	assert.NotEmpty(t, mission.RelatedParty, "Created DemoMission should have at least one RelatedParty entry")
	assert.NotEmpty(t, mission.Asset, "Created DemoMission should have at least oneAsset entry")
	assert.NotEmpty(t, mission.Place, "Created DemoMission should have at least one Place entry")
	assert.NotEmpty(t, mission.Asset, "Created DemoMission should have at least one Asset entry")
	assert.NotEmpty(t, mission.Characteristic, "Created DemoMission should have at least one Characteristic entry")
}

func TestMarshalling(t *testing.T) {

	t.Run("Place", func(t *testing.T) {

		t.Run("Point", func(t *testing.T) {

			geoLoc := openapi.NewGeographicLocation()
			geoLoc.Place = *openapi.NewPlace()
			geoLoc.Place.SetId("the id")
			geoLoc.Place.SetName("a name")
			geoLoc.Place.SetType("GeographicLocation")

			point := openapi.NewPoint()
			point.SetCoordinates([]float32{11.11, 22.22})
			point.SetType("Point")
			geoLoc.GeoJson = &openapi.Geometry{
				Point: point,
			}
			geoLoc.Bbox = []float32{11.11, 22.22, 11.11, 22.22}

			origin := openapi.GeographicLocationAsPlaceRefOrValue(geoLoc)
			ls, _ := json.Marshal(origin)

			var place openapi.PlaceRefOrValue // openapi.NewPlace()
			json.Unmarshal(ls, &place)

			assert.Equal(t, origin, place)
		})

		// Same structure as MultiPoint, but different Discriminator value - see "Type" field
		t.Run("LineString", func(t *testing.T) {

			geoLoc := openapi.NewGeographicLocation()
			geoLoc.Place = *openapi.NewPlace()
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

			origin := openapi.GeographicLocationAsPlaceRefOrValue(geoLoc)
			ls, _ := json.Marshal(origin)

			var place openapi.PlaceRefOrValue
			json.Unmarshal(ls, &place)

			assert.Equal(t, origin, place)
		})

		t.Run("MultiPoint", func(t *testing.T) {

			geoLoc := openapi.NewGeographicLocation()
			geoLoc.Place = *openapi.NewPlace()
			geoLoc.Place.SetType("GeographicLocation")
			multiPoint := openapi.NewMultiPoint()
			multiPoint.SetCoordinates([][]float32{
				{11., 51},
				{10, 50},
				{13, 50.5},
			})
			multiPoint.SetType("MultiPoint")
			geoLoc.GeoJson = &openapi.Geometry{
				MultiPoint: multiPoint,
			}
			geoLoc.Bbox = []float32{10, 50, 13, 51}

			origin := openapi.GeographicLocationAsPlaceRefOrValue(geoLoc)
			ls, _ := json.Marshal(origin)

			var place openapi.PlaceRefOrValue
			json.Unmarshal(ls, &place)

			assert.Equal(t, origin, place)
		})

		// Same structure as MultiLineString, but different Discriminator value - see "Type" field
		t.Run("Polygon", func(t *testing.T) {
			geoLoc := openapi.NewGeographicLocation()
			geoLoc.Place = *openapi.NewPlace()
			geoLoc.Place.SetType("GeographicLocation")
			polygon := openapi.NewPolygon()
			polygon.SetCoordinates([][][]float32{{
				{11., 51},
				{10, 50},
				{13, 50.5},
				{11., 51},
			}})
			polygon.SetType("Polygon")
			geoLoc.GeoJson = &openapi.Geometry{
				Polygon: polygon,
			}

			origin := openapi.GeographicLocationAsPlaceRefOrValue(geoLoc)
			plgn, _ := json.Marshal(origin)

			var place openapi.PlaceRefOrValue
			json.Unmarshal(plgn, &place)

			assert.Equal(t, origin, place)
		})

		// Same structure as Polygon, but different Discriminator value - see "Type" field
		t.Run("MultiLineString", func(t *testing.T) {
			geoLoc := openapi.NewGeographicLocation()
			geoLoc.Place = *openapi.NewPlace()
			geoLoc.Place.SetType("GeographicLocation")
			multiLineString := openapi.NewMultiLineString()
			multiLineString.SetCoordinates([][][]float32{{
				{11., 51},
				{10, 50},
				{13, 50.5},
				{11., 51},
			}})
			multiLineString.SetType("MultiLineString")
			geoLoc.GeoJson = &openapi.Geometry{
				MultiLineString: multiLineString,
			}

			origin := openapi.GeographicLocationAsPlaceRefOrValue(geoLoc)
			plgn, _ := json.Marshal(origin)

			var place openapi.PlaceRefOrValue
			json.Unmarshal(plgn, &place)

			assert.Equal(t, origin, place)
		})
	})

	t.Run("Mission", func(t *testing.T) {
		origin := createDemoMission("Mission")

		data, _ := json.Marshal(origin)

		var result openapi.Mission
		json.Unmarshal(data, &result)

		data2, _ := json.Marshal(result)

		// Equal check of the objects fails, because the unmarshaler chooses sometimes different types: int32 vs. float64, etc.
		// assert.Equal(t, *origin, result)
		assert.Equal(t, data, data2)
	})

}
