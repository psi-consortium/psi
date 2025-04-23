<!--
 Copyright 2025 THE PSI CONSORTIUM

 SPDX-License-Identifier: Apache-2.0
 -->

<script lang="ts">
    import warning from "$lib/assets/warning_24dp_EA3323_FILL0_wght400_GRAD0_opsz24.svg";
    import type { MissionAsset, Place } from "$lib/domains/mission";
    import type { Team } from "$lib/domains/team";
    import {
        frequencyBands,
        getCharacteristicValue,
        getRelatedAssets,
        RelationshipTypeTeam,
        transmissionTechs
    } from "./missionServices";

    let {
        missionMode = true,
        missionId,
        places,
        teams,
        asset,
        onCancel
    }: {
        missionMode?: boolean;
        missionId: string;
        places: Place[];
        teams: Team[];
        asset: MissionAsset | null;
        onCancel: () => void;
    } = $props();
    let frequencyBand = $derived(getCharacteristicValue(asset, "frequencyBand") as string);
    let reflectorDiameter = $derived(getCharacteristicValue(asset, "reflectorDiameter") as number);
    let uplinkPower = $derived(getCharacteristicValue(asset, "uplinkPower") as number);
    let transmissionTech = $derived(getCharacteristicValue(asset, "transmissionTech") as string);

    let relatedTeams = $derived(getRelatedAssets(asset, RelationshipTypeTeam));
    let relatedPlaceRef = $derived(asset?.place && asset.place.length ? asset.place[0] : undefined);

    function getType() {
        return missionMode ? "Mission" : "Template";
    }

    function focusInput(node: HTMLInputElement) {
        $effect(() => {
            node.focus();
        });
    }
</script>

<form class="border-primary-600 grid grid-cols-12 gap-1" action="?/addService" method="POST">
    <input type="hidden" name="missionId" value={missionId} />
    <input type="hidden" name="id" value={asset?.id || ""} />
    <input type="hidden" name="type" value="Terminal" />

    <div class="col-start-1 col-span-10 grid">
        <label class="text-xs p-0 m-0 mb-1" for="assetName">Service Name*</label>
        <input
            name="assetName"
            class="p-1.5 rounded-sm border-primary-600 border-1 border-solid"
            type="text"
            placeholder="Name"
            value={asset?.name}
            required
            use:focusInput
        />
    </div>

    {#if missionMode}
        <div class="col-start-1 col-span-10 grid">
            <label class="text-xs p-0 m-0 mb-1" for="name">Teams</label>
            <select
                name="teams"
                class="p-1.5 border-primary-600 border-1 border-solid rounded-sm cursor-pointer"
                value={relatedTeams.map((t) => `${t.id};${t.name}`)}
                placeholder="Select Teams"
                multiple
            >
                {#each teams as team}
                    <option value={`${team.id};${team.name}`}>{team.name}</option>
                {/each}
            </select>
        </div>
    {/if}

    <div class="col-start-1 col-span-10 grid">
        <label class="text-xs p-0 m-0 mb-1" for="name">Service Area{missionMode ? "*" : ""}</label>
        {#if places && places.length}
            <select
                name="place"
                class="p-1.5 border-primary-600 border-1 border-solid rounded-sm cursor-pointer"
                value={relatedPlaceRef?.id + ";" + relatedPlaceRef?.name}
                placeholder="Service Area"
                required={missionMode}
            >
                {#each places as place}
                    <option value={place.id + ";" + place.name}>{place.name}</option>
                {/each}
            </select>
        {:else}
            <div class="flex flex-row">
                {#if missionMode}
                    <img class="pr-2" src={warning} alt="Warning" />
                {/if}
                <p>{`No Places defined for the current ${getType()}.`}</p>
            </div>
        {/if}
    </div>

    <div class="col-start-1 col-span-10 grid">
        <label class="text-xs p-0 m-0 mb-1" for="frequencyBand">
            Frequency Band{missionMode ? "*" : ""}
        </label>
        <select
            name="frequencyBand"
            class="border-primary-600 border-1 border-solid rounded-sm p-2 cursor-pointer"
            value={frequencyBand}
            required={missionMode}
        >
            {#each frequencyBands as freqBand}
                <option value={freqBand}>{freqBand}</option>
            {/each}
        </select>
    </div>

    <div class="col-start-1 col-span-10 grid">
        <label class="text-xs p-0 m-0 mb-1" for="reflectorDiameter">
            Reflector Diameter{missionMode ? "*" : ""}
        </label>
        <input
            name="reflectorDiameter"
            class="p-1.5 rounded-sm border-primary-600 border-1 border-solid"
            type="number"
            min="1"
            placeholder="Reflector Diameter"
            value={reflectorDiameter}
            required={missionMode}
        />
    </div>
    <div class="col-span-2 self-center mt-4 ml-2">cm</div>

    <div class="col-start-1 col-span-10 grid">
        <label class="text-xs p-0 m-0 mb-1" for="uplinkPower">
            Uplink Power{missionMode ? "*" : ""}
        </label>
        <input
            name="uplinkPower"
            class="p-1.5 rounded-sm border-primary-600 border-1 border-solid"
            type="number"
            min="1"
            placeholder="Uplink Power"
            value={uplinkPower}
            required={missionMode}
        />
    </div>
    <div class="col-span-2 self-center mt-4 ml-2">W</div>

    <div class="col-start-1 col-span-10 grid">
        <label class="text-xs p-0 m-0 mb-1" for="transmissionTech">
            Transmission Tech{missionMode ? "*" : ""}
        </label>
        <select
            name="transmissionTech"
            class="border-primary-600 border-1 border-solid rounded-sm p-2 cursor-pointer"
            value={transmissionTech}
            required={missionMode}
        >
            {#each transmissionTechs as tt}
                <option value={tt}>{tt}</option>
            {/each}
        </select>
    </div>

    <div class="col-start-1 col-span-12 flex flex-row justify-end w-full mt-10">
        <button
            type="button"
            class="mr-3 py-3 px-6 cursor-pointer border-primary-600 hover:bg-gray-100 border-1 border-solid rounded-sm"
            onclick={onCancel}
        >
            Cancel
        </button>

        <button
            type="submit"
            class="px-6 py-3 rounded-sm bg-primary-600 hover:bg-primary-500 text-white disabled:bg-gray-400 disabled:cursor-default"
        >
            Save
        </button>
    </div>
</form>
