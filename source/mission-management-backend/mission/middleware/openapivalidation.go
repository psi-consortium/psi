// Copyright 2025 THE PSI CONSORTIUM
//
// SPDX-License-Identifier: Apache-2.0

package middleware

import (
	"bytes"
	"fmt"
	"log/slog"
	"net/http"
	"strings"
	"time"

	"github.com/getkin/kin-openapi/openapi3"
	"github.com/getkin/kin-openapi/openapi3filter"
	"github.com/getkin/kin-openapi/routers"
	"github.com/getkin/kin-openapi/routers/gorillamux"
	"github.com/gin-gonic/gin"
)

// ResponseBodyWriter could be used as a proxy in the Request processing to retrieve the Response body.
type ResponseBodyWriter struct {
	gin.ResponseWriter
	body *bytes.Buffer
}

func (rw ResponseBodyWriter) Write(b []byte) (int, error) {
	rw.body.Write(b)
	return rw.ResponseWriter.Write(b)
}

func (rw ResponseBodyWriter) WriteString(s string) (int, error) {
	rw.body.WriteString(s)
	return rw.ResponseWriter.WriteString(s)
}

// OpenApiValidationMiddleware is a middleware for the gin-gonic router that deals with validation of the Request.
//
// The mode this middleware could run in and the way of the configuration is explained in detail in [utils.OpenApi].
func OpenApiValidationMiddleware(openApiFilename string, withResponseValidation bool) gin.HandlerFunc {

	validator := newValidator(openApiFilename)

	return func(c *gin.Context) {

		slog.Debug("OpenApiValidationMiddleware", "c", fmt.Sprintf("%+v", c))

		var responseBodyWriter *ResponseBodyWriter
		if withResponseValidation {
			// Add interceptor for the Response Body:
			responseBodyWriter = &ResponseBodyWriter{
				body:           &bytes.Buffer{},
				ResponseWriter: c.Writer,
			}
			c.Writer = responseBodyWriter
		}

		// RequestValidationInput is used for both, Request and Response validation
		requestValidationInput, err := validator.createRequestValidationInput(c)
		if err != nil {
			handleInputValidationError(c, err)
			return
		}

		err = openapi3filter.ValidateRequest(c, requestValidationInput)
		if err != nil {
			handleInputValidationError(c, err)
			return
		}

		c.Next()

		if withResponseValidation {
			slog.Debug("OpenApiValidationMiddleware", "Status Code", c.Writer.Status(),
				"Response Header", c.Writer.Header(),
				"Response Body", responseBodyWriter.body.String())

			err = validator.validateResponse(c, responseBodyWriter, requestValidationInput)
			if err != nil {
				slog.Error("OpenApiValidationMiddleware - Response validation failed", "err", err)
			} else {
				slog.Debug("OpenApiValidationMiddleware - Response validation successful.")
			}
		}
	}
}

type openApiValidator struct {
	OpenApiDoc *openapi3.T
	Router     routers.Router
}

func newValidator(openApiFilename string) *openApiValidator {
	loader := openapi3.NewLoader()
	doc, err := loader.LoadFromFile(openApiFilename)

	slog.Debug("NewValidator", "openApiFilename", openApiFilename)
	// slog.Debug("NewValidator", "doc", doc)

	if err != nil {
		slog.Error("NewValidator", "err", err)
		panic(err)
	}
	if err = doc.Validate(loader.Context); err != nil {
		slog.Error("NewValidator", "err", err)
		panic(err)
	}
	router, err := gorillamux.NewRouter(doc)
	if err != nil {
		slog.Error("NewValidator", "err", err)
		panic(err)
	}

	return &openApiValidator{OpenApiDoc: doc, Router: router}
}

