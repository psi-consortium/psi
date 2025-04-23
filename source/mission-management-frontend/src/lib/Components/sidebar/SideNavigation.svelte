<!--
 Copyright 2025 THE PSI CONSORTIUM

 SPDX-License-Identifier: Apache-2.0
 -->

<script lang="ts">
    import MenuItem from "./MenuItem.svelte";

    let { url }: { url: URL } = $props();
    const path = $derived(url.pathname.split("/")[1]);

    const sections = ["Open", "Missions", "Templates", "Organizations"];
</script>

<div class="grid justify-center fixed h-[100%] grid-cols-1 w-25 z-100">
    <div class="bg-primary-500 grid justify-center h-[100%]">
        <div class="md:h-[50%] lg:h-[40%] grid grid-cols-1 gap-0 mt-10">
            {#each sections as section}
                <MenuItem {path} route={section.toLowerCase()} name={section} />
            {/each}
        </div>
    </div>
</div>

<style>
</style>
