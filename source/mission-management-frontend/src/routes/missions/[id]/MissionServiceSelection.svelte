<!--
 Copyright 2025 THE PSI CONSORTIUM

 SPDX-License-Identifier: Apache-2.0
 -->

<script lang="ts">
    import type { Mission, MissionAsset } from "$lib/domains/mission";
    import { TeamProductSchemaType } from "$lib/domains/team";

    const {
        toMissionId: toMissionId,
        fromMission,
        fromType,
        onCancel
    }: {
        toMissionId: String;
        fromMission: Mission | undefined;
        fromType: string;
        onCancel: () => void;
    } = $props();

    const assets = $derived(
        fromMission?.asset
            ? fromMission.asset.filter(
                  (ma) => ma.targetProductSchema?.type !== TeamProductSchemaType
              )
            : []
    );

    // const placeRefs = $derived.by(fromMission?.place    });

    const teams = $derived(
        fromMission?.asset
            ? fromMission.asset.filter(
                  (ma) => ma.targetProductSchema?.type === TeamProductSchemaType
              )
            : []
    );

    // $inspect("PlaceRefs", placeRefs);

    function getType() {
        return fromType === "Mission" ? "Mission" : "MissionTemplate";
    }
</script>

<div class="mt-4">
    {#if assets.length}
        <h2 class="pb-2">{getType()} {fromMission?.name}</h2>
        <p class="pb-2">
            Please make your choice from the Service Specifications ({assets.length}), Geographies ({fromMission
                ?.place?.length}) and Teams ({teams?.length}), if available:
        </p>

        <h3 class="pt-4 pb-2">Defined Services</h3>

        <form method="POST" action="?/copyServices" class="border-primary-600">
            <input type="hidden" name="missionId" value={toMissionId} />
            <input type="hidden" name="fromMissionId" value={fromMission?.id} />
            <input type="hidden" name="fromMissionType" value={getType()} />
            {#each assets as asset}
                <div class="p-2">
                    <label>
                        <input type="checkbox" checked={true} name={`service:${asset.id}`} />
                        {asset.name} - {asset.targetProductSchema?.type}
                    </label>
                </div>
            {/each}

            <!-- <p class="pt-2">
                Please note that related Teams and Areas of the selected services are not copied for
                now.
            </p> -->

            {#if fromMission?.place?.length}
                <h3 class="pt-4 pb-2">Assigned Geographies</h3>

                {#each fromMission?.place as placeRef}
                    <div class="p-2">
                        <label>
                            <input type="checkbox" checked={true} name={`place:${placeRef.id}`} />
                            {placeRef.name}
                        </label>
                    </div>
                {/each}
            {/if}

            {#if teams?.length}
                <h3 class="pt-4 pb-2">Assigned Teams</h3>

                {#each teams as team}
                    <div class="p-2">
                        <label>
                            <input type="checkbox" checked={true} name={`team:${team.id}`} />
                            {team.name}
                        </label>
                    </div>
                {/each}
            {/if}

            <div class="flex flex-row w-full justify-end mt-6">
                <button
                    type="button"
                    class="mr-3 py-3 px-6 cursor-pointer border-primary-600 hover:bg-gray-100 border-1 border-solid rounded-sm"
                    onclick={onCancel}
                >
                    Cancel
                </button>

                <button
                    type="submit"
                    class="p-2 rounded-sm bg-primary-600 hover:bg-primary-500 text-white disabled:bg-gray-400 disabled:cursor-default"
                >
                    Copy Selected Services</button
                >
            </div>
        </form>
    {:else}
        <p class="pb-2">
            The selected mission contains {assets.length} service configurations.
        </p>
    {/if}
</div>
