/*
 * Copyright 2025 THE PSI CONSORTIUM
 *
 * SPDX-License-Identifier: Apache-2.0
 */

import type { Organization } from "$lib/domains/party";

export function findOrganization(id: string): Organization | undefined {
    return organizations.find((o) => o.id === id);
}

export function findAllOrganizations(offset: number = 0, size: number = 10): Organization[] {
    return organizations.slice(offset, size);
}

export const organizations: Organization[] = [
    {
        id: "B0FAEBDE-079C-430D-A41F-F51700F05864",
        partyCharacteristic: [
            { name: "first_name", value: "Heddi" },
            { name: "last_name", value: "Rautenstrauch" },
            { name: "web", value: "http://www.riebersellhfjr.com" }
        ],
        name: "Riebersell, H F Jr",
        nameType: "Corp",
        tradingName: "Riebersell",
        status: "validated",
        contactMedium: [
            {
                mediumType: "email",
                preferred: true,
                characteristic: {
                    emailAddress: "heddi.rautenstrauch@rautenstrauch.com"
                }
            },
            {
                mediumType: "telephone",
                characteristic: {
                    phoneNumber: "555-569-8399"
                }
            },
            {
                mediumType: "telephone",
                characteristic: {
                    phoneNumber: "555-978-6179"
                }
            },
            {
                mediumType: "address",
                characteristic: {
                    street1: "2335 Canton Hwy #6",
                    city: "Windsor",
                    stateOrProvince: "ON",
                    postCode: "N8N 3N2"
                }
            }
        ]
    },
    {
        id: "E90C5CBE-0AB9-437B-A98C-6E4FA508438F",
        partyCharacteristic: [
            { name: "first_name", value: "Kendra" },
            { name: "last_name", value: "Loud" },
            { name: "web", value: "http://www.delletouche.com" }
        ],
        name: "Delle & Touche",
        nameType: "Ltd",
        tradingName: "Delle & Touche",
        status: "validated",
        contactMedium: [
            {
                mediumType: "email",
                preferred: true,
                characteristic: {
                    emailAddress: "kloud@gmail.com"
                }
            },
            {
                mediumType: "telephone",
                characteristic: {
                    phoneNumber: "555-363-1526"
                }
            },
            {
                mediumType: "telephone",
                characteristic: {
                    phoneNumber: "555-932-4472"
                }
            },
            {
                mediumType: "address",
                characteristic: {
                    street1: "6 Arch St #9757",
                    city: "Alcida",
                    stateOrProvince: "NB",
                    postCode: "E8J 2C4"
                }
            }
        ]
    },
    {
        id: "8D215E52-AB3F-49C1-BCB6-7D97236BF7F2",
        partyCharacteristic: [
            { name: "first_name", value: "Lourdes" },
            { name: "last_name", value: "Bauswell" },
            { name: "web", value: "http://www.oklahomaneoninc.com" }
        ],
        name: "Oklahoma Neon Inc",
        nameType: "Inc",
        tradingName: "Oklahoma Neon",
        status: "validated",
        contactMedium: [
            {
                mediumType: "email",
                preferred: true,
                characteristic: {
                    emailAddress: "lourdes_bauswell@aol.com"
                }
            },
            {
                mediumType: "telephone",
                characteristic: {
                    phoneNumber: "555-903-7043"
                }
            },
            {
                mediumType: "telephone",
                characteristic: {
                    phoneNumber: "555-638-6682"
                }
            },
            {
                mediumType: "address",
                characteristic: {
                    street1: "9547 Belmont Rd #21",
                    city: "Belleville",
                    stateOrProvince: "ON",
                    postCode: "K8P 1B3"
                }
            }
        ]
    },
    {
        id: "880F81BF-EA5D-4493-ABB3-F7B6967AAFF1",
        partyCharacteristic: [
            { name: "first_name", value: "Hannah" },
            { name: "last_name", value: "Edmison" },
            { name: "web", value: "http://www.mbapaintstores.com" }
        ],
        name: "M B A Paint Stores",
        nameType: "Ltd",
        tradingName: "M B A Paint Stores",
        status: "validated",
        contactMedium: [
            {
                mediumType: "email",
                preferred: true,
                characteristic: {
                    emailAddress: "hannah_ed@yahoo.com"
                }
            },
            {
                mediumType: "telephone",
                characteristic: {
                    phoneNumber: "555-334-3686"
                }
            },
            {
                mediumType: "telephone",
                characteristic: {
                    phoneNumber: "555-692-7694"
                }
            },
            {
                mediumType: "address",
                characteristic: {
                    street1: "73 Pittsford Victor Rd",
                    city: "Vancouver",
                    stateOrProvince: "BC",
                    postCode: "V5Z 3K2"
                }
            }
        ]
    },
    {
        id: "A2387968-FED0-4C92-90F7-4183607AE9F6",
        partyCharacteristic: [
            { name: "first_name", value: "Tom" },
            { name: "last_name", value: "Loeza" },
            { name: "web", value: "http://www.shiraltonhotel.com" }
        ],
        name: "Shiralton Hotel",
        nameType: "Inc",
        tradingName: "Shiralton",
        status: "validated",
        contactMedium: [
            {
                mediumType: "email",
                preferred: true,
                characteristic: {
                    emailAddress: "tom.loeza@gmail.com"
                }
            },
            {
                mediumType: "telephone",
                characteristic: {
                    phoneNumber: "555-487-6096"
                }
            },
            {
                mediumType: "telephone",
                characteristic: {
                    phoneNumber: "555-727-4760"
                }
            },
            {
                mediumType: "address",
                characteristic: {
                    street1: "447 Commercial St Se",
                    city: "LIle-Perrot",
                    stateOrProvince: "QC",
                    postCode: "J7V 4T4"
                }
            }
        ]
    },
    {
        id: "F4753C0C-704A-478E-B7D4-D6AE217DB683",
        partyCharacteristic: [
            { name: "first_name", value: "Queenie" },
            { name: "last_name", value: "Kramarczyk" },
            { name: "web", value: "http://www.giermanwoodinc.com" }
        ],
        name: "Gierman Wood Inc",
        nameType: "Inc",
        tradingName: "Gierman Wood",
        status: "validated",
        contactMedium: [
            {
                mediumType: "email",
                preferred: true,
                characteristic: {
                    emailAddress: "queenie.kramarczyk@kramarczyk.org"
                }
            },
            {
                mediumType: "telephone",
                characteristic: {
                    phoneNumber: "555-421-5793"
                }
            },
            {
                mediumType: "telephone",
                characteristic: {
                    phoneNumber: "555-302-7591"
                }
            },
            {
                mediumType: "address",
                characteristic: {
                    street1: "47 Garfield Ave",
                    city: "Swift Current",
                    stateOrProvince: "SK",
                    postCode: "S9H 4V2"
                }
            }
        ]
    },
    {
        id: "C830DA36-1D26-4C61-AE9A-ED0E4BB9DDF8",
        partyCharacteristic: [
            { name: "first_name", value: "Hui" },
            { name: "last_name", value: "Portaro" },
            { name: "web", value: "http://www.astorageinnofgloucester.com" }
        ],
        name: "A Storage Inn Of Gloucester",
        nameType: "Ltd",
        tradingName: "Storage Inn",
        status: "validated",
        contactMedium: [
            {
                mediumType: "email",
                preferred: true,
                characteristic: {
                    emailAddress: "hui_portaro@cox.net"
                }
            },
            {
                mediumType: "telephone",
                characteristic: {
                    phoneNumber: "555-827-7755"
                }
            },
            {
                mediumType: "telephone",
                characteristic: {
                    phoneNumber: "555-276-4830"
                }
            },
            {
                mediumType: "address",
                characteristic: {
                    street1: "3 Mill Rd",
                    city: "Baker Brook",
                    stateOrProvince: "NB",
                    postCode: "E7A 1T3"
                }
            }
        ]
    },
    {
        id: "A6DC9189-09CB-499F-88A0-246AB370462C",
        partyCharacteristic: [
            { name: "first_name", value: "Josefa" },
            { name: "last_name", value: "Opitz" },
            { name: "web", value: "http://www.normanisabi.com" }
        ],
        name: "Norman Isabi",
        nameType: "LLC",
        tradingName: "N. Isabi",
        status: "validated",
        contactMedium: [
            {
                mediumType: "email",
                preferred: true,
                characteristic: {
                    emailAddress: "josefa.opitz@opitz.org"
                }
            },
            {
                mediumType: "telephone",
                characteristic: {
                    phoneNumber: "555-788-7645"
                }
            },
            {
                mediumType: "telephone",
                characteristic: {
                    phoneNumber: "555-526-3721"
                }
            },
            {
                mediumType: "address",
                characteristic: {
                    street1: "136 W Grand Ave #3",
                    city: "Delhi",
                    stateOrProvince: "ON",
                    postCode: "N4B 1C4"
                }
            }
        ]
    },
    {
        id: "DA0BEB26-13D6-4577-BDD0-B75554233EE7",
        partyCharacteristic: [
            { name: "first_name", value: "Lea" },
            { name: "last_name", value: "Steinhaus" },
            { name: "web", value: "http://www.thomachristopheresq.com" }
        ],
        name: "Thoma Christopher Esq",
        nameType: "Corp",
        tradingName: "T. C. Esq",
        status: "validated",
        contactMedium: [
            {
                mediumType: "email",
                preferred: true,
                characteristic: {
                    emailAddress: "lsteinhaus@cox.net"
                }
            },
            {
                mediumType: "telephone",
                characteristic: {
                    phoneNumber: "555-618-8258"
                }
            },
            {
                mediumType: "telephone",
                characteristic: {
                    phoneNumber: "555-651-3298"
                }
            },
            {
                mediumType: "address",
                characteristic: {
                    street1: "80 Maplewood Dr #34",
                    city: "Bradford",
                    stateOrProvince: "ON",
                    postCode: "L3Z 2S4"
                }
            }
        ]
    },
    {
        id: "43F8AFCE-C87C-4DF6-87DA-0280D90E5EA4",
        partyCharacteristic: [
            { name: "first_name", value: "Paola" },
            { name: "last_name", value: "Vielma" },
            { name: "web", value: "http://www.gressiotitle.com" }
        ],
        name: "Gressio Title",
        nameType: "Corp",
        tradingName: "Gressio Title",
        status: "validated",
        contactMedium: [
            {
                mediumType: "email",
                preferred: true,
                characteristic: {
                    emailAddress: "paola_vielma@aol.com"
                }
            },
            {
                mediumType: "telephone",
                characteristic: {
                    phoneNumber: "555-456-1117"
                }
            },
            {
                mediumType: "telephone",
                characteristic: {
                    phoneNumber: "555-263-7711"
                }
            },
            {
                mediumType: "address",
                characteristic: {
                    street1: "58 Hancock St",
                    city: "Aurora",
                    stateOrProvince: "ON",
                    postCode: "L4G 2J7"
                }
            }
        ]
    },
    {
        id: "41898B24-382B-4DB8-A884-3FC9CB1FEA9F",
        partyCharacteristic: [
            { name: "first_name", value: "Hortencia" },
            { name: "last_name", value: "Bresser" },
            { name: "web", value: "http://www.batalchamberofcommerce.com" }
        ],
        name: "Batal Chamber Of Commerce",
        nameType: "Inc",
        tradingName: "Batavia",
        status: "validated",
        contactMedium: [
            {
                mediumType: "email",
                preferred: true,
                characteristic: {
                    emailAddress: "hbresser@aol.com"
                }
            },
            {
                mediumType: "telephone",
                characteristic: {
                    phoneNumber: "555-256-6791"
                }
            },
            {
                mediumType: "telephone",
                characteristic: {
                    phoneNumber: "555-370-8282"
                }
            },
            {
                mediumType: "address",
                characteristic: {
                    street1: "808 Calle De Industrias",
                    city: "New Waterford",
                    stateOrProvince: "NS",
                    postCode: "B1H 1Z4"
                }
            }
        ]
    },
    {
        id: "D7180115-8D86-4656-9FEC-12F2DB72C033",
        partyCharacteristic: [
            { name: "first_name", value: "Leanna" },
            { name: "last_name", value: "Tijerina" },
            { name: "web", value: "http://www.peterriolandsurveying.com" }
        ],
        name: "Peterrio Land Surveying",
        nameType: "Corp",
        tradingName: "Peterrio",
        status: "validated",
        contactMedium: [
            {
                mediumType: "email",
                preferred: true,
                characteristic: {
                    emailAddress: "leanna@cox.net"
                }
            },
            {
                mediumType: "telephone",
                characteristic: {
                    phoneNumber: "555-719-2114"
                }
            },
            {
                mediumType: "telephone",
                characteristic: {
                    phoneNumber: "555-658-1773"
                }
            },
            {
                mediumType: "address",
                characteristic: {
                    street1: "2859 Dorsett Rd",
                    city: "North York",
                    stateOrProvince: "ON",
                    postCode: "M9L 2T9"
                }
            }
        ]
    },
    {
        id: "63F4DDA3-65F5-4927-8E78-A628955CD569",
        partyCharacteristic: [
            { name: "first_name", value: "Danilo" },
            { name: "last_name", value: "Pride" },
            { name: "web", value: "http://www.heikkiharrisonincorporated.com" }
        ],
        name: "Heikki Harrison Incorporated",
        nameType: "Inc",
        tradingName: "Heikkies Inc",
        status: "validated",
        contactMedium: [
            {
                mediumType: "email",
                preferred: true,
                characteristic: {
                    emailAddress: "danilo_pride@hotmail.com"
                }
            },
            {
                mediumType: "telephone",
                characteristic: {
                    phoneNumber: "555-212-4945"
                }
            },
            {
                mediumType: "telephone",
                characteristic: {
                    phoneNumber: "555-888-9985"
                }
            },
            {
                mediumType: "address",
                characteristic: {
                    street1: "6857 Wall St",
                    city: "Red Deer",
                    stateOrProvince: "AB",
                    postCode: "T4R 2H5"
                }
            }
        ]
    },
    {
        id: "35F5405C-8250-4A52-8F56-BB906AD8EA55",
        partyCharacteristic: [
            { name: "first_name", value: "Huey" },
            { name: "last_name", value: "Marcille" },
            { name: "web", value: "http://www.southernidahodrillingcorp.com" }
        ],
        name: "Southern Idaho Drilling Corp",
        nameType: "Corp",
        tradingName: "Southern Idaho Drilling",
        status: "validated",
        contactMedium: [
            {
                mediumType: "email",
                preferred: true,
                characteristic: {
                    emailAddress: "huey.marcille@gmail.com"
                }
            },
            {
                mediumType: "telephone",
                characteristic: {
                    phoneNumber: "555-639-3619"
                }
            },
            {
                mediumType: "telephone",
                characteristic: {
                    phoneNumber: "555-520-1241"
                }
            },
            {
                mediumType: "address",
                characteristic: {
                    street1: "169 Journal Sq",
                    city: "Edmonton",
                    stateOrProvince: "AB",
                    postCode: "T5P 1G9"
                }
            }
        ]
    },
    {
        id: "65C86A01-290D-453A-B5CB-6592EDEFAFA2",
        partyCharacteristic: [
            { name: "first_name", value: "Apolonia" },
            { name: "last_name", value: "Warne" },
            { name: "web", value: "http://www.kitchenandcar.com" }
        ],
        name: "Kitchen and Car",
        nameType: "Corp",
        tradingName: "Kitchen and Car",
        status: "validated",
        contactMedium: [
            {
                mediumType: "email",
                preferred: true,
                characteristic: {
                    emailAddress: "apolonia@gmail.com"
                }
            },
            {
                mediumType: "telephone",
                characteristic: {
                    phoneNumber: "555-978-1488"
                }
            },
            {
                mediumType: "telephone",
                characteristic: {
                    phoneNumber: "555-221-1874"
                }
            },
            {
                mediumType: "address",
                characteristic: {
                    street1: "3 E 31st St #77",
                    city: "Fredericton",
                    stateOrProvince: "NB",
                    postCode: "E3G 0A3"
                }
            }
        ]
    }
];
