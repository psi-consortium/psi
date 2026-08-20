# PSI test-data generator

This generator creates fictional PSI test data without reading the original
sensitive fixtures.

It creates individuals, organizations, products, services, resources,
offerings, and orders. All records are generated from one shared scenario, so
names and references stay consistent. The generator also checks the output
against the PSI OpenAPI structure and field types.

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

## Run

Run from the PSI repository root:

```bash
python3 aiv/generator/generate.py --seed 42
```

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
