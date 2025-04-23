// Copyright 2025 THE PSI CONSORTIUM
//
// SPDX-License-Identifier: Apache-2.0

package utils

import (
	"encoding/json"
)

// Converts a struct to a map while maintaining the json alias as keys
func StructToMap(obj interface{}) (map[string]interface{}, error) {
	data, err := json.Marshal(obj)
	if err != nil {
		return nil, err
	}

	var newMap map[string]interface{}
	err = json.Unmarshal(data, &newMap)
	return newMap, err
}
