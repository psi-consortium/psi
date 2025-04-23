// Copyright 2025 THE PSI CONSORTIUM
//
// SPDX-License-Identifier: Apache-2.0

package mission

import (
	"context"
	"fmt"
	"log/slog"
	"net/http"
	"strconv"
	"strings"
	"time"

	"github.com/gin-gonic/gin"
	"github.com/psi-consortium/oda/mission-management/internal/token"
	"github.com/psi-consortium/oda/mission-management/internal/utils"
	"github.com/psi-consortium/oda/mission-management/mission/config"
	"github.com/psi-consortium/oda/mission-management/mission/openapi"
	"go.mongodb.org/mongo-driver/mongo"
)

// The MissionHandler provides the functions to process the HTTP requests from the router created via [mission.NewRouter].
type MissionHandler interface {
	Create(c *gin.Context)
	Patch(c *gin.Context)
	List(c *gin.Context)
	GetById(c *gin.Context)
	Delete(c *gin.Context)
}

type missionHandler struct {
	missionService MissionService
	config         *config.Configuration
}

// NewMissionHandler initializes a new [mission.MissionHandler] based on the provided [config.Configuration] and [mission.MissionService].
func NewMissionHandler(config *config.Configuration, missionService MissionService) MissionHandler {
	mh := missionHandler{
		missionService: missionService,
		config:         config,
	}

	if config.App.ReleaseMode {
		mh.showAuthenticationHelp()
	}
	return &mh
}

func (app *missionHandler) showAuthenticationHelp() {
	introText := `MissionManagement supports three different access level
	1. No access restrictions
	2. Authentication via OpenIdConnect - valid JWT
	3. Data access restriction for owner defined by the JWT claims`

	if !app.isAuthenticationRequired() || !app.isPartyIdMappingDefined() {
		fmt.Println("")
		slog.Warn("Please check your configuration of the data access level!")
		fmt.Println(introText)
		if !app.isAuthenticationRequired() {
			fmt.Println("You have chosen Level 1: OpenIdConnect is disabled.\nTo enable it, please provide your authority server via the env variable 'OPENID_CONNECT_PUBLIC_KEY_ENDPOINT.'")
		} else if !app.isPartyIdMappingDefined() {
			fmt.Println("You have chosen Level 2: OpenIdConnect is enabled but without data access restrictions for owner only. To enable it, please provide the owning PartyId with the JWT Claims and configure the claim through the env variable 'CLAIM_PARTY_ID'.")
		}
		fmt.Println("")
	}
}

func (app *missionHandler) isAuthenticationRequired() bool {
	return app.config.App.Security.OpenIdConnect.PublicKeyEndpoint != ""
}

func (app *missionHandler) isPartyIdMappingDefined() bool {
	return app.config.App.Security.OpenIdConnect.Claims.PartyId != ""
}

func (app *missionHandler) Create(c *gin.Context) {
	ctx, cancel := app.prepareContext(c)
	defer cancel()

	var mis openapi.MissionCreate
	if err := c.BindJSON(&mis); err != nil {
		slog.ErrorContext(ctx, "missionHandler Create - BindJSON", "Error", err, "mission", mis)
		c.IndentedJSON(http.StatusBadRequest, badRequest("JSON body not readable/not valid"))
		return
	}

	result, err := app.missionService.Create(ctx, &mis)
	if err != nil {
		slog.ErrorContext(ctx, "missionHandler Create - Storing", "err", err, "mission", mis)
		c.IndentedJSON(http.StatusInternalServerError, internalServerErrorResponseBody("Storing of Mission in DB failed"))
		return
	}
	slog.DebugContext(ctx, "Mission-Create Result", "Mission", result)
	c.IndentedJSON(http.StatusAccepted, result)
}

