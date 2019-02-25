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
./mvnw spring-boot:run
```

## Test service

```bash
./mvnw test
```

## Usage

You can retrieve nearest UBS with the following commands:

### Retrieve nearest UBS

```bash
curl -X GET -H "Content-Type:application/json" "http://localhost:8080/nearest?geocode_lat=1500&geocode_long=1500"
```

Example Result:
```bash
[{"id":1,"name":"UBS 1","address":"Rua teste 1","city":"Cidade Teste 1","phone":"","geocode":{"lat":1000.0,"long":1000.0},"scores":{"scores_size":"1","scores_medicine":"4","scores_medical_equipament":"3","scores_adaptation_for_seniors":"2"}},{"id":2,"name":"UBS 2","address":"Rua teste 2","city":"Cidade Teste 2","phone":"","geocode":{"lat":2000.0,"long":2000.0},"scores":{"scores_size":"4","scores_medicine":"1","scores_medical_equipament":"2","scores_adaptation_for_seniors":"3"}}]%
```
