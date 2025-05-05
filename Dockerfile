FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests  # Tutaj generuje się SzkolaJazdy-1.0-SNAPSHOT.jar

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Kopiuj JAR z poprzedniego etapu 
COPY --from=build /app/target/SzkolaJazdy-1.0-SNAPSHOT.jar app.jar  

# Instalacja bibliotek dla GUI i skryptu entrypoint
RUN apk add --no-cache xvfb libxrender libxtst libxi
COPY entrypoint.sh .
RUN chmod +x entrypoint.sh
ENTRYPOINT ["./entrypoint.sh"]