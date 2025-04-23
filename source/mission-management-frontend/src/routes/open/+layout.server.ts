/*
 * Copyright 2025 THE PSI CONSORTIUM
 *
 * SPDX-License-Identifier: Apache-2.0
 */

import type { LayoutServerLoad } from "./$types";
import { fetchMissions, fetchMissionTemplates } from "$lib/common/DataFetchPrivate";

export const load: LayoutServerLoad = async () => {
    const missions = await fetchMissions(fetch, { limit: "6", sort: "lastUpdate,desc" });
    const templates = await fetchMissionTemplates(fetch, { limit: "6", sort: "lastUpdate,desc" });

    return {
        missions,
        templates
    };
};
