/*
 * Copyright 2025 THE PSI CONSORTIUM
 *
 * SPDX-License-Identifier: Apache-2.0
 */

export async function fetchData(fetch: any, url: URL): Promise<any> {
    const options = {
        method: "GET",
        headers: {
            accept: "application/json"
        }
    };

    const response: Response = await fetch(url, options);
    if (!response.ok) {
        console.log("Response: ", response);
        const message = await response.text();
        console.log("Error", url, response.status, message);
        throw new Error(`Error Status: ${response.status}, Error Message: ${message}`);
    }
    return await response.json();
}
