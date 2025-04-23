// Copyright 2025 THE PSI CONSORTIUM
//
// SPDX-License-Identifier: Apache-2.0

package mission

import (
	"bytes"
	"context"
	"encoding/json"
	"errors"
	"io"
	"net/http"
	"net/http/httptest"
	"strconv"
	"testing"
	"time"

	"github.com/gin-gonic/gin"
	"github.com/psi-consortium/oda/mission-management/internal/token"
	"github.com/psi-consortium/oda/mission-management/mission/config"
	mockmission "github.com/psi-consortium/oda/mission-management/mission/mocks"
	"github.com/psi-consortium/oda/mission-management/mission/openapi"
	"github.com/stretchr/testify/assert"
	"github.com/stretchr/testify/mock"
	"github.com/stretchr/testify/require"
	"go.mongodb.org/mongo-driver/mongo"
)

func initMissionHandler(config *config.Configuration, missionService MissionService) MissionHandler {
	return NewMissionHandler(config, missionService)
}

func initMissionHandlerNoAuth(mockMissionService *mockmission.MockMissionService) MissionHandler {
	missionHandler := initMissionHandler(
		getDefaultConfiguration(false, false),
		mockMissionService)
	return missionHandler
}

func getConfiguration(publicKeyEndPoint string, partyId string) *config.Configuration {
	return &config.Configuration{
		App: config.Application{
			Security: config.Security{
				OpenIdConnect: config.OpenIdConnect{
					PublicKeyEndpoint: publicKeyEndPoint,
					Claims: config.ClaimMapping{
						PartyId: partyId,
						// UserId:  "",
						// Name:    "",
					},
				},
			},
			Timeout: 10,
		},
	}
}

func getDefaultConfiguration(withAuth bool, withAccessRestriction bool) *config.Configuration {
	publicKeyEndPoint := ""
	partyId := ""
	if withAuth {
		publicKeyEndPoint = "http://dummy/authentication/service"
	}
	if withAccessRestriction {
		partyId = "customer_id"
	}
	return getConfiguration(publicKeyEndPoint, partyId)
}

func getTestGinContext(providerId string, method string, path string) (*gin.Context, *httptest.ResponseRecorder) {
	gin.SetMode(gin.TestMode)

	w := httptest.NewRecorder()
	c, _ := gin.CreateTestContext(w)

	c.Request, _ = http.NewRequest(method, path, nil)
	c.BindQuery(nil)

	c.Request.Header = make(http.Header)
	c.Request.Header.Set("Content-Type", "application/json")

	psiClaims := &token.PsiClaims{PartyId: providerId}
	c.Set(token.ContextClaimsKey, psiClaims)
	return c, w
}

func addBody(c *gin.Context, body []byte) *gin.Context {
	if len(body) <= 0 {
		return c
	}

	c.Request.Body = io.NopCloser(bytes.NewBuffer(body))
	// c.Request, _ = http.NewRequest(method, path, bodyReader)
	return c
}

func createMission(id string) *openapi.Mission {
	return &openapi.Mission{
		Id:          id,
		Name:        "Test Name",
		Description: ptr("Test Description"),
		Category:    ptr("Test Category"),
		Timeframe:   newTimeFrame(time.Now().AddDate(0, 0, 1), time.Now().AddDate(0, 1, 1)),
		Status:      openapi.DRAFT.Ptr(),
	}
}

func createMissionCreate() *openapi.MissionCreate {
	return &openapi.MissionCreate{
		Name:        "Test Create Name",
		Description: ptr("Test Create Description"),
		Category:    ptr("Test Category"),
		Timeframe:   newTimeFrame(time.Now().AddDate(0, 0, 1), time.Now().AddDate(0, 1, 1)),
		Status:      openapi.DRAFT.Ptr(),
	}
}

