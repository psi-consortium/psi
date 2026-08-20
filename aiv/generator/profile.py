#!/usr/bin/env python3
"""Create a source-data profile without copying source values.

The profile records structure and distributions only: file counts, field
presence, value types, array-size ranges, and numeric ranges. It intentionally
does not record names, descriptions, IDs, URLs, or any other source values.
"""

from __future__ import annotations

import argparse
import json
from collections import defaultdict
from pathlib import Path
from typing import Any


def update_type(types: set[str], value: Any) -> None:
    if value is None:
        types.add("null")
    elif isinstance(value, bool):
        types.add("boolean")
    elif isinstance(value, int) and not isinstance(value, bool):
        types.add("integer")
    elif isinstance(value, float):
        types.add("number")
    elif isinstance(value, str):
        types.add("string")
    elif isinstance(value, list):
        types.add("array")
    elif isinstance(value, dict):
        types.add("object")


def inspect(value: Any, path: str, fields: dict[str, dict[str, Any]], arrays: dict[str, list[int]]) -> None:
    if isinstance(value, dict):
        for key, child in value.items():
            child_path = f"{path}.{key}" if path else key
            entry = fields.setdefault(child_path, {"types": set(), "occurrences": 0, "numbers": []})
            update_type(entry["types"], child)
            entry["occurrences"] += 1
            if isinstance(child, (int, float)) and not isinstance(child, bool):
                entry["numbers"].append(child)
            inspect(child, child_path, fields, arrays)
    elif isinstance(value, list):
        arrays.setdefault(path, []).append(len(value))
        for child in value:
            inspect(child, f"{path}[]", fields, arrays)


def build_profile(input_dir: Path) -> dict[str, Any]:
    files: dict[str, Any] = {}
    summary: dict[str, dict[str, int]] = defaultdict(lambda: {"files": 0, "records": 0})
    for source in sorted(input_dir.rglob("*.json")):
        relative = source.relative_to(input_dir).as_posix()
        with source.open(encoding="utf-8") as handle:
            data = json.load(handle)

        records = data if isinstance(data, list) else [data]
        category = relative.split("/", 1)[0]
        summary[category]["files"] += 1
        summary[category]["records"] += len(records)
        fields: dict[str, dict[str, Any]] = {}
        arrays: dict[str, list[int]] = {}
        inspect(data, "", fields, arrays)

        files[relative] = {
            "records": len(records),
            "fieldPaths": {
                path: {
                    "types": sorted(info["types"]),
                    "occurrences": info["occurrences"],
                    "presenceRate": round(min(1.0, info["occurrences"] / max(1, len(records))), 4),
                    **({"numericRange": {"min": min(info["numbers"]), "max": max(info["numbers"])} } if info["numbers"] else {}),
                }
                for path, info in sorted(fields.items())
            },
            "arrayLengths": {
                path: {"min": min(lengths), "max": max(lengths)}
                for path, lengths in sorted(arrays.items())
            },
        }

    return {
        "source": "structural profile and distributions only",
        "summary": {category: values for category, values in sorted(summary.items())},
        "files": files,
    }


def main() -> None:
    parser = argparse.ArgumentParser()
    parser.add_argument("input", type=Path, help="directory containing source JSON files")
    parser.add_argument("output", type=Path, help="destination for the sanitized profile")
    args = parser.parse_args()

    profile = build_profile(args.input)
    args.output.parent.mkdir(parents=True, exist_ok=True)
    with args.output.open("w", encoding="utf-8") as handle:
        json.dump(profile, handle, indent=2, sort_keys=True)
        handle.write("\n")


if __name__ == "__main__":
    main()
