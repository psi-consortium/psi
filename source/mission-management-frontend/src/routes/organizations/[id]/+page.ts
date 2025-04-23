/*
 * Copyright 2025 THE PSI CONSORTIUM
 *
 * SPDX-License-Identifier: Apache-2.0
 */

import { fetchOrganization } from "$lib/common/DataFetchPublic";
import type { PageLoad } from "./$types";

export const load: PageLoad = async ({ params, fetch }) => {
    return {
        organization: await fetchOrganization(fetch, params.id)
    };
};
