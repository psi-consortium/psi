/*
Mission Management

## PSID API Reference: PSID002 - Mission Management  The Mission API enables customers to create, update and delete missions. A mission is composed of one or more teams, their areas of operation and communication needs. The data can be used to issue an inquiry for matching products (see PSID001).

API version: 1.0.0
*/

// Code generated by OpenAPI Generator (https://openapi-generator.tech); DO NOT EDIT.

package openapi

import (
	"encoding/json"
	"fmt"
)

// MissionStatusType Possible values for the status of a mission
type MissionStatusType string

// List of MissionStatusType
const (
	DRAFT MissionStatusType = "draft"
	ACTIVE MissionStatusType = "active"
	CANCELLED MissionStatusType = "cancelled"
	COMPLETED MissionStatusType = "completed"
)

// All allowed values of MissionStatusType enum
var AllowedMissionStatusTypeEnumValues = []MissionStatusType{
	"draft",
	"active",
	"cancelled",
	"completed",
}

func (v *MissionStatusType) UnmarshalJSON(src []byte) error {
	var value string
	err := json.Unmarshal(src, &value)
	if err != nil {
		return err
	}
	enumTypeValue := MissionStatusType(value)
	for _, existing := range AllowedMissionStatusTypeEnumValues {
		if existing == enumTypeValue {
			*v = enumTypeValue
			return nil
		}
	}

	return fmt.Errorf("%+v is not a valid MissionStatusType", value)
}

// NewMissionStatusTypeFromValue returns a pointer to a valid MissionStatusType
// for the value passed as argument, or an error if the value passed is not allowed by the enum
func NewMissionStatusTypeFromValue(v string) (*MissionStatusType, error) {
	ev := MissionStatusType(v)
	if ev.IsValid() {
		return &ev, nil
	} else {
		return nil, fmt.Errorf("invalid value '%v' for MissionStatusType: valid values are %v", v, AllowedMissionStatusTypeEnumValues)
	}
}

// IsValid return true if the value is valid for the enum, false otherwise
func (v MissionStatusType) IsValid() bool {
	for _, existing := range AllowedMissionStatusTypeEnumValues {
		if existing == v {
			return true
		}
	}
	return false
}

// Ptr returns reference to MissionStatusType value
func (v MissionStatusType) Ptr() *MissionStatusType {
	return &v
}

type NullableMissionStatusType struct {
	value *MissionStatusType
	isSet bool
}

func (v NullableMissionStatusType) Get() *MissionStatusType {
	return v.value
}

func (v *NullableMissionStatusType) Set(val *MissionStatusType) {
	v.value = val
	v.isSet = true
}

func (v NullableMissionStatusType) IsSet() bool {
	return v.isSet
}

func (v *NullableMissionStatusType) Unset() {
	v.value = nil
	v.isSet = false
}

func NewNullableMissionStatusType(val *MissionStatusType) *NullableMissionStatusType {
	return &NullableMissionStatusType{value: val, isSet: true}
}

func (v NullableMissionStatusType) MarshalJSON() ([]byte, error) {
	return json.Marshal(v.value)
}

func (v *NullableMissionStatusType) UnmarshalJSON(src []byte) error {
	v.isSet = true
	return json.Unmarshal(src, &v.value)
}

