CREATE USER bionexo;
CREATE DATABASE bionexo;
GRANT ALL PRIVILEGES ON DATABASE bionexo TO bionexo;
SET search_path = bionexo;
CREATE EXTENSION postgis;
CREATE EXTENSION postgis_topology;
CREATE EXTENSION fuzzystrmatch;
CREATE EXTENSION postgis_tiger_geocoder;
