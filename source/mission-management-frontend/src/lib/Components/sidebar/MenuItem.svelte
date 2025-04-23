<!--
 Copyright 2025 THE PSI CONSORTIUM

 SPDX-License-Identifier: Apache-2.0
 -->

<script lang="ts">
    import { goto } from "$app/navigation";
    let { path, route, name } = $props();

    const getName = () => {
        if (name === "Organizations") {
            return "Parties";
        } else {
            return name;
        }
    };
</script>

<button
    class={path === route
        ? "w-18 h-18 bg-secondary text-white grid justify-center cursor-pointer rounded-sm text-xs p-1"
        : "w-18 h-18 bg-primary-600 text-white hover:bg-dark-blue grid justify-center cursor-pointer rounded-sm text-xs p-1"}
    onclick={() => {
        goto(`/${route}`);
    }}
>
    {getName()}
</button>
