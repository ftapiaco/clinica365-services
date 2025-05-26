# Usar una imagen base de Java (ajústala a tu versión si es distinta)
FROM eclipse-temurin:17-jdk-alpine

# Crear directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el JAR generado al contenedor
COPY target/services-clinic-0.0.1-SNAPSHOT.jar app.jar
# Exponer el puerto que usa tu app (por defecto Spring Boot = 8081)
EXPOSE 8081

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]