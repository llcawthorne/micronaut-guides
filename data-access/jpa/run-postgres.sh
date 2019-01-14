#!/bin/sh
docker run -it -p 5432:5432 -e POSTGRES_USER=dbuser -e POSTGRES_PASSWORD=theSecretPassword -e POSTGRES_DB=micronaut postgres:10.5-alpine
