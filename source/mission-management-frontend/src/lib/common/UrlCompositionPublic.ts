/*
 * Copyright 2025 THE PSI CONSORTIUM
 *
 * SPDX-License-Identifier: Apache-2.0
 */

import { env } from "$env/dynamic/public";

export function getPublicMissionsUrl(queryParams?: { [key: string]: string }): URL {
    return composeUrl(env.PUBLIC_MISSION_MANAGEMENT_URL, "", queryParams);
}
export function getPublicMissionUrl(id: string, queryParams?: { [key: string]: string }): URL {
    return composeUrl(env.PUBLIC_MISSION_MANAGEMENT_URL, id, queryParams);
}

export function getPublicMissionTemplatesUrl(queryParams?: { [key: string]: string }): URL {
    return composeUrl(env.PUBLIC_MISSIONTEMPLATE_MANAGEMENT_URL, "", queryParams);
}
export function getPublicMissionTemplateUrl(
    id: string,
    queryParams?: { [key: string]: string }
): URL {
    return composeUrl(env.PUBLIC_MISSIONTEMPLATE_MANAGEMENT_URL, id, queryParams);
}

export function getServiceTemplatesUrl(queryParams?: { [key: string]: string }): URL {
    return composeUrl(env.PUBLIC_SERVICETEMPLATE_URL, "", queryParams);
}

export function getTeamsUrl(queryParams?: { [key: string]: string }): URL {
    return composeUrl(env.PUBLIC_TEAM_URL, "", queryParams);
}

export function getOrganizationsUrl(queryParams?: { [key: string]: string }): URL {
    return composeUrl(env.PUBLIC_PARTY_MANAGEMENT_URL, "", queryParams);
}
export function getOrganizationUrl(id: string, queryParams?: { [key: string]: string }): URL {
    return composeUrl(env.PUBLIC_PARTY_MANAGEMENT_URL, id, queryParams);
}

function composeUrl(base: string, extension: string, params: { [key: string]: string } = {}) {
    if (extension?.length && !extension.startsWith("/")) {
        extension = "/" + extension;
    }
    const url = new URL(base + extension);
    Object.keys(params).forEach((key) => url.searchParams.append(key, params[key]));

    console.log("URL (public):", url.href);

    return url;
}
