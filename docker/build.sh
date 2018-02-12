#!/bin/bash
git clone https://github.com/elixir-europe/ega-data-api-v3-downloader.git
mvn -f /ega-data-api-v3-downloader/pom.xml install
mv /ega-data-api-v3-downloader/target/downloaderservice-0.0.1-SNAPSHOT.war /EGA_build
mv /ega-data-api-v3-downloader/docker/downloaderd.sh /EGA_build
mv /ega-data-api-v3-downloader/docker/Dockerfile_Deploy /EGA_build
