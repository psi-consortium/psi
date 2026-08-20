#!/usr/bin/env python3
"""Generate deterministic, fictional PSI test data.

This generator has no dependency on the original sensitive fixtures. It uses
the public OpenAPI specifications for structural checks and an optional,
value-free profile for dataset scale.
"""

from __future__ import annotations

import argparse
import json
import random
import re
from datetime import datetime, timedelta, timezone
from pathlib import Path
from typing import Any

from schema_registry import SchemaRegistry


UTC = timezone.utc
START = datetime(2026, 1, 1, tzinfo=UTC)


def iso(value: datetime) -> str:
    return value.isoformat(timespec="milliseconds").replace("+00:00", "Z")


def slug(value: str) -> str:
    return re.sub(r"[^a-z0-9]+", "_", value.lower()).strip("_")


def ref(name: str, referred_type: str, version: str = "1.0") -> dict[str, Any]:
    return {
        "name": name,
        "version": version,
        "@type": f"{referred_type}Ref",
        "@referredType": referred_type,
    }


def related_party(name: str, role: str, referred_type: str) -> dict[str, Any]:
    return {
        "@type": "RelatedPartyRefOrPartyRoleRef",
        "role": role,
        "partyOrPartyRole": {
            "name": name,
            "@type": "PartyRef",
            "@referredType": referred_type,
        },
    }


