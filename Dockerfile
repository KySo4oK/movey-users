FROM openjdk:15-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
ADD ${JAR_FILE} user-service-0.0.1.jar
ENTRYPOINT ["java", "-jar", "/user-service-0.0.1.jar"]