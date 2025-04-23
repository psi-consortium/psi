<!--
 Copyright 2025 THE PSI CONSORTIUM

 SPDX-License-Identifier: Apache-2.0
 -->

<script lang="ts">
    import iconClose from "$lib/assets/close_24dp_1F1F1F_FILL0_wght400_GRAD0_opsz24.svg";

    let {
        name,
        width = "w-xl",
        height = "h-10/12",
        easyClose = true,
        showModal = $bindable(),
        onClose,
        children,
        title
    }: {
        name: string;
        width?: string;
        height?: string;
        easyClose: boolean;
        showModal: boolean;
        onClose: () => void;
        children: any;
        title: string;
    } = $props();

    let dialog: HTMLDialogElement | undefined = $state(); // HTMLDialogElement

    $effect(() => {
        if (showModal) {
            dialog?.showModal();
        } else {
            dialog?.close();
            onClose && onClose();
        }
    });

    function closeModal() {
        showModal = false;
    }
</script>

<!-- svelte-ignore a11y_click_events_have_key_events, a11y_no_noninteractive_element_interactions -->
<dialog
    id={name}
    class="{width} {height} rounded-sm self-center m-auto"
    bind:this={dialog}
    onclose={closeModal}
    onclick={(e) => {
        if (!easyClose) return;
        if (e.target === dialog) dialog.close();
    }}
>
    <div class="p-4">
        <div class="w-full flex flex-row justify-between text-gray-700 mb-4">
            <h1 class="leading-none self-center">
                {title}
            </h1>
            <button
                type="button"
                class="p-2 cursor-pointer self-center rounded-sm hover:bg-gray-100"
                onclick={closeModal}
            >
                <img src={iconClose} alt="Close Icon" />
            </button>
        </div>

        <hr />

        <div class="pt-4">
            {@render children?.()}
        </div>
    </div>
</dialog>
