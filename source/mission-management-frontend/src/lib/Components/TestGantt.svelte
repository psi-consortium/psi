<!--
 Copyright 2025 THE PSI CONSORTIUM

 SPDX-License-Identifier: Apache-2.0
 -->

<script lang="ts">
    import { onMount } from "svelte";
    import { Chart } from "chart.js/auto";
    import "chartjs-adapter-date-fns";

    onMount(() => {
        const ctx = document.getElementById("testchart") as HTMLCanvasElement;
        const data = {
            labels: ["Task 1", "Task 2", "Task 3"],
            datasets: [
                {
                    label: "Project Timeline",

                    data: [
                        {
                            x: new Date("2025-04-01"),
                            y: "Task 1",
                            x2: new Date("2025-04-05")
                        },
                        {
                            x: new Date("2025-04-03"),
                            y: "Task 2",
                            x2: new Date("2025-04-08")
                        },
                        {
                            x: new Date("2025-04-07"),
                            y: "Task 3",
                            x2: new Date("2025-04-10")
                        }
                    ],
                    backgroundColor: [
                        "rgba(75, 192, 192, 0.5)",
                        "rgba(245, 40, 145, 0.8)",
                        "rgba(40, 145, 245, 0.8)"
                    ],
                    borderColor: "rgba(75, 192, 192, 1)",
                    borderWidth: 1
                }
            ]
        };

        if (ctx) {
            new Chart(ctx, {
                type: "bar",
                data: data,
                options: {
                    indexAxis: "y",
                    scales: {
                        x: {
                            type: "timeseries",
                            time: {
                                unit: "day",
                                min: new Date("2025-04-01"),
                                max: new Date("2025-04-10")
                            },
                            title: {
                                display: true,
                                text: "Date"
                            }
                        },
                        y: {
                            title: {
                                display: true,
                                text: "Tasks"
                            }
                        }
                    },
                    plugins: {
                        tooltip: {
                            callbacks: {
                                label: function (context) {
                                    const startDate = context.raw.x;
                                    const endDate = context.raw.x2;
                                    return `Start: ${startDate}, End: ${endDate}`;
                                }
                            }
                        }
                    }
                }
            });
        }
    });
</script>

<canvas id="testchart" width="800" height="400" class="mt-20"></canvas>
