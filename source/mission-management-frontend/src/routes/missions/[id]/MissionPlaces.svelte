<!--
 Copyright 2025 THE PSI CONSORTIUM

 SPDX-License-Identifier: Apache-2.0
 -->

<script lang="ts">
    import type { Mission, PlaceRefOrValue } from "$lib/domains/mission";
    import Collapsible from "$lib/Components/common/Collapsible.svelte";

    let {
        mission
    }: {
        mission: Mission;
    } = $props();

    let places: Array<PlaceRefOrValue> = $derived(mission?.place || []);

    let showPlaces = $state(true);
</script>

<Collapsible bind:expanded={showPlaces}>
    {#snippet header()}
        <h1 class="text-2xl font-bold leading-none self-center">Places</h1>
    {/snippet}

    <div class="flex flex-wrap gap-2">
        {#each places as place}
            <div class="p-1 rounded-sm text-gray-700 bg-fuchsia-100 cursor-default">
                {`${place?.name || "No Name"}`}
            </div>
        {/each}
    </div>
</Collapsible>
