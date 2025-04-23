/*
 * Copyright 2025 THE PSI CONSORTIUM
 *
 * SPDX-License-Identifier: Apache-2.0
 */

import type { Mission } from "$lib/domains/mission";

export function deleteAsset(mission: Mission, assetId: string) {
    let assets = mission.asset || [];
    return assets.filter((old) => old.id !== assetId);
}