func (validator *openApiValidator) createRequestValidationInput(c *gin.Context) (*openapi3filter.RequestValidationInput, error) {
	request := c.Request
	route, pathParams, err := validator.Router.FindRoute(request)
	if err != nil {
		slog.Error("validateRequest - validator.Router.FindRoute:", "err", err)
		return nil, err
	}

	// slog.Debug("validateRequest:", "route", route)
	slog.Debug("validateRequest:", "Method", c.Request.Method, "Path", c.Request.URL.Path,
		"PathDefinition", route.Path, "PathParams", pathParams,
		"QueryParams", c.Request.URL.RawQuery, "BodyLength", c.Request.ContentLength,
		"Summary", route.Operation.Summary)

	requestValidationInput := &openapi3filter.RequestValidationInput{
		Request:    request,
		PathParams: pathParams,
		Route:      route,
		Options: &openapi3filter.Options{
			ExcludeRequestBody:  false,
			ExcludeResponseBody: false,
		},
	}
	return requestValidationInput, nil
}

func (validator *openApiValidator) validateResponse(
	c *gin.Context,
	responseBodyWriter *ResponseBodyWriter,
	requestValidationInput *openapi3filter.RequestValidationInput,
) error {
	responseValidationInput := &openapi3filter.ResponseValidationInput{
		RequestValidationInput: requestValidationInput,
		Status:                 responseBodyWriter.Status(),
		Header:                 responseBodyWriter.Header(),
	}
	responseValidationInput.SetBodyBytes(responseBodyWriter.body.Bytes())
	return openapi3filter.ValidateResponse(c, responseValidationInput)
}

// The error response is based on RFC 7807
func handleInputValidationError(c *gin.Context, err error) {
	if err == nil {
		return
	}

	// TODO: Introduce MultiError support

	slog.Error(fmt.Sprintf("handleInputValidationError: %+v", err))

	type ResultError struct {
		Path    string  `json:"path,omitempty"`
		Value   *string `json:"value,omitempty"`
		Details string  `json:"details,omitempty"`
	}

	result := struct {
		Reason    string        `json:"reason"`
		Code      string        `json:"code"`
		Message   string        `json:"message"`
		Status    int           `json:"status"`
		Timestamp time.Time     `json:"timestamp"`
		Errors    []ResultError `json:"errors"`
	}{
		Reason:    "Input Validation Error",
		Code:      "input_validation_error",
		Message:   err.Error(),
		Status:    http.StatusBadRequest,
		Timestamp: time.Now().UTC().Truncate(time.Millisecond),
		Errors:    []ResultError{{}},
	}

	if reqError, ok := err.(*openapi3filter.RequestError); ok {

		result.Message = reqError.Reason
		errors := &result.Errors[0]

		// TODO: Check usage of openapi3.SchemaError - but the path and details were wrong in the first test.
		// So we parse the result from the error message for now:
		path, details := extractDetails(reqError.Err)
		errors.Path = path
		errors.Details = details
	}

	slog.Error("handleInputValidationError", "result", result)

	// RFC 7807 proposes "application/problem+json", but TM Forum uses "application/json".
	// For the time being, we have decided to follow TM Forum approach:
	c.Header("Content-Type", "application/json; charset=utf-8")

	c.IndentedJSON(http.StatusBadRequest, result)
	c.Abort()
}

// Extracts the path and the detailed message of the error.
//
// Tests with
//
//	reqError.Err.(*openapi3.SchemaError)
//
// did not show the expected results (wrong path, wrong details).
// So we extract the wanted details from the original error message with the expected format:
//
//	"Error at \"<path>\": <details>\n..."
func extractDetails(error error) (string, string) {

	if error == nil {
		slog.Error("OpenApiValidationMiddleware - extractDetails: Error is nil")
		return "", ""
	}

	lines := strings.Split(error.Error(), "\n")
	if len(lines) == 0 {
		return "", ""
	}

	line1 := strings.Split(lines[0], ":")
	if len(lines) <= 2 {
		return "", line1[0]
	}

	return strings.TrimRight(strings.TrimLeft(line1[0], "Error at \""), "\""),
		line1[1]
}