func (app *missionHandler) Patch(c *gin.Context) {
	ctx, cancel := app.prepareContext(c)
	defer cancel()

	id := c.Param("id")

	var missionUpdate openapi.MissionUpdate
	if err := c.BindJSON(&missionUpdate); err != nil {
		slog.ErrorContext(ctx, "missionHandler Create - BindJSON", "Error", err, "MissionUpdate", missionUpdate)
		c.IndentedJSON(http.StatusBadRequest, badRequest("JSON body not readable/not valid"))
		return
	}

	// Persisting adjusted Mission
	result, err := app.missionService.Patch(ctx, id, &missionUpdate)
	if err == mongo.ErrNoDocuments {
		slog.DebugContext(ctx, "missionHandler Patch - Loading", "err", err)
		c.IndentedJSON(http.StatusNotFound, app.notFoundResponseBody(id))
		return
	}
	if err != nil {
		slog.WarnContext(ctx, "missionHandler Patch - Storing", "err", err, "id", id, "mission", missionUpdate)
		c.IndentedJSON(http.StatusInternalServerError, internalServerErrorResponseBody("Patch of Mission failed"))
		return
	}

	c.IndentedJSON(http.StatusOK, result)
}

func (app *missionHandler) List(c *gin.Context) {
	ctx, cancel := app.prepareContext(c)
	defer cancel()

	limit := getInt(c.Query("limit"))
	offset := getInt(c.Query("offset"))
	fields := cleanQueryParams(c.QueryArray("fields"))
	sorting := c.QueryArray("sort")
	slog.DebugContext(ctx, "Options", "limit", limit, "offset", offset, "fields", fields)

	missions, err := app.missionService.List(ctx, offset, limit, sorting)
	if err != nil {
		slog.ErrorContext(ctx, "missionHandler List - loading", "err", err, "offset", offset, "limit", limit)
		c.IndentedJSON(http.StatusInternalServerError, internalServerErrorResponseBody("Loading of missions failed"))
		return
	}

	totalCount, err := app.missionService.CountAll(ctx)
	if err != nil {
		slog.ErrorContext(ctx, "missionHandler List - Getting total count", "err", err)
	}

	c.Header("X-Result-Count", strconv.Itoa(len(missions)))
	c.Header("X-Total-Count", strconv.Itoa(int(totalCount)))

	if len(fields) == 0 {
		c.IndentedJSON(http.StatusOK, missions)
	} else {
		result := make([]map[string]interface{}, 0, len(missions))
		for _, mission := range missions {
			result = append(result, reduceToFields(mission, fields))
		}
		c.IndentedJSON(http.StatusOK, result)
	}
}

func (app *missionHandler) GetById(c *gin.Context) {
	ctx, cancel := app.prepareContext(c)
	defer cancel()

	id := c.Param("id")
	fields := cleanQueryParams(c.QueryArray("fields"))
	slog.DebugContext(ctx, "missionHandler GetById - Params", "id", id, "fields", fields)

	mission, err := app.missionService.GetById(ctx, id)
	if err == mongo.ErrNoDocuments {
		slog.DebugContext(ctx, "missionHandler GetById - GetById", "err", err)
		c.IndentedJSON(http.StatusNotFound, app.notFoundResponseBody(id))
		return
	}
	if err != nil {
		slog.ErrorContext(ctx, "missionHandler GetById - GetById", "err", err)
		c.IndentedJSON(
			http.StatusInternalServerError,
			internalServerErrorResponseBody(fmt.Sprintf("Can't load Mission with Id %s", id)))
		return
	}
	c.IndentedJSON(http.StatusOK, reduceToFields(mission, fields))
}

func (app *missionHandler) Delete(c *gin.Context) {
	ctx, cancel := app.prepareContext(c)
	defer cancel()

	id := c.Param("id")
	slog.DebugContext(ctx, "Mission Delete - Param", "id", id)
	deleteCount, err := app.missionService.Delete(ctx, id)
	if err != nil {
		slog.ErrorContext(ctx, "missionHandler Delete - Delete", "error", err)
		c.IndentedJSON(
			http.StatusInternalServerError,
			internalServerErrorResponseBody(fmt.Sprintf("Can't delete Mission with Id %s", id)))
		return
	}

	if deleteCount == 0 {
		c.IndentedJSON(http.StatusNotFound, app.notFoundResponseBody(id))
	} else {
		c.Status(http.StatusNoContent)
	}
}

func badRequest(reason string) ErrorEnhanced {
	return errorResponseBody(http.StatusBadRequest, "bad_request", reason)
}

