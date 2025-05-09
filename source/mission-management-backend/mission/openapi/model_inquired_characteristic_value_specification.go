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

// InquiredCharacteristicValueSpecification specification of a value (number or text or an object) that can be assigned to a Characteristic.
type InquiredCharacteristicValueSpecification struct {
	Precedence *CharacteristicValuePrecedence `json:"precedence,omitempty"`
	// A length, surface, volume, dry measure, liquid measure, money, weight, time, and the like. In general, a determinate quantity or magnitude of the kind designated, taken as a standard of comparison for others of the same kind, in assigning to them numerical values, as 1 foot, 1 yard, 1 mile, 1 square foot.
	UnitOfMeasure *string `json:"unitOfMeasure,omitempty"`
	// A kind of value that the characteristic value can take on, such as numeric, text and so forth
	ValueType *string `json:"valueType,omitempty"`
	Value interface{} `json:"value,omitempty"`
}

// NewInquiredCharacteristicValueSpecification instantiates a new InquiredCharacteristicValueSpecification object
// This constructor will assign default values to properties that have it defined,
// and makes sure properties required by API are set, but the set of arguments
// will change when the set of required properties is changed
func NewInquiredCharacteristicValueSpecification() *InquiredCharacteristicValueSpecification {
	this := InquiredCharacteristicValueSpecification{}
	return &this
}

// NewInquiredCharacteristicValueSpecificationWithDefaults instantiates a new InquiredCharacteristicValueSpecification object
// This constructor will only assign default values to properties that have it defined,
// but it doesn't guarantee that properties required by API are set
func NewInquiredCharacteristicValueSpecificationWithDefaults() *InquiredCharacteristicValueSpecification {
	this := InquiredCharacteristicValueSpecification{}
	return &this
}

// GetPrecedence returns the Precedence field value if set, zero value otherwise.
func (o *InquiredCharacteristicValueSpecification) GetPrecedence() CharacteristicValuePrecedence {
	if o == nil || isNil(o.Precedence) {
		var ret CharacteristicValuePrecedence
		return ret
	}
	return *o.Precedence
}

// GetPrecedenceOk returns a tuple with the Precedence field value if set, nil otherwise
// and a boolean to check if the value has been set.
func (o *InquiredCharacteristicValueSpecification) GetPrecedenceOk() (*CharacteristicValuePrecedence, bool) {
	if o == nil || isNil(o.Precedence) {
    return nil, false
	}
	return o.Precedence, true
}

// HasPrecedence returns a boolean if a field has been set.
func (o *InquiredCharacteristicValueSpecification) HasPrecedence() bool {
	if o != nil && !isNil(o.Precedence) {
		return true
	}

	return false
}

// SetPrecedence gets a reference to the given CharacteristicValuePrecedence and assigns it to the Precedence field.
func (o *InquiredCharacteristicValueSpecification) SetPrecedence(v CharacteristicValuePrecedence) {
	o.Precedence = &v
}

// GetUnitOfMeasure returns the UnitOfMeasure field value if set, zero value otherwise.
func (o *InquiredCharacteristicValueSpecification) GetUnitOfMeasure() string {
	if o == nil || isNil(o.UnitOfMeasure) {
		var ret string
		return ret
	}
	return *o.UnitOfMeasure
}

// GetUnitOfMeasureOk returns a tuple with the UnitOfMeasure field value if set, nil otherwise
// and a boolean to check if the value has been set.
func (o *InquiredCharacteristicValueSpecification) GetUnitOfMeasureOk() (*string, bool) {
	if o == nil || isNil(o.UnitOfMeasure) {
    return nil, false
	}
	return o.UnitOfMeasure, true
}

// HasUnitOfMeasure returns a boolean if a field has been set.
func (o *InquiredCharacteristicValueSpecification) HasUnitOfMeasure() bool {
	if o != nil && !isNil(o.UnitOfMeasure) {
		return true
	}

	return false
}

