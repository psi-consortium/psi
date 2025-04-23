/*
 * Copyright 2025 THE PSI CONSORTIUM
 *
 * SPDX-License-Identifier: Apache-2.0
 */

import { json } from "@sveltejs/kit";
import { findServiceTemplate } from "../serviceTemplates.js";

export const GET = ({ params }) => {
    const org = findServiceTemplate(params.id);
    if (org) {
        return json(org);
    } else {
        return new Response(
            JSON.stringify({
                error: "notfound",
                message: "Not Found"
            }),
            {
                status: 404
            }
        );
    }
};
