/* tslint:disable */
/* eslint-disable */
/**
 * Mission Management
 * ## PSID API Reference: PSID002 - Mission Management  The Mission API enables customers to create, update and delete missions. A mission is composed of one or more teams, their areas of operation and communication needs. The data can be used to issue an inquiry for matching products (see PSID001).
 *
 * The version of the OpenAPI document: 1.0.0
 *
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

import { mapValues } from "../runtime";
/**
 * A GeoJSON MultiLineString.
 * @export
 * @interface MultiLineString
 */
export interface MultiLineString {
    /**
     *
     * @type {string}
     * @memberof MultiLineString
     */
    type?: string;
    /**
     *
     * @type {Array<Array<Array<number>>>}
     * @memberof MultiLineString
     */
    coordinates?: Array<Array<Array<number>>>;
}

/**
 * Check if a given object implements the MultiLineString interface.
 */
export function instanceOfMultiLineString(value: object): value is MultiLineString {
    return true;
}

export function MultiLineStringFromJSON(json: any): MultiLineString {
    return MultiLineStringFromJSONTyped(json, false);
}

export function MultiLineStringFromJSONTyped(
    json: any,
    ignoreDiscriminator: boolean
): MultiLineString {
    if (json == null) {
        return json;
    }
    return {
        type: json["type"] == null ? undefined : json["type"],
        coordinates: json["coordinates"] == null ? undefined : json["coordinates"]
    };
}

export function MultiLineStringToJSON(json: any): MultiLineString {
    return MultiLineStringToJSONTyped(json, false);
}

export function MultiLineStringToJSONTyped(
    value?: MultiLineString | null,
    ignoreDiscriminator: boolean = false
): any {
    if (value == null) {
        return value;
    }

    return {
        type: value["type"],
        coordinates: value["coordinates"]
    };
}
