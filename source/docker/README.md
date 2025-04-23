# PSID Mock-Up OCI Image

This folder provides all tools to package the PSID Mock-Up and it's dependencies in a single OCI image.
To create one using the latest version run:

```sh
sh deploy.sh
```

This will create a file called `psi-mockup.tar` that can be imported on another machine using podman (or docker):

```sh
podman load -i psi-mockup.tar
```

After that it can be run, e.g. with the following command:

```sh
podman run --name psi -it --rm -p 8001:8001 -p 8081:8081 psi:latest
```

Since this starts an interactive shell, the application can be shut down using `Ctrl+C`.
The container will be removed afterwards, causing it to be empty on the next run.
If persistency is required, map the container-directory `/data/db` to a volume on the host and omit the `--rm` parameter.
You may change the port mapping if the given ports are not available by changing the port number *before* the colon (e.g. `-p 8080:8001`).
Logs are stored in `/var/log`, which can also be mapped to a volume or accessed in a running container using:

```sh
podman exec -it psi /bin/sh
cd /var/log
```

While the container is running, the API is reachable via port `8001` (if not changed as described above).
You can use any HTTP client to call the endpoints described in [PSI-ICD] and the integration tests in `aiv/testing/aitf/procedures/`, e.g.:

```sh
curl -X 'GET' \
  'http://localhost:8001/psi-api/resourceCatalog/v2/resourceSpecification' \
  -H 'accept: application/json;charset=utf-8'
```

Also the mongo-express console should be available using any browser at [http://localhost:8081/](http://admin:pass@localhost:8081/) using `admin` and `pass` to log in.

# License

All containers in this folder are licensed as part of the PSI project.
Details can be found in the [PSI-SLF].

## License Text

Copyright 2022 THE PSI CONSORTIUM
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
