# Mission Management Backend

The Mission Management Backend is a Go application providing REST endpoints to manage a mission.
The REST endpoints and schemas are defined by the PSI Mission Management API:

* PSID002-MissionManagement_v2.oas.json

This Backend is using MongoDB to store the Mission data.

## Running Mission Management

To run the Mission Management, please start the DB and the Mission Management Backend with the help of the `docker-compose.yml`.

```sh
podman-compose up -d
```

Environment variables for the Mission Management Backend are taken from the `config.yml` and, if available, overruled by the definitions of an `.env` file.

For development, the Mission Management Go app, can be executed manually (don't forget to stop the container) via:

```sh
go run cmd/api-server/main.go
```

A `Makefile` was added to support simplified usage via `make`:

```sh
make up
make run
make debug
make build
make monogsh
...
```

### Configuration

A `config.yml` offers the adjustment all introduced configuration parameters.
Nevertheless, all the configuration parameters could be overwritten with ENV variables.
A `.env_example` is added to name the supported ENV variables.

The `docker-compse.yml` and the introduced `Makefile` are making usage of an optional `.env` file for ENV variable adjustments.

## Build Binary

The Mission Management binary can be created by

```sh
CGO_ENABLED=0 go build -ldflags="-w -s" -o mission-management cmd/api-server/main.go
```

This statement already includes optimizations for the image create:

* Statically linking: `CGO_ENABLED=0`
* Remove Debug Data : `-ldflags="-w -s"`

A `Dockerfile` for the image creation of the pre-build binary is provided:

```sh
podman build --tag mission-management-backend .
```

And the `docker-compose.yml` is prepared for an on demand build of the Mission Management image and the start of the Mission Management container.

## OpenAPI library kin-openapi

The Mission Management backend utilizes github.com/getkin/kin-openapi.

**Hint:** It is possible to validate OpenAPI definitions with the help of this library from the command line. E.g.:

```sh
go run github.com/getkin/kin-openapi/cmd/validate@latest internal/api/PSID002-MissionManagement_v2.oas.json
```

## MongoDb / mongosh

Run `monogosh` with the help of the already started MongoDB container (see `docker-compose.yml`):

```sh
podman exec -it mongo-db mongosh -u <username> -p
```
(A shortcut via `make mongosh` is ready for use.)

Example queries:

```sql
$ use MissionManagement
$ db.Missions.findOne({"relatedparty.id": "584e3cd2-661d-4845-a549-79a5448bfdd4"})
$ db.Missions.findOne({"relatedparty": {"$elemMatch": {"id": "584e3cd2-661d-4845-a549-79a5448bfdd4", "role": "Provider"}}})
$ db.Missions.findOne({"relatedparty": {"$elemMatch": {"id": "584e3cd2-661d-4845-a549-79a5448bfdd4", "role": "Provider"}}},
  { "_id": 0, "id": 1, "name": 1, "relatedparty": 1})
{
  id: '38896474-6712-4293-bc24-95176bda6590',
  name: 'Demo 1'
}
$ db.Missions.findOne({"id": "38896474-6712-4293-bc24-95176bda6590", "relatedparty": { "$elemMatch": { "id": "584e3cd2-661d-4845-a549-79a5448bfdd4", "role": "Provider" } } }, { "id": 1, "name": 1, "relatedparty": 1 })
{
  _id: ObjectId('678e2b037d9f920231a0c733'),
  id: '38896474-6712-4293-bc24-95176bda6590',
  name: 'Demo 1',
  relatedparty: [
    {
      id: '584e3cd2-661d-4845-a549-79a5448bfdd4',
      href: null,
      name: 'Test Provider',
      role: 'Provider',
      basetype: null,
      schemalocation: null,
      type: null,
      referredtype: ''
    }
  ]
}
$ MissionManagement> db.Missions.find({"relatedparty": { "$elemMatch": { "id": "584e3cd2-661d-4845-a549-79a5448bfdd4", "role": "Provider" } } }, { "id": 1, "name": 1 })
[
  {
    _id: ObjectId('678e2b037d9f920231a0c733'),
    id: '38896474-6712-4293-bc24-95176bda6590',
    name: 'Demo 1'
  }
]
```

In addition, **Mongo Express** is part of the `docker-compose.yml` and the frontend can be opened in the browser by entering `http://localhost:8888/` (please have the credentials *DB_USER* and *DB_PASSWORD* for the login ready).
