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
import type { MissionAsset } from "./MissionAsset";
import {
    MissionAssetFromJSON,
    MissionAssetFromJSONTyped,
    MissionAssetToJSON,
    MissionAssetToJSONTyped
} from "./MissionAsset";
import type { PlaceRefOrValue } from "./PlaceRefOrValue";
import {
    PlaceRefOrValueFromJSON,
    PlaceRefOrValueFromJSONTyped,
    PlaceRefOrValueToJSON,
    PlaceRefOrValueToJSONTyped
} from "./PlaceRefOrValue";
import type { MissionRelationship } from "./MissionRelationship";
import {
    MissionRelationshipFromJSON,
    MissionRelationshipFromJSONTyped,
    MissionRelationshipToJSON,
    MissionRelationshipToJSONTyped
} from "./MissionRelationship";
import type { TimePeriod } from "./TimePeriod";
import {
    TimePeriodFromJSON,
    TimePeriodFromJSONTyped,
    TimePeriodToJSON,
    TimePeriodToJSONTyped
} from "./TimePeriod";
import type { Characteristic } from "./Characteristic";
import {
    CharacteristicFromJSON,
    CharacteristicFromJSONTyped,
    CharacteristicToJSON,
    CharacteristicToJSONTyped
} from "./Characteristic";
import type { MissionStatusType } from "./MissionStatusType";
import {
    MissionStatusTypeFromJSON,
    MissionStatusTypeFromJSONTyped,
    MissionStatusTypeToJSON,
    MissionStatusTypeToJSONTyped
} from "./MissionStatusType";
import type { RelatedParty } from "./RelatedParty";
import {
    RelatedPartyFromJSON,
    RelatedPartyFromJSONTyped,
    RelatedPartyToJSON,
    RelatedPartyToJSONTyped
} from "./RelatedParty";

/**
 * A mission of a customer.
 * Skipped properties: id,href,lastUpdated
 * @export
 * @interface MissionUpdate
 */
export interface MissionUpdate {
    /**
     * Name of the mission
     * @type {string}
     * @memberof MissionUpdate
     */
    name?: string;
    /**
     * Description of the mission
     * @type {string}
     * @memberof MissionUpdate
     */
    description?: string;
    /**
     * Category of the mission like 'Humanitarian Aid', 'Wildfire Fighting' and so on.
     * @type {string}
     * @memberof MissionUpdate
     */
    category?: string;
    /**
     *
     * @type {TimePeriod}
     * @memberof MissionUpdate
     */
    timeframe?: TimePeriod;
    /**
     *
     * @type {MissionStatusType}
     * @memberof MissionUpdate
     */
    status?: MissionStatusType;
    /**
     *
     * @type {Array<Characteristic>}
     * @memberof MissionUpdate
     */
    characteristic?: Array<Characteristic>;
    /**
     * List of places where the mission is conducted.
     * @type {Array<PlaceRefOrValue>}
     * @memberof MissionUpdate
     */
    place?: Array<PlaceRefOrValue>;
    /**
     * List of assets for this mission.
     * @type {Array<MissionAsset>}
     * @memberof MissionUpdate
     */
    asset?: Array<MissionAsset>;
    /**
     * Relation to other missions.
     * @type {Array<MissionRelationship>}
     * @memberof MissionUpdate
     */
    missionRelationship?: Array<MissionRelationship>;
    /**
     * A related party defines party or party role linked to a specific entity.
     * @type {Array<RelatedParty>}
     * @memberof MissionUpdate
     */
    relatedParty?: Array<RelatedParty>;
    /**
     * When sub-classing, this defines the super-class.
     * @type {string}
     * @memberof MissionUpdate
     */
    baseType?: string;
    /**
     * A URI to a JSON-Schema file that defines additional attributes and relationships
     * @type {string}
     * @memberof MissionUpdate
     */
    schemaLocation?: string;
    /**
     * When sub-classing, this defines the sub-class entity name.
     * @type {string}
     * @memberof MissionUpdate
     */
    type?: string;
}

/**
 * Check if a given object implements the MissionUpdate interface.
 */
export function instanceOfMissionUpdate(value: object): value is MissionUpdate {
    return true;
}

export function MissionUpdateFromJSON(json: any): MissionUpdate {
    return MissionUpdateFromJSONTyped(json, false);
}

export function MissionUpdateFromJSONTyped(json: any, ignoreDiscriminator: boolean): MissionUpdate {
    if (json == null) {
        return json;
    }
    return {
        name: json["name"] == null ? undefined : json["name"],
        description: json["description"] == null ? undefined : json["description"],
        category: json["category"] == null ? undefined : json["category"],
        timeframe: json["timeframe"] == null ? undefined : TimePeriodFromJSON(json["timeframe"]),
        status: json["status"] == null ? undefined : MissionStatusTypeFromJSON(json["status"]),
        characteristic:
            json["characteristic"] == null
                ? undefined
                : (json["characteristic"] as Array<any>).map(CharacteristicFromJSON),
        place:
            json["place"] == null
                ? undefined
                : (json["place"] as Array<any>).map(PlaceRefOrValueFromJSON),
        asset:
            json["asset"] == null
                ? undefined
                : (json["asset"] as Array<any>).map(MissionAssetFromJSON),
        missionRelationship:
            json["missionRelationship"] == null
                ? undefined
                : (json["missionRelationship"] as Array<any>).map(MissionRelationshipFromJSON),
        relatedParty:
            json["relatedParty"] == null
                ? undefined
                : (json["relatedParty"] as Array<any>).map(RelatedPartyFromJSON),
        baseType: json["@baseType"] == null ? undefined : json["@baseType"],
        schemaLocation: json["@schemaLocation"] == null ? undefined : json["@schemaLocation"],
        type: json["@type"] == null ? undefined : json["@type"]
    };
}

export function MissionUpdateToJSON(json: any): MissionUpdate {
    return MissionUpdateToJSONTyped(json, false);
}

export function MissionUpdateToJSONTyped(
    value?: MissionUpdate | null,
    ignoreDiscriminator: boolean = false
): any {
    if (value == null) {
        return value;
    }

    return {
        name: value["name"],
        description: value["description"],
        category: value["category"],
        timeframe: TimePeriodToJSON(value["timeframe"]),
        status: MissionStatusTypeToJSON(value["status"]),
        characteristic:
            value["characteristic"] == null
                ? undefined
                : (value["characteristic"] as Array<any>).map(CharacteristicToJSON),
        place:
            value["place"] == null
                ? undefined
                : (value["place"] as Array<any>).map(PlaceRefOrValueToJSON),
        asset:
            value["asset"] == null
                ? undefined
                : (value["asset"] as Array<any>).map(MissionAssetToJSON),
        missionRelationship:
            value["missionRelationship"] == null
                ? undefined
                : (value["missionRelationship"] as Array<any>).map(MissionRelationshipToJSON),
        relatedParty:
            value["relatedParty"] == null
                ? undefined
                : (value["relatedParty"] as Array<any>).map(RelatedPartyToJSON),
        "@baseType": value["baseType"],
        "@schemaLocation": value["schemaLocation"],
        "@type": value["type"]
    };
}
