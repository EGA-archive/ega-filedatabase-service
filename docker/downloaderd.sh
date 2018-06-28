#!/bin/bash
SERVICE_NAME=FiledatabaseService
PATH_TO_JAR=/filedatabaseservice-0.0.1-SNAPSHOT.jar
PROCESSCNT=$(ps x | grep -v grep | grep -c "filedatabaseservice-0.0.1-SNAPSHOT.jar")
PID=$(ps aux | grep "filedatabaseservice-0.0.1-SNAPSHOT.jar" | grep -v grep | awk '{print $2}')
case $1 in
    start)
	if [ $PROCESSCNT == 0 ]; then
	    echo "Starting $SERVICE_NAME ..."
	    nohup java -jar $PATH_TO_JAR 2>> /dev/null >> /dev/null &
	    echo "$SERVICE_NAME started ..."
	else
	    echo "$SERVICE_NAME is already running ..."
	fi
    ;;
    stop)
        if [ $PROCESSCNT != 0 ]; then
            echo "$SERVICE_NAME stopping ..."
            kill $PID;
                        sleep 2s
            echo "$SERVICE_NAME stopped ..."
        else
            echo "$SERVICE_NAME is not running ..."
        fi
    ;;
esac
