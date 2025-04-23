<!--
 Copyright 2025 THE PSI CONSORTIUM

 SPDX-License-Identifier: Apache-2.0
 -->

<script lang="ts">
    import { onMount } from "svelte";
    import Map from "ol/Map.js";
    import "ol/ol.css";
    import View from "ol/View.js";
    import Draw from "ol/interaction/Draw.js";
    import Modify from "ol/interaction/Modify.js";
    import Select from "ol/interaction/Select.js";
    import Snap from "ol/interaction/Snap.js";
    import TileLayer from "ol/layer/Tile.js";
    import VectorLayer from "ol/layer/Vector.js";
    import { useGeographic } from "ol/proj.js";
    import OSM from "ol/source/OSM.js";
    import { defaults as defaultControls, Zoom, Attribution } from "ol/control";
    import VectorSource, { VectorSourceEvent } from "ol/source/Vector.js";
    import GeoJSON from "ol/format/GeoJSON.js";
    import { v4 as uuid } from "uuid";

    import { Feature, Overlay } from "ol";
    import type { Coordinate } from "ol/coordinate";
    import type { Mission, Place, PlaceRefOrValue } from "$lib/domains/mission";
    import type { Type } from "ol/geom/Geometry";
    import type { FeatureLike } from "ol/Feature";

    import iconEdit from "$lib/assets/edit_24dp_1F1F1F_FILL0_wght400_GRAD0_opsz24.svg";

    interface GeoJsonData {
        type: string;
        geometry: {
            type: string;
            coordinates: GeoJSON[];
        };
        properties: {
            id: string | undefined;
            name: string;
        };
    }

    let {
        mission
    }: {
        mission: Mission;
    } = $props();

    let selectedGeometryType: Type = $state("Point");
    let currentMode = $state("select");

    let map: Map;
    let draw: Draw;
    let snap: Snap;
    let modify: Modify;
    let selectInteraction: Select;
    let overlay: Overlay;
    let vectorSource = new VectorSource();
    const raster = new TileLayer({
        source: new OSM()
    });

    let nameInput: string = $state("");
    let editName: boolean = $state(false);

    let selectedFeature: FeatureLike | undefined = $state(undefined);

    const parisLonLat = [2.341082104539482, 48.57478991092884];

    onMount(() => {
        useGeographic();

        if (mission.place?.length) {
            const geoJsonData: GeoJsonData[] = [];
            const places = mission.place;

            places.forEach((pl) => {
                geoJsonData.push({
                    type: "Feature",
                    geometry: { type: pl.geoJson.type, coordinates: pl.geoJson.coordinates },
                    properties: { id: pl.id, name: pl.name || "" }
                });
            });
            vectorSource = new VectorSource({
                features: new GeoJSON().readFeatures({
                    type: "FeatureCollection",
                    features: geoJsonData
                })
            });
        }
        const vector = new VectorLayer({
            source: vectorSource,
            style: {
                "fill-color": "rgba(255, 255, 255, 0.5)",
                "stroke-color": "oklch(51.52% 0.0999 268.97)",
                "stroke-width": 3,
                "circle-radius": 7,
                "circle-fill-color": "oklch(51.52% 0.0999 268.97)"
            }
        });

        overlay = new Overlay({
            element: document.getElementById("popup") || undefined,
            positioning: "top-left"
        });

        map = new Map({
            layers: [raster, vector],
            target: "map",
            view: new View({
                center: parisLonLat,
                zoom: 4
            }),
            overlays: [overlay],
            controls: defaultControls().extend([new Zoom(), new Attribution()])
        });

        draw = new Draw({
            source: vectorSource,
            type: selectedGeometryType
        });
        snap = new Snap({ source: vectorSource });
        selectInteraction = new Select();
        modify = new Modify({ source: vectorSource, features: selectInteraction.getFeatures() });

        activateMode(currentMode);

        addModifications();

        vectorSource.on("addfeature", (event) => addPlace(event));
    });

    function activateMode(newMode: string) {
        if (newMode === "draw") {
            enableDrawMode();
        } else if (newMode === "select") {
            enableSelectMode();
        } else {
            console.error("Error, unknown Mode:", newMode);
        }
    }

    function enableDrawMode() {
        hidePlaceOverlay();
        currentMode = "draw";
        map.removeInteraction(selectInteraction);
        map.removeInteraction(modify);
        map.removeInteraction(snap);

        map.addInteraction(draw);
        map.addInteraction(snap); // must be added after the draw/modify interaction
    }

    function enableSelectMode() {
        hidePlaceOverlay();
        currentMode = "select";
        map.removeInteraction(draw);
        map.removeInteraction(snap);

        map.addInteraction(selectInteraction);
        map.addInteraction(modify);
        map.addInteraction(snap); // must be added after the draw/modify interaction
    }

    function handleTypeChange(event) {
        hidePlaceOverlay();
        map.removeInteraction(selectInteraction);
        map.removeInteraction(draw);
        map.removeInteraction(modify);
        map.removeInteraction(snap);

        selectedGeometryType = event?.target?.value;
        currentMode = "draw";

        draw = new Draw({
            source: vectorSource,
            type: selectedGeometryType
        });
        map.addInteraction(draw);
        snap = new Snap({ source: vectorSource });
        map.addInteraction(snap);
    }

    function isSelectionMode(): boolean {
        return currentMode === "select";
    }

    function addModifications() {
        map.on("click", (event) => {
            if (!isSelectionMode()) {
                return;
            }

            hidePlaceOverlay();
            const feature = map.forEachFeatureAtPixel(event.pixel, (feature) => {
                // Only features with an Id defined by us
                if (feature.get("id")) {
                    return feature;
                }
            });
            if (!feature) {
                return;
            }

            openPlaceOverlay(feature, event.coordinate);
        });
    }

    function hidePlaceOverlay() {
        overlay.setPosition(undefined);
        selectedFeature = undefined;
    }

    function openPlaceOverlay(feature: FeatureLike, coordinates: Coordinate) {
        selectedFeature = feature;

        const name = feature.get("name");
        nameInput = name;

        overlay.setPosition(coordinates);
        showName(name);
    }

    async function addPlace(event: VectorSourceEvent) {
        const feature = event.feature;
        setFeatureId(feature, uuid());
        for (let i = 1; i < 99999; i++) {
            const name = "Place " + i;
            if (vectorSource.getFeatures().findIndex((f) => f.get("name") === name) < 0) {
                setFeatureName(feature, name);
                break;
            }
        }

        storePlaces();

        const extent = feature?.getGeometry()?.getExtent();
        if (feature && extent) {
            openPlaceOverlay(feature, [(extent[0] + extent[2]) / 2, (extent[1] + extent[3]) / 2]);
            openNameEditor();
        }
    }

    async function storePlaces() {
        const newPlaces = collectPlaces();
        const formData = new FormData();
        formData.append("id", mission.id);
        formData.append("place", JSON.stringify(newPlaces));

        const response = await fetch("?/updatePlace", {
            method: "POST",
            body: formData
        });

        if (response.ok) {
            const result = await response.json();
            mission.place = newPlaces as PlaceRefOrValue[];
            return result;
        } else {
            console.error("Update failed:", await response.text());
        }
    }

    function collectPlaces(): Place[] {
        const features = vectorSource.getFeatures();
        return features.map((feature) => {
            const geometry = feature.getGeometry();
            const coordinates = geometry?.getCoordinates();
            const bbox = geometry?.getExtent();

            const id = getFeatureId(feature);
            const name = getFeatureName(feature);

            return {
                type: "GeographicLocation",
                bbox: bbox,
                geoJson: {
                    coordinates,
                    type: geometry?.getType()
                },
                name: name,
                id: id
            };
        });
    }

    function getFeatureId(featureLike: FeatureLike | undefined): string | undefined {
        if (!featureLike) {
            return undefined;
        }
        return featureLike.get("id");
    }

    function getFeatureName(featureLike: FeatureLike | undefined): string | undefined {
        if (!featureLike) {
            return undefined;
        }
        return featureLike.get("name");
    }

    function setFeatureName(feature: Feature | undefined, name: string): boolean {
        if (!feature) {
            return false;
        }
        feature.set("name", name);
        return true;
    }

    function setFeatureId(feature: Feature | undefined, id: string): boolean {
        if (!feature) {
            return false;
        }
        feature.set("id", id);
        return true;
    }

    function openNameEditor() {
        editName = true;
    }
    function closeNameEditor() {
        editName = false;
    }

    function showName(name: string) {
        closeNameEditor();
        const popupContent = document.getElementById("popup-content");
        if (popupContent) {
            popupContent.innerHTML = !name ? "No name set" : name;
        }
    }

    function getActiveFeature(): Feature | undefined {
        let result: Feature | undefined = undefined;
        vectorSource.getFeatures().forEach((feature) => {
            const featureId: string | undefined = getFeatureId(feature);

            if (featureId && featureId == getFeatureId(selectedFeature)) {
                result = feature;
            }
        });
        return result;
    }

    function updateName(newName: string) {
        const feature = getActiveFeature();
        if (!feature) {
            return;
        }

        setFeatureName(feature, newName);
        showName(newName);
        storePlaces();
    }

    function showOldName() {
        closeNameEditor();
        const feature = getActiveFeature();
        if (feature) {
            nameInput = feature.get("name");
        }
    }

    function saveChange() {
        overlay.setPosition(undefined);
        storePlaces();
    }

    function deleteSelectedFeatures() {
        overlay.setPosition(undefined);
        const feature = getActiveFeature();
        if (feature) {
            vectorSource.removeFeature(feature);
            storePlaces();
        }
    }

    function focusInput(node: HTMLInputElement) {
        $effect(() => {
            node.focus();
        });
    }
