
# Start with a base image containing Java runtime
FROM openjdk:17-jdk-alpine

# Add Maintainer Info
Maintainer lk@ex.com

COPY target/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
