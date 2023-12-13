# Use an OpenJDK base image with the desired version
FROM openjdk:17

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container at /app
COPY AVGitProject/target/AVGitProject-0.0.1-SNAPSHOT.jar /app/

# Database connection information
ENV MYSQL_URL=jdbc:mysql://mysql-db:3306/hb-03-one-to-many
ENV SPRING_DATASOURCE_USERNAME=springuser
ENV SPRING_DATASOURCE_PASSWORD=springuser
ENV MYSQL_ROOT_HOST=%

# Expose the port that your Spring Boot app runs on (adjust if necessary)
EXPOSE 8080

# Command to run the Spring Boot application
CMD ["java", "-jar", "AVGitProject-0.0.1-SNAPSHOT.jar"]
