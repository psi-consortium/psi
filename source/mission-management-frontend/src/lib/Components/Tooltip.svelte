<!--
 Copyright 2025 THE PSI CONSORTIUM

 SPDX-License-Identifier: Apache-2.0
 -->

<script lang="ts">
    export let title = "";
    let isHovered = false;
    let x: number;
    let y: number;

    function mouseOver(event: MouseEvent) {
        isHovered = true;
        x = event.pageX + 5;
        y = event.pageY + 5;
    }
    function mouseMove(event: MouseEvent) {
        x = event.pageX + 5;
        y = event.pageY + 5;
    }
    function mouseLeave() {
        isHovered = false;
    }
</script>

<div
    role="tooltip"
    aria-roledescription="tooltip"
    on:mouseover={mouseOver}
    on:mouseleave={mouseLeave}
    on:mousemove={mouseMove}
    on:focus={mouseOver}
>
    <slot />
</div>

{#if isHovered}
    <div style="top: {y}px; left: {x}px;" class="tooltip">{title}</div>
{/if}

<style>
    .tooltip {
        border: 1px solid #ddd;
        box-shadow: 1px 1px 1px #ddd;
        background: white;
        border-radius: 4px;
        padding: 4px;
        position: absolute;
    }
</style>
