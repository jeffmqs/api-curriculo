
FROM maven:3.8.8-eclipse-temurin-17 AS build

WORKDIR /app


COPY curriculo-api/pom.xml .


RUN mvn dependency:go-offline


COPY curriculo-api/ .


RUN mvn clean install


FROM openjdk:17-jdk-slim


WORKDIR /app


EXPOSE 8083


COPY --from=build /app/target/curriculo-api-0.0.1-SNAPSHOT.jar app.jar


ENTRYPOINT ["java", "-jar", "app.jar"]
