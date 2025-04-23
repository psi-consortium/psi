/*
 * Copyright 2025 THE PSI CONSORTIUM
 *
 * SPDX-License-Identifier: Apache-2.0
 */

import type { ServiceTemplate } from "$lib/domains/serviceTemplate";

export function findServiceTemplate(id: string): ServiceTemplate | undefined {
    return serviceTemplates.find((o) => o.id === id);
}

export function findAllServiceTemplates(offset: number = 0, size: number = 10): ServiceTemplate[] {
    return serviceTemplates.slice(offset, size);
}

export const serviceTemplates: ServiceTemplate[] = [
    {
        id: "B2DEAA23-70C0-4C71-BC35-AF52E21750E2",
        name: "Bandwidth",
        relatedType: "Bandwidth"
    },
    {
        id: "B0FAEBDE-079C-430D-A41F-F51700F05864",
        name: "Internet Access",
        relatedType: "InternetAccess"
    },
    {
        id: "9789DA56-0D47-4FB6-95BA-73BB7457971A",
        name: "Virtual Private Network",
        relatedType: "VPN"
    },
    {
        id: "96C19F21-30F5-47B1-BE6C-A1E1C693FF4E",
        name: "Terminal",
        relatedType: "Terminal"
    }
];
