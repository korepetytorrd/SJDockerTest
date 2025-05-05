FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Final stage with Alpine
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy the JAR with dependencies (assuming you use maven-assembly-plugin)
COPY --from=build /app/target/*-with-dependencies.jar app.jar

# Install X11 libs for Swing
RUN apk add --no-cache xvfb libxrender libxtst libxi

# Entrypoint script
COPY entrypoint.sh .
RUN chmod +x entrypoint.sh
ENTRYPOINT ["./entrypoint.sh"]