# PSI test-data generator

This generator creates fictional PSI test data without reading the original
sensitive fixtures.

It creates individuals, organizations, products, services, resources,
offerings, and orders. All records are generated from one shared scenario, so
names and references stay consistent. The generator also checks the output
against the PSI OpenAPI structure and field types. Generation fails if the
resolved schemas reject any record.

The generation workflow is:

```text
Load OpenAPI schemas
        ↓
Generate fictional data
        ↓
Check the generated data against the schemas
        ↓
Write the JSON files
```

The schemas define the expected structure and field types. The generator rules
provide the fictional names, descriptions, values, and relationships.

The generator also uses the value-free `profile.json` to match the original
dataset's scale by default. It can be regenerated locally with `profile.py`,
but the original data is not needed when running the generator.

## Run

Run from the PSI repository root:

```bash
python3 aiv/generator/generate.py --seed 42
```

The generator requires the Python `jsonschema` package for its final OpenAPI
validation step.

The generated files are written to:

```text
aiv/testing/testdata/
```

The seed makes the output reproducible. To create a different dataset:

```bash
python3 aiv/generator/generate.py \
  --seed 123 \
  --organizations 3 \
  --products-per-organization 4 \
  --individuals 5 \
  --orders 6
```

The `--organizations`, `--products-per-organization`, and `--individuals`
options override the profile-derived sizes.

The OpenAPI directory can be overridden when testing a schema revision:

```bash
python3 aiv/generator/generate.py \
  --openapi /path/to/open-apis/oas
```

## Validate

```bash
python3 aiv/generator/validate.py aiv/testing/testdata
```

For local source-value leakage checks, provide the original data directory:

```bash
python3 aiv/generator/validate.py \
  aiv/testing/testdata \
  --source ../testdata
```

The original data is only needed for this optional local check, not for normal
generation.

To update the value-free profile from local source fixtures:

```bash
python3 aiv/generator/profile.py \
  ../testdata \
  aiv/generator/profile.json
```

## Schema integration test

Run the minimal integration test from the PSI repository root:

```bash
python3 -m unittest aiv/generator/test_integration.py
```

It generates temporary fixtures, checks every configured category against the
public OpenAPI structure, and verifies that an invalid field type is rejected.
The temporary fixtures are removed after the test.
