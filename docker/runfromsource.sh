#!/bin/bash
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
sudo docker run --rm --name build -v $DIR:/EGA_build -it alexandersenf/ega_build sh -c 'exec /EGA_build/build.sh'
sudo docker build -t ega_downloader -f Dockerfile_Deploy .
sudo rm downloaderservice-0.0.1-SNAPSHOT.war
sudo rm Dockerfile_Deploy
sudo rm downloaderd.sh
sudo docker run -d -p 9050:9050 ega_downloader
