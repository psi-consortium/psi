/*
 * Copyright 2025 THE PSI CONSORTIUM
 *
 * SPDX-License-Identifier: Apache-2.0
 */

import { v4 as uuid } from "uuid";
import {
    type Mission,
    type MissionAsset,
    type MissionAssetRelationship,
    type RelatedPlaceRefOrValue
} from "$lib/domains/mission";
import type { IdMapping } from "../places/copyPlaces";
import type { PlacesMap } from "./mergeServices";

export function copyAssetsByIds(assetIds: string[], fromMission: Mission): MissionAsset[] {
    const newAssets = fromMission.asset?.filter((a) => a.id && assetIds.includes(a.id)) || [];
    return copyAssets(newAssets);
}

export function filterAssetsById(
    assetIds: string[],
    assets: MissionAsset[] | undefined
): MissionAsset[] {
    return assets?.filter((a) => a.id && assetIds.includes(a.id)) || [];
}

export function copyAssets(
    assets: MissionAsset[] | undefined,
    keepTeamIds?: string[],
    places?: PlacesMap,
    placeIdMappings?: IdMapping
): MissionAsset[] {
    if (!assets) {
        return [];
    }

    return assets?.map((ma) => {
        ma.id = uuid();

        // TODO: Shall we keep old AssetRelationships?
        // TODO: Can we add the Mission-/Template-Id? To faster determine whether a reference to a Template still exists.
        const assetRelationships =
            ma.assetRelationship?.filter(
                (ar) =>
                    ar.relationshipType !== "cloneOf" &&
                    (ar.relationshipType !== "Team" || isAllowedTeam(ar, keepTeamIds || []))
            ) || [];
        assetRelationships.push({
            id: ma.id,
            name: ma.name,
            relationshipType: "cloneOf"
        });
        ma.assetRelationship = assetRelationships;

        ma.place = placeIdMappings ? copyPlaceRefs(ma.place, placeIdMappings, places ?? {}) : [];

        return ma;
    });
}

function isAllowedTeam(assetRel: MissionAssetRelationship, teamIds: string[]): boolean {
    return assetRel.relationshipType === "Team" && !!assetRel.id && teamIds.includes(assetRel.id);
}

function copyPlaceRefs(
    placeRefs: RelatedPlaceRefOrValue[] | undefined,
    idMappings: IdMapping,
    places: PlacesMap
): RelatedPlaceRefOrValue[] {
    if (!placeRefs || placeRefs.length === 0) {
        return [];
    }

    return placeRefs.map((placeRef) => {
        const id = idMappings[placeRef.id ?? "unknown"] || "unknown"; // should never happen
        return {
            id,
            href: placeRef.href,
            name: places[id] ? places[id].name : placeRef.name,
            role: placeRef.role,
            baseType: placeRef.baseType,
            schemaLocation: placeRef.schemaLocation,
            type: placeRef.type,
            referredType: placeRef.referredType
        };
    });
}