func TestMHGetById(t *testing.T) {

	t.Run("Successful", func(t *testing.T) {
		tests := []struct {
			name                            string
			withAuth, withAccessRestriction bool
		}{
			{"No Auth", false, false},
			{"With Auth, No Access Restriction", true, false},
			{"With Auth, With Access Restriction", true, true},
		}

		for _, tt := range tests {
			t.Run(tt.name, func(t *testing.T) {
				mockMissionService := mockmission.NewMockMissionService(t)
				config := getDefaultConfiguration(tt.withAuth, tt.withAccessRestriction)
				config.App.ReleaseMode = true // to enable once in the tests with showAuthenticationHelp() method
				missionHandler := initMissionHandler(config, mockMissionService)

				missionId := "1234"
				mission := createMission(missionId)
				c, rr := getTestGinContext("P123", "GET", "/")
				c.Params = []gin.Param{{Key: "id", Value: missionId}}

				mockMissionService.EXPECT().GetById(
					mock.MatchedBy(func(ctx context.Context) bool {
						psiClaim, err := token.GetPsiClaims(ctx)
						return ((!tt.withAuth && err != nil) || (tt.withAuth && err == nil)) &&
							isValidPsiClaim(psiClaim, tt.withAuth, tt.withAccessRestriction)
					}),
					missionId).
					Return(mission, nil).
					Once()

				missionHandler.GetById(c)

				mockMissionService.AssertExpectations(t)
				// Attention: HTTP Status Code is correctly set in c.Writer: `c.Writer.Status()`,
				// but not in the ResponseRecorder `w`. To set the status properly, we have to execute:
				c.Writer.WriteHeaderNow()
				assert.Equal(t, http.StatusOK, c.Writer.Status())
				assert.Equal(t, http.StatusOK, rr.Code)

				var actualMission openapi.Mission
				json.Unmarshal(rr.Body.Bytes(), &actualMission)
				assert.Equal(t, *mission, actualMission)

			})
		}

		t.Run("Field Reduction", func(t *testing.T) {
			mockMissionService := mockmission.NewMockMissionService(t)
			missionHandler := initMissionHandlerNoAuth(mockMissionService)

			missionId := "1234"
			mission := createMission(missionId)
			c, rr := getTestGinContext("P123", "GET", "/?fields=id,name&fields=description")
			c.Params = []gin.Param{{Key: "id", Value: missionId}}

			mockMissionService.EXPECT().GetById(mock.Anything, missionId).Return(mission, nil).Once()

			missionHandler.GetById(c)

			mockMissionService.AssertExpectations(t)
			c.Writer.WriteHeaderNow() // c.Writer.Status() to w.Code
			assert.Equal(t, http.StatusOK, rr.Code)

			// Assure expected query data (either via c.Request.URL.Query() or c.QueryArray(<key>))
			assert.Len(t, c.QueryArray("fields"), 2)
			assert.Contains(t, c.QueryArray("fields"), "id,name")
			assert.Contains(t, c.QueryArray("fields"), "description")

			var actualMission openapi.Mission
			json.Unmarshal(rr.Body.Bytes(), &actualMission)
			assert.NotEqual(t, mission, actualMission)
			assert.Equal(t, mission.Id, actualMission.Id)
			assert.Equal(t, mission.Name, actualMission.Name)
			assert.Equal(t, *mission.Description, *actualMission.Description)
			assert.Nil(t, actualMission.Category)
			assert.Nil(t, actualMission.Timeframe)
		})
	})

	t.Run("Not Successful", func(t *testing.T) {
		mockMissionService := mockmission.NewMockMissionService(t)
		missionHandler := initMissionHandlerNoAuth(mockMissionService)

		t.Run("404: Not Found", func(t *testing.T) {
			missionId := "1234"
			c, rr := getTestGinContext("P123", "GET", "/")
			c.Params = []gin.Param{{Key: "id", Value: missionId}}

			mockMissionService.EXPECT().GetById(mock.Anything, missionId).
				Return(nil, mongo.ErrNoDocuments).Once()

			missionHandler.GetById(c)

			mockMissionService.AssertExpectations(t)
			c.Writer.WriteHeaderNow() // c.Writer.Status() to w.Code
			assert.Equal(t, http.StatusNotFound, rr.Code)
			assert.Regexp(t, "not_found", rr.Body.String())
		})

		t.Run("500: Internal Server Error", func(t *testing.T) {
			missionId := "1234"
			c, rr := getTestGinContext("P123", "GET", "/")
			c.Params = []gin.Param{{Key: "id", Value: missionId}}

			mockMissionService.EXPECT().GetById(mock.Anything, missionId).
				Return(nil, errors.New("Any other error")).Once()

			missionHandler.GetById(c)

			mockMissionService.AssertExpectations(t)
			c.Writer.WriteHeaderNow() // c.Writer.Status() to w.Code
			assert.Equal(t, http.StatusInternalServerError, rr.Code)
		})
	})
}

