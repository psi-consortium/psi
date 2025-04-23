<!--
 Copyright 2025 THE PSI CONSORTIUM

 SPDX-License-Identifier: Apache-2.0
 -->

<script lang="ts">
    import iconClosePanel from "$lib/assets/arrow_menu_close_24dp_FFF_FILL0_wght400_GRAD0_opsz24.svg";
    import iconOpenPanel from "$lib/assets/arrow_menu_open_24dp_FFF_FILL0_wght400_GRAD0_opsz24.svg";
    import iconForward from "$lib/assets/chevron_forward_24dp_1F1F1F_FILL0_wght400_GRAD0_opsz24.svg";
    import iconBackward from "$lib/assets/chevron_backward_24dp_1F1F1F_FILL0_wght400_GRAD0_opsz24.svg";
    import iconSearch from "$lib/assets/search_24dp_1F1F1F_FILL0_wght400_GRAD0_opsz24.svg";
    import { goto } from "$app/navigation";

    import type { Organization } from "$lib/domains/party";
    import type { Mission } from "$lib/domains/mission";
    import { onMount } from "svelte";

    let { data, route }: { data: Organization[] | Mission[]; route: string } = $props();

    let filteredData: Array<Organization | Mission> = $state(data);
    let visibleData: Array<Organization | Mission> = $state([]);
    let page = $state(0);
    let pageSize = $state(9);

    onMount(() => {
        pageClipping();
    });

    let visible = $state(true);
    function handleRoute(route: string) {
        goto(route);
    }

    let filter = $state("");

    function performFilter() {
        if (!filter) {
            filteredData = data;
        }
        const f = filter.toUpperCase();
        filteredData = data.filter((org) => org.name?.toUpperCase()?.includes(f));
        pageClipping();
    }

    function pageClipping() {
        if (filteredData.length > pageSize) {
            visibleData = filteredData.slice(page * pageSize, (page + 1) * pageSize);
        } else {
            visibleData = filteredData;
        }
    }

    function forward() {
        const pos = page * pageSize;
        const maxSize = filteredData.length;
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
    const title = route.slice(0, 1).toUpperCase() + route.slice(1);

    const getTitle = () => {
        if (route === "organizations") {
            return "Parties";
        } else {
            return title;
        }
    };
</script>

<div class="flex flex-row justify-start fixed">
    {#if visible}
        <div class="w-75 relative">
            <div class=" bg-secondary grid col-span-2 p-2 h-[100vh] grid-rows-8">
                <div class="grid w-full justify-end row-span-1">
                    <button class="w-fit h-fit cursor-pointer" onclick={() => (visible = false)}>
                        <img src={iconClosePanel} alt="close navigation" />
                    </button>
                </div>
                <h1 class="ml-2 row">{getTitle()}</h1>
                <div class="relative text-gray-700 mb-10">
                    <input
                        bind:value={filter}
                        onkeyup={performFilter}
                        type="text"
                        class="bg-white h-10 w-full rounded-sm p-2"
                        placeholder="Filter by"
                    />
                    <button
                        class="absolute inset-y-2 right-0 flex items-center px-2 h-fit cursor-pointer rounded-sm hover:bg-gray-100"
                    >
                        <img src={iconSearch} alt="Filter Data" />
                    </button>
                </div>
                <div class="grid grid-cols-3 justify-start ml-2 row-span-3">
                    {#each visibleData as dataItem}
                        <button
                            onclick={() => {
                                handleRoute(`/${route}/${dataItem.id}`);
                            }}
                            class="w-18 h-18 bg-white text-primary-500 grid justify-center cursor-pointer hover:bg-gray-200 rounded-sm text-xs p-1 mt-4"
                        >
                            {dataItem.name}
                        </button>
                    {/each}
                </div>
                <div class="mt-2.5 flex flex-row justify-around h-2">
                    <button onclick={backward}>
                        <img class="w-8" src={iconBackward} alt="Backward" />
                    </button>
                    <span class="h-fit">
                        {`${page + 1} of ${Math.ceil(filteredData.length / pageSize)} Pages`}
                    </span>
                    <button onclick={forward}>
                        <img class="w-8" src={iconForward} alt="Forward" />
                    </button>
                </div>
            </div>
        </div>
    {/if}
    {#if !visible}
        <div class="bg-secondary grid w-10 col-span-2 p-2 h-[100vh]">
            <button class="w-fit h-fit cursor-pointer" onclick={() => (visible = true)}>
                <img src={iconOpenPanel} alt="show navigation" />
            </button>
        </div>
    {/if}
</div>
