/*
 * Copyright 2025 THE PSI CONSORTIUM
 *
 * SPDX-License-Identifier: Apache-2.0
 */

import {
    MissionCreateToJSON,
    MissionStatusTypeFromJSON,
    MissionUpdateToJSON,
    type Mission,
    type PlaceRefOrValue
} from "$lib/domains/mission";
import { redirect, type Actions } from "@sveltejs/kit";
import type { PageServerLoad } from "./$types";

import {
    createMissionAsset,
    deleteAsset,
    updateByAsset,
    copyAssets,
    removeInvalidPlaceReferences,
    mergeServices
} from "$lib/mission/assets";
import { getMissionTemplateUrl } from "$lib/common/UrlComposition";
import { fetchServiceTemplates } from "$lib/common/DataFetchPublic";
import {
    fetchMission,
    postMission,
    fetchMissionTemplate,
    patchMissionTemplate,
    deleteMissionTemplate
} from "$lib/common/DataFetchPrivate";

import { toDate } from "$lib/common/FormData";
import { getPublicMissionTemplateUrl } from "$lib/common/UrlCompositionPublic";
import { copyPlaces } from "$lib/mission/places/copyPlaces";
import { mergePlaces } from "$lib/mission/places/mergePlaces";
import { mergeTeams } from "$lib/mission/teams/mergeTeams";

// TODO: Improve error handling
export const load: PageServerLoad = async ({ fetch, params }) => {
    const id = params.id;

    const missionTemplate = await getTemplateById(fetch, id);
    const serviceTemplates = await fetchServiceTemplates(fetch, { size: "100" });

    return { missionTemplate, serviceTemplates };
};

const getTemplateById = async (fetch: any, id: string): Promise<Mission> => {
    return fetchMissionTemplate(fetch, id);
};

