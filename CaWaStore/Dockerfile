FROM openjdk:8-jdk-alpine
RUN addgroup -S webapp && adduser -S cawastore -G webapp
USER cawastore:webapp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
