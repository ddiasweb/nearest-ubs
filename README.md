<!--
title: 'nearest-ubs'
description: 'Locate nearest UBS Web API'
layout: Doc
framework: v1
language: java
authorLink: 'https://github.com/ddiasweb'
authorName: 'Décio Dias'
authorAvatar: 'https://avatars3.githubusercontent.com/u/298960?s=140&v=4'
-->
# Nearest UBS

It provides a REST API to get nearest UBS stored in a postgres. A Dockerfile is provided to create a postgres database to the application.

## Requisites

- java 1.8
- docker

## Setup

```bash
sudo docker build -t postgres-bionexo .
sudo docker run --name postgres-bionexo -p 5432:5432 -d postgres-bionexo
```

## Run service

```bash
sudo docker start postgres-bionexo
./mvnw spring-boot:run -Dspring-boot.run.arguments="reload"
```

## API documentation

[http://localhost:8080/swagger-ui.html#/web-controller](http://localhost:8080/swagger-ui.html#/web-controller)


## Load sample data

```bash
./mvnw spring-boot:run -Dspring-boot.run.arguments="reload,-f,database/ubs-sample.csv"
```

## Test application

```bash
./mvnw test
```

## Build application

```bash
./mvnw package
```

## Load data on production database
```
wget http://repositorio.dados.gov.br/saude/unidades-saude/unidade-basica-saude/ubs.csv
java -jar target/nearest-ubs-<VERSION>.jar reload -f ubs.csv
```

## Usage

You can retrieve nearest UBS with the following commands:

### Retrieve nearest UBS

```bash
curl -X GET -H "Content-Type:application/json" "http://localhost:8080/nearest?location=-23.591210,-46.686830&radius=5000"
```

Example Result:

```bash
[{"id":2027380,"name":"UBS MENINOPOLIS MARIO FRANCISCO NAPOLITANO","address":"RUA OSCAR GOMES CARDIM","city":"São Paulo","phone":"1150961058","geocode":{"long":-46.687686920166016,"lat":-23.620895385742188},"scores":{"scores_size":2,"scores_adaptation_for_seniors":1,"scores_medical_equipament":1,"scores_medicine":2}}]
```
