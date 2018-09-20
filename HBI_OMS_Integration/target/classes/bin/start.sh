#!/usr/bin/env bash
ENV=$1
if [ "$1" == '' ]
then
	ENV="dev";
fi
DB=$2
if [ "$2" == '' ]
then
	DB=false;
fi
java -Xms512m -Xmx1024m -server -verbose:gc -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=../logs  -Dspring.profiles.active=$ENV -Dspring.datasource.initialize=$DB -jar ../lib/omsintegration.jar > /dev/null