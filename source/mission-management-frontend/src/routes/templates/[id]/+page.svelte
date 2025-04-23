<!--
 Copyright 2025 THE PSI CONSORTIUM

 SPDX-License-Identifier: Apache-2.0
 -->

<script lang="ts">
    import Collapsible from "$lib/Components/common/Collapsible.svelte";
    import Form from "$lib/Components/common/Form.svelte";
    import Map from "$lib/Components/Map.svelte";
    import type { Mission } from "$lib/domains/mission";
    import MissionPlaces from "../../missions/[id]/MissionPlaces.svelte";
    import MissionService from "../../missions/[id]/MissionService.svelte";
    import type { PageProps } from "./$types";

    let { data, form }: PageProps = $props();
    let missionTemplate: Mission | undefined = $derived(data.missionTemplate);

    let showTemplateBasics = $state(true);

    let mode = $state("overview");

    function selectOverview() {
        mode = "overview";
    }
    function isOverview(): boolean {
        return mode === "overview";
    }
    function selectGeography() {
        mode = "geography";
    }
    function isGeography(): boolean {
        return mode === "geography";
    }
</script>

<div class="w-full justify-center">
    <div class="w-full bg-gray-100">
        <div
            class="w-fit flex flex-row justify-around sm:gap-4 lg:gap-8 m-auto pb-2 pt-2 bg-gray-100"
        >
            <button
                onclick={selectOverview}
                class={isOverview()
                    ? "font-bold text-primary-600"
                    : "text-primary-500 cursor-pointer"}
            >
                Overview
            </button>
            <span>|</span>
            <button
                onclick={selectGeography}
                class={isGeography()
                    ? "font-bold text-primary-600 "
                    : "text-primary-500 cursor-pointer "}
            >
                Template Geography
            </button>
        </div>
    </div>

    <div class={"grid grid-cols-12 gap-3 w-full mt-6"}>
        <div
            class={isOverview()
                ? "p-6 grid grid-cols-1 col-span-10 col-start-2 sm:col-span-8 sm:col-start-3 lg:col-start-4 lg:col-span-6 xl:col-start-5 xl:col-span-4 gap-5 h-[50rem] overflow-auto border-primary-600 border-1 border-solid rounded-sm"
                : "p-6 grid grid-cols-1 col-span-3 gap-5 h-[50rem] overflow-auto border-primary-600 border-1 border-solid rounded-sm"}
        >
            <Collapsible bind:expanded={showTemplateBasics}>
                {#snippet header()}
                    <h1 class="self-center">
                        {`Template ${missionTemplate?.name || ""}`}
                    </h1>
                {/snippet}

                <Form
                    {form}
                    mission={missionTemplate}
                    readOnly={!isOverview()}
                    type="MissionTemplate"
                />
            </Collapsible>

            <MissionPlaces mission={missionTemplate} />
            <MissionService
                mission={missionTemplate}
                mode="MissionTemplate"
                serviceTemplates={data.serviceTemplates}
                readOnly={!isOverview()}
            />
        </div>

        {#if isGeography()}
            <div class="col-start-5 col-span-7 h-fit">
                <Map mission={missionTemplate} />
            </div>
        {/if}
    </div>
</div>
