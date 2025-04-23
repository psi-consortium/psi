/*
 * Copyright 2025 THE PSI CONSORTIUM
 *
 * SPDX-License-Identifier: Apache-2.0
 */

import { json } from "@sveltejs/kit";
import { teams } from "./teams";

export async function GET({ url }) {
    const size = parseInt(url.searchParams?.get("size") || "10");
    const offset = parseInt(url.searchParams?.get("offset") || "0");

    const result = teams.slice(offset, offset + size).map((team) => {
        return {
            id: team.id,
            name: team.name
        };
    });

    const headers = new Headers();
    headers.append("X-Total-Count", teams.length.toString());
    headers.append("X-Result-Count", result.length.toString());

    return json(result, {
        headers: headers
    });
}
