/*
 * Copyright 2025 THE PSI CONSORTIUM
 *
 * SPDX-License-Identifier: Apache-2.0
 */

import { v4 as uuid } from "uuid";
import {
    type GeographicAddress,
    type GeographicLocation,
    type Place,
    type PlaceRefOrValue
} from "$lib/domains/mission";

export type IdMapping = { [key: string]: string };

export type PlaceCopy = {
    placeIdMappings: IdMapping;
    places: Array<PlaceRefOrValue>;
};

export function copyPlaces(places: PlaceRefOrValue[] | undefined): PlaceCopy {
    if (!places) {
        return {
            placeIdMappings: {},
            places: []
        };
    }

    const idMappings: IdMapping = {};
    const newPlaces: PlaceRefOrValue[] = places.map((place) => {
        let result = copyPlace(place);
        result.id = uuid();
        idMappings[place?.id ?? "this will never happen"] = result.id;
        return result;
    });

    return {
        places: newPlaces,
        placeIdMappings: idMappings
    };
}

function copyPlace(place: PlaceRefOrValue): PlaceRefOrValue {
    switch (place.type) {
        case "GeographicAddress":
            return copyGeographicAddress(place as GeographicAddress);
        case "GeographicLocation":
            return copyGeographicLocation(place as GeographicLocation);
        default:
            return copyPlaceType(place) as PlaceRefOrValue;
    }
}

function copyPlaceType(place: PlaceRefOrValue): Place {
    return {
        // id: place.id,
        name: place.name,
        baseType: place.baseType,
        type: place.type,
        href: place.href,
        schemaLocation: place.schemaLocation
    };
}

function copyGeographicAddress(ga: GeographicAddress): PlaceRefOrValue {
    const result: GeographicAddress = copyPlaceType(ga as PlaceRefOrValue);
    result.city = ga.city;
    result.country = ga.country;
    result.locality = ga.locality;
    result.postcode = ga.postcode;
    result.stateOrProvince = ga.stateOrProvince;
    result.streetName = ga.streetName;
    result.streetNr = ga.streetNr;
    result.streetNrLast = ga.streetNrLast;
    result.streetNrLastSuffix = ga.streetNrLastSuffix;
    result.streetNrSuffix = ga.streetNrSuffix;
    result.streetSuffix = ga.streetSuffix;
    result.streetType = ga.streetType;
    result.geographicLocation = ga.geographicLocation;
    result.geographicAddressType = ga.geographicAddressType;

    result.type = "GeographicAddress";
    return result as PlaceRefOrValue;
}

function copyGeographicLocation(gl: GeographicLocation): PlaceRefOrValue {
    const result: GeographicLocation = copyPlaceType(gl as PlaceRefOrValue);
    result.geoJson = gl.geoJson;
    result.bbox = gl.bbox;

    result.type = "GeographicLocation";
    return result as PlaceRefOrValue;
}
