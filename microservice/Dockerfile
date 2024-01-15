FROM openjdk:19-jdk-slim-buster
EXPOSE 8081
COPY target/microservice-0.0.1-SNAPSHOT.jar /app2.jar
ENTRYPOINT ["java", "-jar", "/app2.jar"]
