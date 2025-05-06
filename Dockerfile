FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests  # Tutaj generuje się SzkolaJazdy-1.0-SNAPSHOT.jar

FROM eclipse-temurin:21-jre
WORKDIR /app
# Kopiuj JAR z poprzedniego etapu 
COPY --from=build /app/target/SzkolaJazdy-1.0-SNAPSHOT-jar-with-dependencies.jar app.jar

# Instalacja bibliotek dla GUI i skryptu entrypoint
# Ustaw DISPLAY i instaluj biblioteki GUI
ENV DISPLAY=host.docker.internal:0

RUN apt-get update && apt-get install -y \
    libxrender1 libxtst6 libxi6 libxrandr2 libxinerama1 libfreetype6 \
    && rm -rf /var/lib/apt/lists/*
COPY entrypoint.sh .
RUN chmod +x entrypoint.sh
ENTRYPOINT ["./entrypoint.sh"]