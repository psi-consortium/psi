<!--
 Copyright 2025 THE PSI CONSORTIUM

 SPDX-License-Identifier: Apache-2.0
 -->

<script lang="ts">
    import warning from "$lib/assets/warning_24dp_EA3323_FILL0_wght400_GRAD0_opsz24.svg";
    import type { Mission, MissionAsset } from "$lib/domains/mission";
    import type { Team } from "$lib/domains/team";
    import {
        frequencyBands,
        getCharacteristicValue,
        getRefDefault,
        getRelatedAssets,
        RelationshipTypeTeam
    } from "./missionServices";

    let {
        missionMode = true,
        mission,
        asset,
        teams,
        onCancel
    }: {
        missionMode?: boolean;
        mission: Mission;
        asset: MissionAsset | null;
        teams: Team[];
        onCancel: () => void;
    } = $props();
    let frequencyBand = $derived(getCharacteristicValue(asset, "frequencyBand") as string);
    let forwardCIR = $derived(getCharacteristicValue(asset, "forwardCIR") as number);
    let forwardPIR = $derived(getCharacteristicValue(asset, "forwardPIR") as number);
    let returnCIR = $derived(getCharacteristicValue(asset, "returnCIR") as number);
    let returnPIR = $derived(getCharacteristicValue(asset, "returnPIR") as number);
    let latency = $derived(getCharacteristicValue(asset, "latency") as number);
    let availability = $derived(getCharacteristicValue(asset, "availability") as number);

    let relatedTeams = $derived(getRelatedAssets(asset, RelationshipTypeTeam));
    let relatedPlaceRef = $derived(getRefDefault(asset));

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
    <input type="hidden" name="missionId" value={mission.id} />
    <input type="hidden" name="id" value={asset?.id || ""} />
    <input type="hidden" name="type" value="InternetAccess" />

    <div class="col-start-1 col-span-10 grid">
        <label class="text-xs p-0 m-0 mb-1" for="assetName">Service Name*</label>
        <input
            name="assetName"
            class="p-1.5 border-primary-600 border-1 border-solid rounded-sm"
            type="text"
            placeholder="Name"
            value={asset?.name}
            required
            use:focusInput
        />
    </div>

    {#if teams && teams.length}
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
        {#if mission.place && mission.place.length}
            <select
                name="place"
                class="p-1.5 border-primary-600 border-1 border-solid rounded-sm cursor-pointer"
                value={relatedPlaceRef?.id + ";" + relatedPlaceRef?.name}
                placeholder="Service Area"
                required={missionMode}
            >
                {#each mission.place || [] as place}
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
            class="p-1.5 border-primary-600 border-1 border-solid rounded-sm cursor-pointer"
            value={frequencyBand}
            required={missionMode}
        >
            {#each frequencyBands as freqBand}
                <option value={freqBand}>{freqBand}</option>
            {/each}
        </select>
    </div>

    <div class="col-start-1 col-span-10 grid">
        <label class="text-xs p-0 m-0 mb-1" for="forwardCIR">
            Forward CIR{missionMode ? "*" : ""}
        </label>
        <input
            name="forwardCIR"
            class="p-1.5 border-primary-600 border-1 border-solid rounded-sm"
            type="number"
            min="1"
            placeholder="Forward CIR"
            value={forwardCIR}
            required={missionMode}
        />
    </div>
    <div class="col-span-2 self-center mt-4 ml-2">kbit/s</div>

    <div class="col-start-1 col-span-10 grid">
        <label class="text-xs p-0 m-0 mb-1" for="forwardPIR">
            Forward PIR{missionMode ? "*" : ""}
        </label>
        <input
            name="forwardPIR"
            class="p-1.5 border-primary-600 border-1 border-solid rounded-sm"
            type="number"
            min="1"
            placeholder="Forward PIR"
            value={forwardPIR}
            required={missionMode}
        />
    </div>
    <div class="col-span-2 self-center mt-4 ml-2">kbit/s</div>

    <div class="col-start-1 col-span-10 grid">
        <label class="text-xs p-0 m-0 mb-1" for="returnCIR">
            Return CIR{missionMode ? "*" : ""}
        </label>
        <input
            name="returnCIR"
            class="p-1.5 border-primary-600 border-1 border-solid rounded-sm"
            type="number"
            min="0"
            placeholder="Return CIR"
            value={returnCIR}
            required={missionMode}
        />
    </div>
    <div class="col-span-2 self-center mt-4 ml-2">kbit/s</div>

    <div class="col-start-1 col-span-10 grid">
        <label class="text-xs p-0 m-0 mb-1" for="returnPIR">
            Return PIR{missionMode ? "*" : ""}
        </label>
        <input
            name="returnPIR"
            class="p-1.5 border-primary-600 border-1 border-solid rounded-sm"
            type="number"
            min="1"
            placeholder="Return PIR"
            value={returnPIR}
            required={missionMode}
        />
    </div>
    <div class="col-span-2 self-center mt-4 ml-2">kbit/s</div>

    <div class="col-start-1 col-span-10 grid">
        <label class="text-xs p-0 m-0 mb-1" for="latency">
            Max. Latency{missionMode ? "*" : ""}
        </label>
        <input
            name="latency"
            class="p-1.5 border-primary-600 border-1 border-solid rounded-sm"
            type="number"
            min="1"
            placeholder="Max. Latency"
            value={latency}
            required={missionMode}
        />
    </div>
    <div class="col-span-2 self-center mt-4 ml-2">ms</div>

    <div class="col-start-1 col-span-10 grid">
        <label class="text-xs p-0 m-0 mb-1" for="availability">
            Availability{missionMode ? "*" : ""}
        </label>
        <input
            name="availability"
            class="p-1.5 border-primary-600 border-1 border-solid rounded-sm"
            type="number"
            step=".01"
            min="95"
            max="99.99"
            placeholder="Availability in Percent"
            value={asset?.id ? availability : 99.5}
            required={missionMode}
        />
    </div>
    <div class="col-span-2 self-center mt-4 ml-2">%</div>

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
