/*
 * Copyright 2025 THE PSI CONSORTIUM
 *
 * SPDX-License-Identifier: Apache-2.0
 */

import type { MissionAsset } from "$lib/domains/mission";

export function getTeams(assets: MissionAsset[]): MissionAsset[] {
    return assets?.filter((a) => a.targetProductSchema?.type === "Team") || [];
}

export function getTeamIds(assets: MissionAsset[]): string[] {
    return getTeams(assets)
        .map((team) => team.id)
        .filter((id) => id) as string[];
}
