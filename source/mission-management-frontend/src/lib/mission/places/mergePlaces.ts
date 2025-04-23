/*
 * Copyright 2025 THE PSI CONSORTIUM
 *
 * SPDX-License-Identifier: Apache-2.0
 */

import type { PlaceRefOrValue } from "$lib/domains/mission";
import { copyPlaces, type IdMapping } from "./copyPlaces";

export function mergePlaces(
    from: PlaceRefOrValue[] | undefined,
    copyPlaceIds: string[],
    to: PlaceRefOrValue[]
): IdMapping {
    if (!from) {
        return {};
    }

    const selectedPlaces = from.filter((p) => p.id && copyPlaceIds.includes(p.id)) || [];
    const { places: newPlaces, placeIdMappings } = copyPlaces(selectedPlaces);

    // Deal with name duplicates
    for (const place of newPlaces) {
        const orgNames = to.map((p) => p.name ?? "");
        if (orgNames.includes(place.name ?? "")) {
            place.name = getUniqueName(place.name ?? "", orgNames, 1);
        }
    }

    to.push(...newPlaces);

    return placeIdMappings;
}

function getUniqueName(prefix: string, orgNames: string[], counter: number): string {
    const newName = prefix + "_" + counter;
    if (orgNames.includes(newName)) {
        return getUniqueName(prefix, orgNames, ++counter);
    }
    return newName;
}
