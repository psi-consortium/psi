<!--
 Copyright 2025 THE PSI CONSORTIUM

 SPDX-License-Identifier: Apache-2.0
 -->

<script lang="ts">
    import { type Mission } from "$lib/domains/mission";
    import MissionSearch from "./MissionSearch.svelte";
    import MissionServiceSelection from "./MissionServiceSelection.svelte";

    let {
        missionId,
        selectedMission = $bindable(null),
        onCancel
    }: {
        missionId: string;
        selectedMission: Mission | null;
        onCancel: () => void;
    } = $props();

    // $inspect('SelectedMission', selectedMission);

    let activeTabValue = $state(1);
    function isActive(tabNo: number): string {
        return tabNo === activeTabValue ? "active" : "";
    }
    function selectTab(tabNo: number) {
        activeTabValue = tabNo;
    }

    function getFromType(): string {
        return activeTabValue === 2 ? "MissionTemplate" : "Mission";
    }
</script>

{#if !selectedMission}
    <!-- svelte-ignore a11y_no_static_element_interactions -->
    <!-- svelte-ignore a11y_click_events_have_key_events -->
    <ul class="flex flex-wrap mt-4 mb-0 pl-0 border-b-1 border-gray-300">
        <li class={isActive(1)}>
            <span onclick={() => selectTab(1)}>Missions</span>
        </li>
        <li class="{isActive(2)} ">
            <span onclick={() => selectTab(2)}>Mission Templates</span>
        </li>
    </ul>
    {#if activeTabValue == 1}
        <MissionSearch
            type="Mission"
            onSelect={(mission: Mission) => {
                selectedMission = mission;
            }}
        />
    {/if}
    {#if activeTabValue == 2}
        <MissionSearch
            type="MissionTemplate"
            onSelect={(mission: Mission) => {
                selectedMission = mission;
            }}
        />
    {/if}
{:else}
    <MissionServiceSelection
        toMissionId={missionId}
        fromMission={selectedMission}
        fromType={getFromType()}
        {onCancel}
    />
{/if}

<style>
    li {
        margin-bottom: -1px;
    }

    span {
        border: 1px solid transparent;
        border-top-left-radius: 0.25rem;
        border-top-right-radius: 0.25rem;
        display: block;
        padding: 0.5rem 1rem;
        cursor: pointer;
    }

    span:hover {
        border-color: #e9ecef #e9ecef #dee2e6;
    }

    li.active > span {
        color: #495057;
        background-color: #fff;
        border-color: #dee2e6 #dee2e6 #fff;
    }
</style>
