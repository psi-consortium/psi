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
 * Reference of a tax definition. A tax is levied by an authorized tax jurisdiction. There are many different types of tax (Federal Tax levied by the US Government, State Tax levied by the State of California,…).
 * @export
 * @interface TaxDefinition
 */
export interface TaxDefinition {
    /**
     * Unique identifier of the tax.
     * @type {string}
     * @memberof TaxDefinition
     */
    id?: string;
    /**
     * Level of the jurisdiction that levies the tax
     * @type {string}
     * @memberof TaxDefinition
     */
    jurisdictionLevel?: string;
    /**
     * Name of the jurisdiction that levies the tax
     * @type {string}
     * @memberof TaxDefinition
     */
    jurisdictionName?: string;
    /**
     * Tax name.
     * @type {string}
     * @memberof TaxDefinition
     */
    name?: string;
    /**
     * Type of the tax.
     * @type {string}
     * @memberof TaxDefinition
     */
    taxType?: string;
    /**
     *
     * @type {TimePeriod}
     * @memberof TaxDefinition
     */
    validFor?: TimePeriod;
    /**
     * When sub-classing, this defines the super-class
     * @type {string}
     * @memberof TaxDefinition
     */
    baseType?: string;
    /**
     * A URI to a JSON-Schema file that defines additional attributes and relationships
     * @type {string}
     * @memberof TaxDefinition
     */
    schemaLocation?: string;
    /**
     * When sub-classing, this defines the sub-class Extensible name
     * @type {string}
     * @memberof TaxDefinition
     */
    type?: string;
}

/**
 * Check if a given object implements the TaxDefinition interface.
 */
export function instanceOfTaxDefinition(value: object): value is TaxDefinition {
    return true;
}

export function TaxDefinitionFromJSON(json: any): TaxDefinition {
    return TaxDefinitionFromJSONTyped(json, false);
}

export function TaxDefinitionFromJSONTyped(json: any, ignoreDiscriminator: boolean): TaxDefinition {
    if (json == null) {
        return json;
    }
    return {
        id: json["id"] == null ? undefined : json["id"],
        jurisdictionLevel:
            json["jurisdictionLevel"] == null ? undefined : json["jurisdictionLevel"],
        jurisdictionName: json["jurisdictionName"] == null ? undefined : json["jurisdictionName"],
        name: json["name"] == null ? undefined : json["name"],
        taxType: json["taxType"] == null ? undefined : json["taxType"],
        validFor: json["validFor"] == null ? undefined : TimePeriodFromJSON(json["validFor"]),
        baseType: json["@baseType"] == null ? undefined : json["@baseType"],
        schemaLocation: json["@schemaLocation"] == null ? undefined : json["@schemaLocation"],
        type: json["@type"] == null ? undefined : json["@type"]
    };
}

export function TaxDefinitionToJSON(json: any): TaxDefinition {
    return TaxDefinitionToJSONTyped(json, false);
}

export function TaxDefinitionToJSONTyped(
    value?: TaxDefinition | null,
    ignoreDiscriminator: boolean = false
): any {
    if (value == null) {
        return value;
    }

    return {
        id: value["id"],
        jurisdictionLevel: value["jurisdictionLevel"],
        jurisdictionName: value["jurisdictionName"],
        name: value["name"],
        taxType: value["taxType"],
        validFor: TimePeriodToJSON(value["validFor"]),
        "@baseType": value["baseType"],
        "@schemaLocation": value["schemaLocation"],
        "@type": value["type"]
    };
}
