<!--
 Copyright 2025 THE PSI CONSORTIUM

 SPDX-License-Identifier: Apache-2.0
 -->

<script lang="ts">
    import Map from "$lib/Components/Map.svelte";
    import TestGantt from "$lib/Components/TestGantt.svelte";
    import type { PageProps } from "./$types";
    import Mission from "./Mission.svelte";
    import node from "$lib/assets/node.png";

    let { data, form }: PageProps = $props();
    let mission = $derived(data.mission);
    const missionId = $derived(data.mission?.id);

    let selectedView = $state({ overview: true, location: false, calendar: false, logical: false });
    function selectOverview() {
        selectedView = { overview: true, location: false, calendar: false, logical: false };
    }
    function selectLocation() {
        selectedView = { overview: false, location: true, calendar: false, logical: false };
    }
    function selectCalendar() {
        selectedView = { overview: false, location: false, calendar: true, logical: false };
    }
    function selectLogical() {
        selectedView = { overview: false, location: false, calendar: false, logical: true };
    }

    const closeMap = () => {
        selectOverview();
        selectedView = { overview: true, location: false, calendar: false, logical: false };
    };

    // Closes the Map component in case of switching to another Mission
    $effect(() => {
        missionId;
        closeMap();
    });
</script>

<div class="w-full justify-center">
    <div class="w-full bg-gray-100">
        <div
            class="w-fit flex flex-row justify-around sm:gap-4 lg:gap-8 m-auto pb-2 pt-2 bg-gray-100"
        >
            <button
                onclick={selectOverview}
                class={selectedView.overview
                    ? "font-bold text-primary-600"
                    : "text-primary-500 cursor-pointer"}
            >
                Overview
            </button>
            <span>|</span>
            <button
                onclick={selectLocation}
                class={selectedView.location
                    ? "font-bold text-primary-600 "
                    : "text-primary-500 cursor-pointer "}>Mission Geography</button
            >
            <span>|</span>
            <button
                onclick={selectCalendar}
                class={selectedView.calendar
                    ? "font-bold text-primary-600 "
                    : "text-primary-500 cursor-pointer "}>Mission Timeline</button
            >
            <span>|</span>
            <button
                onclick={selectLogical}
                class={selectedView.logical
                    ? "font-bold text-primary-600 "
                    : "text-primary-500 cursor-pointer "}>Interdependencies</button
            >
        </div>
    </div>

    <div class={"grid grid-cols-12 gap-3 w-full mt-6"}>
        <Mission
            {mission}
            serviceTemplates={data.serviceTemplates}
            teams={data.teams}
            {form}
            overview={selectedView.overview}
        />

        {#if !selectedView.overview}
            <div class="col-start-5 col-span-7 h-fit">
                {#if selectedView.location}
                    <Map {mission} />
                {:else if selectedView.calendar}
                    <div class="h-[100vh}">
                        <TestGantt />
                    </div>
                {:else}
                    <img src={node} alt="Logical node diagram" class="mt-20 w-3xl" />
                {/if}
            </div>
        {/if}
    </div>
</div>
