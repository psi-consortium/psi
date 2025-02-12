# Bandwidth (De-)Fragmentation

* ID: ADR030
* Status: :accepted:
* Deciders: @cgr
* Date: 2023-08-25
* Version: 1.0
* Category: Design

## Context and Problem Statement

When bandwidth is sold in chunks, over time it will probably end up fragmented.
An example of a fragmented frequency band is shown in {@fig:fragmented-bandwidth}.

```plantuml
@startuml
concise "Transponder" as t0

@4003
t0 is "Customer 1"

@4006

@4009
t0 is {-}

@4012
t0 is "Customer 2"

@4015
t0 is {-}

'mark the end
@4018

highlight 4009 to 4012 : Free Bandwidth
highlight 4015 to 4018 : Free Bandwidth
@enduml
```

![Fragmented Bandwidth](../../common/pixel.png){#fig:fragmented-bandwidth}

In theory, there are 6 MHz left for a third customer to use, but because of fragmentation it can not be fully utilized.
We have to analyse if a defragmentation as shown in {@fig:defragmented-bandwidth} is already supported by PSI or if further extensions have to be made.

```plantuml
@startuml
concise "Transponder" as t0

@4003
t0 is "Customer 1"

@4006

@4009
t0 is "Customer 2"

@4012
t0 is {-}

@4015

'mark the end
@4018

highlight 4012 to 4018 : Free Bandwidth
@enduml
```

![Defragmented Bandwidth](../../common/pixel.png){#fig:defragmented-bandwidth}

## Decision Outcome

The defragmentation of bandwidth lies in the responsibility of the resource owner, which can be either the PSS (for committed resources) or the provider.
It could be done automatically (for managed services), but may still require manual intervention to evaluate the impact on active connections and the resulting legal and financial risks.
While the latter is out-of-scope for this project, PSI does already support the necessary communication between the systems to synchronize the configuration changes.

The following subsections describe the information flow and subsequent model changes depending on the initiator.

### Initiation by the Provider

In case of uncommitted resources, the PSS inventory contains only the two booked chunks of bandwidth as shown in {@fig:pss-inventory-before-defrag-by-provider}.

```plantuml
@startuml
skinparam defaultFontColor automatic

/'
json "Product: Bandwidth on Sat XY" as prod1 #ffc399 {
    "id": "6c7acda1",
    "startDate": "01.07.2023",
    "terminationDate": "30.09.2023",
    "specificationId": "2aa3165a",
    "customerId": "f7fcd60e",
    "@type": "BandwidthProduct",
    "satelliteId": "9ea0a4b3",
    "transponderId": "bd513ca4",
    "allocatedBandwidth": 6,
    "uplinkFrequencyStart": 4003,
    "uplinkFrequencyEnd": 4009,
    "downlinkFrequencyStart": 5003,
    "downlinkFrequencyEnd": 5009
}
json "Resource: Bandwidth on Sat XY" as res1 #d6a5b1 {
    "startOperatingDate": "01.07.2023",
    "endOperatingDate": "30.09.2023",
    "specificationId": "9309e405",
    "customerId": "f7fcd60e",
    "@type": "BandwidthResource",
    "satelliteId": "9ea0a4b3",
    "transponderId": "bd513ca4",
    "allocatedBandwidth": 6,
    "uplinkFrequencyStart": 4003,
    "uplinkFrequencyEnd": 4009,
    "downlinkFrequencyStart": 5003,
    "downlinkFrequencyEnd": 5009
}
json "Resource: Uplink on Sat XY" as ul1 {
    "frequencyStart": 4003,
    "frequencyEnd": 4009
}
prod1 o-- res1
res1 o-- ul1
'/
json "Product: Bandwidth on Sat XY" as prod2 #ffc399 {
    "id": "ea4af0b2",
    "startDate": "01.08.2023",
    "terminationDate": "30.11.2023",
    "specificationId": "2aa3165a",
    "customerId": "cfc8f923",
    "@type": "BandwidthProduct",
    "satelliteId": "9ea0a4b3",
    "transponderId": "bd513ca4",
    "allocatedBandwidth": 3,
    "uplinkFrequencyStart": 4012,
    "uplinkFrequencyEnd": 4015,
    "downlinkFrequencyStart": 5012,
    "downlinkFrequencyEnd": 5015
}
json "Resource: Carrier on Sat XY" as res2 #d6a5b1 {
    "id": "45fb658a",
    "startOperatingDate": "01.07.2023",
    "endOperatingDate": "30.09.2023",
    "specificationId": "9309e405",
    "customerId": "cfc8f923",
    "@type": "CarrierResource",
    "satelliteId": "9ea0a4b3",
    "transponderId": "bd513ca4",
    "allocatedBandwidth": 3,
    "uplinkFrequencyStart": 4012,
    "uplinkFrequencyEnd": 4015,
    "downlinkFrequencyStart": 5012,
    "downlinkFrequencyEnd": 5015
}
json "Resource: Uplink on Sat XY" as ul2 {
    "id": "0f386d4f",
    "startOperatingDate": "01.07.2023",
    "endOperatingDate": "30.09.2023",
    "specificationId": "532b58f1",
    "customerId": "cfc8f923",
    "@type": "UplinkResource",
    "frequencyStart": 4012,
    "frequencyEnd": 4015
}
json "Resource: Downlink on Sat XY" as dl2 {
    "id": "c9afde6a",
    "startOperatingDate": "01.07.2023",
    "endOperatingDate": "30.09.2023",
    "specificationId": "de3c926c",
    "customerId": "cfc8f923",
    "@type": "DownlinkResource",
    "frequencyStart": 5012,
    "frequencyEnd": 5015
}
prod2 o-- res2
res2 o-- ul2
res2 o-- dl2
@enduml
```

![PSS Inventory before defragmentation by the provider](../../common/pixel.png){#fig:pss-inventory-before-defrag-by-provider}

After checking the technical and legal feasibility, the provider can decide to move the bandwidth "Customer B" to 4009-4012 MHz (downlink alike) as shown in {@fig:pss-inventory-after-defrag-by-provider}.
The BSS/OSS then invokes the operation TOD-05-01-02 (`PATCH /resourceInventory/v1/resource/{id}`) on the PSS, sending the new feature characteristics in the request body.

```plantuml
@startuml
json "Product: Bandwidth on Sat XY" as prod2 #ffc399 {
    "id": "ea4af0b2",
    "startDate": "01.08.2023",
    "terminationDate": "30.11.2023",
    "specificationId": "2aa3165a",
    "customerId": "cfc8f923",
    "@type": "BandwidthProduct",
    "satelliteId": "9ea0a4b3",
    "transponderId": "bd513ca4",
    "allocatedBandwidth": 3,
    "uplinkFrequencyStart": 4009,
    "uplinkFrequencyEnd": 4012,
    "downlinkFrequencyStart": 5009,
    "downlinkFrequencyEnd": 5012
}
json "Resource: Carrier on Sat XY" as res2 #d6a5b1 {
    "id": "45fb658a",
    "startOperatingDate": "01.07.2023",
    "endOperatingDate": "30.09.2023",
    "specificationId": "9309e405",
    "customerId": "cfc8f923",
    "@type": "CarrierResource",
    "satelliteId": "9ea0a4b3",
    "transponderId": "bd513ca4",
    "allocatedBandwidth": 3,
    "uplinkFrequencyStart": 4009,
    "uplinkFrequencyEnd": 4012,
    "downlinkFrequencyStart": 5009,
    "downlinkFrequencyEnd": 5012
}
json "Resource: Uplink on Sat XY" as ul2 {
    "id": "0f386d4f",
    "startOperatingDate": "01.07.2023",
    "endOperatingDate": "30.09.2023",
    "specificationId": "532b58f1",
    "customerId": "cfc8f923",
    "@type": "UplinkResource",
    "frequencyStart": 4009,
    "frequencyEnd": 4012
}
json "Resource: Downlink on Sat XY" as dl2 {
    "id": "c9afde6a",
    "startOperatingDate": "01.07.2023",
    "endOperatingDate": "30.09.2023",
    "specificationId": "de3c926c",
    "customerId": "cfc8f923",
    "@type": "DownlinkResource",
    "frequencyStart": 5009,
    "frequencyEnd": 5012
}
prod2 o-- res2
res2 o-- ul2
res2 o-- dl2
@enduml
```

![PSS Inventory after defragmentation by the provider](../../common/pixel.png){#fig:pss-inventory-after-defrag-by-provider}

### Initiation by the PSS

If a resource is committed, it has a dedicated entry in the inventory resembling the full frequency range.
This allows the PSS to perform defragmentation on its own in the given boundaries.
An example for an initial state *before* defragmentation is shown in {@fig:inventory-before-defrag-pss}.

```plantuml
@startuml
json "Committed Resource: Carrier on Sat XY" as res0 #d6a5b1 {
    "id": "7896de2f",
    "startOperatingDate": "01.01.2023",
    "endOperatingDate": "31.12.2023",
    "specificationId": "9309e405",
    "customerId": null,
    "@type": "CarrierResource",
    "satelliteId": "9ea0a4b3",
    "transponderId": "bd513ca4",
    "allocatedBandwidth": 15,
    "uplinkFrequencyStart": 4003,
    "uplinkFrequencyEnd": 4018,
    "downlinkFrequencyStart": 5003,
    "downlinkFrequencyEnd": 5018
}

/'
json "Product: Bandwidth on Sat XY" as prod1 #ffc399 {
    "id": "6c7acda1"
}
json "Resource: Bandwidth on Sat XY" as res1 #d6a5b1 {
    "id": "d76bcd08"
}
res0 --> res1
prod1 o-- res1
'/

json "Product: Bandwidth on Sat XY" as prod2 #ffc399 {
    "id": "ea4af0b2",
    "startDate": "01.08.2023",
    "terminationDate": "30.11.2023",
    "specificationId": "2aa3165a",
    "customerId": "cfc8f923",
    "@type": "BandwidthProduct",
    "satelliteId": "9ea0a4b3",
    "transponderId": "bd513ca4",
    "allocatedBandwidth": 3,
    "uplinkFrequencyStart": 4009,
    "uplinkFrequencyEnd": 4012,
    "downlinkFrequencyStart": 5009,
    "downlinkFrequencyEnd": 5012
}
json "Resource: Carrier on Sat XY" as res2 #d6a5b1 {
    "id": "45fb658a",
    "startOperatingDate": "01.07.2023",
    "endOperatingDate": "30.09.2023",
    "specificationId": "9309e405",
    "customerId": "cfc8f923",
    "@type": "CarrierResource",
    "satelliteId": "9ea0a4b3",
    "transponderId": "bd513ca4",
    "allocatedBandwidth": 3,
    "uplinkFrequencyStart": 4009,
    "uplinkFrequencyEnd": 4012,
    "downlinkFrequencyStart": 5009,
    "downlinkFrequencyEnd": 5012
}
json "Resource: Uplink on Sat XY" as ul2 {
    "id": "0f386d4f",
    "startOperatingDate": "01.07.2023",
    "endOperatingDate": "30.09.2023",
    "specificationId": "532b58f1",
    "customerId": "cfc8f923",
    "@type": "UplinkResource",
    "frequencyStart": 4009,
    "frequencyEnd": 4012
}
json "Resource: Downlink on Sat XY" as dl2 {
    "id": "c9afde6a",
    "startOperatingDate": "01.07.2023",
    "endOperatingDate": "30.09.2023",
    "specificationId": "de3c926c",
    "customerId": "cfc8f923",
    "@type": "DownlinkResource",
    "frequencyStart": 5009,
    "frequencyEnd": 5012
}
res0 --> res2
prod2 o-- res2
res2 o-- ul2
res2 o-- dl2

@enduml
```

![Inventory before defragmentation by the PSS](../../common/pixel.png){#fig:inventory-before-defrag-pss}

The PSS governance may perform the same manual checks as the provider above, but may also employ automatic defragmentation algorithms.
Since it has no direct control over the service or resource, it requests the changes by sending product orders with action "modify" via operation TOD-03-02-01, see {@fig:product-order-with-modify}.
Note that by using the `requestedStartDate` property may be used to plan the migration path for multiple chunks in a defined order.

```plantuml
@startuml
json "ProductOrder" as order {
    "requestedStartDate" : "2019-05-03T08:13:59.506Z",
    "priority": "high",
    "productOrderItem": [
        {
            "action": "modify",
            "product": {
                "id": "ea4af0b2",
                "uplinkFrequencyStart": 4009,
                "uplinkFrequencyEnd": 4012,
                "downlinkFrequencyStart": 5009,
                "downlinkFrequencyEnd": 5012,
                "@referredType": "BandwidthProduct"
            }
        }
    ]
}
@enduml
```

![Product Order with modify action](../../common/pixel.png){#fig:product-order-with-modify}

The provider then handles it like any other change request (e.g., from the customers themselves) and either carries it out or rejects giving the reasoning.
On acceptance, the updates of the inventory are then performed exactly like shown in the previous example.

## Compliance

Configuration changes will be included in the case study, hinting at the possibility that defragmentation can be a reason to trigger them.

## Implications for the Scope

The described workflow can be used by the customers themselves (through the PSS) to request changes to any other characteristic.
