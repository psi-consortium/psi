/*
 * Copyright 2025 THE PSI CONSORTIUM
 *
 * SPDX-License-Identifier: Apache-2.0
 */

import { redirect } from "@sveltejs/kit";
import type { Actions } from "./$types";
import { MissionCreateToJSON, MissionStatusType } from "$lib/domains/mission";
import { getMissionsUrl, getMissionTemplatesUrl } from "$lib/common/UrlComposition";
import { toDate } from "$lib/common/FormData";
import { postMission, postMissionTemplate } from "$lib/common/DataFetchPrivate";

export const actions = {
    createMission: async ({ request }) => {
        const data = await request.formData();

        console.log("Action 'Mission updateMission' with Mission.name", data.get("name"));

        const missionCreate = MissionCreateToJSON({
            type: "Mission",
            name: data.get("name"),
            timeframe: {
                startDateTime: toDate(data.get("startDate")),
                endDateTime: toDate(data.get("endDate"))
            },
            description: data.get("description"),
            category: data.get("category"),
            status: data.get("status") || MissionStatusType.Draft
        });

        let newId: string;

        try {
            const response = await postMission(fetch, missionCreate);

            if (!response.ok) {
                const message = await response.text();
                const url = getMissionsUrl();
                console.log("Error when creating a Mission ", url, response.status, message);
                throw new Error(`Error Status: ${response.status}, Error Message: ${message}`);
            }

            const res = await response.json();
            newId = res.id;
        } catch (error) {
            console.error(error.message);
            return { success: false, error: error.message };
        }
        if (newId) {
            throw redirect(303, `/missions/${newId}`);
        }
    },

    addMissionTemplate: async ({ request }) => {
        const data = await request.formData();

        const templateCreate = MissionCreateToJSON({
            type: "Mission",
            name: data.get("name"),
            timeframe: {
                startDateTime: toDate(data.get("startDate")),
                endDateTime: toDate(data.get("endDate"))
            },
            description: data.get("description"),
            category: data.get("category"),
            status: data.get("status") || MissionStatusType.Draft
        });
        let newId;

        try {
            const response = await postMissionTemplate(fetch, templateCreate);

            if (!response.ok) {
                const message = await response.text();
                const url = getMissionTemplatesUrl();
                console.log(
                    "Error when creating a MissionTemplate ",
                    url,
                    response.status,
                    message
                );
                throw new Error(`Error Status: ${response.status}, Error Message: ${message}`);
            }

            const res = await response.json();
            newId = res.id;
        } catch (error) {
            console.error(error.message);
            return { success: false, error: error.message };
        }
        if (newId) {
            throw redirect(303, `/templates/${newId}`);
        }
    }
} satisfies Actions;
