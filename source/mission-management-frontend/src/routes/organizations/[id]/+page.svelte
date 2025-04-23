<!--
 Copyright 2025 THE PSI CONSORTIUM

 SPDX-License-Identifier: Apache-2.0
 -->

<script lang="ts">
    import { capitalize } from "$lib/common/strings";
    import type { ContactMedium, Organization } from "$lib/domains/party";
    import type { PageProps } from "./$types";

    let { data }: PageProps = $props();
    let organization = $derived(data.organization);
    let webAddress = $derived(getWebAddress(organization));

    function getContactMediumValue(contactMedium: ContactMedium): string {
        if (contactMedium.mediumType === "email") {
            return contactMedium.characteristic?.emailAddress || "";
        }
        if (contactMedium.mediumType === "telephone") {
            return contactMedium.characteristic?.phoneNumber || "";
        }
        if (contactMedium.mediumType === "address") {
            return [
                contactMedium.characteristic?.street1,
                contactMedium.characteristic?.postCode,
                contactMedium.characteristic?.city,
                contactMedium.characteristic?.stateOrProvince
            ]
                .filter((item) => !!item)
                .join(", ");
        }
        return "";
    }

    function getWebAddress(organization: Organization): string | undefined {
        return organization.partyCharacteristic?.find((c) => c.name === "web")?.value.toString();
    }
</script>

<div class="flex flex-row justify-center items-center">
    <div class="border-primary-600 border-1 border-solid rounded-sm mt-50 w-[80%] p-6">
        <div>
            <h1>{organization?.tradingName || organization?.name || ""}</h1>
        </div>
        <div class="grid lg:grid-cols-2 justify-center w-full pt-6">
            <div class="w-full h-full">
                <p>Name: {organization?.name || ""}</p>
                <p>Trading Name: {organization?.tradingName || ""}</p>
                <p>Type: {organization?.nameType || ""}</p>
                <p>Status: {organization?.status || ""}</p>

                {#if !!webAddress}
                    <!-- <p>Web: <a href={webAddress} target="_blank">{webAddress}</a></p> -->
                    <p>Web: {webAddress}</p>
                {/if}
            </div>

            <div class="w-full h-full">
                <p class="pt-2">Contact:</p>
                {#each organization?.contactMedium || [] as contactMedium}
                    <p>
                        {capitalize(contactMedium.mediumType)}: {getContactMediumValue(
                            contactMedium
                        )}
                    </p>
                {/each}
            </div>
        </div>
    </div>
</div>