export const actions = {
    updateMissionTemplate: async ({ request }) => {
        const data = await request.formData();
        const id = data.get("id") as string;

        console.log("Action 'MissionTemplate updateMissionTemplate' with TemplateId", id);

        const templateUpdate = MissionUpdateToJSON({
            type: "Mission",
            name: data.get("name"),
            timeframe: {
                startDateTime: toDate(data.get("startDate")),
                endDateTime: toDate(data.get("endDate"))
            },
            description: data.get("description"),
            category: data.get("category")
        });

        const status = data.get("status");
        if (status) {
            templateUpdate.status = MissionStatusTypeFromJSON(status);
        }

        try {
            const response = await patchMissionTemplate(fetch, id, templateUpdate);

            if (!response.ok) {
                const message = await response.text();
                console.log("Error when trying to patch the mission template", id, message);
                return {
                    status: response.status,
                    body: { message }
                };
            }

            return {
                status: 200
            };
        } catch (error) {
            console.log("Error in Action 'templates/[id] - updateMissionTemplate':", error);
            return {
                status: 500,
                body: { message: error.message }
            };
        }
    },

    deleteMissionTemplate: async ({ fetch, request }) => {
        const data = await request.formData();
        const templateId = data.get("id") as string;

        console.log("Action 'MissionTemplate deleteMissionTemplate' with templateId", templateId);

        try {
            const response = await deleteMissionTemplate(fetch, templateId);

            if (!response.ok) {
                console.log("Error when trying to delete the mission", templateId);
                return {
                    status: response.status,
                    body: { message: await response.text() }
                };
            }
        } catch (error) {
            console.log("Error in Action 'templates/[id] - deleteMissionTemplate':", error);
            return {
                status: 500,
                body: { message: error.message }
            };
        }
        throw redirect(303, "/templates");
    },

    createMission: async ({ request }) => {
        const data = await request.formData();
        const templateId = data.get("id") as string;

        console.log("Action 'MissionTemplate createMission' for templateId", templateId);

        if (!templateId) {
            return { success: false, error: "Cannot create a Mission from an unknown TemplateId." };
        }
        const fromTemplate = await fetchMissionTemplate(fetch, templateId);

        const { places, placeIdMappings } = copyPlaces(fromTemplate.place);
        const assets = copyAssets(fromTemplate.asset, placeIdMappings);

        const missionRelationship = [
            {
                id: fromTemplate.id,
                name: fromTemplate.name,
                href: fromTemplate.href,
                relationshipType: "template"
            }
        ];

        const missionCreate = MissionCreateToJSON({
            type: "Mission",
            name: data.get("name"),
            timeframe: {
                startDateTime: toDate(data.get("startDate")),
                endDateTime: toDate(data.get("endDate"))
            },
            description: data.get("description"),
            category: data.get("category"),
            asset: assets,
            place: places,
            missionRelationship
        });

        let missionId;
        try {
            const response = await postMission(fetch, missionCreate);
            const res = await response.json();
            missionId = res.id;

            if (!response.ok) {
                throw new Error(`Error Status: ${res.status}, Error Message: ${res.message}`);
            }
        } catch (error) {
            console.log("Error in Action 'templates/[id] - addMission':", error);
            return { success: false, error: error.message };
        }

        throw redirect(303, "/missions/" + missionId);
    },

    addService: async ({ fetch, request }) => {
        const formData = await request.formData();

        const asset = createMissionAsset(formData);
        if (asset === null) {
            return {
                status: 400,
                body: { message: "addService: Couldn't find any FormData in the request." }
            };
        }

        const missionId = formData.get("missionId") as string;
        const mission = await fetchMissionTemplate(fetch, missionId);

        const assets = updateByAsset(mission, asset);

        try {
            const response = await patchMissionTemplate(
                fetch,
                missionId,
                MissionUpdateToJSON({
                    type: "Mission",
                    asset: assets
                })
            );

            if (!response.ok) {
                const message = await response.text();
                const url = getMissionTemplateUrl(missionId);
                console.log("Error when patching the mission", url, response.status, message);
                return {
                    status: response.status,
                    body: { message: message }
                };
            }

            return {
                status: 200
            };
        } catch (error) {
            console.log("Error in Action 'templates/[id] - addService':", error);
            return {
                status: 500,
                body: { message: error.message }
            };
        }
    },

    deleteService: async ({ request }) => {
        const formData = await request.formData();
        const missionId = formData.get("missionId") as string;
        const assetId = formData.get("id") as string;

        const mission = await fetchMissionTemplate(fetch, missionId);
        const assets = deleteAsset(mission, assetId);

        try {
            const response = await patchMissionTemplate(
                fetch,
                missionId,
                MissionUpdateToJSON({
                    type: "Mission",
                    asset: assets
                })
            );

            if (!response.ok) {
                const message = await response.text();
                const url = getMissionTemplateUrl(missionId);
                console.log("Error when patching the mission", url, response.status, message);
                return {
                    status: response.status,
                    body: { message: message }
                };
            }

            return {
                status: 200
            };
        } catch (error) {
            console.log("Error in Action 'templates/[id] - deleteService':", error);
            return {
                status: 500,
                body: { message: error.message }
            };
        }
    },

    copyServices: async ({ request }) => {
        const formData = await request.formData();

        const missionId = formData.get("missionId") as string;
        const fromMissionId = formData.get("fromMissionId") as string;
        const fromMissionType = formData.get("fromMissionType") as string;

        console.log(
            `Action 'MissionTemplate copyServices' with MissionId ${missionId} from ${fromMissionType} ${fromMissionId}`
        );

        const missionTemplate = await fetchMissionTemplate(fetch, missionId);

        let fromMission: Mission;
        if (fromMissionType === "MissionTemplate") {
            fromMission = await fetchMissionTemplate(fetch, fromMissionId);
        } else {
            fromMission = await fetchMission(fetch, fromMissionId);
        }

        const {
            assetIds: copyAssetIds,
            teamIds: copyTeamIds,
            placeIds: copyPlaceIds
        } = selectionsToGroupedEntityIds(formData);

        const place = missionTemplate.place || [];
        const placeIdMappings = mergePlaces(fromMission.place, copyPlaceIds, place);

        const asset = missionTemplate.asset || [];
        mergeTeams(fromMission.asset, copyTeamIds, asset);
        mergeServices(fromMission.asset, copyAssetIds, asset, placeIdMappings, place);

        // Mission Relationship
        const missionRelationship = missionTemplate.missionRelationship || [];
        // avoid repetitions
        if (missionRelationship.filter((rel) => rel.id === fromMission.id).length === 0) {
            missionRelationship.push({
                id: fromMission.id,
                name: fromMission.name,
                href: fromMission.href,
                relationshipType: "template"
            });
        }

        const missionUpdate = MissionUpdateToJSON({
            type: "Mission",
            asset,
            place,
            missionRelationship
        });

        try {
            const response = await patchMissionTemplate(fetch, missionId, missionUpdate);

            if (!response.ok) {
                const message = await response.text();
                const url = getMissionTemplateUrl(missionId);
                console.log("Error when patching the mission", url, response.status, message);
                return {
                    status: response.status,
                    body: { message: message }
                };
            }
        } catch (error) {
            console.log("Error in Action CopyServices:", error);
            return {
                status: 500,
                body: { message: error.message }
            };
        }

        throw redirect(303, "/templates/" + missionTemplate.id);
    },

    updatePlace: async ({ request }) => {
        const formData = await request.formData();
        const templateId = formData.get("id") as string;
        const place: Array<PlaceRefOrValue> = JSON.parse(formData.get("place") as string);

        console.log("Action 'MissionTemplate updatePlace' for id", templateId);

        const template = await fetchMissionTemplate(fetch, templateId);
        const asset = removeInvalidPlaceReferences(place, template.asset || []);

        const templateUpdate = MissionUpdateToJSON({
            type: "Mission",
            place,
            asset
        });

        try {
            const response = await patchMissionTemplate(fetch, templateId, templateUpdate);

            if (!response.ok) {
                const message = await response.text();
                const url = getPublicMissionTemplateUrl(templateId);
                console.log("Error when patching the mission", url, response.status, message);
                return {
                    status: response.status,
                    body: { message: message }
                };
            }

            const updatedMission = await response.json();

            return {
                status: 200,
                body: updatedMission
            };
        } catch (error) {
            console.log("Error in Action 'template/[id] - updatePlace':", error);
            return {
                status: 500,
                body: { message: error.message }
            };
        }
    }
} satisfies Actions;

function selectionsToGroupedEntityIds(formData: FormData): {
    assetIds: string[];
    teamIds: string[];
    placeIds: string[];
} {
    const pairs = Array.from(formData.entries())
        .filter((pair) => pair[1] === "on")
        .map((pair) => {
            const str = pair[0].split(":");
            return {
                type: str[0],
                id: str[1]
            };
        });

    const assetIds: string[] = [];
    const teamIds: string[] = [];
    const placeIds: string[] = [];
    pairs.forEach((pair) => {
        switch (pair.type) {
            case "service":
                assetIds.push(pair.id);
                break;
            case "team":
                teamIds.push(pair.id);
                break;
            case "place":
                placeIds.push(pair.id);
                break;
            default:
                console.log("Error, unknown Asset type:", pair.type);
        }
    });

    return {
        assetIds,
        teamIds,
        placeIds
    };
}
