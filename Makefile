.PHONY: up

up:
	docker-compose up -d

.PHONY: down

down:
	docker-compose down


.PHONY: logs

logs:
	docker-compose logs -f


.PHONY: build

build:
	docker build -t springio/gs-spring-boot-docker .
