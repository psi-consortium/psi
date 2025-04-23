#!make

include .env
export $(shell sed 's/=.*//' .env)

run:
	pnpm run dev
build:
	pnpm run build
preview:
	pnpm run preview

up:
	podman-compose up -d
down:
	podman-compose down

frontend:
	podman-compose -f docker-compose.frontend.yml up -d
frontend-down:
	podman-compose -f docker-compose.frontend.yml down
frontend-remove:
	podman rmi -f mission-management-frontend
restart: down remove up

shell:
	podman exec -it mission-frontend ash

logs:
	podman logs -f mission-frontend

env:
	podman exec -it mission-frontend env


image-clean:
	docker rmi $$(podman images | grep "<none>" | awk '{print $$3}')
