<!--
 Copyright 2025 THE PSI CONSORTIUM

 SPDX-License-Identifier: Apache-2.0
 -->

<script lang="ts">
    import Modal from "$lib/Components/common/Modal.svelte";
    import iconAdd from "$lib/assets/add_24dp_1F1F1F_FILL0_wght400_GRAD0_opsz24.svg";
    import iconDelete from "$lib/assets/delete_24dp_1F1F1F_FILL0_wght400_GRAD0_opsz24.svg";
    import iconMoveDown from "$lib/assets/move_down_24dp_1F1F1F_FILL0_wght400_GRAD0_opsz24.svg";
    import warning from "$lib/assets/warning_24dp_EA3323_FILL0_wght400_GRAD0_opsz24.svg";
    import type { ServiceTemplate } from "$lib/domains/serviceTemplate";
    import Bandwidth from "$lib/Components/Mission/MissionServices/Bandwidth.svelte";
    import InternetAccess from "$lib/Components/Mission/MissionServices/InternetAccess.svelte";
    import VPN from "$lib/Components/Mission/MissionServices/VPN.svelte";
    import Terminal from "$lib/Components/Mission/MissionServices/Terminal.svelte";
    import {
        type Mission,
        type MissionAsset,
        type TargetProductSchema
    } from "$lib/domains/mission";
    import Collapsible from "$lib/Components/common/Collapsible.svelte";
    import { TeamProductSchemaType, type Team } from "$lib/domains/team";
    import MissionAssetCopy from "./MissionAssetCopy.svelte";
    import Tooltip from "$lib/Components/Tooltip.svelte";
    import { isValidateMissionAsset } from "$lib/Components/Mission/MissionServices/missionServices";

    let {
        serviceTemplates,
        mission,
        mode = "Mission",
        readOnly
    }: {
        serviceTemplates: ServiceTemplate[];
        mission: Mission;
        mode?: string;
        readOnly: boolean;
    } = $props();

    let assets: Array<MissionAsset> = $derived.by(() => {
        if (!mission.asset) {
            return [];
        }
        return mission.asset.filter(
            (asset) => asset.targetProductSchema?.type !== TeamProductSchemaType
        );
    });

    let derivedTeams: Team[] = $derived.by(() => {
        if (!mission.asset) {
            return [];
        }
        return mission.asset
            .filter((asset) => asset.targetProductSchema?.type === TeamProductSchemaType)
            .map((asset) => {
                return { id: asset.id || "", name: asset.name || "" };
            });
    });

    let showServices = $state(true);
    let showServiceSelection = $state(false);
    let activeAsset: MissionAsset | null = $state(null);
    let selectedMissionForAssetCopy: Mission | null = $state(null);

    function showServiceTemplateSelection() {
        showServiceSelection = true;
        showServices = true;
    }

    function addServiceTemplate(service: ServiceTemplate) {
        const newAsset: MissionAsset = {
            targetProductSchema: {
                type: service.relatedType,
                schemaLocation: ""
            } as TargetProductSchema,
            assetRelationship: []
        };
        editAsset(newAsset);
    }

    function editAsset(asset: MissionAsset | null) {
        activeAsset = asset;
    }
    function closeAssetEditor() {
        activeAsset = null;
    }

    function camelToWords(camel: string): string {
        return camel.replace(/([a-z])([A-Z])/g, "$1 $2");
    }

    let showServiceClone = $state(false);
    function openServiceClone() {
        showServices = true;
        showServiceClone = true;
    }
    function closeServiceClone() {
        showServiceClone = false;
        selectedMissionForAssetCopy = null;
    }

    function isMissionMode() {
        return mode === "Mission";
    }
</script>

