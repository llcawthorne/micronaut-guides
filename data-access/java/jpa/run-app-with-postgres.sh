#!/bin/sh
export JDBC_URL=jdbc:postgresql://localhost:5432/micronaut
export JDBC_USER=dbuser
export JDBC_PASSWORD=theSecretPassword
export JDBC_DRIVER=org.postgresql.Driver
./gradlew run
