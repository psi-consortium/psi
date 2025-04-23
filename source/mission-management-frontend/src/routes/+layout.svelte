<!--
 Copyright 2025 THE PSI CONSORTIUM

 SPDX-License-Identifier: Apache-2.0
 -->

<script lang="ts">
    import SideNavigation from "$lib/Components/sidebar/SideNavigation.svelte";
    import "../app.css";
    import type { LayoutProps } from "./$types";
    import { page } from "$app/state";

    let { children }: LayoutProps = $props();
    const url = $derived(page.url);
</script>

<SideNavigation {url} />

<!-- Sidebar has a fix part of 25 and another hovering part of 75 -->
<!-- Tailwind spacing unit 1 = 0.25rem = 4px -->
<main id="app" class="ml-25 w-[calc(100%-6.25rem)] h-full">
    {@render children()}
</main>
