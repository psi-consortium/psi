<!--
 Copyright 2025 THE PSI CONSORTIUM

 SPDX-License-Identifier: Apache-2.0
 -->

<script lang="ts">
    import { type Mission } from "$lib/domains/mission";
    import { onMount } from "svelte";

    import iconClose from "$lib/assets/close_24dp_5F6368_FILL0_wght200_GRAD0_opsz24.png";
    import iconSearch from "$lib/assets/search_24dp_1F1F1F_FILL0_wght400_GRAD0_opsz24.svg";
    import iconForward from "$lib/assets/chevron_forward_24dp_1F1F1F_FILL0_wght400_GRAD0_opsz24.svg";
    import iconBackward from "$lib/assets/chevron_backward_24dp_1F1F1F_FILL0_wght400_GRAD0_opsz24.svg";
    import { fetchPublicMissions, fetchPublicMissionTemplates } from "$lib/common/DataFetchPublic";

    let { type, onSelect }: { type: string; onSelect: (mission: Mission) => void } = $props();

    let missions: Mission[] = $state([]);
    let filteredMissions: Array<Mission> = $state([]);

    let visibleMissions: Array<Mission> = $state([]);
    let page = $state(0);
    let pageSize = $state(10);

    onMount(async () => {
        let result: Mission[];

        if (type === "Mission") {
            result = await fetchPublicMissions(fetch, { limit: "100" });
        } else {
            result = await fetchPublicMissionTemplates(fetch, { limit: "100" });
        }

        missions = result;
        filteredMissions = result;
        pageClipping();
    });

    let filter = $state("");

    function performFilter() {
        if (!filter) {
            filteredMissions = missions;
        }
        const f = filter.toUpperCase();
        filteredMissions = missions.filter((org) => org.name?.toUpperCase()?.includes(f));
        pageClipping();
    }

    function pageClipping() {
        if (filteredMissions.length > pageSize) {
            visibleMissions = filteredMissions.slice(page * pageSize, (page + 1) * pageSize);
        } else {
            visibleMissions = filteredMissions;
        }
    }

    function forward() {
        const pos = page * pageSize;
        const maxSize = filteredMissions.length;
        if (pos + pageSize < maxSize) {
            page++;
            pageClipping();
        }
    }

    function backward() {
        if (page > 0) {
            page--;
            pageClipping();
        }
    }
</script>

<div class="bg-white grid col-span-3 p-2">
    <div class="w-full grid h-fit justify-end">
        <button class="w-fit h-fit relative cursor-pointer" onclick={close}>
            <img src={iconClose} alt="close navigation" />
        </button>
    </div>
    <nav class="row-span-14">
        <div class="relative text-gray-700 mb-10">
            <input
                bind:value={filter}
                onkeyup={performFilter}
                type="text"
                class="w-full border-1 border-solid rounded-sm p-2 border-primary-600 bg-white h-10 border-color"
                placeholder="Filter by"
            />
            <button
                class="absolute inset-y-0 right-0 flex items-center px-2 rounded-sm hover:bg-gray-100"
            >
                <img src={iconSearch} alt="Filter Missions" />
            </button>
        </div>

        <table class="w-full">
            <thead>
                <tr>
                    <th class="w-2/6">Name</th>
                    <th>Description</th>
                </tr>
            </thead>
            <tbody>
                {#each visibleMissions as mis}
                    <tr class="cursor-pointer hover:bg-gray-100" onclick={() => onSelect(mis)}>
                        <td class="p-2">{mis?.name || ""}</td>
                        <td class="p-2">{mis?.description || ""}</td>
                    </tr>
                {/each}
            </tbody>
        </table>

        <div class="pagination">
            <button onclick={backward}>
                <img class="w-8" src={iconBackward} alt="Backward" />
            </button>
            {`${page + 1} of ${Math.ceil((filteredMissions?.length ?? 0) / pageSize)} Pages`}
            <button onclick={forward}>
                <img class="w-8" src={iconForward} alt="Forward" />
            </button>
        </div>
    </nav>
</div>

<style>
    .pagination {
        margin-top: 10px;
        display: flex;
        align-items: center;
        justify-content: center;
    }
</style>