func TestMHList(t *testing.T) {

	maxCount := 15
	missionList := make([]*openapi.Mission, 0, maxCount)
	for i := range maxCount {
		missionList = append(missionList, createMission(strconv.Itoa(i)))
	}

	t.Run("Successful", func(t *testing.T) {
		t.Run("No Query Parameter", func(t *testing.T) {
			mockMissionService := mockmission.NewMockMissionService(t)
			missionHandler := initMissionHandlerNoAuth(mockMissionService)

			c, rr := getTestGinContext("P123", "GET", "/")

			mockMissionService.EXPECT().List(mock.Anything, int64(-1), int64(-1)).Return(missionList, nil).Once()
			mockMissionService.EXPECT().CountAll(mock.Anything).Return(int64(maxCount), nil).Once()

			missionHandler.List(c)

			mockMissionService.AssertExpectations(t)
			c.Writer.WriteHeaderNow() // c.Writer.Status() to r.Code
			assert.Equal(t, http.StatusOK, rr.Code)

			header := rr.Header()
			assert.Equal(t, header.Get("X-Result-Count"), strconv.Itoa(len(missionList)))
			assert.Equal(t, header.Get("X-Total-Count"), strconv.Itoa(maxCount))

			var resultingMissions []*openapi.Mission
			err := json.Unmarshal(rr.Body.Bytes(), &resultingMissions)
			expectedLength := maxCount
			assert.NoError(t, err, "error on Unmarshal of 'missionHandler.List(c)' ( => list of Missions)")
			assert.NotEmpty(t, resultingMissions)
			assert.Len(t, resultingMissions, expectedLength)
			assert.Equal(t, missionList, resultingMissions)
		})

		t.Run("With Limit and Offset", func(t *testing.T) {
			mockMissionService := mockmission.NewMockMissionService(t)
			missionHandler := initMissionHandlerNoAuth(mockMissionService)

			offset := 2
			limit := 10
			expectedMissionList := missionList[offset : offset+limit]
			queryString := "limit=" + strconv.Itoa(limit) + "&offset=" + strconv.Itoa(offset)
			c, rr := getTestGinContext("P123", "GET", "/?"+queryString)

			mockMissionService.EXPECT().List(mock.Anything, int64(offset), int64(limit)).
				Return(expectedMissionList, nil).
				Once()
			mockMissionService.EXPECT().CountAll(mock.Anything).Return(int64(maxCount), nil).Once()

			missionHandler.List(c)

			mockMissionService.AssertExpectations(t)
			c.Writer.WriteHeaderNow() // c.Writer.Status() to r.Code
			assert.Equal(t, http.StatusOK, rr.Code)

			header := rr.Header()
			assert.Equal(t, strconv.Itoa(limit), header.Get("X-Result-Count"))
			assert.Equal(t, strconv.Itoa(maxCount), header.Get("X-Total-Count"))

			var resultingMissions []*openapi.Mission
			err := json.Unmarshal(rr.Body.Bytes(), &resultingMissions)
			expectedLength := limit
			assert.NoError(t, err, "error on Unmarshal of 'missionHandler.List(c)' ( => list of Missions)")
			assert.NotEmpty(t, resultingMissions)
			assert.Len(t, resultingMissions, expectedLength)
			assert.Equal(t, expectedMissionList, resultingMissions)
		})

		t.Run("With Fields Reduction", func(t *testing.T) {
			mockMissionService := mockmission.NewMockMissionService(t)
			missionHandler := initMissionHandlerNoAuth(mockMissionService)

			queryString := "fields=id,name"
			c, rr := getTestGinContext("P123", "GET", "/?"+queryString)

			mockMissionService.EXPECT().List(mock.Anything, int64(-1), int64(-1)).Return(missionList, nil).Once()
			mockMissionService.EXPECT().CountAll(mock.Anything).Return(int64(maxCount), nil).Once()

			missionHandler.List(c)

			mockMissionService.AssertExpectations(t)
			c.Writer.WriteHeaderNow() // c.Writer.Status() to rr.Code
			assert.Equal(t, http.StatusOK, rr.Code)

			var resultingMissions []openapi.Mission
			json.Unmarshal(rr.Body.Bytes(), &resultingMissions)
			assert.Len(t, resultingMissions, maxCount)

			// A few random tests
			assert.NotEqual(t, missionList[0], resultingMissions[0])
			assert.Equal(t, missionList[0].Id, resultingMissions[0].Id)
			assert.Equal(t, missionList[0].Name, resultingMissions[0].Name)
			assert.NotNil(t, missionList[0].Description)
			assert.Nil(t, resultingMissions[0].Description)

			assert.Equal(t, missionList[10].Id, resultingMissions[10].Id)
			assert.Equal(t, missionList[10].Name, resultingMissions[10].Name)
			assert.NotNil(t, missionList[10].Description)
			assert.Nil(t, resultingMissions[10].Description)
		})

		t.Run("CountAll failed", func(t *testing.T) {
			mockMissionService := mockmission.NewMockMissionService(t)
			missionHandler := initMissionHandlerNoAuth(mockMissionService)

			c, rr := getTestGinContext("P123", "GET", "/")

			mockMissionService.EXPECT().List(mock.Anything, int64(-1), int64(-1)).Return(missionList, nil).Once()
			mockMissionService.EXPECT().CountAll(mock.Anything).Return(-1, errors.New("Any Error")).Once()

			missionHandler.List(c)

			mockMissionService.AssertExpectations(t)
			c.Writer.WriteHeaderNow() // c.Writer.Status() to r.Code
			assert.Equal(t, http.StatusOK, rr.Code)

			header := rr.Header()
			assert.Equal(t, header.Get("X-Result-Count"), strconv.Itoa(len(missionList)))
			assert.Equal(t, header.Get("X-Total-Count"), "-1")

			var resultingMissions []*openapi.Mission
			err := json.Unmarshal(rr.Body.Bytes(), &resultingMissions)
			expectedLength := maxCount
			assert.NoError(t, err, "error on Unmarshal of 'missionHandler.List(c)' ( => list of Missions)")
			assert.NotEmpty(t, resultingMissions)
			assert.Len(t, resultingMissions, expectedLength)
			assert.Equal(t, missionList, resultingMissions)
		})

	})

	t.Run("Not Successful", func(t *testing.T) {
		t.Run("500: Internal Server Error", func(t *testing.T) {
			mockMissionService := mockmission.NewMockMissionService(t)
			missionHandler := initMissionHandlerNoAuth(mockMissionService)

			c, rr := getTestGinContext("P123", "GET", "/")

			mockMissionService.EXPECT().List(mock.Anything, int64(-1), int64(-1)).Return(nil, errors.New("Internal Test-Error")).Once()
			// mockMissionService.EXPECT().CountAll(mock.Anything).Return(int64(maxCount), nil).Once()

			missionHandler.List(c)

			mockMissionService.AssertExpectations(t)
			mockMissionService.AssertNotCalled(t, "CountAll", mock.Anything)
			c.Writer.WriteHeaderNow() // c.Writer.Status() to rr.Code
			assert.Equal(t, http.StatusInternalServerError, rr.Code)
			assert.Regexp(t, "Loading of missions failed", rr.Body.String())
		})
	})
}

