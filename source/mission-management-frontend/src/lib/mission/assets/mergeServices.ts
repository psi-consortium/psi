/*
 * Copyright 2025 THE PSI CONSORTIUM
 *
 * SPDX-License-Identifier: Apache-2.0
 */

import type { MissionAsset, PlaceRefOrValue } from "$lib/domains/mission";
import { copyAssets, filterAssetsById } from "../assets";
import type { IdMapping } from "../places/copyPlaces";
import { getTeamIds } from "../teams/getTeams";

export type PlacesMap = { [key: string]: PlaceRefOrValue };

export function mergeServices(
    from: MissionAsset[] | undefined,
    copyServiceIds: string[],
    to: MissionAsset[],
    placeIdMappings: IdMapping,
    places: PlaceRefOrValue[]
) {
    if (!from) {
        return;
    }

    const newAssets = copyAssets(
        filterAssetsById(copyServiceIds, from),
        getTeamIds(to),
        getPlacesMap(places),
        placeIdMappings
    );

    if (!newAssets || newAssets.length === 0) {
        console.log("mergeService: No services found for copying. Stopping!");
        return;
    }
    to.push(...newAssets);
}

function getPlacesMap(places: PlaceRefOrValue[]): PlacesMap {
    return places.reduce((result, place): PlacesMap => {
        result[place.id ?? "unknown"] = place;
        return result;
    }, {} as PlacesMap);
}