func internalServerErrorResponseBody(message string) ErrorEnhanced {
	return errorResponseBody(http.StatusInternalServerError, "internal_server_error", message)
}

type ErrorEnhanced struct {
	Code      string    `json:"code"`
	Reason    string    `json:"reason"`
	Status    *string   `json:"status,omitempty"`
	Timestamp time.Time `json:"timestamp"`
}

func (app *missionHandler) notFoundResponseBody(id string) ErrorEnhanced {
	domainName := strings.TrimSuffix(app.config.Database.Collection, "s")
	var message string
	if id == "" {
		message = fmt.Sprintf("Couldn't find %s for given identifier", domainName)
	} else {
		message = fmt.Sprintf("Couldn't find %s for given Id %s", domainName, id)
	}
	return errorResponseBody(http.StatusNotFound, "not_found", message)
}

func errorResponseBody(status int, code string, reason string) ErrorEnhanced {
	return ErrorEnhanced{
		Timestamp: time.Now().UTC().Truncate(time.Millisecond),
		Code:      code,
		Reason:    reason,
		Status:    strPtr(strconv.Itoa(status)),
	}
}

func getInt(value string) int64 {
	if value == "" {
		return -1
	}
	result, err := strconv.ParseInt(value, 10, 64)
	if err != nil {
		slog.Warn("Cannot convert value " + value + " to int64")
		return -1
	}
	return result
}

func reduceToFields(mission *openapi.Mission, fields []string) map[string]interface{} {
	data, _ := utils.StructToMap(mission)
	if len(fields) == 0 {
		return data
	}

	newMap := make(map[string]interface{}, len(fields))
	for _, fieldName := range fields {
		value := data[fieldName]
		if value != nil && value != "" {
			newMap[fieldName] = value
		}
	}
	return newMap
}

func cleanQueryParams(queryParams []string) []string {
	str := strings.Join(queryParams, ",")
	str = strings.ReplaceAll(str, " ", "")
	str = strings.ReplaceAll(str, "[", "")
	str = strings.ReplaceAll(str, "]", "")
	if str == "" {
		return []string{}
	} else {
		return strings.Split(str, ",")
	}
}

func (app *missionHandler) prepareContext(c *gin.Context) (context.Context, context.CancelFunc) {
	ctx := app.withPsiClaims(c,
		app.withExternalIds(c.Request.Context(), c))
	return context.WithTimeout(ctx, time.Duration(app.config.App.Timeout)*time.Second)
}

func (app *missionHandler) withExternalIds(ctx context.Context, c *gin.Context) context.Context {

	v, exists := c.Get(string(config.SlogFields))
	if !exists {
		slog.DebugContext(ctx, "missionHandler.withExternalIds - No 'SlogFields' found in gin.context. Proceeding without ExtIds.")
		return ctx
	}
	attrs, ok := v.([]slog.Attr)
	if !ok {
		slog.ErrorContext(ctx, "missionHandler.withExternalIds - SlogFields of gin.Context are not of required type '[]slog.Attr'", "type", fmt.Sprintf("%T", v))
		return ctx
	}
	slog.DebugContext(ctx, "missionHandler.withExternalIds", "attrs", attrs)
	return context.WithValue(ctx, config.SlogFields, attrs)
}

func (app *missionHandler) withPsiClaims(c *gin.Context, ctx context.Context) context.Context {
	if !app.isAuthenticationRequired() {
		return ctx
	}

	value := c.Value(token.ContextClaimsKey)
	psiClaims, ok := value.(*token.PsiClaims)
	if psiClaims == nil {
		slog.DebugContext(ctx, "withPsiClaims - No claim data attached to context")
		return ctx
	}
	if !ok {
		slog.ErrorContext(ctx, "withPsiClaims - Found claim value is not of type PsiClaims", "value", value)
		return ctx
	}

	slog.DebugContext(ctx, "withPsiClaims - Found in Gin-Context", "psiClaims", psiClaims)

	if !app.isPartyIdMappingDefined() {
		psiClaims.PartyId = ""
	}
	return token.WithPsiClaims(ctx, *psiClaims)
}