<Collapsible bind:expanded={showServices}>
    {#snippet header()}
        <div class="w-full flex flex-row justify-between text-gray-700">
            <h1 class="leading-none self-center">Service Needs</h1>
            {#if !readOnly}
                <div>
                    <button
                        class="rounded-sm p-2 self-center cursor-pointer hover:bg-gray-100"
                        onclick={openServiceClone}
                    >
                        <Tooltip title="Copy Service Needs from Mission or MissionTemplate">
                            <img
                                src={iconMoveDown}
                                alt="Copy Service Needs from Mission or MissionTemplate"
                            />
                        </Tooltip>
                    </button>
                    <button
                        class="rounded-sm p-2 self-center cursor-pointer hover:bg-gray-100"
                        onclick={showServiceTemplateSelection}
                    >
                        <Tooltip title="Add Service Needs">
                            <img src={iconAdd} alt="Add Service Needs" />
                        </Tooltip>
                    </button>
                </div>
            {/if}
        </div>
    {/snippet}

    <div>
        {#if assets === null || assets.length === 0}
            <div class="w-full p-2">
                <p>Please start the definition of Services by pressing the plus button above.</p>
            </div>
        {/if}
        {#each assets as asset}
            {#if !readOnly}
                <div
                    class="w-full p-2 flex flex-row justify-between rounded-sm text-gray-700 bg-white hover:bg-gray-100"
                >
                    <!-- svelte-ignore a11y_no_static_element_interactions -->
                    <!-- svelte-ignore a11y_click_events_have_key_events -->
                    <div
                        onclick={() => {
                            editAsset(asset);
                        }}
                        class="w-full h-fit grid justify-start cursor-pointer"
                    >
                        <div class="flex flex-row">
                            {#if isMissionMode() && !isValidateMissionAsset(asset)}
                                <Tooltip title="Please finalize Service Specification">
                                    <img class="pr-2" src={warning} alt="Warning" />
                                </Tooltip>
                            {/if}
                            <p>
                                {`${asset.name} (${asset.targetProductSchema?.type || ""})`}
                            </p>
                        </div>
                    </div>

                    <form method="POST" action="?/deleteService">
                        <input type="hidden" name="missionId" value={mission.id} />
                        <input type="hidden" name="id" value={asset.id} />
                        <button class="cursor-pointer" aria-label="Delete Asset">
                            <img src={iconDelete} alt="Delete Service Needs" />
                        </button>
                    </form>
                </div>
            {:else}
                <div
                    class="w-full p-2 flex flex-row justify-between rounded-sm text-gray-700 bg-white hover:bg-gray-100"
                >
                    <div class="w-full h-fit grid justify-start">
                        <div class="flex flex-row">
                            {#if isMissionMode() && !isValidateMissionAsset(asset)}
                                <Tooltip title="Please finalize Service Specification">
                                    <img class="pr-2" src={warning} alt="Warning" />
                                </Tooltip>
                            {/if}
                            <p>
                                {`${asset.name} (${asset.targetProductSchema?.type || ""})`}
                            </p>
                        </div>
                    </div>
                </div>
            {/if}
        {/each}
    </div>
</Collapsible>

<Modal
    name="ServiceTemplate Selection"
    bind:showModal={showServiceSelection}
    easyClose={true}
    onClose={() => {}}
    width="w-lg"
    height="h-1/2"
    title="Select a Service"
>
    <div>
        <div class="grid grid-cols-1 mt-3">
            {#each serviceTemplates as serviceTemplate}
                <button
                    onclick={() => {
                        addServiceTemplate(serviceTemplate);
                    }}
                    class="w-full h-fit p-3 mb-0.5 bg-white grid justify-start cursor-pointer rounded-sm hover:bg-gray-100"
                >
                    {serviceTemplate.name}
                </button>
            {/each}
        </div>
    </div>
</Modal>

<Modal
    name="Mission/Template Selection"
    bind:showModal={showServiceClone}
    easyClose={false}
    width="w-3xl"
    onClose={closeServiceClone}
    title="Service Copying"
>
    <MissionAssetCopy
        missionId={mission.id}
        bind:selectedMission={selectedMissionForAssetCopy}
        onCancel={closeServiceClone}
    />
</Modal>

<Modal
    name="MissionService Editor"
    showModal={activeAsset != null}
    easyClose={false}
    onClose={closeAssetEditor}
    title={camelToWords(activeAsset?.targetProductSchema?.type || "") + " Definition"}
>
    <div class="w-full h-fit">
        {#if activeAsset?.targetProductSchema?.type === "Bandwidth"}
            <Bandwidth
                missionMode={isMissionMode()}
                missionId={mission.id}
                places={mission.place || []}
                teams={derivedTeams}
                asset={activeAsset}
                onCancel={closeAssetEditor}
            />
        {:else if activeAsset?.targetProductSchema?.type === "InternetAccess"}
            <InternetAccess
                missionMode={isMissionMode()}
                {mission}
                asset={activeAsset}
                teams={derivedTeams}
                onCancel={closeAssetEditor}
            />
        {:else if activeAsset?.targetProductSchema?.type === "VPN"}
            <VPN
                missionMode={isMissionMode()}
                {mission}
                asset={activeAsset}
                teams={derivedTeams}
                onCancel={closeAssetEditor}
            />
        {:else if activeAsset?.targetProductSchema?.type === "Terminal"}
            <Terminal
                missionMode={isMissionMode()}
                missionId={mission.id}
                places={mission.place || []}
                teams={derivedTeams}
                asset={activeAsset}
                onCancel={closeAssetEditor}
            />
        {:else}
            <p class="text-2xl font-bold">
                Unknown MissionService type {activeAsset?.targetProductSchema?.type || ""}
            </p>
        {/if}
    </div>
</Modal>
