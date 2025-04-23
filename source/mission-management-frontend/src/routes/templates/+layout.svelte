<!--
 Copyright 2025 THE PSI CONSORTIUM

 SPDX-License-Identifier: Apache-2.0
 -->

<script lang="ts">
    import type { LayoutProps } from "./$types";
    import SubNavigation from "$lib/Components/sidebar/SubNavigation.svelte";

    let { data, children }: LayoutProps = $props();
    let templates = $derived(data.missionTemplates);
    let route = "templates";
</script>

<SubNavigation data={templates} {route} />

<div id="template content" class="ml-10 flex flex-row justify-start w-[calc(100%-2.5rem)]">
    {@render children()}
</div>
