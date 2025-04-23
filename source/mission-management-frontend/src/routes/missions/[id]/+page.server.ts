/*
 * Copyright 2025 THE PSI CONSORTIUM
 *
 * SPDX-License-Identifier: Apache-2.0
 */

import {
    MissionUpdateToJSON,
    type Mission,
    type MissionAsset,
    type PlaceRefOrValue
} from "$lib/domains/mission";
import type { PageServerLoad, Actions } from "./$types";
import { redirect } from "@sveltejs/kit";

import {
    createMissionAsset,
    deleteAsset,
    getIdAndName,
    updateByAsset,
    mergeServices,
    removeInvalidPlaceReferences
} from "$lib/mission/assets";
import { replaceTeamAssets } from "$lib/mission/teams/cleanupTeams";
import { TeamProductSchemaType } from "$lib/domains/team";
import {
    fetchMission,
    patchMission,
    fetchMissions,
    fetchMissionTemplate,
    deleteMission
} from "$lib/common/DataFetchPrivate";
import { fetchServiceTemplates, fetchTeams } from "$lib/common/DataFetchPublic";
import { getPublicMissionUrl } from "$lib/common/UrlCompositionPublic";
import { toDate } from "$lib/common/FormData";
import { mergePlaces } from "$lib/mission/places/mergePlaces";
import { mergeTeams } from "$lib/mission/teams/mergeTeams";

// TODO: Improve error handling
export const load: PageServerLoad = async ({ fetch, params }) => {
    const missionId = params.id;

    const mission = await fetchMission(fetch, missionId);
    const allMissions = await fetchMissions(fetch, { limit: "10" });
    const serviceTemplates = await fetchServiceTemplates(fetch, { size: "100" });
    const teams = await fetchTeams(fetch, { size: "100" });

    return { allMissions, mission, serviceTemplates, teams };
};

