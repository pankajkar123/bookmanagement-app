# Stage 1: Build the project

# Set the working directory in the container



# Copy the pom.xml and source code


# Build the Maven project


# Stage 2: Run the project


# Copy the JAR file from the build stage


# Expose the port the app runs on


# Run the JAR file

FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY  src ./src
RUN mvn package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/*.jar ./bookmanagement-app.jar

EXPOSE 8081

CMD ["java", "-jar" , "bookmanagement-app.jar"]