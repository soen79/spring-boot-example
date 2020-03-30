FROM  openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRY_POINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]