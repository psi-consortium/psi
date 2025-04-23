/*
 * Copyright 2025 THE PSI CONSORTIUM
 *
 * SPDX-License-Identifier: Apache-2.0
 */

import { env } from "$env/dynamic/private";

export function getMissionsUrl(queryParams?: { [key: string]: string }): URL {
    return composeUrl(env.MISSION_MANAGEMENT_URL, "", queryParams);
}
export function getMissionUrl(id: string, queryParams?: { [key: string]: string }): URL {
    return composeUrl(env.MISSION_MANAGEMENT_URL, id, queryParams);
}

export function getMissionTemplatesUrl(queryParams?: { [key: string]: string }): URL {
    return composeUrl(env.MISSIONTEMPLATE_MANAGEMENT_URL, "", queryParams);
}
export function getMissionTemplateUrl(id: string, queryParams?: { [key: string]: string }): URL {
    return composeUrl(env.MISSIONTEMPLATE_MANAGEMENT_URL, id, queryParams);
}

function composeUrl(base: string, extension: string, params: { [key: string]: string } = {}) {
    if (extension?.length && !extension.startsWith("/")) {
        extension = "/" + extension;
    }
    const url = new URL(base + extension);
    Object.keys(params).forEach((key) => url.searchParams.append(key, params[key]));

    console.log("URL (private):", url.href);

    return url;
}
