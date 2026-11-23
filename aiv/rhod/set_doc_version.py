#!/usr/bin/env python3

"""Replace document version placeholders with the release tag for PDF builds."""

from pathlib import Path
import sys


PLACEHOLDER = b"PSI-DOC-VERSION"


def main() -> int:
    if len(sys.argv) != 2 or not sys.argv[1]:
        print("Usage: set_doc_version.py <version>", file=sys.stderr)
        return 1
    version = sys.argv[1]

    doc_root = Path(__file__).resolve().parents[2] / "doc"
    changed_files = 0
    for path in doc_root.rglob("*.md"):
        content = path.read_bytes()
        if PLACEHOLDER in content:
            path.write_bytes(content.replace(PLACEHOLDER, version.encode("utf-8")))
            changed_files += 1

    remaining = [str(path) for path in doc_root.rglob("*.md") if PLACEHOLDER in path.read_bytes()]
    if remaining:
        print("Unreplaced document version placeholder(s):", file=sys.stderr)
        print("\n".join(remaining), file=sys.stderr)
        return 1

    if changed_files == 0:
        print("No document version placeholders were found", file=sys.stderr)
        return 1

    print(f"Set document version to {version} in {changed_files} Markdown file(s)")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
