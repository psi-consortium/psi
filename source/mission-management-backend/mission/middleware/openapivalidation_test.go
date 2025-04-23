// Copyright 2025 THE PSI CONSORTIUM
//
// SPDX-License-Identifier: Apache-2.0

package middleware

import (
	"bytes"
	"encoding/json"
	"io"
	"net/http"
	"net/http/httptest"
	"strings"
	"testing"

	"github.com/gin-gonic/gin"
	"github.com/psi-consortium/oda/mission-management/internal/utils"
	"github.com/psi-consortium/oda/mission-management/mission/openapi"
	"github.com/stretchr/testify/assert"
	"github.com/stretchr/testify/require"
)

const missionPath = "/mission"

func TestOpenApiMiddleware(t *testing.T) {

	const OpenApiDefinitionFile = "../../internal/api/PSID002-MissionManagement_v2.oas.json"

	t.Run("GET", func(t *testing.T) {
		t.Run("Successful", func(t *testing.T) {

			c, _ := getOAVTestGinContext("GET", missionPath)

			ginHandlerFunc := OpenApiValidationMiddleware(OpenApiDefinitionFile, true)
			ginHandlerFunc(c)

			assert.Equal(t, http.StatusOK, c.Writer.Status())
		})

		t.Run("Not Successful", func(t *testing.T) {

			c, _ := getOAVTestGinContext("GET", "/invalid/path")

			ginHandlerFunc := OpenApiValidationMiddleware(OpenApiDefinitionFile, false)
			ginHandlerFunc(c)

			assert.Equal(t, http.StatusBadRequest, c.Writer.Status())
		})
	})

	t.Run("POST", func(t *testing.T) {
		t.Run("Successful", func(t *testing.T) {

			c, _ := getOAVTestGinContext("POST", missionPath)
			c.Request.Header.Set("Content-Type", "application/json; charset=utf-8")

			draft := openapi.DRAFT
			mBytes, err := json.Marshal(openapi.MissionCreate{Name: "123", Status: &draft, Type: utils.StringPointer("Mission")})
			require.Nil(t, err)
			c = addBody(c, mBytes)

			ginHandlerFunc := OpenApiValidationMiddleware(OpenApiDefinitionFile, false)
			ginHandlerFunc(c)

			assert.Equal(t, http.StatusOK, c.Writer.Status())
		})

		t.Run("Not Successful", func(t *testing.T) {
			t.Run("Unknown Content Type", func(t *testing.T) {
				c, rr := getOAVTestGinContext("POST", missionPath)

				mBytes, err := json.Marshal(openapi.MissionCreate{})

				require.Nil(t, err)
				c = addBody(c, mBytes)

				ginHandlerFunc := OpenApiValidationMiddleware(OpenApiDefinitionFile, false)
				ginHandlerFunc(c)

				assert.Equal(t, http.StatusBadRequest, c.Writer.Status())
				assert.Regexp(t, "Input Validation Error", rr.Body.String())
				assert.Regexp(t, "header Content-Type has unexpected value", rr.Body.String())
			})
		})

		t.Run("Invalid Status", func(t *testing.T) {
			c, rr := getOAVTestGinContext("POST", missionPath)
			c.Request.Header.Set("Content-Type", "application/json; charset=utf-8")

			draft := openapi.DRAFT
			mBytes, err := json.Marshal(openapi.MissionCreate{Name: "123", Status: &draft, Type: utils.StringPointer("Mission")})
			require.Nil(t, err)
			mBytes = []byte(strings.Replace(string(mBytes), "draft", "InvalidState", 1))
			c = addBody(c, mBytes)

			ginHandlerFunc := OpenApiValidationMiddleware(OpenApiDefinitionFile, false)
			ginHandlerFunc(c)

			assert.Equal(t, http.StatusBadRequest, c.Writer.Status())
			assert.Regexp(t, "Input Validation Error", rr.Body.String())
			assert.Regexp(t, "/status", rr.Body.String())
			assert.Regexp(t, "value is not one of the allowed values", rr.Body.String())
		})
	})
}

func getOAVTestGinContext(method string, path string) (*gin.Context, *httptest.ResponseRecorder) {
	gin.SetMode(gin.TestMode)

	responseRecorder := httptest.NewRecorder()
	c, _ := gin.CreateTestContext(responseRecorder)

	c.Request, _ = http.NewRequest(method, path, nil)
	c.BindQuery(nil)

	c.Request.Header = make(http.Header)

	return c, responseRecorder
}

func addBody(c *gin.Context, body []byte) *gin.Context {
	if len(body) <= 0 {
		return c
	}
	c.Request.Body = io.NopCloser(bytes.NewBuffer(body))
	return c
}
