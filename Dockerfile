FROM eclipse-temurin:17-jdk-alpine
RUN mkdir -p /opt/app
WORKDIR /opt/app
COPY target/clinica365-services-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "app.jar"]
