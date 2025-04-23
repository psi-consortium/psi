<!--
 Copyright 2025 THE PSI CONSORTIUM

 SPDX-License-Identifier: Apache-2.0
 -->

<script lang="ts">
    import SubNavigation from "$lib/Components/sidebar/SubNavigation.svelte";
    import type { LayoutProps } from "./$types";

    let { data, children }: LayoutProps = $props();
    let missions = $derived(data.missions);
    let route = "missions";
</script>

<SubNavigation data={missions} {route} />

<div id="mis-content" class="ml-10 flex flex-row justify-start w-[calc(100%-2.5rem)]">
    {@render children()}
</div>
