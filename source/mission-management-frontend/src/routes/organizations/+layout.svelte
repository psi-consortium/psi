<!--
 Copyright 2025 THE PSI CONSORTIUM

 SPDX-License-Identifier: Apache-2.0
 -->

<script lang="ts">
    import type { LayoutProps } from "./$types";
    import SubNavigation from "$lib/Components/sidebar/SubNavigation.svelte";

    let { data, children }: LayoutProps = $props();
    let organizations = $derived(data.organizations);
    let route = "organizations";
</script>

<div class="flex flex-row justify-start">
    <SubNavigation data={organizations} {route} />
    <div class="w-full grid grid-rows-1 justify-center">
        {@render children()}
    </div>
</div>