func TestMHCreate(t *testing.T) {
	t.Run("Successful", func(t *testing.T) {
		mockMissionService := mockmission.NewMockMissionService(t)
		missionHandler := initMissionHandlerNoAuth(mockMissionService)

		missionCreate := createMissionCreate()
		missionCreate.Category = ptr("Create - No Auth")
		mBytes, err := json.Marshal(missionCreate)
		require.Nil(t, err)
		c, rr := getTestGinContext("P123", "POST", "/")
		c = addBody(c, mBytes)

		var deadline time.Time
		var deadlineOk bool

		mockMissionService.EXPECT().Create(mock.Anything, mock.Anything).
			RunAndReturn(func(ctx context.Context, missionCreate *openapi.MissionCreate) (*openapi.Mission, error) {
				// Info: The correct PsiClaim data is tested once in TestMHGetById and not repeated here.

				deadline, deadlineOk = ctx.Deadline() // storing for test

				mission := createMission("9876")
				mission.Name = missionCreate.Name
				mission.Description = missionCreate.Description
				return mission, nil
			}).
			Once()

		missionHandler.Create(c)

		mockMissionService.AssertExpectations(t)
		assert.True(t, deadlineOk, "Deadline is missing in the Context")
		assert.Greater(t, deadline, time.Now())

		c.Writer.WriteHeaderNow() // c.Writer.Status() to w.Code
		assert.Equal(t, http.StatusAccepted, rr.Code)

		var actualMission openapi.Mission
		json.Unmarshal(rr.Body.Bytes(), &actualMission)
		assert.NotNil(t, actualMission.Id)
		assert.Equal(t, missionCreate.Name, actualMission.Name)
	})

	t.Run("NotSuccessful", func(t *testing.T) {

		t.Run("Mission Unmarshal", func(t *testing.T) {
			mockMissionService := mockmission.NewMockMissionService(t)
			missionHandler := initMissionHandlerNoAuth(mockMissionService)

			mBytes := []byte("Not a valid JSON...")
			c, rr := getTestGinContext("P123", "POST", "/")
			c = addBody(c, mBytes)

			missionHandler.Create(c)

			mockMissionService.AssertNotCalled(t, "Create", mock.Anything, mock.Anything)
			c.Writer.WriteHeaderNow() // c.Writer.Status() to w.Code
			assert.Equal(t, http.StatusBadRequest, c.Writer.Status())
			assert.Regexp(t, "bad_request", rr.Body.String())
		})

		t.Run("DB, Internal Error", func(t *testing.T) {
			mockMissionService := mockmission.NewMockMissionService(t)
			missionHandler := initMissionHandlerNoAuth(mockMissionService)

			initialMission := createMission("123")
			mBytes, err := json.Marshal(initialMission)
			require.Nil(t, err)
			c, _ := getTestGinContext("P123", "POST", "/")
			c = addBody(c, mBytes)

			mockMissionService.EXPECT().Create(mock.Anything, mock.Anything).Return(nil, errors.New("Internal Test-Error")).Once()

			missionHandler.Create(c)

			mockMissionService.AssertExpectations(t)
			assert.Equal(t, http.StatusInternalServerError, c.Writer.Status())
		})
	})
}