class Scenario:
    organizations = [
        "Aurora Relay",
        "Blue Meridian",
        "Northstar Signal",
        "Cedar Orbit",
        "Vela Connect",
        "Solstice Array",
        "Harbor Vector",
        "Ember Link",
        "Polar Meridian",
        "Quasar Field",
    ]
    people = [
        ("Mira", "Keller"),
        ("Daniel", "Novak"),
        ("Leonie", "Fischer"),
        ("Arun", "Mehta"),
        ("Sofia", "Marin"),
        ("Tomas", "Varga"),
    ]
    product_families = [
        ("Horizon", "Access"),
        ("Vector", "Trunk"),
        ("Lumen", "Relay"),
        ("Pioneer", "Beam"),
    ]

    def __init__(self, seed: int, organizations: int, products_per_org: int | None, individuals: int, product_counts: list[int] | None = None):
        self.random = random.Random(seed)
        self.orgs = self.organizations[:organizations]
        self.people = self.people[:individuals]
        self.product_counts = product_counts or [products_per_org or 3] * organizations
        self.products: list[dict[str, Any]] = []
        self.services: list[dict[str, Any]] = []
        self.resources: list[dict[str, Any]] = []
        self.offerings: list[dict[str, Any]] = []

    def build_parties(self) -> tuple[list[dict[str, Any]], list[dict[str, Any]]]:
        individuals = []
        for given, family in self.people:
            individuals.append({
                "givenName": given,
                "familyName": family,
                "name": f"{given} {family}",
                "partyCharacteristic": [{
                    "name": "maxPriority",
                    "valueType": "string",
                    "value": self.random.choice(["LOW", "MEDIUM", "HIGH"]),
                    "@type": "StringCharacteristic",
                }],
                "@type": "Individual",
            })

        organizations = []
        for index, name in enumerate(self.orgs, 1):
            record: dict[str, Any] = {
                "name": name,
                "@type": "Organization",
                "partyCharacteristic": [
                    {"name": "orderResponseTime", "valueType": "string", "value": "PT12H", "@type": "StringCharacteristic"},
                    {"name": "inquiryResponseTime", "valueType": "string", "value": "PT24H", "@type": "StringCharacteristic"},
                ],
            }
            if self.people:
                person = self.people[(index - 1) % len(self.people)]
                record["relatedParty"] = [related_party(f"{person[0]} {person[1]}", "Operator", "Individual")]
            organizations.append(record)
        return individuals, organizations

    def build_catalog(self) -> None:
        for org_index, organization in enumerate(self.orgs):
            for product_index in range(self.product_counts[org_index]):
                family, kind = self.product_families[(org_index + product_index) % len(self.product_families)]
                tier = ["Core", "Plus", "Max"][product_index % 3]
                name = f"{family} {organization.split()[0]} {kind} {tier}"
                number = f"{slug(organization)[:4].upper()}-{family.upper()}-{product_index + 1:02d}"
                service_name = f"{name} Managed Service"
                resource_name = f"{name} Edge Terminal"
                offering_name = f"{name} Offering"
                product = {
                    "name": name,
                    "brand": organization,
                    "productNumber": number,
                    "description": f"{name} provides fictional {kind.lower()} connectivity with configurable capacity and service monitoring.",
                    "isBundle": False,
                    "lifecycleStatus": "Active",
                    "validFor": {"startDateTime": iso(START), "endDateTime": iso(START + timedelta(days=1095))},
                    "version": "1.0",
                    "relatedParty": [related_party(organization, "ServiceProvider", "Organization")],
                    "attachment": [],
                    "bundledProductSpecification": [],
                    "productSpecificationRelationship": [],
                    "serviceSpecification": [ref(service_name, "ServiceSpecification")],
                    "resourceSpecification": [ref(resource_name, "PhysicalResourceSpecification")],
                    "productSpecCharacteristic": [
                        {"name": "bandwidth", "description": "Configured service bandwidth", "valueType": "integer", "configurable": True, "minCardinality": 1, "maxCardinality": 1, "characteristicValueSpecification": [{"isDefault": True, "valueType": "integer", "value": 50 + product_index * 25, "unitOfMeasure": "Mbps", "@type": "IntegerCharacteristicValueSpecification"}], "@type": "CharacteristicSpecification"},
                        {"name": "networkUptime", "description": "Target network availability", "valueType": "double", "configurable": False, "minCardinality": 1, "maxCardinality": 1, "characteristicValueSpecification": [{"isDefault": True, "valueType": "double", "value": 99.5, "unitOfMeasure": "percent (%)", "@type": "NumberCharacteristicValueSpecification"}], "@type": "CharacteristicSpecification"},
                    ],
                    "@type": "ProductSpecification",
                }
                service = {
                    "name": service_name,
                    "description": f"Managed delivery for the fictional {name} product.",
                    "isBundle": False,
                    "lifecycleStatus": "Active",
                    "validFor": {"startDateTime": iso(START), "endDateTime": iso(START + timedelta(days=1095))},
                    "version": "1.0",
                    "relatedParty": [related_party(organization, "ServiceProvider", "Organization")],
                    "resourceSpecification": [ref(resource_name, "PhysicalResourceSpecification")],
                    "specCharacteristic": [{"name": "serviceLevel", "valueType": "string", "@type": "StringCharacteristicSpecification"}],
                    "@type": "ServiceSpecification",
                }
                resource = {
                    "name": resource_name,
                    "category": "Customer edge equipment",
                    "description": f"Fictional terminal resource used by {name}.",
                    "isBundle": False,
                    "model": f"{family}-{product_index + 1:02d}",
                    "vendor": "Aster Devices",
                    "validFor": {"startDateTime": iso(START), "endDateTime": iso(START + timedelta(days=1460))},
                    "version": "1.0",
                    "resourceSpecCharacteristic": [{"name": "weight", "valueType": "double", "unitOfMeasure": "kg", "@type": "CharacteristicSpecification"}],
                    "@type": "PhysicalResourceSpecification",
                }
                offering = {
                    "name": offering_name,
                    "description": f"Commercial offering for {name} with a {12 + product_index * 6}-month term.",
                    "isBundle": False,
                    "isSellable": True,
                    "lifecycleStatus": "Active",
                    "statusReason": "Available for synthetic test scenarios",
                    "version": "1.0",
                    "validFor": {"startDateTime": iso(START), "endDateTime": iso(START + timedelta(days=730))},
                    "productSpecification": ref(name, "ProductSpecification"),
                    "serviceCandidate": ref(service_name, "ServiceSpecification"),
                    "resourceCandidate": ref(resource_name, "PhysicalResourceSpecification"),
                    "relatedParty": [related_party(organization, "Seller", "Organization")],
                    "productOfferingPrice": [{"name": f"{offering_name} Monthly Price", "priceType": "recurring", "price": {"taxIncludedAmount": {"unit": "EUR", "value": 120 + product_index * 45}}, "@type": "ProductOfferingPrice"}],
                    "@type": "ProductOffering",
                }
                self.products.append((organization, product))
                self.services.append((organization, service))
                self.resources.append((organization, resource))
                self.offerings.append((organization, offering))

    def build_orders(self, count: int) -> list[dict[str, Any]]:
        if not self.offerings or not self.people:
            return []
        orders = []
        for order_index in range(count):
            organization, offering = self.offerings[order_index % len(self.offerings)]
            person = self.people[order_index % len(self.people)]
            created = START + timedelta(days=order_index * 3)
            orders.append({
                "category": "Synthetic connectivity order",
                "description": f"Synthetic order for {offering['name']}",
                "priority": str((order_index % 3) + 1),
                "requestedCompletionDate": iso(created + timedelta(days=5)),
                "requestedStartDate": iso(created + timedelta(days=2)),
                "note": [{"id": f"note-{order_index + 1:04d}", "author": f"{person[0]} {person[1]}", "date": iso(created), "text": "Generated test order note", "@type": "Note"}],
                "productOrderItem": [{"id": f"item-{order_index + 1:04d}", "quantity": 1, "action": "add", "productOffering": ref(offering["name"], "ProductOffering"), "product": {"startDate": iso(created + timedelta(days=2)), "@type": "Product"}, "@type": "ProductOrderItem"}],
                "relatedParty": [related_party(organization, "Broker", "Organization"), related_party(f"{person[0]} {person[1]}", "Customer", "Individual")],
                "@type": "ProductOrder",
            })
        return orders

    def write(self, output: Path, orders: int, registry: SchemaRegistry) -> None:
        individuals, organizations = self.build_parties()
        self.build_catalog()
        groups = {
            "partyIndividuals/party_individuals.json": individuals,
            "partyOrganizations/party_organizations.json": organizations,
            "productOrders/synthetic_product_orders.json": self.build_orders(orders),
        }
        for directory, records in [("productSpecifications", self.products), ("serviceSpecifications", self.services), ("resourceSpecifications", self.resources), ("productOfferings", self.offerings)]:
            for organization in self.orgs:
                groups[f"{directory}/{slug(organization)}_{directory[:-1]}.json"] = [record for owner, record in records if owner == organization]

        records_by_category = {
            "partyIndividuals": individuals,
            "partyOrganizations": organizations,
            "productOrders": groups["productOrders/synthetic_product_orders.json"],
            "productSpecifications": [record for _, record in self.products],
            "serviceSpecifications": [record for _, record in self.services],
            "resourceSpecifications": [record for _, record in self.resources],
            "productOfferings": [record for _, record in self.offerings],
        }
        errors = [error for category, records in records_by_category.items() for error in registry.validate_category(category, records)]
        if errors:
            raise ValueError("Generated data does not match the PSI OpenAPI structure:\n" + "\n".join(errors))

        for relative, data in groups.items():
            target = output / relative
            target.parent.mkdir(parents=True, exist_ok=True)
            target.write_text(json.dumps(data, indent=2) + "\n", encoding="utf-8")


