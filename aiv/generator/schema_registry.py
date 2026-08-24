"""Small OpenAPI schema registry used by the synthetic data generator.

The PSI specifications contain FVO schemas whose required fields describe API
requests after server-side enrichment. The generator therefore performs a
permissive structural check here: references, property types, arrays, and
objects are checked, while required fields and discriminator alternatives are
left to the PSI loader.
"""

from __future__ import annotations

import json
from pathlib import Path
from typing import Any


SCHEMAS = {
    "partyIndividuals": ("PSID632-Party_Management-v5.0.0.oas.json", "Individual_FVO"),
    "partyOrganizations": ("PSID632-Party_Management-v5.0.0.oas.json", "Organization_FVO"),
    "productSpecifications": ("PSID620-Product_Catalog_Management-v5.0.0.oas.json", "ProductSpecification_FVO"),
    "productOfferings": ("PSID620-Product_Catalog_Management-v5.0.0.oas.json", "ProductOffering_FVO"),
    "serviceSpecifications": ("PSID633-Service_Catalog_Management-v5.0.0.oas.json", "ServiceSpecification_FVO"),
    # DemoDataLoader reads resource fixtures as ResourceSpecificationFVO, so
    # validate the request/input schema rather than the persisted concrete
    # PhysicalResourceSpecification schema.
    "resourceSpecifications": ("PSID634-Resource_Catalog_Management-v5.0.0.oas.json", "ResourceSpecification_FVO"),
    "productOrders": ("PSID622-ProductOrdering-v5.0.0.oas.json", "ProductOrder_FVO"),
}


class SchemaRegistry:
    def __init__(self, openapi_dir: Path):
        self.openapi_dir = openapi_dir
        self.documents: dict[str, dict[str, Any]] = {}
        for filename, _ in SCHEMAS.values():
            if filename not in self.documents:
                path = openapi_dir / filename
                if not path.exists():
                    raise FileNotFoundError(f"OpenAPI specification not found: {path}")
                self.documents[filename] = json.loads(path.read_text(encoding="utf-8"))

    def validate_category(self, category: str, records: list[dict[str, Any]]) -> list[str]:
        filename, schema_name = SCHEMAS[category]
        schema = self.documents[filename]["components"]["schemas"][schema_name]
        errors: list[str] = []
        for index, record in enumerate(records):
            self._validate(record, schema, filename, f"{category}[{index}]", errors, set())
        return errors

    def _resolve(self, schema: dict[str, Any], filename: str) -> tuple[dict[str, Any], str]:
        reference = schema.get("$ref")
        if not reference:
            return schema, filename
        if not reference.startswith("#/components/schemas/"):
            return schema, filename
        name = reference.rsplit("/", 1)[-1]
        return self.documents[filename]["components"]["schemas"][name], filename

    def _validate(self, value: Any, schema: dict[str, Any], filename: str, path: str, errors: list[str], seen: set[int]) -> None:
        schema, filename = self._resolve(schema, filename)
        marker = id(schema)
        if marker in seen:
            return
        seen.add(marker)

        for branch in schema.get("allOf", []):
            self._validate(value, branch, filename, path, errors, seen)

        # PSI uses large discriminator-based oneOf/anyOf trees. The selected
        # concrete @type is already supplied by the domain factory; expanding
        # every alternative here is both expensive and unnecessarily strict.
        # The normal checks below still validate the concrete properties we
        # can resolve.

        expected = schema.get("type")
        if expected and not self._matches_type(value, expected):
            errors.append(f"{path}: expected {expected}, got {type(value).__name__}")
            return

        allowed = schema.get("enum")
        if allowed is not None and value not in allowed:
            errors.append(f"{path}: value {value!r} is not one of {allowed!r}")

        if isinstance(value, (int, float)) and not isinstance(value, bool):
            if "minimum" in schema and value < schema["minimum"]:
                errors.append(f"{path}: value {value!r} is below minimum {schema['minimum']!r}")
            if "maximum" in schema and value > schema["maximum"]:
                errors.append(f"{path}: value {value!r} is above maximum {schema['maximum']!r}")

        if isinstance(value, str):
            if "minLength" in schema and len(value) < schema["minLength"]:
                errors.append(f"{path}: length is below minLength {schema['minLength']}")
            if "maxLength" in schema and len(value) > schema["maxLength"]:
                errors.append(f"{path}: length exceeds maxLength {schema['maxLength']}")

        if isinstance(value, list):
            if "minItems" in schema and len(value) < schema["minItems"]:
                errors.append(f"{path}: item count is below minItems {schema['minItems']}")
            if "maxItems" in schema and len(value) > schema["maxItems"]:
                errors.append(f"{path}: item count exceeds maxItems {schema['maxItems']}")

        if isinstance(value, dict):
            properties = schema.get("properties", {})
            for key, child in value.items():
                if key in properties:
                    self._validate(child, properties[key], filename, f"{path}.{key}", errors, seen)
        elif isinstance(value, list) and schema.get("items"):
            for index, child in enumerate(value):
                self._validate(child, schema["items"], filename, f"{path}[{index}]", errors, seen)

    @staticmethod
    def _matches_type(value: Any, expected: str | list[str]) -> bool:
        expected_types = [expected] if isinstance(expected, str) else expected
        checks = {
            "object": lambda v: isinstance(v, dict),
            "array": lambda v: isinstance(v, list),
            "string": lambda v: isinstance(v, str),
            "integer": lambda v: isinstance(v, int) and not isinstance(v, bool),
            "number": lambda v: isinstance(v, (int, float)) and not isinstance(v, bool),
            "boolean": lambda v: isinstance(v, bool),
            "null": lambda v: v is None,
        }
        return any(checks.get(kind, lambda _: True)(value) for kind in expected_types)