// SetUnitOfMeasure gets a reference to the given string and assigns it to the UnitOfMeasure field.
func (o *InquiredCharacteristicValueSpecification) SetUnitOfMeasure(v string) {
	o.UnitOfMeasure = &v
}

// GetValueType returns the ValueType field value if set, zero value otherwise.
func (o *InquiredCharacteristicValueSpecification) GetValueType() string {
	if o == nil || isNil(o.ValueType) {
		var ret string
		return ret
	}
	return *o.ValueType
}

// GetValueTypeOk returns a tuple with the ValueType field value if set, nil otherwise
// and a boolean to check if the value has been set.
func (o *InquiredCharacteristicValueSpecification) GetValueTypeOk() (*string, bool) {
	if o == nil || isNil(o.ValueType) {
    return nil, false
	}
	return o.ValueType, true
}

// HasValueType returns a boolean if a field has been set.
func (o *InquiredCharacteristicValueSpecification) HasValueType() bool {
	if o != nil && !isNil(o.ValueType) {
		return true
	}

	return false
}

// SetValueType gets a reference to the given string and assigns it to the ValueType field.
func (o *InquiredCharacteristicValueSpecification) SetValueType(v string) {
	o.ValueType = &v
}

// GetValue returns the Value field value if set, zero value otherwise (both if not set or set to explicit null).
func (o *InquiredCharacteristicValueSpecification) GetValue() interface{} {
	if o == nil {
		var ret interface{}
		return ret
	}
	return o.Value
}

// GetValueOk returns a tuple with the Value field value if set, nil otherwise
// and a boolean to check if the value has been set.
// NOTE: If the value is an explicit nil, `nil, true` will be returned
func (o *InquiredCharacteristicValueSpecification) GetValueOk() (*interface{}, bool) {
	if o == nil || isNil(o.Value) {
    return nil, false
	}
	return &o.Value, true
}

// HasValue returns a boolean if a field has been set.
func (o *InquiredCharacteristicValueSpecification) HasValue() bool {
	if o != nil && isNil(o.Value) {
		return true
	}

	return false
}

// SetValue gets a reference to the given interface{} and assigns it to the Value field.
func (o *InquiredCharacteristicValueSpecification) SetValue(v interface{}) {
	o.Value = v
}

func (o InquiredCharacteristicValueSpecification) MarshalJSON() ([]byte, error) {
	toSerialize := map[string]interface{}{}
	if !isNil(o.Precedence) {
		toSerialize["precedence"] = o.Precedence
	}
	if !isNil(o.UnitOfMeasure) {
		toSerialize["unitOfMeasure"] = o.UnitOfMeasure
	}
	if !isNil(o.ValueType) {
		toSerialize["valueType"] = o.ValueType
	}
	if o.Value != nil {
		toSerialize["value"] = o.Value
	}
	return json.Marshal(toSerialize)
}

type NullableInquiredCharacteristicValueSpecification struct {
	value *InquiredCharacteristicValueSpecification
	isSet bool
}

func (v NullableInquiredCharacteristicValueSpecification) Get() *InquiredCharacteristicValueSpecification {
	return v.value
}

func (v *NullableInquiredCharacteristicValueSpecification) Set(val *InquiredCharacteristicValueSpecification) {
	v.value = val
	v.isSet = true
}

func (v NullableInquiredCharacteristicValueSpecification) IsSet() bool {
	return v.isSet
}

func (v *NullableInquiredCharacteristicValueSpecification) Unset() {
	v.value = nil
	v.isSet = false
}

func NewNullableInquiredCharacteristicValueSpecification(val *InquiredCharacteristicValueSpecification) *NullableInquiredCharacteristicValueSpecification {
	return &NullableInquiredCharacteristicValueSpecification{value: val, isSet: true}
}

func (v NullableInquiredCharacteristicValueSpecification) MarshalJSON() ([]byte, error) {
	return json.Marshal(v.value)
}

func (v *NullableInquiredCharacteristicValueSpecification) UnmarshalJSON(src []byte) error {
	v.isSet = true
	return json.Unmarshal(src, &v.value)
}


