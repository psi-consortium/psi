/*
 * Copyright 2025 THE PSI CONSORTIUM
 *
 * SPDX-License-Identifier: Apache-2.0
 */

import type { Team } from "$lib/domains/team";

export function findTeam(id: string): Team | undefined {
    return teams.find((o) => o.id === id);
}

export function findAllTeams(offset: number = 0, size: number = 10): Team[] {
    return teams.slice(offset, size);
}

export const teams: Team[] = [
    { id: "16E5128C-C252-40FA-9493-2B331240939B", name: "Fire Department A" },
    { id: "C76EB8D6-8B63-4948-A72B-AF978A5A5D3B", name: "Fire Department B" },
    { id: "9D20024F-F701-444B-A893-FBC43162AEAF", name: "Fire Department C" },
    { id: "5DE49EB3-3DC3-4069-8C05-C20275256312", name: "Team C67" },
    { id: "C8E3EDCD-9DCC-4753-B6B8-1B370B420EC7", name: "Team 457" },
    { id: "DB3FC58E-C263-433C-9D7E-BA77613CC7EA", name: "Team X32" }
];
