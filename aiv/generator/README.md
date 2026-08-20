# Synthetic PSI test-data generator

This directory contains the first implementation for generating fictional PSI
test data without requiring the original sensitive fixtures at runtime.

## What it generates

The generator currently creates deterministic data for:

- Individuals and organizations
- Product specifications
- Service specifications
- Resource specifications
- Product offerings
- Product orders

All records are created from one shared in-memory scenario. Names and
references therefore remain consistent across files. The default output is
FVO-style fixture data: IDs are intentionally omitted because the existing
PSI demo-data loader resolves IDs from names while loading the fixtures.

## Generate data

From the PSI repository root (`psi/`):

```bash
python3 aiv/generator/generate.py --output aiv/testing/testdata --seed 42
```

Useful options:

```text
--seed                         Reproduce the same scenario
--organizations N              Number of fictional organizations
--products-per-organization N  Products generated for each organization
--individuals N                 Number of fictional individuals
--orders N                      Number of product orders
--output PATH                  Destination directory
```

For example:

```bash
python3 aiv/generator/generate.py \
  --output /tmp/psi-testdata \
  --seed 123 \
  --organizations 3 \
  --products-per-organization 4 \
  --individuals 5 \
  --orders 6
```

The generator uses only Python’s standard library and does not read the
original `testdata/` directory.

## Validate generated data

Install the validator dependency:

```bash
python3 -m pip install -r aiv/generator/requirements.txt
```

Run JSON, reference, and source-leakage checks:

```bash
python3 aiv/generator/validate.py aiv/testing/testdata --source ../testdata
```

The `--source` argument is intended for local development only. It compares
generated output against selected sensitive values from the original fixtures;
the originals do not need to be available in CI or in the deployed generator.

The repository also contains the public PSI OpenAPI specifications. They are
useful for schema validation, but the raw fixtures and the published FVO
schemas are not perfectly aligned: the demo loader accepts name-only
references and resolves their IDs. OpenAPI validation should therefore be
applied to a post-resolution representation, while raw generated fixtures are
checked using the loader-compatible validation rules.

## Existing structural profiler

`profile.py` can create a source-data profile containing only structural
metadata such as field paths, types, record counts, and array-size ranges:

```bash
python3 aiv/generator/profile.py ../testdata aiv/generator/profile.json
```

It deliberately does not copy names, descriptions, IDs, URLs, or other source
values into the profile.

## Implementation summary

The current implementation uses:

1. A fixed random seed for reproducible fixtures.
2. Fictional organizations, people, products, services, and resources.
3. A shared scenario registry to keep cross-file references consistent.
4. Generated dates and numeric characteristics with sensible relationships.
5. Template-based descriptions instead of copied source text.
6. Validation for JSON syntax, dangling references, and source-value leakage.

The next natural extensions are broader optional-field coverage, document
fixtures, simulated ID resolution, and separate request/response generation
modes.

## PSI mockup integration

The existing mockup test expects demo data at:

```text
aiv/testing/testdata
```

Generate directly into that isolated location with:

```bash
python3 aiv/generator/generate.py \
  --output aiv/testing/testdata \
  --seed 42
```

Then run the loader test from the PSI project:

```bash
cd psi
./gradlew :source:psid-mockup:test \
  --tests com.cgi.space.psi.pss.stub.demodata.DemoDataLoaderTest \
  --no-daemon
```

This requires the Gradle wrapper JAR or a system Gradle installation. The
current checkout used during development did not contain
`gradle/wrapper/gradle-wrapper.jar`, so the Java integration test still needs
to be run in a fully provisioned PSI development environment.
