/*
 * Copyright 2025 THE PSI CONSORTIUM
 *
 * SPDX-License-Identifier: Apache-2.0
 */

import type { MissionAsset } from "$lib/domains/mission";
import { filterAssetsById } from "../assets";

export function mergeTeams(
    from: MissionAsset[] | undefined,
    copyTeamIds: string[],
    to: MissionAsset[]
) {
    if (!from) {
        return;
    }

    const newTeams = filterAssetsById(copyTeamIds, from);
    for (const team of newTeams) {
        if (to.findIndex((a) => a.id === team.id && a.targetProductSchema?.type === "Team") === -1)
            to.push(team);
    }
}
