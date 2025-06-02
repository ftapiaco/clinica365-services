# Usar una imagen base de Java (ajústala a tu versión si es distinta)
FROM eclipse-temurin:17-jdk-alpine
# Crea el directorio /opt/app en la imagen.
# -p asegura que se creen todos los directorios intermedios si no existen.
RUN mkdir -p /opt/app
#Establece el directorio de trabajo dentro del contenedor en /opt/app.
WORKDIR /opt/app

# Copiar el JAR generado al contenedor
# COPY target/clinica365-services-0.0.1-SNAPSHOT.jar /opt/app/target/

# Copia el jar compilado al contenedor
COPY target/clinica365-services-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto que usa tu app (por defecto Spring Boot = 8081)
EXPOSE 8083

# Ejecutar la aplicación
# Define el comando por defecto que se ejecutará al iniciar el contenedor
# Usa sh -c para ejecutar el comando como si fuera desde una terminal de shell.
# Lanza la aplicación Java usando java -jar
# CMD ["sh", "-c", "java -jar /opt/app/target/clinica365-services-0.0.1-SNAPSHOT.jar"]
ENTRYPOINT ["java", "-jar", "app.jar"]
