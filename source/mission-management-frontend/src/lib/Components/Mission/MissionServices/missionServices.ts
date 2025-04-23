/*
 * Copyright 2025 THE PSI CONSORTIUM
 *
 * SPDX-License-Identifier: Apache-2.0
 */

import type {
    MissionAsset,
    MissionAssetRelationship,
    RelatedPlaceRefOrValue
} from "$lib/domains/mission";

export const frequencyBands = ["", "P", "L", "S", "C", "X", "KA", "MILKA", "KU"];
export const transmissionTechs = ["", "iDirect", "UHP", "HNS", "mPowered", "G2X"];

export const RelationshipTypeTeam = "Team";
export function getRelatedAssets(
    missionAsset: MissionAsset | null,
    relationshipType: string
): MissionAssetRelationship[] {
    return (
        missionAsset?.assetRelationship?.filter(
            (rel) => rel.relationshipType === relationshipType
        ) || []
    );
}

export function getCharacteristicValue(mAsset: MissionAsset | null, id: string): any {
    const characteristic = mAsset?.inquiredProductCharacteristic?.find((c) => c.id === id);
    const charValue =
        characteristic?.inquiredProductCharacteristicValue?.length &&
        characteristic?.inquiredProductCharacteristicValue[0];
    return charValue ? charValue.value : undefined;
}

export function getRelatedPlaceRef(
    assets: MissionAsset | null,
    role: string
): RelatedPlaceRefOrValue | undefined {
    const found = assets?.place?.filter((p) => p.role === role);
    return found?.length ? found[0] : undefined;
}

export const AreaRoleDefault = "Service Area";
export function getRefDefault(asset: MissionAsset | null) {
    return getRelatedPlaceRef(asset, AreaRoleDefault);
}

export const AreaRoleUplink = "Uplink Area";
export function getRefUplink(asset: MissionAsset | null) {
    return getRelatedPlaceRef(asset, AreaRoleUplink);
}

export const AreaRoleDownlink = "Downlink Area";
export function getRefDownlink(asset: MissionAsset | null) {
    return getRelatedPlaceRef(asset, AreaRoleDownlink);
}

export function isValidateMissionAsset(asset: MissionAsset): boolean {
    if (asset.targetProductSchema?.type === "Bandwidth") {
        return validateBandwidth(asset);
    } else if (asset.targetProductSchema?.type === "InternetAccess") {
        return validateInternetAccess(asset);
    } else if (asset.targetProductSchema?.type === "VPN") {
        return validateVPN(asset);
    } else if (asset.targetProductSchema?.type === "Terminal") {
        return validateTerminal(asset);
    } else {
        return false;
    }
}

export function validateBandwidth(asset: MissionAsset): boolean {
    const frequencyBand = getCharacteristicValue(asset, "frequencyBand") as string;
    const bandwidth = getCharacteristicValue(asset, "bandwidth") as number;
    const latency = getCharacteristicValue(asset, "latency") as number;
    const uplinkPlaceRef = getRefUplink(asset);
    const downlinkPlaceRef = getRefDownlink(asset);

    return (
        !!asset?.name &&
        !!uplinkPlaceRef &&
        !!downlinkPlaceRef &&
        !!frequencyBand &&
        !!bandwidth &&
        !!latency
    );
}

export function validateInternetAccess(asset: MissionAsset): boolean {
    const frequencyBand = getCharacteristicValue(asset, "frequencyBand") as string;
    const forwardCIR = getCharacteristicValue(asset, "forwardCIR") as number;
    const forwardPIR = getCharacteristicValue(asset, "forwardPIR") as number;
    const returnCIR = getCharacteristicValue(asset, "returnCIR") as number;
    const returnPIR = getCharacteristicValue(asset, "returnPIR") as number;
    const latency = getCharacteristicValue(asset, "latency") as number;
    const availability = getCharacteristicValue(asset, "availability") as number;
    const relatedPlaceRef = getRefDefault(asset);

    return (
        !!asset?.name &&
        !!relatedPlaceRef &&
        !!frequencyBand &&
        !!forwardCIR &&
        !!forwardPIR &&
        !!returnCIR &&
        !!returnPIR &&
        !!latency &&
        !!availability
    );
}

export function validateVPN(asset: MissionAsset): boolean {
    const frequencyBand = getCharacteristicValue(asset, "frequencyBand") as string;
    const forwardCIR = getCharacteristicValue(asset, "forwardCIR") as number;
    const forwardPIR = getCharacteristicValue(asset, "forwardPIR") as number;
    const returnCIR = getCharacteristicValue(asset, "returnCIR") as number;
    const returnPIR = getCharacteristicValue(asset, "returnPIR") as number;
    const latency = getCharacteristicValue(asset, "latency") as number;
    const availability = getCharacteristicValue(asset, "availability") as number;

    return (
        !!asset?.name &&
        !!asset?.place &&
        asset.place.length >= 2 &&
        !!frequencyBand &&
        !!forwardCIR &&
        !!forwardPIR &&
        !!returnCIR &&
        !!returnPIR &&
        !!latency &&
        !!availability
    );
}

export function validateTerminal(asset: MissionAsset): boolean {
    const frequencyBand = getCharacteristicValue(asset, "frequencyBand") as string;
    const reflectorDiameter = getCharacteristicValue(asset, "reflectorDiameter") as number;
    const uplinkPower = getCharacteristicValue(asset, "uplinkPower") as number;
    const transmissionTech = getCharacteristicValue(asset, "transmissionTech") as string;
    const relatedPlaceRef = asset?.place?.length ? asset.place[0] : undefined;

    return (
        !!asset?.name &&
        !!relatedPlaceRef &&
        !!frequencyBand &&
        !!reflectorDiameter &&
        !!uplinkPower &&
        !!transmissionTech
    );
}
