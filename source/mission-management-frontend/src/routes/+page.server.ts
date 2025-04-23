/*
 * Copyright 2025 THE PSI CONSORTIUM
 *
 * SPDX-License-Identifier: Apache-2.0
 */

import { redirect } from "@sveltejs/kit";

export function load() {
    redirect(302, "/open");
}
