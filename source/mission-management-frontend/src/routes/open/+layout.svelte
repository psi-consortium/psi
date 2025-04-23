<!--
 Copyright 2025 THE PSI CONSORTIUM

 SPDX-License-Identifier: Apache-2.0
 -->

<script lang="ts">
    import SubNavigationOpen from "$lib/Components/sidebar/SubNavigationOpen.svelte";
    import type { LayoutProps } from "./$types";

    let { data, children }: LayoutProps = $props();
    let missions = $derived(data.missions);
    let templates = $derived(data.templates);
    let routeMis = "missions";
    let routeTemp = "templates";
</script>

<div class="flex flex-row justify-start">
    <SubNavigationOpen {missions} {templates} {routeMis} {routeTemp} />
    <div class="w-full grid grid-rows-1 justify-center">
        {@render children()}
    </div>
</div>
