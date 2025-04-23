<!--
 Copyright 2025 THE PSI CONSORTIUM

 SPDX-License-Identifier: Apache-2.0
 -->

<script lang="ts">
    import Form from "$lib/Components/common/Form.svelte";
    import type { PageProps } from "./$types";
    let { form }: PageProps = $props();
</script>

<div class="border-primary-600 border-1 border-solid rounded-sm w-fit h-fit p-6 m-auto">
    <h1>Mission/MissionTemplate Creation</h1>
    <Form {form} />
</div>
