FROM mdillon/postgis

ENV POSTGRES_USER bionexo
ENV POSTGRES_PASSWORD bionexo

COPY database/init.sql /docker-entrypoint-initdb.d/