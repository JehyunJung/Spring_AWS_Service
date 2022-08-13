#!/bin/bash
PROJECT_ROOT=~/app
REPOSITORY=~/app/S3

DEPLOY_LOG=$PROJECT_ROOT/deploy.log
JAR_FILE="$PROJECT_ROOT/Spring_AWS_Webapp.jar"

TIME_NOW=$(date +%c)


CURRENT_PID=$(pgrep -f JAR_FILE)

if [ -z "$CURRENT_PID" ]; then
    echo "$TIME_NOW > 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다." >> $DEPLOY_LOG
else
    echo "$TIME_NOW > kill -15 $CURRENT_PID" >> $DEPLOY_LOG
    kill -15 $CURRENT_PID
    sleep 5
fi
