// Copyright 2025 THE PSI CONSORTIUM
//
// SPDX-License-Identifier: Apache-2.0

package token

import (
	"context"
	"testing"

	"github.com/stretchr/testify/assert"
)

func TestWithPsiClaims(t *testing.T) {
	psiClaims := PsiClaims{
		PartyId: "testParty",
		UserId:  "testUser",
		Name:    "testName",
	}

	ctx := WithPsiClaims(context.Background(), psiClaims)
	value := ctx.Value(key)
	result, ok := value.(PsiClaims)

	assert.True(t, ok, "Type assertion failed, expected %T, actual %T", psiClaims, value)
	assert.Equal(t, psiClaims, result)
}

func TestGetPartyId(t *testing.T) {
	partyId := "testParty"
	psiClaims := PsiClaims{PartyId: partyId}

	ctx := WithPsiClaims(context.Background(), psiClaims)

	assert.Equal(t, partyId, GetPartyId(ctx))
}

func TestGetPartyIdUnknown(t *testing.T) {
	assert.Equal(t, "", GetPartyId(context.Background()))
}

func TestGetUserId(t *testing.T) {
	userId := "testUser"
	psiClaims := PsiClaims{UserId: userId}

	ctx := WithPsiClaims(context.Background(), psiClaims)

	assert.Equal(t, userId, GetUserId(ctx))
}

func TestGetUserIdUnknown(t *testing.T) {
	assert.Equal(t, "", GetUserId(context.Background()))
}

func TestName(t *testing.T) {
	name := "testName"
	psiClaims := PsiClaims{Name: name}

	ctx := WithPsiClaims(context.Background(), psiClaims)

	assert.Equal(t, name, GetUserName(ctx))
}

func TestGetUserNameUnknown(t *testing.T) {
	assert.Equal(t, "", GetUserName(context.Background()))
}
