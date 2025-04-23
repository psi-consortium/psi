<!--
 Copyright 2025 THE PSI CONSORTIUM

 SPDX-License-Identifier: Apache-2.0
 -->

<script lang="ts">
    import iconEdit from "$lib/assets/edit_24dp_1F1F1F_FILL0_wght400_GRAD0_opsz24.svg";
    import type { Mission, MissionAsset } from "$lib/domains/mission";
    import Collapsible from "$lib/Components/common/Collapsible.svelte";
    import { TeamProductSchemaType, type Team } from "$lib/domains/team";
    import Modal from "$lib/Components/common/Modal.svelte";

    let {
        mission,
        teams,
        readOnly
    }: {
        mission: Mission;
        teams: Team[];
        readOnly: boolean;
    } = $props();

    let teamAssets: Array<MissionAsset> = $derived.by(() => {
        if (!mission.asset) {
            return [];
        }
        return mission.asset.filter(
            (asset) => asset.targetProductSchema?.type === TeamProductSchemaType
        );
    });

    let showTeams = $state(true);
    let showTeamsSelection = $state(false);
    function openTeamsSelection() {
        showTeamsSelection = true;
    }
    function closeTeamsSelection() {
        showTeamsSelection = false;
    }
</script>

<Collapsible bind:expanded={showTeams}>
    {#snippet header()}
        <div class="w-full flex flex-row justify-between text-gray-700">
            <h1 class="leading-none self-center">Teams</h1>
            {#if !readOnly}
                <button
                    class="rounded-sm p-2 self-center cursor-pointer hover:bg-gray-100"
                    onclick={openTeamsSelection}
                >
                    <img src={iconEdit} alt="Copy Service Needs from Mission or MissionTemplate" />
                </button>
            {/if}
        </div>
    {/snippet}

    <div class="flex flex-wrap gap-2">
        {#each teamAssets as asset}
            <div class="p-1 rounded-sm text-gray-700 bg-fuchsia-100 cursor-default">
                {`${asset.name}`}
            </div>
        {/each}
    </div>
</Collapsible>

<Modal
    name="ServiceTemplate Selection"
    bind:showModal={showTeamsSelection}
    easyClose={true}
    onClose={() => {}}
    width="w-lg"
    height="h-1/2"
    title="Select the Teams for the Mission"
>
    <form action="?/addTeams" method="POST">
        <input type="hidden" name="missionId" value={mission?.id} />

        <div class="col-start-1 col-span-10 grid">
            <label class="text-xs p-0 m-0 mb-1" for="name">Teams</label>
            <select
                name="teams"
                class="p-1.5 border-primary-600 border-1 border-solid rounded-sm cursor-pointer"
                value={teamAssets.map((t) => `${t.id};${t.name}`)}
                placeholder="Select Teams"
                multiple
                size="10"
            >
                <!-- TODO: Position does not fit
                <Tooltip title="Ctrl-Click for multiple selections"> -->
                {#each teams as team}
                    <option value={`${team.id};${team.name}`}>{team.name}</option>
                {/each}
                <!-- </Tooltip> -->
            </select>
        </div>

        <div class="flex flex-row w-full justify-end gap-4 mt-14">
            <button
                type="button"
                class="border-primary-600 hover:bg-gray-100 border-1 border-solid rounded-sm py-3 px-6 cursor-pointer"
                onclick={closeTeamsSelection}
            >
                Cancel
            </button>

            <button
                type="submit"
                class="bg-primary-600 hover:bg-primary-500 text-white rounded-sm py-3 px-6 cursor-pointer disabled:bg-gray-400 disabled:cursor-default"
            >
                Save Teams
            </button>
        </div>
    </form>
</Modal>
