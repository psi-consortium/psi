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
 * A period of time relative to another.
 * @export
 * @interface RelativeTimePeriod
 */
export interface RelativeTimePeriod {
    /**
     * End of the time period, using IETC-RFC-3339 format
     * @type {Date}
     * @memberof RelativeTimePeriod
     */
    endDateTime?: Date;
    /**
     * Start of the time period, using IETC-RFC-3339 format
     * @type {Date}
     * @memberof RelativeTimePeriod
     */
    startDateTime?: Date;
    /**
     * Relative start of the time period, using IETC-RFC-3339 duration format.
     * @type {string}
     * @memberof RelativeTimePeriod
     */
    startOffset?: string;
    /**
     * Reference for startDateTime, e.g. 'mission.startDateTime', 'mission.endDateTime', 'self.endDateTime' or 'asset[1234].startTime'.
     * @type {string}
     * @memberof RelativeTimePeriod
     */
    startAnchor?: string;
    /**
     * Relative end of the time period, using IETC-RFC-3339 duration format.
     * @type {string}
     * @memberof RelativeTimePeriod
     */
    endOffset?: string;
    /**
     * Reference for endDateTime, e.g. 'mission.startDateTime', 'mission.endDateTime', 'self.startDateTime' or 'asset[1234].endTime'.
     * @type {string}
     * @memberof RelativeTimePeriod
     */
    endAnchor?: string;
}

/**
 * Check if a given object implements the RelativeTimePeriod interface.
 */
export function instanceOfRelativeTimePeriod(value: object): value is RelativeTimePeriod {
    return true;
}

export function RelativeTimePeriodFromJSON(json: any): RelativeTimePeriod {
    return RelativeTimePeriodFromJSONTyped(json, false);
}

export function RelativeTimePeriodFromJSONTyped(
    json: any,
    ignoreDiscriminator: boolean
): RelativeTimePeriod {
    if (json == null) {
        return json;
    }
    return {
        endDateTime: json["endDateTime"] == null ? undefined : new Date(json["endDateTime"]),
        startDateTime: json["startDateTime"] == null ? undefined : new Date(json["startDateTime"]),
        startOffset: json["startOffset"] == null ? undefined : json["startOffset"],
        startAnchor: json["startAnchor"] == null ? undefined : json["startAnchor"],
        endOffset: json["endOffset"] == null ? undefined : json["endOffset"],
        endAnchor: json["endAnchor"] == null ? undefined : json["endAnchor"]
    };
}

export function RelativeTimePeriodToJSON(json: any): RelativeTimePeriod {
    return RelativeTimePeriodToJSONTyped(json, false);
}

export function RelativeTimePeriodToJSONTyped(
    value?: RelativeTimePeriod | null,
    ignoreDiscriminator: boolean = false
): any {
    if (value == null) {
        return value;
    }

    return {
        endDateTime: value["endDateTime"] == null ? undefined : value["endDateTime"].toISOString(),
        startDateTime:
            value["startDateTime"] == null ? undefined : value["startDateTime"].toISOString(),
        startOffset: value["startOffset"],
        startAnchor: value["startAnchor"],
        endOffset: value["endOffset"],
        endAnchor: value["endAnchor"]
    };
}
