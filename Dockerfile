# Gradle image to build the project
FROM gradle:8.10.2-jdk21 AS build

# Working directory in the container
WORKDIR /app

# Copy the Gradle build files and source code
COPY build.gradle.kts settings.gradle.kts /app/
COPY src /app/src

# Run the Gradle build
RUN gradle shadowJar --no-daemon

# Debug: Check if the JAR was created
RUN ls -la /app/build/libs

# OpenJDK for running the app
FROM openjdk:21-jdk-slim

# Working directory for runtime container
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/build/libs/*.jar /app/app.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
