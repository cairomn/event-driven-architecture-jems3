#!/bin/bash
docker-compose -f "./infrastructure/docker-compose.yml" up -d --build
docker-compose -f "./applications/docker-compose.yml" up -d --build