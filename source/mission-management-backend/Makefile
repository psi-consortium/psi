#!make

include .env
export $(shell sed 's/=.*//' .env)

build:
	CGO_ENABLED=0 go build -ldflags="-w -s" -o mission-management cmd/api-server/main.go
run:
	go run cmd/api-server/main.go
debug:
	LOG_LEVEL=Debug go run cmd/api-server/main.go

mockery:
	mockery
test:
	go test ./... --cover
coverage-out:
	go test ./... -covermode=set -coverprofile=cov.out
	@# go test ./... -covermode=count -coverprofile=cov.out
	grep -v "/openapi/" cov.out | grep -v "mocks" > cov_filtered.out
coverage: coverage-out
	go tool cover -html=cov_filtered.out -o coverage.html
cov: coverage
cov-func: coverage-out
	go tool cover -func=cov_filtered.out | less

up:
	podman-compose up -d
build-mis: build
	podman build --tag mission-management-backend .
up-mis: build-mis
	podman rm -f mis
	podman run --name mis --rm -e DB_HOST -e DB_USER -e DB_PASSWORD -e APP_SECURITY_OPENIDCONNECT_PUBLICKEYENDPOINT -e LOG_LEVEL=Debug -p 7000:7000 --network=host localhost/mission-management-backend:latest
up-all: up up-mis


down-mis:
	podman rm -f mission-backend
down:
	podman-compose down
down-all: down-mis down


env:
	podman exec -it mission-backend

restart:
	podman-compose down
	podman rmi -i localhost/mission-management-backend
	CGO_ENABLED=0 go build -ldflags="-w -s" -o mission-management cmd/api-server/main.go
	podman-compose up -d


rm-mongo-volume:
	podman volume rm mission-management-backend_database-data

clean-start:
	down
	rm-data
	up

log-mis:
	podman logs -f mission-backend

mongosh:
	podman exec -it mongo-db mongosh -u ${DB_USER} -p

validate-openapi:
	go run github.com/getkin/kin-openapi/cmd/validate@latest ./internal/api/PSID002-MissionManagement_v2.oas.json

image-clean:
	podman rmi $$(podman images | grep "<none>" | awk '{print $$3}')


doc-install:
	go install golang.org/x/pkgsite/cmd/pkgsite@latest
doc:
	pkgsite -http :7070 .

