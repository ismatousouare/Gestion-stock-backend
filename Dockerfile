FROM maven:3.9.7-sapmachine  AS application
LABEL authors="matoukolon"

WORKDIR /build
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests
FROM openjdk:22-jdk-slim
ENV MONGODB_HOST=localhost
ENV MONGODB_PORT=27017
ENV MONGODB_DB=product_db
WORKDIR /app
COPY --from=application /build/target/Gestion-stock-management-0.0.1-SNAPSHOT.jar /app/Gestion-stock-management-0.0.1-SNAPSHOT.jar


ENTRYPOINT ["java", "-jar", "Gestion-stock-management-0.0.1-SNAPSHOT.jar"]