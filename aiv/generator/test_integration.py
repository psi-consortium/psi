#!/usr/bin/env python3
"""Minimal integration tests for generated PSI fixtures."""

from __future__ import annotations

import json
import subprocess
import sys
import tempfile
import unittest
from pathlib import Path


GENERATOR_DIR = Path(__file__).resolve().parent
PSI_ROOT = GENERATOR_DIR.parents[1]
OPENAPI_DIR = PSI_ROOT / "doc" / "PSI" / "PSI-ICD" / "open-apis" / "oas"

# The generator files are standalone scripts. Import the registry directly so
# this test needs no package metadata or third-party test dependencies.
sys.path.insert(0, str(GENERATOR_DIR))
from schema_registry import SCHEMAS, SchemaRegistry  # noqa: E402


class GeneratedDataSchemaTest(unittest.TestCase):
    def generate_fixture_directory(self, output: Path) -> None:
        subprocess.run(
            [
                sys.executable,
                str(GENERATOR_DIR / "generate.py"),
                "--output", str(output),
                "--openapi", str(OPENAPI_DIR),
                "--seed", "42",
                "--organizations", "2",
                "--products-per-organization", "2",
                "--individuals", "2",
                "--orders", "2",
            ],
            check=True,
            cwd=PSI_ROOT,
        )

    @staticmethod
    def records_by_category(output: Path, category: str) -> list[dict]:
        records: list[dict] = []
        for file in sorted((output / category).glob("*.json")):
            records.extend(json.loads(file.read_text(encoding="utf-8")))
        return records

    def test_generated_data_matches_all_configured_schemas(self) -> None:
        with tempfile.TemporaryDirectory() as directory:
            output = Path(directory)
            self.generate_fixture_directory(output)
            registry = SchemaRegistry(OPENAPI_DIR)

            for category in SCHEMAS:
                with self.subTest(category=category):
                    records = self.records_by_category(output, category)
                    self.assertTrue(records, f"generator produced no records for {category}")
                    errors = registry.validate_category(category, records)
                    self.assertEqual([], errors, "\n".join(errors))

    def test_schema_check_rejects_wrong_field_type(self) -> None:
        with tempfile.TemporaryDirectory() as directory:
            output = Path(directory)
            self.generate_fixture_directory(output)
            records = self.records_by_category(output, "productSpecifications")
            records[0]["isBundle"] = "not-a-boolean"

            errors = SchemaRegistry(OPENAPI_DIR).validate_category(
                "productSpecifications", records
            )

            self.assertTrue(any("isBundle" in error for error in errors), errors)

    def test_schema_check_rejects_invalid_enum_value(self) -> None:
        with tempfile.TemporaryDirectory() as directory:
            output = Path(directory)
            self.generate_fixture_directory(output)
            records = self.records_by_category(output, "productOrders")
            records[0]["productOrderItem"][0]["action"] = "unsupported-action"

            errors = SchemaRegistry(OPENAPI_DIR).validate_category(
                "productOrders", records
            )

            self.assertTrue(any("action" in error for error in errors), errors)

    def test_schema_check_rejects_numeric_range_violation(self) -> None:
        with tempfile.TemporaryDirectory() as directory:
            output = Path(directory)
            self.generate_fixture_directory(output)
            records = self.records_by_category(output, "productOfferings")
            records[0]["accessProbability"] = 2.0

            errors = SchemaRegistry(OPENAPI_DIR).validate_category(
                "productOfferings", records
            )

            self.assertTrue(any("accessProbability" in error for error in errors), errors)

    def test_schema_check_rejects_array_size_violation(self) -> None:
        with tempfile.TemporaryDirectory() as directory:
            output = Path(directory)
            self.generate_fixture_directory(output)
            records = self.records_by_category(output, "productOrders")
            records[0]["productOrderItem"] = []

            errors = SchemaRegistry(OPENAPI_DIR).validate_category(
                "productOrders", records
            )

            self.assertTrue(any("productOrderItem" in error for error in errors), errors)

    def test_schema_check_enforces_string_length(self) -> None:
        errors: list[str] = []
        SchemaRegistry(OPENAPI_DIR)._validate(
            "x", {"type": "string", "minLength": 2}, "", "field", errors, set()
        )

        self.assertTrue(errors)


if __name__ == "__main__":
    unittest.main()
