/*
 * Copyright 2025 THE PSI CONSORTIUM
 *
 * SPDX-License-Identifier: Apache-2.0
 */

import { fetchOrganizations } from "$lib/common/DataFetchPublic";
import type { LayoutServerLoad } from "./$types";

export const load: LayoutServerLoad = async () => {
    return {
        organizations: await fetchOrganizations(fetch)
    };
};
