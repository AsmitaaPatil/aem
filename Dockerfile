FROM openjdk:8-jdk-alpine
ARG JAR_FILE=build/libs/aem-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /adobe_aem.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/adobe_aem.jar"]