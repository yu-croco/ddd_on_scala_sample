#!/usr/bin/env bash
docker-compose up -d db &
sbt clean
sbt run -Dhttp.port=9011 -Dconfig.file=./conf/application.conf -Dlogger.file=./conf/logback.xml
