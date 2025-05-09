/*
Mission Management

## PSID API Reference: PSID002 - Mission Management  The Mission API enables customers to create, update and delete missions. A mission is composed of one or more teams, their areas of operation and communication needs. The data can be used to issue an inquiry for matching products (see PSID001).

API version: 1.0.0
*/

// Code generated by OpenAPI Generator (https://openapi-generator.tech); DO NOT EDIT.

package openapi

import (
	"encoding/json"
)

// MultiPolygon A GeoJSON MultiPolygon.
type MultiPolygon struct {
	Type *string `json:"type,omitempty"`
	Coordinates [][][][]float32 `json:"coordinates,omitempty"`
}

// NewMultiPolygon instantiates a new MultiPolygon object
// This constructor will assign default values to properties that have it defined,
// and makes sure properties required by API are set, but the set of arguments
// will change when the set of required properties is changed
func NewMultiPolygon() *MultiPolygon {
	this := MultiPolygon{}
	return &this
}

// NewMultiPolygonWithDefaults instantiates a new MultiPolygon object
// This constructor will only assign default values to properties that have it defined,
// but it doesn't guarantee that properties required by API are set
func NewMultiPolygonWithDefaults() *MultiPolygon {
	this := MultiPolygon{}
	return &this
}

// GetType returns the Type field value if set, zero value otherwise.
func (o *MultiPolygon) GetType() string {
	if o == nil || isNil(o.Type) {
		var ret string
		return ret
	}
	return *o.Type
}

// GetTypeOk returns a tuple with the Type field value if set, nil otherwise
// and a boolean to check if the value has been set.
func (o *MultiPolygon) GetTypeOk() (*string, bool) {
	if o == nil || isNil(o.Type) {
    return nil, false
	}
	return o.Type, true
}

// HasType returns a boolean if a field has been set.
func (o *MultiPolygon) HasType() bool {
	if o != nil && !isNil(o.Type) {
		return true
	}

	return false
}

// SetType gets a reference to the given string and assigns it to the Type field.
func (o *MultiPolygon) SetType(v string) {
	o.Type = &v
}

// GetCoordinates returns the Coordinates field value if set, zero value otherwise.
func (o *MultiPolygon) GetCoordinates() [][][][]float32 {
	if o == nil || isNil(o.Coordinates) {
		var ret [][][][]float32
		return ret
	}
	return o.Coordinates
}

// GetCoordinatesOk returns a tuple with the Coordinates field value if set, nil otherwise
// and a boolean to check if the value has been set.
func (o *MultiPolygon) GetCoordinatesOk() ([][][][]float32, bool) {
	if o == nil || isNil(o.Coordinates) {
    return nil, false
	}
	return o.Coordinates, true
}

// HasCoordinates returns a boolean if a field has been set.
func (o *MultiPolygon) HasCoordinates() bool {
	if o != nil && !isNil(o.Coordinates) {
		return true
	}

	return false
}

// SetCoordinates gets a reference to the given [][][][]float32 and assigns it to the Coordinates field.
func (o *MultiPolygon) SetCoordinates(v [][][][]float32) {
	o.Coordinates = v
}

func (o MultiPolygon) MarshalJSON() ([]byte, error) {
	toSerialize := map[string]interface{}{}
	if !isNil(o.Type) {
		toSerialize["type"] = o.Type
	}
	if !isNil(o.Coordinates) {
		toSerialize["coordinates"] = o.Coordinates
	}
	return json.Marshal(toSerialize)
}

type NullableMultiPolygon struct {
	value *MultiPolygon
	isSet bool
}

func (v NullableMultiPolygon) Get() *MultiPolygon {
	return v.value
}

func (v *NullableMultiPolygon) Set(val *MultiPolygon) {
	v.value = val
	v.isSet = true
}

func (v NullableMultiPolygon) IsSet() bool {
	return v.isSet
}

func (v *NullableMultiPolygon) Unset() {
	v.value = nil
	v.isSet = false
}

func NewNullableMultiPolygon(val *MultiPolygon) *NullableMultiPolygon {
	return &NullableMultiPolygon{value: val, isSet: true}
}

func (v NullableMultiPolygon) MarshalJSON() ([]byte, error) {
	return json.Marshal(v.value)
}

func (v *NullableMultiPolygon) UnmarshalJSON(src []byte) error {
	v.isSet = true
	return json.Unmarshal(src, &v.value)
}


