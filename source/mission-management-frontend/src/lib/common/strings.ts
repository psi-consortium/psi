/*
 * Copyright 2025 THE PSI CONSORTIUM
 *
 * SPDX-License-Identifier: Apache-2.0
 */

export function capitalize(s: string | undefined): string {
    if (!s) {
        return "";
    }
    if (s.length === 1) {
        return s.toUpperCase();
    }
    return String(s[0]).toUpperCase() + String(s).slice(1).toLowerCase();
}
