<!--
 Copyright 2025 THE PSI CONSORTIUM

 SPDX-License-Identifier: Apache-2.0
 -->

<script lang="ts">
    import { capitalize } from "$lib/common/strings";
    import { MissionStatusType, type Mission } from "$lib/domains/mission";

    let {
        form,
        mission = undefined,
        type,
        readOnly = false
    }: {
        form: any;
        mission?: Mission;
        type?: string;
        readOnly?: boolean;
    } = $props();

    let name = $state("");
    let startDate: string | undefined = $state(undefined);
    let endDate: string | undefined = $state(undefined);

    $effect(() => {
        if (mission?.name) {
            name = mission.name;
        } else {
            name = "";
        }
        if (mission?.timeframe?.startDateTime) {
            startDate = toInputDate(mission?.timeframe?.startDateTime);
        } else {
            startDate = undefined;
        }
        if (mission?.timeframe?.endDateTime) {
            endDate = toInputDate(mission?.timeframe?.endDateTime);
        } else {
            endDate = undefined;
        }
    });

    function toInputDate(date: Date | undefined): string | undefined {
        if (!date) {
            return undefined;
        }
        return new Date(date.getTime() - new Date().getTimezoneOffset() * 60000)
            .toISOString()
            .slice(0, 19);
    }

    function isMissionMode() {
        return type === undefined || type === "Mission";
    }
    function isMissionTemplateMode() {
        return type === undefined || type === "MissionTemplate";
    }

    function requiredFields4MissionDefined(): boolean {
        return !!name && !!startDate;
    }
    function requiredFields4TemplateDefined(): boolean {
        return !!name;
    }

    function isUpdate() {
        return !!mission?.id;
    }
    function getTemplateButtonText() {
        return isUpdate() ? "Update" : "Create Template";
    }
    function getMissionButtonText() {
        return isUpdate() && isMissionMode() ? "Update" : "Create Mission";
    }
    function getMissionAction() {
        const prefix = isUpdate() && isMissionMode() ? "update" : "create";
        return "?/" + prefix + "Mission";
    }
    function getTemplateAction() {
        const prefix = isUpdate() ? "update" : "add";
        return "?/" + prefix + "MissionTemplate";
    }
    function getDeleteAction(): string {
        return "?/delete" + type;
    }

    function focusInput(node: HTMLInputElement) {
        $effect(() => {
            node.focus();
        });
    }
</script>

<form action={getMissionAction()} method="POST">
    <input type="hidden" name="id" value={mission?.id} />
    <input type="hidden" name="type" value={type} />
    <div class="grid grid-cols-1">
        <label class="text-xs p-0 m-0 mb-1 mt-4" for="name">Name</label>
        <input
            name="name"
            bind:value={name}
            class="{readOnly
                ? 'border-gray-300'
                : 'border-primary-600'} border-1 border-solid rounded-sm h-[2.5rem] p-1.5"
            type="text"
            placeholder="Name"
            required
            readonly={readOnly}
            use:focusInput
        />
    </div>
    <div class="grid grid-cols-1 pt-2">
        <label class="text-xs p-0 m-0 mb-1" for="startDate">Start Date</label>
        <input
            bind:value={startDate}
            type="datetime-local"
            name="startDate"
            class="{readOnly
                ? 'border-gray-300'
                : 'border-primary-600'} border-1 border-solid rounded-sm h-[2.5rem] p-1.5"
            readonly={readOnly}
        />
    </div>
    <div class="grid grid-cols-1 pt-2">
        <label class="text-xs p-0 m-0 mb-1" for="startDate">End Date</label>
        <input
            bind:value={endDate}
            type="datetime-local"
            name="endDate"
            class="{readOnly
                ? 'border-gray-300'
                : 'border-primary-600'} border-1 border-solid rounded-sm h-[2.5rem] p-1.5"
            readonly={readOnly}
        />
    </div>
    <div class="grid grid-cols-1 pt-2">
        <label class="text-xs p-0 m-0 mb-1" for="description">Description</label>
        <textarea
            name="description"
            placeholder="Description"
            value={mission?.description}
            class="{readOnly
                ? 'border-gray-300'
                : 'border-primary-600'} border-1 border-solid rounded-sm h-[10rem] p-1.5"
            readonly={readOnly}
        ></textarea>
    </div>
    <div class="grid grid-cols-1 pt-2">
        <label class="text-xs p-0 m-0 mb-1" for="category">Category</label>
        <input
            name="category"
            class="{readOnly
                ? 'border-gray-300'
                : 'border-primary-600'} border-1 border-solid rounded-sm h-[2.5rem] p-1.5"
            type="text"
            value={mission?.category}
            placeholder="Category"
            readonly={readOnly}
        />
    </div>
    {#if mission}
        <div class="grid grid-cols-1 pt-2">
            <label class="text-xs p-0 m-0 mb-1" for="name">Status</label>
            {#if !readOnly && (!mission?.status || mission?.status === MissionStatusType.Draft)}
                <div class="col-start-1 col-span-10 grid">
                    <select
                        name="status"
                        class="p-1.5 border-primary-600 border-1 border-solid rounded-sm cursor-pointer"
                        value={mission?.status || MissionStatusType.Draft}
                        placeholder="Mission Status"
                        required
                    >
                        <!-- Not all values of MissionStatusType! "Cancelled" or "Completed" belong to a processing -->
                        <option value={MissionStatusType.Draft}
                            >{capitalize(MissionStatusType.Draft)}</option
                        >
                        <option value={MissionStatusType.Active}
                            >{capitalize(MissionStatusType.Active)}</option
                        >
                    </select>
                </div>
            {:else}
                <input
                    name="status"
                    class="border-gray-300 border-1 border-solid rounded-sm h-[2.5rem] p-1.5"
                    type="text"
                    value={capitalize(mission.status)}
                    readonly
                />
            {/if}
        </div>
    {/if}

    {#if form?.success}
        <p class="text-bold text-green-800">Successfully submitted</p>
    {/if}

    {#if !readOnly}
        <div class="flex flex-row w-full justify-end gap-4 mt-8">
            {#if !isUpdate()}
                <button
                    type="reset"
                    class="border-primary-600 hover:bg-gray-100 border-1 border-solid rounded-sm py-3 px-6 cursor-pointer"
                >
                    Cancel
                </button>
            {:else}
                <button
                    formaction={getDeleteAction()}
                    class="border-primary-600 hover:bg-gray-100 border-1 border-solid rounded-sm py-3 px-6 cursor-pointer"
                >
                    Delete
                </button>
            {/if}
            {#if isMissionTemplateMode()}
                <button
                    formaction={getTemplateAction()}
                    class="bg-primary-600 hover:bg-primary-500 text-white rounded-sm py-3 px-6 cursor-pointer disabled:bg-gray-400 disabled:cursor-default"
                    disabled={!requiredFields4TemplateDefined()}
                >
                    {getTemplateButtonText()}
                </button>
            {/if}
            <button
                type="submit"
                class="bg-primary-600 hover:bg-primary-500 text-white rounded-sm py-3 px-6 cursor-pointer disabled:bg-gray-400 disabled:cursor-default"
                disabled={!requiredFields4MissionDefined()}
            >
                {getMissionButtonText()}
            </button>
        </div>
    {/if}
</form>

<style>
    :global(input.s-CRdfky8B1JGz) {
        border-color: white !important;
        width: 100% !important;
    }
</style>
