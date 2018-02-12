#!/bin/bash
SERVICE_NAME=DownloaderService
PATH_TO_JAR=/downloaderservice-0.0.1-SNAPSHOT.war
PROCESSCNT=$(ps x | grep -v grep | grep -c "downloaderservice-0.0.1-SNAPSHOT.war")
#PID=$(ps aux | grep "downloaderservice-0.0.1-SNAPSHOT.war" | grep -v grep | awk '{print $2}')
if [ $PROCESSCNT == 0 ]; then
    echo "Starting $SERVICE_NAME ..."
    nohup java -jar $PATH_TO_JAR 2>> /dev/null >> /dev/null &
    echo "$SERVICE_NAME started ..."
#else
#    echo "$SERVICE_NAME is already running ..."
fi
