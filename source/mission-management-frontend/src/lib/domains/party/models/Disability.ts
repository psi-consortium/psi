/* tslint:disable */
/* eslint-disable */
/**
 * API Party
 * ## TMF API Reference : TMF 632 - Party   ### Release : 19.0   The party API provides standardized mechanism for party management such as creation, update, retrieval, deletion and notification of events. Party can be an individual or an organization that has any kind of relation with the enterprise. Party is created to record individual or organization information before the assignment of any role. For example, within the context of a split billing mechanism, Party API allows creation of the individual or organization that will play the role of 3 rd payer for a given offer and, then, allows consultation or update of his information.  ### Resources - Organization - Individual - Hub  Party API performs the following operations : - Retrieve an organization or an individual - Retrieve a collection of organizations or individuals according to given criteria - Create a new organization or a new individual - Update an existing organization or an existing individual - Delete an existing organization or an existing individual - Notify events on organizatin or individual
 *
 * The version of the OpenAPI document: 4.0.0
 *
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

import { mapValues } from "../runtime";
import type { TimePeriod } from "./TimePeriod";
import {
    TimePeriodFromJSON,
    TimePeriodFromJSONTyped,
    TimePeriodToJSON,
    TimePeriodToJSONTyped
} from "./TimePeriod";

/**
 * Lack or inadequate strength or ability.
 * @export
 * @interface Disability
 */
export interface Disability {
    /**
     * unique identifier
     * @type {string}
     * @memberof Disability
     */
    id?: string;
    /**
     * Hyperlink reference
     * @type {string}
     * @memberof Disability
     */
    href?: string;
    /**
     * Code of the disability
     * @type {string}
     * @memberof Disability
     */
    disabilityCode?: string;
    /**
     * Name of the disability
     * @type {string}
     * @memberof Disability
     */
    disabilityName?: string;
    /**
     *
     * @type {TimePeriod}
     * @memberof Disability
     */
    validFor?: TimePeriod;
    /**
     * When sub-classing, this defines the super-class
     * @type {string}
     * @memberof Disability
     */
    baseType?: string;
    /**
     * A URI to a JSON-Schema file that defines additional attributes and relationships
     * @type {string}
     * @memberof Disability
     */
    schemaLocation?: string;
    /**
     * When sub-classing, this defines the sub-class Extensible name
     * @type {string}
     * @memberof Disability
     */
    type?: string;
}

/**
 * Check if a given object implements the Disability interface.
 */
export function instanceOfDisability(value: object): value is Disability {
    return true;
}

export function DisabilityFromJSON(json: any): Disability {
    return DisabilityFromJSONTyped(json, false);
}

export function DisabilityFromJSONTyped(json: any, ignoreDiscriminator: boolean): Disability {
    if (json == null) {
        return json;
    }
    return {
        id: json["id"] == null ? undefined : json["id"],
        href: json["href"] == null ? undefined : json["href"],
        disabilityCode: json["disabilityCode"] == null ? undefined : json["disabilityCode"],
        disabilityName: json["disabilityName"] == null ? undefined : json["disabilityName"],
        validFor: json["validFor"] == null ? undefined : TimePeriodFromJSON(json["validFor"]),
        baseType: json["@baseType"] == null ? undefined : json["@baseType"],
        schemaLocation: json["@schemaLocation"] == null ? undefined : json["@schemaLocation"],
        type: json["@type"] == null ? undefined : json["@type"]
    };
}

export function DisabilityToJSON(json: any): Disability {
    return DisabilityToJSONTyped(json, false);
}

export function DisabilityToJSONTyped(
    value?: Disability | null,
    ignoreDiscriminator: boolean = false
): any {
    if (value == null) {
        return value;
    }

    return {
        id: value["id"],
        href: value["href"],
        disabilityCode: value["disabilityCode"],
        disabilityName: value["disabilityName"],
        validFor: TimePeriodToJSON(value["validFor"]),
        "@baseType": value["baseType"],
        "@schemaLocation": value["schemaLocation"],
        "@type": value["type"]
    };
}
