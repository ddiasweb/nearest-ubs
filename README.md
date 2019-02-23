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

