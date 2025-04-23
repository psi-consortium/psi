// Copyright 2025 THE PSI CONSORTIUM
//
// SPDX-License-Identifier: Apache-2.0

package config

import (
	"os"
	"testing"

	"github.com/stretchr/testify/assert"
)

func TestLoadConfiguration(t *testing.T) {
	os.Setenv("CONFIG_PATH", "../../config.yml")

	config := LoadConfiguration()

	assert.NotNil(t, config, "Instantiated Configuration should not be nil")
}
