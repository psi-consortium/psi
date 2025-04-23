<!--
 Copyright 2025 THE PSI CONSORTIUM

 SPDX-License-Identifier: Apache-2.0
 -->

<script>
    import iconArrowDown from "$lib/assets/keyboard_arrow_down_24dp_1F1F1F_FILL0_wght400_GRAD0_opsz24.svg";
    import iconArrowLeft from "$lib/assets/keyboard_arrow_left_24dp_1F1F1F_FILL0_wght400_GRAD0_opsz24.svg";

    let { expanded = $bindable(false), header, children } = $props();
</script>

<div>
    <div class="w-full flex flex-row justify-between text-gray-700">
        {@render header?.()}

        <button
            class="p-2 self-center rounded-sm hover:bg-gray-100"
            onclick={() => (expanded = !expanded)}
        >
            <img src={expanded ? iconArrowDown : iconArrowLeft} alt="Collapse/Expand" />
        </button>
    </div>

    <div class="contents" hidden={!expanded}>
        {@render children?.()}
    </div>
</div>