</script>

<div class="grid-cols-3">
    <div class="grid col-span-2">
        <div class="w-full flex flex-row justify-start gap-3 pb-4">
            <button
                class={currentMode === "draw"
                    ? "border-primary-600 bg-secondary text-white border-1 border-solid rounded-sm p-2 cursor-pointer"
                    : "border-primary-600 border-1 border-solid rounded-sm p-2 cursor-pointer"}
                onclick={enableDrawMode}>Enable Draw Mode</button
            >
            <button
                class={currentMode === "select"
                    ? "border-primary-600 bg-secondary text-white  border-1 border-solid rounded-sm p-2 cursor-pointer"
                    : "border-primary-600 border-1 border-solid rounded-sm p-2 cursor-pointer"}
                onclick={enableSelectMode}>Enable Select Mode</button
            >
            <button
                disabled={currentMode === "draw"}
                class="border-primary-600 border-1 border-solid rounded-sm p-2 cursor-pointer hover:bg-secondary hover:text-white disabled:cursor-default disabled:bg-gray-300 disabled:text-white disabled:border-gray-400"
                onclick={saveChange}>Save Change</button
            >
            <button
                disabled={currentMode === "draw" || selectedFeature == undefined}
                class="border-primary-600 border-1 border-solid rounded-sm p-2 cursor-pointer hover:bg-secondary hover:text-white disabled:cursor-default disabled:bg-gray-300 disabled:text-white disabled:border-gray-400"
                onclick={deleteSelectedFeatures}>Delete Selected Place</button
            >
        </div>
        {#if currentMode === "draw"}
            <form class="p-3 grid grid-cols-1 w-fit">
                <label for="typeSelection" class="mb-2">Geometry type:</label>
                <select
                    id="typeSelection"
                    bind:value={selectedGeometryType}
                    onchange={handleTypeChange}
                    class="border-primary-600 border-1 border-solid rounded-sm p-2 cursor-pointer"
                >
                    <option value="Point">Point</option>
                    <option value="Polygon">Polygon</option>
                </select>
            </form>
        {/if}
    </div>
</div>
<div id="map" class="map border-primary-600 border-1 border-solid rounded-sm"></div>
<div id="popup" class="ol-popup border-1 p-3 pt-4 pb-4 rounded-sm bg-white">
    {#if !editName}
        <div class="w-full flex flex-row justify-start gap-3 p-2">
            <div class="bg-white" id="popup-content">
                {nameInput}
            </div>
            <button class="w-fit h-fit relative cursor-pointer" onclick={openNameEditor}>
                <img src={iconEdit} alt="close navigation" />
            </button>
        </div>
    {:else}
        <form class="grid pl-3 p-2">
            <div>
                <input
                    class="border-primary-600 border-1 border-solid rounded-sm p-2"
                    type="text"
                    id="name"
                    bind:value={nameInput}
                    use:focusInput
                    onkeydown={(e: KeyboardEvent) => {
                        if (e.key === "Enter") {
                            updateName(nameInput);
                        } else if (e.key === "Escape") {
                            showOldName();
                        }
                    }}
                />
                <button
                    type="button"
                    onclick={() => {
                        updateName(nameInput);
                    }}
                    class="border-primary-600 border-1 border-solid rounded-sm p-2 cursor-pointer bg-secondary text-white disabled:cursor-default disabled:bg-gray-300 disabled:text-white disabled:border-gray-400"
                >
                    Rename
                </button>
            </div>
        </form>
    {/if}
</div>

<style>
    #map {
        width: 100%;
        height: 700px;
        border-radius: 0.25rem;
    }

    .ol-attribution {
        bottom: 10px;
        right: 10px;
    }
</style>