export const actions = {
    updateMission: async ({ request }) => {
        const data = await request.formData();
        const missionId = data.get("id") as string;

        console.log("Action 'Mission updateMission' with MissionId", missionId);

        const missionUpdate = MissionUpdateToJSON({
            type: "Mission",
            name: data.get("name"),
            timeframe: {
                startDateTime: toDate(data.get("startDate")),
                endDateTime: toDate(data.get("endDate"))
            },
            description: data.get("description"),
            category: data.get("category"),
            status: data.get("status")
        });

        try {
            const response = await patchMission(fetch, missionId, missionUpdate);

            if (!response.ok) {
                const message = await response.text();
                console.log("Error when trying to patch the mission", missionId, message);
                return {
                    status: response.status,
                    body: { message }
                };
            }

            return {
                status: response.status
            };
        } catch (error) {
            console.log("Error in Action 'missions/[id] - updateMission':", error);
            return {
                status: 500,
                body: { message: error.message }
            };
        }
    },

    deleteMission: async ({ fetch, request }) => {
        const data = await request.formData();
        const missionId = data.get("id") as string;

        console.log("Action 'Mission deleteMission' with MissionId", missionId);

        try {
            const response = await deleteMission(fetch, missionId);

            if (!response.ok) {
                console.log("Error when trying to delete the mission", missionId);
                return {
                    status: response.status,
                    body: { message: await response.text() }
                };
            }
        } catch (error) {
            console.log("Error in Action 'missions/[id] - deleteMission':", error);
            return {
                status: 500,
                body: { message: error.message }
            };
        }
        throw redirect(303, "/missions");
    },

    updatePlace: async ({ request }) => {
        const formData = await request.formData();
        const missionId = formData.get("id") as string;
        const place: Array<PlaceRefOrValue> = JSON.parse(formData.get("place") as string);

        console.log("Action 'Mission updatePlace' for MissionId", missionId);

        const mission = await fetchMission(fetch, missionId);
        const asset = removeInvalidPlaceReferences(place, mission.asset || []);

        const missionUpdate = MissionUpdateToJSON({
            type: "Mission",
            place,
            asset
        });

        try {
            const response = await patchMission(fetch, missionId, missionUpdate);

            if (!response.ok) {
                const message = await response.text();
                const url = getPublicMissionUrl(missionId);
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
            console.log("Error in Action 'missions/[id] - updatePlace':", error);
            return {
                status: 500,
                body: { message: error.message }
            };
        }
    },

    addTeams: async ({ fetch, request }) => {
        const formData = await request.formData();

        const missionId = formData.get("missionId") as string;
        const assignedTeamIds = formData.getAll("teams") as string[];

        console.log("Action 'Mission addTeams' for MissionId", missionId);

        const teamAssets: MissionAsset[] = assignedTeamIds.map((teamId) => {
            const { id, name } = getIdAndName(teamId);
            return {
                id: id,
                name: name,
                targetProductSchema: {
                    type: TeamProductSchemaType,
                    schemaLocation: "./psi-api/schemas/Mission/MissionAsset.schema.json"
                }
            };
        });

        const mission = await fetchMission(fetch, missionId);
        const assets = replaceTeamAssets(mission, teamAssets);

        try {
            const response = await patchMission(
                fetch,
                missionId,
                MissionUpdateToJSON({
                    type: "Mission",
                    asset: assets
                })
            );

            if (!response.ok) {
                const message = await response.text();
                const url = getPublicMissionUrl(missionId);
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
            console.log("Error in Action 'missions/[id] - addTeams':", error);
            return {
                status: 500,
                body: { message: error.message }
            };
        }
    },

    addService: async ({ fetch, request }) => {
        const formData = await request.formData();
        const missionId = formData.get("missionId") as string;
        const mission = await fetchMission(fetch, missionId);

        console.log(
            `Action 'Mission addService' for MissionId ${missionId} from Mission ${mission.id}`
        );

        const asset = createMissionAsset(formData);
        if (asset === null) {
            return {
                status: 400,
                body: { message: "addService: Couldn't find any FormData in the request." }
            };
        }

        const assets = updateByAsset(mission, asset);

        try {
            const response = await patchMission(
                fetch,
                missionId,
                MissionUpdateToJSON({
                    type: "Mission",
                    asset: assets
                })
            );

            if (!response.ok) {
                const message = await response.text();
                const url = getPublicMissionUrl(missionId);
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
            console.log("Error in Action 'missions/[id] - addService':", error);
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

        console.log("Action 'Mission deleteService' for MissionId", missionId, " AssetId", assetId);

        const mission = await fetchMission(fetch, missionId);
        const assets = deleteAsset(mission, assetId);

        try {
            const response = await patchMission(
                fetch,
                missionId,
                MissionUpdateToJSON({
                    type: "Mission",
                    asset: assets
                })
            );

            if (!response.ok) {
                const message = await response.text();
                const url = getPublicMissionUrl(missionId);
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
            console.log("Error in Action 'missions/[id] - deleteService':", error);
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
        const fromMissionType = (formData.get("fromMissionType") as string) || "Mission";

        console.log(
            `Action 'Mission copyServices' with MissionId ${missionId} from ${fromMissionType} ${fromMissionId}`
        );

        const mission = await fetchMission(fetch, missionId);

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

        const place = mission.place || [];
        const placeIdMappings = mergePlaces(fromMission.place, copyPlaceIds, place);

        const asset = mission.asset || [];
        mergeTeams(fromMission.asset, copyTeamIds, asset);
        mergeServices(fromMission.asset, copyAssetIds, asset, placeIdMappings, place);

        // Mission Relationship
        const missionRelationship = mission.missionRelationship || [];
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
            const response = await patchMission(fetch, missionId, missionUpdate);

            if (!response.ok) {
                const message = await response.text();
                const url = getPublicMissionUrl(missionId);
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

        throw redirect(303, "/missions/" + mission.id);
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
