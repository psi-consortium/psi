/*
 * Copyright 2025 THE PSI CONSORTIUM
 *
 * SPDX-License-Identifier: Apache-2.0
 */

import { MissionFromJSON, type Mission } from "$lib/domains/mission";
import { OrganizationFromJSON, type Organization } from "$lib/domains/party";
import type { ServiceTemplate } from "$lib/domains/serviceTemplate";
import type { Team } from "$lib/domains/team";
import { fetchData } from "./DataFetch";
import {
    getOrganizationsUrl,
    getOrganizationUrl,
    getPublicMissionsUrl,
    getPublicMissionTemplatesUrl,
    getPublicMissionTemplateUrl,
    getPublicMissionUrl,
    getServiceTemplatesUrl,
    getTeamsUrl
} from "./UrlCompositionPublic";

export async function fetchOrganizations(
    fetch: any,
    queryParams?: {
        [key: string]: string;
    }
): Promise<Organization[]> {
    const raw = await fetchData(fetch, getOrganizationsUrl(queryParams));
    return raw.map((org: any) => {
        return OrganizationFromJSON(org);
    });
}

export async function fetchOrganization(fetch: any, id: string): Promise<Organization> {
    const orgResponse = await fetch(getOrganizationUrl(id));
    const organization = await orgResponse.json();
    return OrganizationFromJSON(organization);
}

export async function fetchServiceTemplates(
    fetch: any,
    queryParams?: {
        [key: string]: string;
    }
): Promise<ServiceTemplate[]> {
    return fetchData(fetch, getServiceTemplatesUrl(queryParams));
}

export async function fetchTeams(
    fetch: any,
    queryParams?: { [key: string]: string }
): Promise<Team[]> {
    return fetchData(fetch, getTeamsUrl(queryParams));
}

// MissionTemplate
//

export async function fetchPublicMissions(
    fetch: any,
    queryParams?: { [key: string]: string }
): Promise<Mission[]> {
    const missionResult = await fetchData(fetch, getPublicMissionsUrl(queryParams));
    return missionResult.map((m: any) => {
        return MissionFromJSON(m);
    });
}

export async function fetchPublicMission(fetch: any, id: string): Promise<Mission> {
    const missionRaw = await fetchData(fetch, getPublicMissionUrl(id));
    return MissionFromJSON(missionRaw);
}

// MissionTemplate
//
export async function fetchPublicMissionTemplates(
    fetch: any,
    queryParams?: { [key: string]: string }
): Promise<Mission[]> {
    const result = await fetchData(fetch, getPublicMissionTemplatesUrl(queryParams));
    return result.map((mt: any) => {
        return MissionFromJSON(mt);
    });
}

export async function fetchPublicMissionTemplate(fetch: any, id: string): Promise<Mission> {
    const raw = await fetchData(fetch, getPublicMissionTemplateUrl(id));
    return MissionFromJSON(raw);
}
