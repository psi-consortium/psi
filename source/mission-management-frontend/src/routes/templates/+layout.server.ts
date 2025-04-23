/*
 * Copyright 2025 THE PSI CONSORTIUM
 *
 * SPDX-License-Identifier: Apache-2.0
 */

import { fetchMissionTemplates } from "$lib/common/DataFetchPrivate";
import type { LayoutServerLoad } from "./$types";

export const load: LayoutServerLoad = async () => {
    const missionTemplates = await fetchMissionTemplates(fetch, {});
    return {
        missionTemplates
    };
};
