<!--
 Copyright 2025 THE PSI CONSORTIUM

 SPDX-License-Identifier: Apache-2.0
 -->

<script lang="ts">
    import { onMount } from "svelte";
    import { Chart } from "chart.js/auto";
    import "chartjs-adapter-date-fns";

    onMount(() => {
        const ctx = document.getElementById("ganttChart") as HTMLCanvasElement;

        if (ctx) {
            new Chart(ctx, {
                type: "bar",
                data: {
                    datasets: [
                        {
                            label: "Task 1",
                            data: [
                                {
                                    x: new Date("2025-04-01"),
                                    x2: new Date("2025-04-05"),
                                    y: "Task 1"
                                }
                            ],
                            backgroundColor: "rgba(245, 40, 145, 0.8)",
                            borderColor: "rgba(245, 40, 145, 0.8)",
                            borderWidth: 15
                        },
                        {
                            label: "Task 2",
                            data: [
                                {
                                    x: new Date("2025-04-03"),
                                    x2: new Date("2025-04-07"),
                                    y: "Task 2"
                                }
                            ],
                            backgroundColor: "rgba(40, 145, 245, 0.8)",
                            borderColor: "rgba(40, 145, 245, 0.8)",
                            borderWidth: 15
                        }
                    ]
                },
                options: {
                    scales: {
                        x: {
                            type: "time",
                            time: {
                                unit: "day"
                            },
                            title: {
                                display: true,
                                text: "Date"
                            }
                        },
                        y: {
                            type: "category",
                            labels: ["Task 1", "Task 2"],
                            title: {
                                display: true,
                                text: "Tasks"
                            }
                        }
                    }
                }
            });
        }
    });
</script>

<canvas id="ganttChart" class="w-full h-[800px]"></canvas>

<style>
</style>
