# Etap 1: Budowanie z Maven
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etap 2: Ostateczny obraz z JDK 21 (Alpine)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Uruchom H2 i aplikację
CMD (nohup java -cp app.jar org.h2.tools.Server -tcp -web &) && \
    java -jar app.jar