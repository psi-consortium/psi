/*
 * Copyright 2025 THE PSI CONSORTIUM
 *
 * SPDX-License-Identifier: Apache-2.0
 */

import { v4 as uuid } from "uuid";
import { AreaRoleDefault } from "$lib/Components/Mission/MissionServices/missionServices";
import type {
    CharacteristicValuePrecedence,
    InquiredProductCharacteristic,
    MissionAsset,
    MissionAssetRelationship,
    RelatedPlaceRefOrValue
} from "$lib/domains/mission";

type IpcParser = (data: FormData) => Array<InquiredProductCharacteristic>;
type PlaceParser = (data: FormData) => RelatedPlaceRefOrValue[];
type TypeParsers = {
    [key: string]: {
        ipcParser: IpcParser;
        placeParser: PlaceParser;
    };
};

export function createMissionAsset(formData: FormData): MissionAsset | null {
    const typeParser: TypeParsers = {
        Bandwidth: {
            ipcParser: parseBandwidthIPCs,
            placeParser: createBandwidthPlaceRef
        },
        InternetAccess: {
            ipcParser: parseInternetAccessIPCs,
            placeParser: createDefaultRelatedPlaceRefOrValue
        },
        VPN: {
            ipcParser: parseInternetAccessIPCs,
            placeParser: createDefaultRelatedPlaceRefOrValue
        },
        Terminal: {
            ipcParser: parseTerminalIPCs,
            placeParser: createDefaultRelatedPlaceRefOrValue
        }
    };

    const type = formData.get("type") as string;

    const { ipcParser, placeParser } = typeParser[type];
    if (!ipcParser) {
        return null;
    }
    const relatedTeams = formData.getAll("teams") as string[];

    return {
        id: (formData.get("id") as string) || uuid(),
        name: formData.get("assetName") as string,

        // servicePeriod,   // to be added in a later step
        // ...

        place: placeParser ? placeParser(formData) : [],
        inquiredProductCharacteristic: ipcParser ? ipcParser(formData) : [],
        assetRelationship: createAssetRelationship(relatedTeams),
        targetProductSchema: {
            type,
            schemaLocation: "./psi-api/schemas/Mission/MissionAsset.schema.json"
        }
    };
}

function getNumber(formData: FormData, key: string): number {
    return parseFloat(formData.get(key) as string);
}

function parseBandwidthIPCs(data: FormData): Array<InquiredProductCharacteristic> {
    return [
        createStringIPC("frequencyBand", data.get("frequencyBand") as string),
        createDecimalIPC("bandwidth", getNumber(data, "bandwidth"), "kHz"),
        createDecimalIPC("latency", getNumber(data, "latency"), "ms")
    ].filter((item) => item) as Array<InquiredProductCharacteristic>;
}

function parseInternetAccessIPCs(data: FormData): Array<InquiredProductCharacteristic> {
    return [
        createStringIPC("frequencyBand", data.get("frequencyBand") as string),
        createDecimalIPC("forwardCIR", getNumber(data, "forwardCIR"), "kbps"),
        createDecimalIPC("forwardPIR", getNumber(data, "forwardPIR"), "kbps"),
        createDecimalIPC("returnCIR", getNumber(data, "returnCIR"), "kbps"),
        createDecimalIPC("returnPIR", getNumber(data, "returnPIR"), "kbps"),
        createDecimalIPC("latency", getNumber(data, "latency"), "ms"),
        createDecimalIPC("availability", getNumber(data, "availability"), "%")
    ].filter((item) => item) as Array<InquiredProductCharacteristic>;
}

function parseTerminalIPCs(data: FormData): Array<InquiredProductCharacteristic> {
    return [
        createStringIPC("frequencyBand", data.get("frequencyBand") as string),
        createDecimalIPC("reflectorDiameter", getNumber(data, "reflectorDiameter"), "cm"),
        createDecimalIPC("uplinkPower", getNumber(data, "uplinkPower"), "W"),
        createStringIPC("transmissionTech", data.get("transmissionTech") as string),
        createDecimalIPC("width", getNumber(data, "width"), "kbps"),
        createDecimalIPC("length", getNumber(data, "length"), "kbps"),
        createDecimalIPC("height", getNumber(data, "height"), "kbps"),
        createDecimalIPC("weight", getNumber(data, "weight"), "kbps")
    ].filter((item) => item) as Array<InquiredProductCharacteristic>;
}

function createDecimalIPC(
    id: string,
    value: number,
    unitOfMeasure?: string,
    name?: string,
    priority?: number,
    precedence?: CharacteristicValuePrecedence
): InquiredProductCharacteristic | null {
    if (!value) {
        return null;
    }
    return {
        id,
        name,
        priority,
        valueType: "decimal",
        inquiredProductCharacteristicValue: [
            {
                precedence,
                value,
                unitOfMeasure
            }
        ]
    };
}

function createStringIPC(
    id: string,
    value: string,
    unitOfMeasure?: string,
    name?: string,
    priority?: number,
    precedence?: CharacteristicValuePrecedence
): InquiredProductCharacteristic | null {
    if (!value) {
        return null;
    }
    return {
        id,
        name,
        priority,
        valueType: "string",
        inquiredProductCharacteristicValue: [
            {
                precedence,
                value,
                unitOfMeasure
            }
        ]
    };
}

function createDefaultRelatedPlaceRefOrValue(formData: FormData): RelatedPlaceRefOrValue[] {
    const placeIds = formData.getAll("place") as string[];
    const role = AreaRoleDefault;

    const placeRefs = placeIds?.map((placeId) => createRelatedPlaceRefOrValue(placeId, role));
    return placeRefs?.filter((v) => !!v) || [];
}

function createBandwidthPlaceRef(formData: FormData): RelatedPlaceRefOrValue[] {
    const uplinkId = formData.get("uplinkPlace") as string;
    const downlinkId = formData.get("downlinkPlace") as string;

    return [
        createRelatedPlaceRefOrValue(uplinkId, "Uplink Area"),
        createRelatedPlaceRefOrValue(downlinkId, "Downlink Area")
    ].filter((v) => !!v);
}

function createAssetRelationship(teamIds: string[]): MissionAssetRelationship[] {
    return teamIds.map((combinedId) => {
        const { id, name } = getIdAndName(combinedId);
        return {
            id,
            name,
            relationshipType: "Team"
        };
    });
}

function createRelatedPlaceRefOrValue(
    placeId: string | undefined,
    role: string
): RelatedPlaceRefOrValue | undefined {
    if (!placeId) {
        return undefined;
    }

    const { id, name } = getIdAndName(placeId);
    return {
        id,
        name,
        role,
        // href,
        // baseType: '1',
        referredType: "RelatedPlaceRefOrValue",
        type: "PlaceRef"
    };
}

export function getIdAndName(combinedValue: string): { id: string; name: string } {
    const id = combinedValue.split(";")[0];
    const name = combinedValue.slice(id.length + 1);
    return { id, name };
}
