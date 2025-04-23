/*
 * Copyright 2025 THE PSI CONSORTIUM
 *
 * SPDX-License-Identifier: Apache-2.0
 */

import { json } from "@sveltejs/kit";
import { organizations } from "./organizations";

export async function GET({ url }) {
    const size = parseInt(url.searchParams?.get("size") || "10");
    const offset = parseInt(url.searchParams?.get("offset") || "0");

    const result = organizations.slice(offset, offset + size).map((org) => {
        return {
            id: org.id,
            name: org.name,
            nameType: org.nameType,
            tradingName: org.tradingName
        };
    });

    const headers = new Headers();
    headers.append("X-Total-Count", organizations.length.toString());
    headers.append("X-Result-Count", result.length.toString());

    return json(result, {
        headers: headers
    });
}
