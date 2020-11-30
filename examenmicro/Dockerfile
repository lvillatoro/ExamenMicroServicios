FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
ADD target/examenmicro-0.0.1-SNAPSHOT.jar examenmicro.jar
EXPOSE 3000
ENTRYPOINT exec java $JAVA_OPTS -jar examenmicro.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar examenmicro.jar
