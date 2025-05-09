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
import type { CharacteristicRelationship } from "./CharacteristicRelationship";
import {
    CharacteristicRelationshipFromJSON,
    CharacteristicRelationshipFromJSONTyped,
    CharacteristicRelationshipToJSON,
    CharacteristicRelationshipToJSONTyped
} from "./CharacteristicRelationship";

/**
 * Describes a given characteristic of an object or entity through a name/value pair.
 * @export
 * @interface Characteristic
 */
export interface Characteristic {
    /**
     * Unique identifier of the characteristic
     * @type {string}
     * @memberof Characteristic
     */
    id?: string;
    /**
     * Name of the characteristic
     * @type {string}
     * @memberof Characteristic
     */
    name: string;
    /**
     * Data type of the value of the characteristic
     * @type {string}
     * @memberof Characteristic
     */
    valueType?: string;
    /**
     *
     * @type {Array<CharacteristicRelationship>}
     * @memberof Characteristic
     */
    characteristicRelationship?: Array<CharacteristicRelationship>;
    /**
     *
     * @type {any}
     * @memberof Characteristic
     */
    value: any | null;
    /**
     * When sub-classing, this defines the super-class
     * @type {string}
     * @memberof Characteristic
     */
    baseType?: string;
    /**
     * A URI to a JSON-Schema file that defines additional attributes and relationships
     * @type {string}
     * @memberof Characteristic
     */
    schemaLocation?: string;
    /**
     * When sub-classing, this defines the sub-class Extensible name
     * @type {string}
     * @memberof Characteristic
     */
    type?: string;
    /**
     * A length, surface, volume, dry measure, liquid measure, money, weight, time, and the like. In general, a determinate quantity or magnitude of the kind designated, taken as a standard of comparison for others of the same kind, in assigning them to numerical values, as 1 foot, 1 yard, 1 mile, 1 square foot.
     * @type {string}
     * @memberof Characteristic
     */
    unitOfMeasure?: string;
}

/**
 * Check if a given object implements the Characteristic interface.
 */
export function instanceOfCharacteristic(value: object): value is Characteristic {
    if (!("name" in value) || value["name"] === undefined) return false;
    if (!("value" in value) || value["value"] === undefined) return false;
    return true;
}

export function CharacteristicFromJSON(json: any): Characteristic {
    return CharacteristicFromJSONTyped(json, false);
}

export function CharacteristicFromJSONTyped(
    json: any,
    ignoreDiscriminator: boolean
): Characteristic {
    if (json == null) {
        return json;
    }
    return {
        id: json["id"] == null ? undefined : json["id"],
        name: json["name"],
        valueType: json["valueType"] == null ? undefined : json["valueType"],
        characteristicRelationship:
            json["characteristicRelationship"] == null
                ? undefined
                : (json["characteristicRelationship"] as Array<any>).map(
                      CharacteristicRelationshipFromJSON
                  ),
        value: json["value"],
        baseType: json["@baseType"] == null ? undefined : json["@baseType"],
        schemaLocation: json["@schemaLocation"] == null ? undefined : json["@schemaLocation"],
        type: json["@type"] == null ? undefined : json["@type"],
        unitOfMeasure: json["unitOfMeasure"] == null ? undefined : json["unitOfMeasure"]
    };
}

export function CharacteristicToJSON(json: any): Characteristic {
    return CharacteristicToJSONTyped(json, false);
}

export function CharacteristicToJSONTyped(
    value?: Characteristic | null,
    ignoreDiscriminator: boolean = false
): any {
    if (value == null) {
        return value;
    }

    return {
        id: value["id"],
        name: value["name"],
        valueType: value["valueType"],
        characteristicRelationship:
            value["characteristicRelationship"] == null
                ? undefined
                : (value["characteristicRelationship"] as Array<any>).map(
                      CharacteristicRelationshipToJSON
                  ),
        value: value["value"],
        "@baseType": value["baseType"],
        "@schemaLocation": value["schemaLocation"],
        "@type": value["type"],
        unitOfMeasure: value["unitOfMeasure"]
    };
}