def main() -> None:
    parser = argparse.ArgumentParser()
    default_output = Path(__file__).resolve().parents[2] / "aiv" / "testing" / "testdata"
    default_openapi = Path(__file__).resolve().parents[2] / "doc" / "PSI" / "PSI-ICD" / "open-apis" / "oas"
    default_profile = Path(__file__).resolve().parent / "profile.json"
    parser.add_argument("--output", type=Path, default=default_output)
    parser.add_argument("--openapi", type=Path, default=default_openapi)
    parser.add_argument("--profile", type=Path, default=default_profile)
    parser.add_argument("--seed", type=int, default=42)
    parser.add_argument("--organizations", type=int)
    parser.add_argument("--products-per-organization", type=int)
    parser.add_argument("--individuals", type=int)
    parser.add_argument("--orders", type=int, default=4)
    args = parser.parse_args()

    profile = json.loads(args.profile.read_text(encoding="utf-8")) if args.profile.exists() else {}
    summary = profile.get("summary", {})
    organization_count = args.organizations or summary.get("partyOrganizations", {}).get("records", 5)
    individual_count = args.individuals or summary.get("partyIndividuals", {}).get("records", 6)
    product_total = summary.get("productSpecifications", {}).get("records", organization_count * 3)
    if args.products_per_organization:
        product_counts = [args.products_per_organization] * organization_count
    else:
        base, remainder = divmod(product_total, organization_count)
        product_counts = [base + (index < remainder) for index in range(organization_count)]

    registry = SchemaRegistry(args.openapi)
    Scenario(args.seed, organization_count, args.products_per_organization, individual_count, product_counts).write(args.output, args.orders, registry)


if __name__ == "__main__":
    main()
