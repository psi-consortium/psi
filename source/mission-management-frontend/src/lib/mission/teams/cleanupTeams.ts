/*
 * Copyright 2025 THE PSI CONSORTIUM
 *
 * SPDX-License-Identifier: Apache-2.0
 */

import type { Mission, MissionAsset } from "$lib/domains/mission";
import { TeamProductSchemaType } from "$lib/domains/team";

export function cleanUpTeamAssets(assets: MissionAsset[]): MissionAsset[] {
    const referencedTeamIds = new Set<string>();
    const teamNames: { [key: string]: string } = {};
    assets.forEach((asset) =>
        asset.assetRelationship?.forEach((rel) => {
            if (rel.relationshipType === "Team" && rel.id) {
                referencedTeamIds.add(rel.id);
                if (rel.name) {
                    teamNames[rel.id] = rel.name;
                }
            }
        })
    );

    const availableTeamAssetIds = new Set<string>();
    const unusedTeamAssetIds = new Set<string>();
    assets
        .filter((asset) => asset.targetProductSchema?.type === TeamProductSchemaType)
        .forEach((asset) => {
            if (
                asset.id &&
                (referencedTeamIds.has(asset.id) || availableTeamAssetIds.has(asset.id))
            ) {
                availableTeamAssetIds.add(asset.id);
                referencedTeamIds.delete(asset.id);
            } else {
                asset.id && unusedTeamAssetIds.add(asset.id);
            }
        });

    // Remove unused Team-Assets
    unusedTeamAssetIds.forEach((unusedAssetId) => {
        const index = assets.findIndex((asset) => asset.id === unusedAssetId);
        if (index > -1) {
            assets.splice(index, 1);
        } else {
            // Shouldn't happen
            console.log(`ERROR: Cannot find unusedAssetId ${unusedAssetId} in the MissionAssets.`);
        }
    });

    // Add new Team-Assets
    referencedTeamIds.forEach(async (teamId) => {
        assets.push({
            id: teamId,
            name: teamNames[teamId],
            targetProductSchema: {
                type: TeamProductSchemaType,
                schemaLocation: "./psi-api/schemas/Mission/MissionAsset.schema.json"
            }
        });
    });

    return assets;
}

export function replaceTeamAssets(mission: Mission, newTeamAssets: MissionAsset[]): MissionAsset[] {
    const newTeamAssetIds = newTeamAssets.map((team) => team.id);

    let teamAssets =
        mission.asset?.filter((asset) => asset.targetProductSchema?.type === "Team") || [];

    // Add and replace TeamAssets
    newTeamAssets.forEach((updateRef) => {
        const index = teamAssets.findIndex((item) => item.id === updateRef.id);
        if (index === -1) {
            teamAssets.push(updateRef);
        } else {
            teamAssets[index] = updateRef;
        }
    });

    // Remove Teams that not belong to newTeamAssets:
    teamAssets = teamAssets.filter((ref) => newTeamAssetIds.includes(ref.id));

    // Remove invalid Team-Relationships
    //
    const teamIds = teamAssets.map((team) => team.id);
    const nonTeamAssets =
        mission.asset?.filter((asset) => asset.targetProductSchema?.type !== "Team") || [];
    nonTeamAssets.forEach((asset) => {
        asset.assetRelationship = asset.assetRelationship?.filter(
            (rel) => rel.relationshipType !== "Team" || teamIds.includes(rel.id)
        );
    });

    teamAssets.sort((a, b) => (a.name ?? "").localeCompare(b.name ?? ""));
    teamAssets.push(...nonTeamAssets);
    return teamAssets;
}
