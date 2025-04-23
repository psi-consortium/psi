/*
 * Copyright 2025 THE PSI CONSORTIUM
 *
 * SPDX-License-Identifier: Apache-2.0
 */

import { json } from "@sveltejs/kit";
import { serviceTemplates } from "./serviceTemplates";

export async function GET({ url }) {
    const size = parseInt(url.searchParams?.get("size") || "10");
    const offset = parseInt(url.searchParams?.get("offset") || "0");

    const result = serviceTemplates.slice(offset, offset + size);

    const headers = new Headers();
    headers.append("X-Total-Count", serviceTemplates.length.toString());
    headers.append("X-Result-Count", result.length.toString());

    return json(result, {
        headers: headers
    });
}
