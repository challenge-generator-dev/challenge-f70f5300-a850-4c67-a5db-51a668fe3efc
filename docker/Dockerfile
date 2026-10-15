# Dockerfile multi-stage para construir la imagen del contenedor del servicio Java
# SUPERFICIE DE PRACTICA - El estudiante completara la configuracion

FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app

# Copiar archivos de dependencias primero para aprovechar cache
COPY pom.xml .
COPY src ./src

# Construir la aplicacion con Maven
RUN apk add --no-cache maven
RUN mvn clean package -DskipTests

# Etapa de produccion - imagen final optimizada
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Crear usuario no root para seguridad
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# Copiar el JAR desde la etapa de build
COPY --from=build /app/target/*.jar app.jar

# Copiar script de health check
COPY --from=build /app/docker/healthcheck.sh /healthcheck.sh
RUN chmod +x /healthcheck.sh

# Exponer puerto de la aplicacion
EXPOSE 8080

# Configurar health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
    CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1

# Cambiar a usuario no root
USER appuser

# Comando de inicio de la aplicacion
ENTRYPOINT ["java", "-jar", "app.jar"]