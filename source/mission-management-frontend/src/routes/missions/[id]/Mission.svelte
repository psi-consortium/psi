<!--
 Copyright 2025 THE PSI CONSORTIUM

 SPDX-License-Identifier: Apache-2.0
 -->

<script lang="ts">
    import Collapsible from "$lib/Components/common/Collapsible.svelte";
    import Form from "$lib/Components/common/Form.svelte";
    import type { Mission } from "$lib/domains/mission";
    import type { ServiceTemplate } from "$lib/domains/serviceTemplate";
    import type { Team } from "$lib/domains/team";
    import MissionPlaces from "./MissionPlaces.svelte";
    import MissionService from "./MissionService.svelte";
    import MissionTeams from "./MissionTeams.svelte";

    let {
        mission,
        serviceTemplates,
        teams,
        overview,
        form
    }: {
        mission: Mission | undefined;
        serviceTemplates: ServiceTemplate[];
        overview: boolean;
        teams: Team[];
        form: any;
    } = $props();

    let showMissionBasics = $state(true);
</script>

<div
    class={overview
        ? "p-6 grid grid-cols-1 col-span-10 col-start-2 sm:col-span-8 sm:col-start-3 lg:col-start-4 lg:col-span-6 xl:col-start-5 xl:col-span-4 gap-5 h-[50rem] overflow-auto border-primary-600 border-1 border-solid rounded-sm"
        : "p-6 grid grid-cols-1 col-span-3 gap-5 h-[50rem] overflow-auto border-primary-600 border-1 border-solid rounded-sm"}
>
    <Collapsible bind:expanded={showMissionBasics}>
        {#snippet header()}
            <h1 class="self-center">
                {`Mission ${mission?.name || ""}`}
            </h1>
        {/snippet}

        <Form {form} {mission} readOnly={!overview} type="Mission" />
    </Collapsible>

    {#if mission}
        <MissionPlaces {mission} />
        <MissionTeams {mission} {teams} readOnly={!overview} />
        <MissionService {serviceTemplates} {mission} readOnly={!overview} />
    {/if}
</div>
