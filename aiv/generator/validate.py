#!/usr/bin/env python3
"""Validate generated PSI fixtures for JSON, references, and source leakage."""

from __future__ import annotations

import argparse
import json
import re
from pathlib import Path
from typing import Any


OPENAPI_FILES = {
    "partyIndividuals": ("PSID632-Party_Management-v5.0.0.oas.json", "Individual_FVO"),
    "partyOrganizations": ("PSID632-Party_Management-v5.0.0.oas.json", "Organization_FVO"),
    "productSpecifications": ("PSID620-Product_Catalog_Management-v5.0.0.oas.json", "ProductSpecification_FVO"),
    "productOfferings": ("PSID620-Product_Catalog_Management-v5.0.0.oas.json", "ProductOffering_FVO"),
    "serviceSpecifications": ("PSID633-Service_Catalog_Management-v5.0.0.oas.json", "ServiceSpecification_FVO"),
    "resourceSpecifications": ("PSID634-Resource_Catalog_Management-v5.0.0.oas.json", "PhysicalResourceSpecification_FVO"),
    "productOrders": ("PSID622-ProductOrdering-v5.0.0.oas.json", "ProductOrder_FVO"),
}


def walk(value: Any):
    if isinstance(value, dict):
        yield value
        for child in value.values():
            yield from walk(child)
    elif isinstance(value, list):
        for child in value:
            yield from walk(child)


def source_terms(source: Path) -> set[str]:
    terms: set[str] = set()
    fields = {"givenName", "familyName", "brand", "vendor", "model", "productNumber", "description", "href"}
    for file in source.rglob("*.json"):
        data = json.loads(file.read_text(encoding="utf-8"))
        for obj in walk(data):
            for key in fields:
                value = obj.get(key)
                minimum = 12 if key == "description" else 4
                if isinstance(value, str) and len(value) >= minimum:
                    terms.add(value)
            name = obj.get("name")
            # Names of concrete entities are useful leak indicators. Generic
            # characteristic names such as `networkUptime` are not.
            entity_type = str(obj.get("@type", ""))
            if isinstance(name, str) and ("Characteristic" not in entity_type) and (len(name) >= 8 or " " in name):
                terms.add(name)
    return terms


def validate_references(records: list[Any]) -> list[str]:
    known_names = {obj["name"] for record in records for obj in walk(record) if isinstance(obj.get("name"), str)}
    errors = []
    for record in records:
        for obj in walk(record):
            if str(obj.get("@type", "")).endswith("Ref") and isinstance(obj.get("name"), str):
                if obj["name"] not in known_names:
                    errors.append(f"dangling reference: {obj['name']}")
    return errors


def validate_openapi(output: Path, spec_dir: Path) -> list[str]:
    try:
        from jsonschema import Draft7Validator, RefResolver
    except ImportError:
        return ["OpenAPI validation requires the 'jsonschema' package"]

    errors: list[str] = []
    cache: dict[str, dict[str, Any]] = {}
    for category, (filename, schema_name) in OPENAPI_FILES.items():
        spec_path = spec_dir / filename
        if not spec_path.exists():
            errors.append(f"missing OpenAPI specification: {spec_path}")
            continue
        if filename not in cache:
            cache[filename] = json.loads(spec_path.read_text(encoding="utf-8"))
        spec = cache[filename]
        schema = spec.get("components", {}).get("schemas", {}).get(schema_name)
        if schema is None:
            errors.append(f"schema not found: {filename}#{schema_name}")
            continue
        validator = Draft7Validator(schema, resolver=RefResolver.from_schema(spec))
        for file in sorted((output / category).glob("*.json")):
            for index, record in enumerate(json.loads(file.read_text(encoding="utf-8"))):
                for error in validator.iter_errors(record):
                    # Several PSI schemas use oneOf with overlapping
                    # discriminator branches. jsonschema reports valid
                    # instances as ambiguous; this is a schema ambiguity,
                    # not invalid generated data.
                    if "is valid under each of" in error.message:
                        continue
                    path = ".".join(str(part) for part in error.path)
                    errors.append(f"{file}:{index}:{path}: {error.message}")
    return errors


def main() -> None:
    parser = argparse.ArgumentParser()
    parser.add_argument("output", type=Path)
    parser.add_argument("--source", type=Path, help="optional local original data directory")
    parser.add_argument("--openapi", type=Path, help="directory containing the full OAS files")
    args = parser.parse_args()

    files = sorted(args.output.rglob("*.json"))
    records = []
    errors: list[str] = []
    for file in files:
        try:
            records.extend(json.loads(file.read_text(encoding="utf-8")))
        except (json.JSONDecodeError, TypeError) as exc:
            errors.append(f"invalid JSON: {file}: {exc}")

    errors.extend(validate_references(records))
    if args.source:
        all_text = "\n".join(file.read_text(encoding="utf-8") for file in files)
        leaked = sorted(term for term in source_terms(args.source) if term in all_text)
        errors.extend(f"source value found in output: {term}" for term in leaked)
    if args.openapi:
        errors.extend(validate_openapi(args.output, args.openapi / "oas"))

    if errors:
        print("Validation failed:")
        print("\n".join(f"- {error}" for error in errors))
        raise SystemExit(1)
    print(f"Validation passed for {len(files)} files and {len(records)} records.")


if __name__ == "__main__":
    main()
