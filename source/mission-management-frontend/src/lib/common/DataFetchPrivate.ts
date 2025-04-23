/*
 * Copyright 2025 THE PSI CONSORTIUM
 *
 * SPDX-License-Identifier: Apache-2.0
 */

import {
    MissionFromJSON,
    type Mission,
    type MissionCreate,
    type MissionUpdate
} from "$lib/domains/mission";
import { fetchData } from "./DataFetch";
import {
    getMissionsUrl,
    getMissionUrl,
    getMissionTemplatesUrl,
    getMissionTemplateUrl
} from "./UrlComposition";

// MissionTemplate
//
export async function fetchMissions(
    fetch: any,
    queryParams?: { [key: string]: string }
): Promise<Mission[]> {
    const missionResult = await fetchData(fetch, getMissionsUrl(queryParams)) || [];
    return missionResult.map((m: any) => {
        return MissionFromJSON(m);
    });
}

export async function fetchMission(fetch: any, id: string): Promise<Mission> {
    const missionRaw = await fetchData(fetch, getMissionUrl(id));
    return MissionFromJSON(missionRaw);
}

export async function postMission(fetch: any, missionCreate: MissionCreate): Promise<Response> {
    return await fetch(getMissionsUrl(), {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(missionCreate)
    });
}

export async function patchMission(
    fetch: any,
    id: string,
    missionUpdate: MissionUpdate
): Promise<Response> {
    const url = getMissionUrl(id);
    return await fetch(url, {
        method: "PATCH",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(missionUpdate)
    });
}

export async function deleteMission(fetch: any, missionId: string): Promise<Response> {
    return await fetch(getMissionUrl(missionId), {
        method: "DELETE",
        headers: {}
    });
}

// MissionTemplate
//
export async function fetchMissionTemplates(
    fetch: any,
    queryParams?: { [key: string]: string }
): Promise<Mission[]> {
    const result = await fetchData(fetch, getMissionTemplatesUrl(queryParams)) || [];
    return result.map((mt: any) => {
        return MissionFromJSON(mt);
    });
}

export async function fetchMissionTemplate(fetch: any, id: string): Promise<Mission> {
    const raw = await fetchData(fetch, getMissionTemplateUrl(id));
    return MissionFromJSON(raw);
}

export async function postMissionTemplate(
    fetch: any,
    templateCreate: MissionCreate
): Promise<Response> {
    return await fetch(getMissionTemplatesUrl(), {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(templateCreate)
    });
}

export async function patchMissionTemplate(
    fetch: any,
    id: string,
    missionUpdate: MissionUpdate
): Promise<Response> {
    const url = getMissionTemplateUrl(id);
    return await fetch(url, {
        method: "PATCH",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(missionUpdate)
    });
}

export async function deleteMissionTemplate(fetch: any, templateId: string): Promise<Response> {
    return await fetch(getMissionTemplateUrl(templateId), {
        method: "DELETE",
        headers: {}
    });
}
