FROM adoptopenjdk:15-jdk
VOLUME /tmp
ARG JAR_FILE
ADD ${JAR_FILE} user-service-0.0.3.jar
ENTRYPOINT ["java", "-jar", "/user-service-0.0.3.jar"]