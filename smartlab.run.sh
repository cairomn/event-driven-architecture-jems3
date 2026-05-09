#!/bin/bash
docker-compose -f "./infrastructure/messengers/docker-compose.yml" up -d --build
docker-compose -f "./infrastructure/databases/docker-compose.yml" up -d --build
docker-compose -f "./applications/docker-compose.yml" up -d --build