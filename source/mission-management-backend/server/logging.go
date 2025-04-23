// Copyright 2025 THE PSI CONSORTIUM
//
// SPDX-License-Identifier: Apache-2.0

package server

import (
	"context"
	"log/slog"
	"os"

	"github.com/psi-consortium/oda/mission-management/mission/config"
)

type contextHandler struct {
	slog.Handler
}

// InitLogger configures the [Slog] logger based on the [config.LogSettings].
//
// [Slog]: https://pkg.go.dev/log/slog
func InitLogger(logConfig config.LogSettings) {
	var programLevel = new(slog.LevelVar) // by default: Info

	if logConfig.Level == "DEBUG" {
		programLevel.Set(slog.LevelDebug)
	} else if logConfig.Level == "INFO" {
		programLevel.Set(slog.LevelInfo)
	} else if logConfig.Level == "WARN" {
		programLevel.Set(slog.LevelWarn)
	}

	handlerOpt := &slog.HandlerOptions{
		Level: programLevel,
	}
	var logHandler slog.Handler
	if logConfig.Handler == "JSON" {
		logHandler = slog.NewJSONHandler(os.Stderr, handlerOpt)
	} else {
		logHandler = slog.NewTextHandler(os.Stderr, handlerOpt)
	}

	// Add extIds, like CorrelationId and more
	contextHandler := &contextHandler{logHandler}

	slog.SetDefault(slog.New(contextHandler))
}

// Adds contextual attributes to the Record before calling the underlying handler
func (h contextHandler) Handle(ctx context.Context, r slog.Record) error {
	if attrs, ok := ctx.Value(config.SlogFields).([]slog.Attr); ok {
		for _, v := range attrs {
			r.AddAttrs(v)
		}
	}
	return h.Handler.Handle(ctx, r)
}
