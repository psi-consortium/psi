/*
 * Copyright 2025 THE PSI CONSORTIUM
 *
 * SPDX-License-Identifier: Apache-2.0
 */

import type { MissionAsset, PlaceRefOrValue } from "$lib/domains/mission";

export function removeInvalidPlaceReferences(
    places: PlaceRefOrValue[],
    assets: MissionAsset[]
): MissionAsset[] {
    const placeIds = places.map((place) => place.id);
    assets.forEach((asset) => {
        asset.place = asset.place?.filter((placeRef) => placeIds.includes(placeRef.id));
    });
    return assets;
}
