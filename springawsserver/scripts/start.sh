#!/bin/bash
PROJECT_ROOT=~/app
REPOSITORY=~/app/S3

APP_LOG="$PROJECT_ROOT/application.log"
ERROR_LOG="$PROJECT_ROOT/error.log"
DEPLOY_LOG="$PROJECT_ROOT/deploy.log"

JAR_FILE="$PROJECT_ROOT/Spring_AWS_Webapp.jar"

TIME_NOW=$(date +%c)

echo "$TIME_NOW > Build 파일 복사" >> $DEPLOY_LOG

cp $REPOSITORY/zip/build/libs/*.jar $JAR_FILE

echo "$TIME_NOW > 새 어플리케이션 실행: $JAR_FILE" >> $DEPLOY_LOG
chmod +x $JAR_FILE
nohup java -jar \
    -Dspring.config.location=classpath:/application-real.properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties \
    -Dspring.profiles.active=real \
    $JAR_NAME > $APP_LOG 2>$ERROR_LOG &

CURRENT_PID=$(pgrep -f JAR_FILE)
echo "$TIMENOW > 실행된 프로세스 ID: $CURRENT_PID" >> $DEPLOY_LOG