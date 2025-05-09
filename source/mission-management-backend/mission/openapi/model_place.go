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

// Place Place reference. Place defines the places where the products are sold or delivered.
type Place struct {
	// Unique identifier of the place
	Id *string `json:"id,omitempty"`
	// Unique reference of the place
	Href *string `json:"href,omitempty"`
	// A user-friendly name for the place, such as [Paris Store], [London Store], [Main Home]
	Name *string `json:"name,omitempty"`
	// When sub-classing, this defines the super-class.
	BaseType *string `json:"@baseType,omitempty"`
	// A URI to a JSON-Schema file that defines additional attributes and relationships.
	SchemaLocation *string `json:"@schemaLocation,omitempty"`
	// When sub-classing, this defines the sub-class entity name.
	Type *string `json:"@type,omitempty"`
}

// NewPlace instantiates a new Place object
// This constructor will assign default values to properties that have it defined,
// and makes sure properties required by API are set, but the set of arguments
// will change when the set of required properties is changed
func NewPlace() *Place {
	this := Place{}
	return &this
}

// NewPlaceWithDefaults instantiates a new Place object
// This constructor will only assign default values to properties that have it defined,
// but it doesn't guarantee that properties required by API are set
func NewPlaceWithDefaults() *Place {
	this := Place{}
	return &this
}

// GetId returns the Id field value if set, zero value otherwise.
func (o *Place) GetId() string {
	if o == nil || isNil(o.Id) {
		var ret string
		return ret
	}
	return *o.Id
}

// GetIdOk returns a tuple with the Id field value if set, nil otherwise
// and a boolean to check if the value has been set.
func (o *Place) GetIdOk() (*string, bool) {
	if o == nil || isNil(o.Id) {
    return nil, false
	}
	return o.Id, true
}

// HasId returns a boolean if a field has been set.
func (o *Place) HasId() bool {
	if o != nil && !isNil(o.Id) {
		return true
	}

	return false
}

// SetId gets a reference to the given string and assigns it to the Id field.
func (o *Place) SetId(v string) {
	o.Id = &v
}

// GetHref returns the Href field value if set, zero value otherwise.
func (o *Place) GetHref() string {
	if o == nil || isNil(o.Href) {
		var ret string
		return ret
	}
	return *o.Href
}

// GetHrefOk returns a tuple with the Href field value if set, nil otherwise
// and a boolean to check if the value has been set.
func (o *Place) GetHrefOk() (*string, bool) {
	if o == nil || isNil(o.Href) {
    return nil, false
	}
	return o.Href, true
}

// HasHref returns a boolean if a field has been set.
func (o *Place) HasHref() bool {
	if o != nil && !isNil(o.Href) {
		return true
	}

	return false
}

// SetHref gets a reference to the given string and assigns it to the Href field.
func (o *Place) SetHref(v string) {
	o.Href = &v
}

// GetName returns the Name field value if set, zero value otherwise.
func (o *Place) GetName() string {
	if o == nil || isNil(o.Name) {
		var ret string
		return ret
	}
	return *o.Name
}

// GetNameOk returns a tuple with the Name field value if set, nil otherwise
// and a boolean to check if the value has been set.
func (o *Place) GetNameOk() (*string, bool) {
	if o == nil || isNil(o.Name) {
    return nil, false
	}
	return o.Name, true
}

// HasName returns a boolean if a field has been set.
func (o *Place) HasName() bool {
	if o != nil && !isNil(o.Name) {
		return true
	}

	return false
}

// SetName gets a reference to the given string and assigns it to the Name field.
func (o *Place) SetName(v string) {
	o.Name = &v
}

// GetBaseType returns the BaseType field value if set, zero value otherwise.
func (o *Place) GetBaseType() string {
	if o == nil || isNil(o.BaseType) {
		var ret string
		return ret
	}
	return *o.BaseType
}

// GetBaseTypeOk returns a tuple with the BaseType field value if set, nil otherwise
// and a boolean to check if the value has been set.
func (o *Place) GetBaseTypeOk() (*string, bool) {
	if o == nil || isNil(o.BaseType) {
    return nil, false
	}
	return o.BaseType, true
}

// HasBaseType returns a boolean if a field has been set.
func (o *Place) HasBaseType() bool {
	if o != nil && !isNil(o.BaseType) {
		return true
	}

	return false
}

// SetBaseType gets a reference to the given string and assigns it to the BaseType field.
func (o *Place) SetBaseType(v string) {
	o.BaseType = &v
}

// GetSchemaLocation returns the SchemaLocation field value if set, zero value otherwise.
func (o *Place) GetSchemaLocation() string {
	if o == nil || isNil(o.SchemaLocation) {
		var ret string
		return ret
	}
	return *o.SchemaLocation
}

// GetSchemaLocationOk returns a tuple with the SchemaLocation field value if set, nil otherwise
// and a boolean to check if the value has been set.
func (o *Place) GetSchemaLocationOk() (*string, bool) {
	if o == nil || isNil(o.SchemaLocation) {
    return nil, false
	}
	return o.SchemaLocation, true
}

// HasSchemaLocation returns a boolean if a field has been set.
func (o *Place) HasSchemaLocation() bool {
	if o != nil && !isNil(o.SchemaLocation) {
		return true
	}

	return false
}

// SetSchemaLocation gets a reference to the given string and assigns it to the SchemaLocation field.
func (o *Place) SetSchemaLocation(v string) {
	o.SchemaLocation = &v
}

// GetType returns the Type field value if set, zero value otherwise.
func (o *Place) GetType() string {
	if o == nil || isNil(o.Type) {
		var ret string
		return ret
	}
	return *o.Type
}

// GetTypeOk returns a tuple with the Type field value if set, nil otherwise
// and a boolean to check if the value has been set.
func (o *Place) GetTypeOk() (*string, bool) {
	if o == nil || isNil(o.Type) {
    return nil, false
	}
	return o.Type, true
}

// HasType returns a boolean if a field has been set.
func (o *Place) HasType() bool {
	if o != nil && !isNil(o.Type) {
		return true
	}

	return false
}

// SetType gets a reference to the given string and assigns it to the Type field.
func (o *Place) SetType(v string) {
	o.Type = &v
}

func (o Place) MarshalJSON() ([]byte, error) {
	toSerialize := map[string]interface{}{}
	if !isNil(o.Id) {
		toSerialize["id"] = o.Id
	}
	if !isNil(o.Href) {
		toSerialize["href"] = o.Href
	}
	if !isNil(o.Name) {
		toSerialize["name"] = o.Name
	}
	if !isNil(o.BaseType) {
		toSerialize["@baseType"] = o.BaseType
	}
	if !isNil(o.SchemaLocation) {
		toSerialize["@schemaLocation"] = o.SchemaLocation
	}
	if !isNil(o.Type) {
		toSerialize["@type"] = o.Type
	}
	return json.Marshal(toSerialize)
}

type NullablePlace struct {
	value *Place
	isSet bool
}

func (v NullablePlace) Get() *Place {
	return v.value
}

func (v *NullablePlace) Set(val *Place) {
	v.value = val
	v.isSet = true
}

func (v NullablePlace) IsSet() bool {
	return v.isSet
}

func (v *NullablePlace) Unset() {
	v.value = nil
	v.isSet = false
}

func NewNullablePlace(val *Place) *NullablePlace {
	return &NullablePlace{value: val, isSet: true}
}

func (v NullablePlace) MarshalJSON() ([]byte, error) {
	return json.Marshal(v.value)
}

func (v *NullablePlace) UnmarshalJSON(src []byte) error {
	v.isSet = true
	return json.Unmarshal(src, &v.value)
}


