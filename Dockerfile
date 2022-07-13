# syntax=docker/dockerfile:experimental
FROM openjdk:15.0.2-slim-buster as build
WORKDIR application

COPY mvnw .
COPY pom.xml .
COPY .mvn .mvn

# download dependencies
RUN ./mvnw dependency:go-offline

COPY src src

# Setup the maven cache
RUN --mount=type=cache,target=/root/.m2,rw ./mvnw -B package -DskipTests

FROM openjdk:15.0.2-slim-buster
COPY --from=build target/*.jar app.jar
ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v1.11.0/opentelemetry-javaagent.jar /opt/opentelemetry-javaagent.jar
ENV JAVA_TOOL_OPTIONS=-javaagent:/opt/opentelemetry-javaagent.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
