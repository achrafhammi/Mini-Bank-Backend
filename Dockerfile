# Use a base image with Java and Alpine Linux
FROM amazoncorretto:17

LABEL authors = "Achraf HAMMI"

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file of your Spring Boot application into the container
COPY ./target/Mini-Bank-Backend-0.0.1.jar app.jar

# Expose the port that your Spring Boot application listens on
EXPOSE 8081

# Set any environment variables if needed
ENV JAVA_OPTS=""


# Set the entrypoint to Java, and append the default command arguments
ENTRYPOINT ["java", "-jar", "app.jar"]
