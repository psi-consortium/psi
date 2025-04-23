# PSI source code repository

This is the root folder for all source code contributions.
Find the documentation in the `../doc` folder.
Most notably please find the OpenAPI definition in `PSI-ICD`, which can be imported in tools like Postman or used to create clients for the endpoints.

The following sub-folders are most relevant:

* `docker` contains files to create a single running container of the mock-up
* `common` and `psid-mockup` contain the Java code of the mock-up, which is mostly generated
* `mongodb` contains the configuration to run a local MongoDB for development

## Development Environment

The project uses CGI's "ZORG" container, which comes with all tools preinstalled.
If you intend to set up the environment manually, we assume minimally the following tools:

* Linux OS (e.g. CentOS Stream or Red Hat Enterprise Linux 8)
* Podman or Docker

For active development we also recommend the following tools:

* OpenJDK 11
* Gradle 7.4
* VS Code

## License

All source code underneath and within this folder is licensed as part of the PSI project.
Details can be found in the [PSI-SLF] as part of the documentation.

### License Text

Copyright 2022-2024 THE PSI CONSORTIUM
Portions of this creation are adapted from the TM Forum project (https://www.tmforum.org).

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
