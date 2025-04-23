/*
 * Copyright 2025 THE PSI CONSORTIUM
 *
 * SPDX-License-Identifier: Apache-2.0
 */

export function toDate(value: FormDataEntryValue | null): Date | undefined {
    if (!value || typeof value !== "string") {
        return undefined;
    }
    return new Date(value.toString());
}
