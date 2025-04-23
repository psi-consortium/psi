/*
 * Copyright 2025 THE PSI CONSORTIUM
 *
 * SPDX-License-Identifier: Apache-2.0
 */

import type { Mission, MissionAsset } from "$lib/domains/mission";

export function updateByAsset(mission: Mission, asset: MissionAsset): MissionAsset[] {
    let assets = mission.asset || [];
    const index = assets.findIndex((item) => item.id === asset.id);
    if (index === -1) {
        assets.push(asset);
    } else {
        assets[index] = asset;
    }
    return assets;
}

export function updateByAssets(mission: Mission, assets: MissionAsset[]): MissionAsset[] {
    let result = mission.asset || [];

    assets.forEach((asset) => {
        const index = result.findIndex((item) => item.id === asset.id);
        if (index === -1) {
            result.push(asset);
        } else {
            result[index] = asset;
        }
    });
    return result;
}
