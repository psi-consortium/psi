<!--
 Copyright 2025 THE PSI CONSORTIUM

 SPDX-License-Identifier: Apache-2.0
 -->

<script lang="ts">
    import iconClosePanel from "$lib/assets/arrow_menu_close_24dp_FFF_FILL0_wght400_GRAD0_opsz24.svg";
    import iconOpenPanel from "$lib/assets/arrow_menu_open_24dp_FFF_FILL0_wght400_GRAD0_opsz24.svg";
    import { goto } from "$app/navigation";

    import type { Mission } from "$lib/domains/mission";

    let {
        missions,
        templates,
        routeMis,
        routeTemp
    }: { missions: Mission[]; templates: Mission[]; routeMis: string; routeTemp: string } =
        $props();

    let visible = $state(true);
    function handleRoute(route: string) {
        goto(route);
    }
</script>

<div class="flex flex-row justify-start">
    {#if visible}
        <div class="w-75">
            <div class=" bg-secondary grid col-span-2 p-2 h-[100vh] grid-rows-10">
                <div class="grid w-full justify-end row-span-1">
                    <button
                        class="w-fit h-fit relative cursor-pointer"
                        onclick={() => (visible = false)}
                    >
                        <img src={iconClosePanel} alt="close navigation" />
                    </button>
                </div>
                <div class="grid w-full row-span-2 ml-2">
                    <button
                        onclick={() => {
                            handleRoute("/open");
                        }}
                        class="w-18 h-18 bg-white text-primary-500 grid justify-center hover:bg-gray-200 cursor-pointer rounded-sm text-xs p-1 mt-4"
                    >
                        Open Blank
                    </button>
                    <div class="w-[95%]">
                        <hr class=" mt-8" />
                        <p class="text-dark-brown-500 mt-3">Open Recent Mission</p>
                    </div>
                </div>
                <div class="grid grid-cols-3 justify-start ml-2 row-span-2">
                    {#each missions as mission}
                        <button
                            onclick={() => {
                                handleRoute(`/${routeMis}/${mission.id}`);
                            }}
                            class="w-18 h-18 bg-white text-primary-500 grid justify-center cursor-pointer hover:bg-gray-200 rounded-sm text-xs p-1 mt-4"
                        >
                            {mission.name}
                        </button>
                    {/each}
                </div>
                <div>
                    <hr class="mt-8" />
                    <p class="text-dark-brown-500 mt-3">Open Recent Template</p>
                </div>
                <div class="grid grid-cols-3 justify-start ml-2 row-span-2">
                    {#each templates as template}
                        <button
                            onclick={() => {
                                handleRoute(`/${routeTemp}/${template.id}`);
                            }}
                            class="w-18 h-18 bg-white text-primary-500 grid justify-center cursor-pointer hover:bg-gray-200 rounded-sm text-xs p-1 mt-4"
                        >
                            {template.name}
                        </button>
                    {/each}
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
