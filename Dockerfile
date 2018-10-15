FROM openjdk:8-jre-slim
MAINTAINER Sergey Yeranosyan <serg.yer@gmail.com>
#Logging
RUN mkdir /logging

ADD logback.xml /logging

ARG JAR_FILE
ADD target/bike-1.0.0.jar target/

ENTRYPOINT [ "sh", \
             "-c", \
             "java -Xmx1014M -Xms1014M -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -Dlogging.config=/logging/logback.xml $JAVA_OPTS -jar target/bike-1.0.0.jar"]