func TestMHPatch(t *testing.T) {
	t.Run("Successful", func(t *testing.T) {
		mockMissionService := mockmission.NewMockMissionService(t)
		missionHandler := initMissionHandlerNoAuth(mockMissionService)

		missionId := "1234"
		mission := createMission(missionId)
		missionUpdate := openapi.MissionUpdate{
			Name:     ptr("Updated Name"),
			Category: ptr("Updated Category"),
			Status:   openapi.ACTIVE.Ptr(),
		}

		mBytes, err := json.Marshal(missionUpdate)
		require.Nil(t, err)
		c, rr := getTestGinContext("P123", "PATCH", "/")
		c.Params = []gin.Param{{Key: "id", Value: missionId}}
		c = addBody(c, mBytes)

		mockMissionService.EXPECT().Patch(mock.Anything, missionId, mock.Anything).
			RunAndReturn(func(ctx context.Context, id string, patch *openapi.MissionUpdate) (*openapi.Mission, error) {
				mission.Name = *patch.Name
				mission.Category = patch.Category
				mission.Status = patch.Status
				return mission, nil
			}).
			Once()

		missionHandler.Patch(c)

		mockMissionService.AssertExpectations(t)
		c.Writer.WriteHeaderNow() // c.Writer.Status() to w.Code
		assert.Equal(t, http.StatusOK, rr.Code)

		var actualMission openapi.Mission
		json.Unmarshal(rr.Body.Bytes(), &actualMission)
		assert.Equal(t, missionId, actualMission.Id)
		assert.Equal(t, *missionUpdate.Name, actualMission.Name)
		assert.Equal(t, *missionUpdate.Category, *actualMission.Category)
		assert.Equal(t, *missionUpdate.Category, *actualMission.Category)
		assert.Equal(t, missionUpdate.Status, actualMission.Status)
	})

	t.Run("Not Successful", func(t *testing.T) {
		t.Run("400: Bad Request", func(t *testing.T) {
			mockMissionService := mockmission.NewMockMissionService(t)
			missionHandler := initMissionHandlerNoAuth(mockMissionService)

			missionId := "1234"
			mBytes := []byte("Not a valid JSON...")
			c, rr := getTestGinContext("P123", "PATCH", "/"+missionId)
			c.Params = []gin.Param{{Key: "id", Value: missionId}} // TODO: check for setting of Params via Path parsing
			c = addBody(c, mBytes)

			missionHandler.Patch(c)

			mockMissionService.AssertNotCalled(t, "Patch", mock.Anything, mock.Anything, mock.Anything)
			c.Writer.WriteHeaderNow() // c.Writer.Status() to rr.Code
			assert.Equal(t, http.StatusBadRequest, c.Writer.Status())
			assert.Regexp(t, "not valid", rr.Body.String())
		})

		t.Run("404: Not Found", func(t *testing.T) {
			mockMissionService := mockmission.NewMockMissionService(t)
			missionHandler := initMissionHandlerNoAuth(mockMissionService)

			missionId := "1234"
			c, rr := getTestGinContext("P123", "PATCH", "/")
			c.Params = []gin.Param{{Key: "id", Value: missionId}}
			mBytes, err := json.Marshal(openapi.MissionUpdate{Name: ptr("Updated Name")})
			require.Nil(t, err)
			c = addBody(c, mBytes)

			mockMissionService.EXPECT().Patch(mock.Anything, missionId, mock.Anything).
				Return(nil, mongo.ErrNoDocuments).Once()

			missionHandler.Patch(c)

			mockMissionService.AssertExpectations(t)
			c.Writer.WriteHeaderNow() // c.Writer.Status() to rr.Code
			assert.Equal(t, http.StatusNotFound, rr.Code)
		})

		t.Run("500: Internal Server Error", func(t *testing.T) {
			mockMissionService := mockmission.NewMockMissionService(t)
			missionHandler := initMissionHandlerNoAuth(mockMissionService)

			missionId := "1234"
			c, rr := getTestGinContext("P123", "PATCH", "/")
			c.Params = []gin.Param{{Key: "id", Value: missionId}}
			mBytes, err := json.Marshal(openapi.MissionUpdate{Name: ptr("Updated Name")})
			require.Nil(t, err)
			c = addBody(c, mBytes)

			mockMissionService.EXPECT().Patch(mock.Anything, missionId, mock.Anything).
				Return(nil, errors.New("Any other error")).Once()

			missionHandler.Patch(c)

			mockMissionService.AssertExpectations(t)
			c.Writer.WriteHeaderNow() // c.Writer.Status() to rr.Code
			assert.Equal(t, http.StatusInternalServerError, rr.Code)
		})
	})
}

