// Copyright 2025 THE PSI CONSORTIUM
//
// SPDX-License-Identifier: Apache-2.0

package utils

import (
	"math"
	"testing"

	"github.com/stretchr/testify/assert"
)

type TestMission struct {
	ID           string             `json:"id"`
	Href         *string            `json:"href,omitempty"`
	RelatedParty []TestRelatedParty `json:"relatedParty,omitempty"`
}

type TestRelatedParty struct {
	ID   string  `json:"id"`
	Role *string `json:"role,omitempty"`
}

var Provider_Role = "Provider"
var Customer_Role = "Customer"

func TestStructToMap(t *testing.T) {

	t.Run("Successful", func(t *testing.T) {

		t.Run("Mission with RelatedParty", func(t *testing.T) {
			mission := TestMission{
				ID: "12345",
				RelatedParty: []TestRelatedParty{
					{ID: "55", Role: &Provider_Role},
					{ID: "66", Role: &Customer_Role},
				},
			}

			data, err := StructToMap(mission)
			assert.Nil(t, err, "StructToMap run into an error: %v", err)
			assert.Equal(t, 2, len(data), "Mission-Map: %+v", data)

			assert.NotContains(t, data, "ID")
			assert.Contains(t, data, "id")
			assert.NotContains(t, data, "RelatedParty")
			assert.Contains(t, data, "relatedParty")

			relatedParty := data["relatedParty"].([]interface{})
			assert.NotNil(t, relatedParty, "Related-Map: %+v", relatedParty)
			assert.Equal(t, 2, len(relatedParty), "Related-Map: %+v", relatedParty)
		})

		t.Run("Mission", func(t *testing.T) {
			mission := TestMission{
				ID: "12345",
			}

			data, err := StructToMap(mission)

			assert.Nil(t, err, "StructToMap run into an error: %v", err)
			assert.Equal(t, 1, len(data), "Mission-Map: %+v", data)
			assert.Contains(t, data, "id")
			assert.NotContains(t, data, "relatedParty")
		})
	})

	t.Run("Not Successful", func(t *testing.T) {

		t.Run("NaN", func(t *testing.T) {
			_, err := StructToMap(math.NaN())
			assert.Error(t, err, "StructToMap (json.marshal) should run into an error.")
		})

		t.Run("Int", func(t *testing.T) {
			_, err := StructToMap(1234)
			assert.Error(t, err, "StructToMap (json.unmarshal) should run into an error.")
		})
	})
}
