#!/bin/bash
rm -rf $(pwd)/gatling/results/*
docker run -it --rm -v $(pwd)/gatling/conf:/opt/gatling/conf \
--name gatling \
--network my-ne \
--hostname gatling \
-v $(pwd)/gatling/user-files:/opt/gatling/user-files \
-v $(pwd)/gatling/results:/opt/gatling/results \
-e BASE_URL="http://rest-api-app:8080" \
denvazh/gatling --simulation simplerest.PasswordApiSimulation