func TestMHDelete(t *testing.T) {
	t.Run("Successful", func(t *testing.T) {
		mockMissionService := mockmission.NewMockMissionService(t)
		missionHandler := initMissionHandlerNoAuth(mockMissionService)

		missionId := "1234"
		c, rr := getTestGinContext("P123", "DELETE", "/")
		c.Params = []gin.Param{{Key: "id", Value: missionId}}

		mockMissionService.EXPECT().Delete(mock.Anything, missionId).Return(1, nil).Once()

		missionHandler.Delete(c)

		mockMissionService.AssertExpectations(t)
		c.Writer.WriteHeaderNow() // c.Writer.Status() to w.Code
		assert.Equal(t, http.StatusNoContent, rr.Code)
	})

	t.Run("Notfound", func(t *testing.T) {
		mockMissionService := mockmission.NewMockMissionService(t)
		missionHandler := initMissionHandlerNoAuth(mockMissionService)

		missionId := "1234"
		c, rr := getTestGinContext("P123", "DELETE", "/")
		c.Params = []gin.Param{{Key: "id", Value: missionId}}

		mockMissionService.EXPECT().Delete(mock.Anything, missionId).Return(0, nil).Once()

		missionHandler.Delete(c)

		mockMissionService.AssertExpectations(t)
		c.Writer.WriteHeaderNow() // c.Writer.Status() to w.Code
		assert.Equal(t, http.StatusNotFound, rr.Code)
	})

	t.Run("DB, Internal Error", func(t *testing.T) {
		mockMissionService := mockmission.NewMockMissionService(t)
		missionHandler := initMissionHandlerNoAuth(mockMissionService)

		missionId := "1234"
		c, rr := getTestGinContext("P123", "DELETE", "/")
		c.Params = []gin.Param{{Key: "id", Value: missionId}}

		mockMissionService.EXPECT().Delete(mock.Anything, missionId).Return(-1, errors.New("internal DB error")).Once()

		missionHandler.Delete(c)

		mockMissionService.AssertExpectations(t)
		c.Writer.WriteHeaderNow() // c.Writer.Status() to w.Code
		assert.Equal(t, http.StatusInternalServerError, rr.Code)
	})
}

func isValidPsiClaim(psiClaims *token.PsiClaims,
	withAuthentication bool, withAccessRestriction bool) bool {

	if !withAuthentication {
		return psiClaims == nil
	} else {
		if !withAccessRestriction {
			return psiClaims != nil && psiClaims.PartyId == ""
		} else {
			return psiClaims != nil && psiClaims.PartyId != ""
		}
	}
}

func ptr(value string) *string {
	return &value
}
