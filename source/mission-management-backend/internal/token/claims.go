// Copyright 2025 THE PSI CONSORTIUM
//
// SPDX-License-Identifier: Apache-2.0

package token

import (
	"context"
	"errors"
)

// The Context key to share the PSI Claims.
const ContextClaimsKey = "PsiClaims"

// PsiClaims is used to store and share a PartyId, UserId and UserName.
type PsiClaims struct {
	PartyId string
	UserId  string
	Name    string
}

// IsEmpty checks that all components of the PsiClaims are empty.
func (psiClaims *PsiClaims) IsEmpty() bool {
	return psiClaims.PartyId == "" &&
		psiClaims.UserId == "" &&
		psiClaims.Name == ""
}

type ctxKey string

const key ctxKey = "PsiClaims"

// WithPsiClaims returns a copy of the given Context including the given PsiClaims.
func WithPsiClaims(ctx context.Context, claims PsiClaims) context.Context {
	return context.WithValue(ctx, key, claims)
}

// GetPsiClaims returns the PsiClaims of the given Context, if available.
func GetPsiClaims(ctx context.Context) (*PsiClaims, error) {
	psiClaims, ok := ctx.Value(key).(PsiClaims)
	if ok {
		return &psiClaims, nil
	} else {
		return nil, errors.New("no PsiClaims found in given Context")
	}
}

// GetPartyId returns the PsiClaims.PartyId of the given Context, if available.
func GetPartyId(ctx context.Context) string {
	psiClaims, err := GetPsiClaims(ctx)
	if err == nil {
		return psiClaims.PartyId
	} else {
		return ""
	}
}

// GetUserId returns the PsiClaims.UserId of the given Context, if available.
func GetUserId(ctx context.Context) string {
	psiClaims, err := GetPsiClaims(ctx)
	if err == nil {
		return psiClaims.UserId
	} else {
		return ""
	}
}

// GetUserName returns the PsiClaims.Name of the given Context, if available.
func GetUserName(ctx context.Context) string {
	psiClaims, err := GetPsiClaims(ctx)
	if err == nil {
		return psiClaims.Name
	} else {
		return ""
	}
}
