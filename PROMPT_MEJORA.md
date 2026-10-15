# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `docker/Dockerfile` — El topic pide contenedores/orquestacion: este archivo es el ejercicio, no scaffolding.
- `docker/docker-compose.yml` — El topic pide contenedores/orquestacion: este archivo es el ejercicio, no scaffolding.
- `k8s/deployment.yml` — El topic pide contenedores/orquestacion: este archivo es el ejercicio, no scaffolding.
- `k8s/service.yml` — El topic pide contenedores/orquestacion: este archivo es el ejercicio, no scaffolding.
- `k8s/hpa.yml` — El topic pide contenedores/orquestacion: este archivo es el ejercicio, no scaffolding.
- `k8s/configmap.yml` — El topic pide contenedores/orquestacion: este archivo es el ejercicio, no scaffolding.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Archivos que la arquitectura del reto declara y no estan

Creálos con implementacion real, en la capa que les corresponde:

- `docker/.dockerignore`

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/banca/contenedores/application/AccountService.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/banca/contenedores/infrastructure/AccountController.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/banca/contenedores/infrastructure/GlobalExceptionHandler.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/banca/contenedores/infrastructure/JpaAccountRepository.java` — `Account.id`: Se invoca `id` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/contenedores/infrastructure/JpaAccountRepository.java` — `Account.accountNumber`: Se invoca `accountNumber` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/contenedores/infrastructure/JpaAccountRepository.java` — `Account.accountHolder`: Se invoca `accountHolder` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/contenedores/infrastructure/JpaAccountRepository.java` — `Account.balance`: Se invoca `balance` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/contenedores/infrastructure/JpaAccountRepository.java` — `Account.status`: Se invoca `status` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/contenedores/infrastructure/JpaAccountRepository.java` — `Account.createdAt`: Se invoca `createdAt` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/contenedores/infrastructure/JpaAccountRepository.java` — `Account.updatedAt`: Se invoca `updatedAt` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/contenedores/application/AccountService.java` — `Account.id`: Se invoca `id` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/contenedores/application/AccountService.java` — `Account.status`: Se invoca `status` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/contenedores/application/AccountService.java` — `Account.balance`: Se invoca `balance` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/contenedores/infrastructure/AccountController.java` — `Account.id`: Se invoca `id` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/contenedores/infrastructure/AccountController.java` — `Account.accountNumber`: Se invoca `accountNumber` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/contenedores/infrastructure/AccountController.java` — `Account.accountHolder`: Se invoca `accountHolder` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/contenedores/infrastructure/AccountController.java` — `Account.balance`: Se invoca `balance` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/contenedores/infrastructure/AccountController.java` — `Account.status`: Se invoca `status` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/contenedores/infrastructure/AccountController.java` — `Account.createdAt`: Se invoca `createdAt` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/contenedores/infrastructure/AccountController.java` — `Account.updatedAt`: Se invoca `updatedAt` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/contenedores/infrastructure/AccountController.java` — `TransferRequest.accountNumber`: Se invoca `accountNumber` sobre `TransferRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/contenedores/infrastructure/AccountController.java` — `TransferRequest.accountHolder`: Se invoca `accountHolder` sobre `TransferRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/contenedores/infrastructure/AccountController.java` — `TransferRequest.initialBalance`: Se invoca `initialBalance` sobre `TransferRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/contenedores/infrastructure/AccountController.java` — `TransferRequest.amount`: Se invoca `amount` sobre `TransferRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/contenedores/infrastructure/AccountController.java` — `TransferRequest.toAccountId`: Se invoca `toAccountId` sobre `TransferRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Backend, Especialidad Desarrollador, Tecnología Java, Advanced

### Brecha de conocimiento
Implementa la contenerización y orquestación de contenedores en proyectos, teniendo en cuenta las diferencias, ventajas y desventajas de cada enfoque. Utiliza al menos dos herramientas de contenerización/orquestación, como Docker, Docker Compose, Docker Swarm, K8s (Kubernetes) u OpenShift.

### Misión / candidato
Candidato con experiencia avanzada en Backend con Java.

### Reto
- Tema: implementaPlataformasContenedores
- Seniority: advanced-l2
- Tipo: practical
- Título: Diseñar y orquestar contenedores en un proyecto Java
- Tiempo estimado: 30 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Configurar entorno de desarrollo con Docker — objetivo: Establecer un entorno de desarrollo utilizando Docker para el proyecto Java. — entregable (NO resolver): Dockerfile y script de inicio del contenedor.
- Fase 2: Orquestar contenedores con Kubernetes — objetivo: Implementar la orquestación de contenedores utilizando Kubernetes. — entregable (NO resolver): Archivo de configuración de Kubernetes y verificación de despliegue.
- Fase 3: Optimizar y asegurar la tolerancia a fallos — objetivo: Optimizar la configuración y asegurar que el sistema es tolerante a fallos. — entregable (NO resolver): Configuración optimizada y verificación de tolerancia a fallos.
- Fase 4: Documentar y comunicar el diseño — objetivo: Documentar el diseño y comunicarlo a diferentes audiencias. — entregable (NO resolver): Documentación técnica y presentación del diseño.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.banca</groupId>
    <artifactId>contenedores</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>contenedores</name>
    <description>Proyecto de backend para orquestación de contenedores en el dominio bancario</description>

    <properties>
        <java.version>21</java.version>
        <maven.compiler.source>${java.version}</maven.compiler.source>
        <maven.compiler.target>${java.version}</maven.compiler.target>
    </properties>

    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <!-- Base de datos -->
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>42.7.3</version>
        </dependency>

        <!-- Herramientas de desarrollo -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>

        <!-- Testing -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.testcontainers</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>1.19.8</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.testcontainers</groupId>
            <artifactId>postgresql</artifactId>
            <version>1.19.8</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

    <repositories>
        <repository>
            <id>spring-milestones</id>
            <name>Spring Milestones</name>
            <url>https://repo.spring.io/milestone</url>
        </repository>
    </repositories>
</project>

// === ARCHIVO: src/main/java/com/banca/contenedores/Application.java ===
package com.banca.contenedores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class Application {
    private final Environment environment;

    public Application(Environment environment) {
        this.environment = environment;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        String activeProfiles = Arrays.toString(environment.getActiveProfiles());
        System.out.printf("\n\n=== Aplicación iniciada con perfiles: %s ===\n", activeProfiles);
        System.out.println("Servicio de orquestación de contenedores listo para operar.");
        System.out.println("Endpoints disponibles:");
        System.out.println("- API REST: http://localhost:" + environment.getProperty("server.port", "8080"));
        System.out.println("- Actuator: http://localhost:" + environment.getProperty("server.port", "8080") + "/actuator");
    }

    @EventListener(org.springframework.context.event.ContextClosedEvent.class)
    public void onContextClosed() {
        System.out.println("\n=== Aplicación detenida ===\n");
    }
}

// === ARCHIVO: src/main/resources/application.yml ===
spring:
  application:
    name: contenedores
  profiles:
    active: dev
  
  datasource:
    url: jdbc:postgresql://localhost:5432/banca_contenedores
    username: postgres
    password: postgres
    driver-class-name: org.postgresql.Driver
    hikari:
      connection-timeout: 20000
      maximum-pool-size: 10
      pool-name: banca-contenedores-pool

  jpa:
    database-platform: org.hibernate.dialect.PostgreSQLDialect
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true
        jdbc:
          batch_size: 20

server:
  port: 8080
  servlet:
    context-path: /api

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,env,beans,configprops
  endpoint:
    health:
      show-details: always

logging:
  level:
    root: INFO
    com.banca.contenedores: DEBUG
    org.hibernate.SQL: DEBUG
    org.hibernate.type.descriptor.sql.BasicBinder: TRACE

springdoc:
  api-docs:
    path: /v3/api-docs
  swagger-ui:
    path: /swagger-ui.html
    operationsSorter: method

// === ARCHIVO: src/main/java/com/banca/contenedores/domain/Account.java ===
package com.banca.contenedores.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record Account(
    @NotNull
    UUID id,

    @NotNull
    @Size(min = 5, max = 20)
    String accountNumber,

    @NotNull
    @Size(min = 3, max = 50)
    String accountHolder,

    @NotNull
    @PositiveOrZero
    BigDecimal balance,

    @NotNull
    AccountStatus status,

    @NotNull
    LocalDateTime createdAt,

    LocalDateTime updatedAt
) {
    public enum AccountStatus {
        ACTIVE, BLOCKED, CLOSED
    }

    public Account {
        if (balance == null) {
            balance = BigDecimal.ZERO;
        }
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (status == null) {
            status = AccountStatus.ACTIVE;
        }
    }

    public Account updateBalance(BigDecimal amount) {
        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }
        if (status != AccountStatus.ACTIVE) {
            throw new IllegalStateException("Account must be active to update balance");
        }
        BigDecimal newBalance = balance.add(amount);
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        return new Account(id, accountNumber, accountHolder, newBalance, status, createdAt, LocalDateTime.now());
    }

    public Account changeStatus(AccountStatus newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        return new Account(id, accountNumber, accountHolder, balance, newStatus, createdAt, LocalDateTime.now());
    }
}

// === ARCHIVO: src/main/java/com/banca/contenedores/domain/AccountRepository.java ===
package com.banca.contenedores.domain;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

public interface AccountRepository {
    Account save(Account account);
    
    Optional<Account> findById(UUID id);
    
    Optional<Account> findByAccountNumber(String accountNumber);
    
    List<Account> findAll();
    
    void deleteById(UUID id);
}

// === ARCHIVO: src/main/java/com/banca/contenedores/infrastructure/JpaAccountRepository.java ===
package com.banca.contenedores.infrastructure;


import com.banca.contenedores.domain.AccountStatus;
import com.banca.contenedores.domain.Account;
import com.banca.contenedores.domain.AccountRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Repository
public interface JpaAccountRepository extends JpaRepository<AccountEntity, UUID>, AccountRepository {
    @Override
    default Account save(Account account) {
        AccountEntity entity = AccountEntity.fromDomain(account);
        AccountEntity savedEntity = save(entity);
        return savedEntity.toDomain();
    }
    
    @Override
    default Optional<Account> findById(UUID id) {
        return findById(id).map(AccountEntity::toDomain);
    }
    
    @Override
    default Optional<Account> findByAccountNumber(String accountNumber) {
        return findByAccountNumber(accountNumber).map(AccountEntity::toDomain);
    }
    
    Optional<AccountEntity> findByAccountNumber(String accountNumber);
}

@Entity
@Table(name = "accounts")
class AccountEntity {
    @Id
    private UUID id;
    
    @Column(nullable = false, unique = true, length = 20)
    private String accountNumber;
    
    @Column(nullable = false, length = 50)
    private String accountHolder;
    
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal balance;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Account.AccountStatus status;
    
    @Column(nullable = false)
    private LocalDateTime createdAt;
    
    @Column
    private LocalDateTime updatedAt;

    public AccountEntity() {
    }

    public AccountEntity(UUID id, String accountNumber, String accountHolder, BigDecimal balance,
                        Account.AccountStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static AccountEntity fromDomain(Account account) {
        return new AccountEntity(
            account.id(),
            account.accountNumber(),
            account.accountHolder(),
            account.balance(),
            account.status(),
            account.createdAt(),
            account.updatedAt()
        );
    }

    public Account toDomain() {
        return new Account(
            id,
            accountNumber,
            accountHolder,
            balance,
            status,
            createdAt,
            updatedAt
        );
    }

    // Getters and setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Account.AccountStatus getStatus() {
        return status;
    }

    public void setStatus(Account.AccountStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

// === ARCHIVO: src/main/java/com/banca/contenedores/application/AccountService.java ===
package com.banca.contenedores.application;


import com.banca.contenedores.domain.AccountStatus;
import com.banca.contenedores.domain.Account;
import com.banca.contenedores.domain.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    private static final BigDecimal MINIMUM_BALANCE = new BigDecimal("100.00");
    private static final BigDecimal MAXIMUM_WITHDRAWAL = new BigDecimal("10000.00");

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(String accountNumber, String accountHolder, BigDecimal initialBalance) {
        logger.info("Creando nueva cuenta: {} para titular: {}", accountNumber, accountHolder);

        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("El número de cuenta no puede estar vacío");
        }

        if (accountHolder == null || accountHolder.isBlank()) {
            throw new IllegalArgumentException("El titular de la cuenta no puede estar vacío");
        }

        Optional<Account> existingAccount = accountRepository.findByAccountNumber(accountNumber);
        if (existingAccount.isPresent()) {
            throw new IllegalArgumentException("Ya existe una cuenta con el número: " + accountNumber);
        }

        Account newAccount = new Account(
            UUID.randomUUID(),
            accountNumber,
            accountHolder,
            initialBalance != null ? initialBalance : BigDecimal.ZERO,
            Account.AccountStatus.ACTIVE,
            java.time.LocalDateTime.now(),
            java.time.LocalDateTime.now()
        );

        Account savedAccount = accountRepository.save(newAccount);
        logger.info("Cuenta creada exitosamente con ID: {}", savedAccount.id());
        return savedAccount;
    }

    public Optional<Account> getAccountById(UUID id) {
        logger.debug("Buscando cuenta por ID: {}", id);
        return accountRepository.findById(id);
    }

    public Optional<Account> getAccountByNumber(String accountNumber) {
        logger.debug("Buscando cuenta por número: {}", accountNumber);
        return accountRepository.findByAccountNumber(accountNumber);
    }

    public List<Account> getAllAccounts() {
        logger.debug("Obteniendo todas las cuentas");
        return accountRepository.findAll();
    }

    public Account deposit(UUID accountId, BigDecimal amount) {
        logger.info("Depósito de {} en cuenta {}", amount, accountId);

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto del depósito debe ser mayor a cero");
        }

        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada: " + accountId));

        if (account.status() != Account.AccountStatus.ACTIVE) {
            throw new IllegalStateException("No se puede depositar en una cuenta con estado: " + account.status());
        }

        BigDecimal newBalance = account.balance().add(amount);
        Account updatedAccount = account.updateBalance(newBalance);
        logger.info("Depósito exitoso. Nuevo saldo: {}", updatedAccount.balance());
        return accountRepository.save(updatedAccount);
    }

    public Account withdraw(UUID accountId, BigDecimal amount) {
        logger.info("Retiro de {} de cuenta {}", amount, accountId);

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto del retiro debe ser mayor a cero");
        }

        if (amount.compareTo(MAXIMUM_WITHDRAWAL) > 0) {
            throw new IllegalArgumentException("El monto del retiro excede el límite máximo de: " + MAXIMUM_WITHDRAWAL);
        }

        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada: " + accountId));

        if (account.status() != Account.AccountStatus.ACTIVE) {
            throw new IllegalStateException("No se puede retirar de una cuenta con estado: " + account.status());
        }

        BigDecimal newBalance = account.balance().subtract(amount);
        if (newBalance.compareTo(MINIMUM_BALANCE) < 0) {
            throw new IllegalStateException("Saldo insuficiente. Saldo mínimo requerido: " + MINIMUM_BALANCE);
        }

        Account updatedAccount = account.updateBalance(newBalance);
        logger.info("Retiro exitoso. Nuevo saldo: {}", updatedAccount.balance());
        return accountRepository.save(updatedAccount);
    }

    public Account transfer(UUID fromAccountId, UUID toAccountId, BigDecimal amount) {
        logger.info("Transferencia de {} desde cuenta {} hacia cuenta {}", amount, fromAccountId, toAccountId);

        if (fromAccountId.equals(toAccountId)) {
            throw new IllegalArgumentException("No se puede transferir a la misma cuenta");
        }

        Account fromAccount = accountRepository.findById(fromAccountId)
            .orElseThrow(() -> new IllegalArgumentException("Cuenta origen no encontrada: " + fromAccountId));

        Account toAccount = accountRepository.findById(toAccountId)
            .orElseThrow(() -> new IllegalArgumentException("Cuenta destino no encontrada: " + toAccountId));

        if (fromAccount.status() != Account.AccountStatus.ACTIVE || toAccount.status() != Account.AccountStatus.ACTIVE) {
            throw new IllegalStateException("Ambas cuentas deben estar activas para realizar la transferencia");
        }

        BigDecimal newFromBalance = fromAccount.balance().subtract(amount);
        if (newFromBalance.compareTo(MINIMUM_BALANCE) < 0) {
            throw new IllegalStateException("Saldo insuficiente en cuenta origen para realizar la transferencia");
        }

        Account updatedFromAccount = fromAccount.updateBalance(newFromBalance);
        Account updatedToAccount = toAccount.updateBalance(toAccount.balance().add(amount));

        accountRepository.save(updatedFromAccount);
        accountRepository.save(updatedToAccount);

        logger.info("Transferencia exitosa. Saldo origen: {}, Saldo destino: {}", 
            updatedFromAccount.balance(), updatedToAccount.balance());
        return updatedFromAccount;
    }

    public Account changeAccountStatus(UUID accountId, Account.AccountStatus newStatus) {
        logger.info("Cambiando estado de cuenta {} a {}", accountId, newStatus);

        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada: " + accountId));

        Account updatedAccount = account.changeStatus(newStatus);
        logger.info("Estado de cuenta cambiado exitosamente a: {}", newStatus);
        return accountRepository.save(updatedAccount);
    }

    public void deleteAccount(UUID accountId) {
        logger.info("Eliminando cuenta: {}", accountId);

        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada: " + accountId));

        if (account.balance().compareTo(BigDecimal.ZERO) > 0) {
            throw new IllegalStateException("No se puede eliminar una cuenta con saldo positivo");
        }

        accountRepository.deleteById(accountId);
        logger.info("Cuenta eliminada exitosamente: {}", accountId);
    }
}

// === ARCHIVO: src/main/java/com/banca/contenedores/infrastructure/AccountController.java ===
package com.banca.contenedores.infrastructure;


import com.banca.contenedores.domain.AccountStatus;
import com.banca.contenedores.application.AccountService;
import com.banca.contenedores.domain.Account;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    public record CreateAccountRequest(
        @NotBlank(message = "El número de cuenta es obligatorio")
        String accountNumber,
        
        @NotBlank(message = "El titular de la cuenta es obligatorio")
        String accountHolder,
        
        @DecimalMin(value = "0.0", message = "El saldo inicial no puede ser negativo")
        BigDecimal initialBalance
    ) {}

    public record TransactionRequest(
        @NotNull(message = "El monto es obligatorio")
        @DecimalMin(value = "0.01", message = "El monto debe ser mayor a cero")
        BigDecimal amount
    ) {}

    public record TransferRequest(
        @NotNull(message = "La cuenta de destino es obligatoria")
        UUID toAccountId,
        
        @NotNull(message = "El monto es obligatorio")
        @DecimalMin(value = "0.01", message = "El monto debe ser mayor a cero")
        BigDecimal amount
    ) {}

    public record AccountResponse(
        UUID id,
        String accountNumber,
        String accountHolder,
        BigDecimal balance,
        String status,
        String createdAt,
        String updatedAt
    ) {
        public static AccountResponse fromDomain(Account account) {
            return new AccountResponse(
                account.id(),
                account.accountNumber(),
                account.accountHolder(),
                account.balance(),
                account.status().name(),
                account.createdAt().toString(),
                account.updatedAt().toString()
            );
        }
    }

    public record ApiResponse<T>(
        boolean success,
        String message,
        T data
    ) {
        public static <T> ApiResponse<T> ok(T data, String message) {
            return new ApiResponse<>(true, message, data);
        }

        public static <T> ApiResponse<T> error(String message) {
            return new ApiResponse<>(false, message, null);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AccountResponse>> createAccount(
            @Valid @RequestBody CreateAccountRequest request) {
        logger.info("POST /api/v1/accounts - Creando cuenta: {}", request.accountNumber());
        
        Account account = accountService.createAccount(
            request.accountNumber(),
            request.accountHolder(),
            request.initialBalance()
        );
        
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.ok(AccountResponse.fromDomain(account), "Cuenta creada exitosamente"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AccountResponse>>> getAllAccounts() {
        logger.info("GET /api/v1/accounts - Obteniendo todas las cuentas");
        
        List<Account> accounts = accountService.getAllAccounts();
        List<AccountResponse> response = accounts.stream()
            .map(AccountResponse::fromDomain)
            .toList();
        
        return ResponseEntity.ok(ApiResponse.ok(response, "Cuentas obtenidas exitosamente"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AccountResponse>> getAccountById(@PathVariable UUID id) {
        logger.info("GET /api/v1/accounts/{} - Obteniendo cuenta por ID", id);
        
        return accountService.getAccountById(id)
            .map(account -> ResponseEntity.ok(
                ApiResponse.ok(AccountResponse.fromDomain(account), "Cuenta encontrada")))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("Cuenta no encontrada: " + id)));
    }

    @GetMapping("/number/{accountNumber}")
    public ResponseEntity<ApiResponse<AccountResponse>> getAccountByNumber(
            @PathVariable String accountNumber) {
        logger.info("GET /api/v1/accounts/number/{} - Obteniendo cuenta por número", accountNumber);
        
        return accountService.getAccountByNumber(accountNumber)
            .map(account -> ResponseEntity.ok(
                ApiResponse.ok(AccountResponse.fromDomain(account), "Cuenta encontrada")))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("Cuenta no encontrada: " + accountNumber)));
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<ApiResponse<AccountResponse>> deposit(
            @PathVariable UUID id,
            @Valid @RequestBody TransactionRequest request) {
        logger.info("POST /api/v1/accounts/{}/deposit - Depósito de {}", id, request.amount());
        
        Account account = accountService.deposit(id, request.amount());
        
        return ResponseEntity.ok(
            ApiResponse.ok(AccountResponse.fromDomain(account), "Depósito realizado exitosamente"));
    }

    @PostMapping("/{id}/withdraw")
    public ResponseEntity<ApiResponse<AccountResponse>> withdraw(
            @PathVariable UUID id,
            @Valid @RequestBody TransactionRequest request) {
        logger.info("POST /api/v1/accounts/{}/withdraw - Retiro de {}", id, request.amount());
        
        Account account = accountService.withdraw(id, request.amount());
        
        return ResponseEntity.ok(
            ApiResponse.ok(AccountResponse.fromDomain(account), "Retiro realizado exitosamente"));
    }

    @PostMapping("/{id}/transfer")
    public ResponseEntity<ApiResponse<AccountResponse>> transfer(
            @PathVariable UUID id,
            @Valid @RequestBody TransferRequest request) {
        logger.info("POST /api/v1/accounts/{}/transfer - Transferencia de {} a {}", 
            id, request.amount(), request.toAccountId());
        
        Account account = accountService.transfer(id, request.toAccountId(), request.amount());
        
        return ResponseEntity.ok(
            ApiResponse.ok(AccountResponse.fromDomain(account), "Transferencia realizada exitosamente"));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<AccountResponse>> changeStatus(
            @PathVariable UUID id,
            @RequestParam Account.AccountStatus status) {
        logger.info("PATCH /api/v1/accounts/{}/status - Cambiando estado a {}", id, status);
        
        Account account = accountService.changeAccountStatus(id, status);
        
        return ResponseEntity.ok(
            ApiResponse.ok(AccountResponse.fromDomain(account), "Estado actualizado exitosamente"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAccount(@PathVariable UUID id) {
        logger.info("DELETE /api/v1/accounts/{} - Eliminando cuenta", id);
        
        accountService.deleteAccount(id);
        
        return ResponseEntity.ok(ApiResponse.ok(null, "Cuenta eliminada exitosamente"));
    }
}

// === ARCHIVO: src/main/java/com/banca/contenedores/infrastructure/GlobalExceptionHandler.java ===
package com.banca.contenedores.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public record ErrorResponse(
        int status,
        String error,
        String message,
        String path,
        LocalDateTime timestamp,
        Map<String, String> details
    ) {}

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
            IllegalArgumentException ex, WebRequest request) {
        logger.warn("Argumento ilegal: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Bad Request",
            ex.getMessage(),
            request.getDescription(false).replace("uri=", ""),
            LocalDateTime.now(),
            null
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> handleIllegalStateException(
            IllegalStateException ex, WebRequest request) {
        logger.warn("Estado ilegal: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.CONFLICT.value(),
            "Conflict",
            ex.getMessage(),
            request.getDescription(false).replace("uri=", ""),
            LocalDateTime.now(),
            null
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {
        logger.warn("Error de validación: {}", ex.getMessage());

        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
            .collect(Collectors.toMap(
                FieldError::getField,
                error -> error.getDefaultMessage() != null ? error.getDefaultMessage() : "Valor inválido",
                (existing, replacement) -> existing
            ));

        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Validation Failed",
            "Los datos proporcionados no son válidos",
            request.getDescription(false).replace("uri=", ""),
            LocalDateTime.now(),
            errors
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse> handleNullPointerException(
            NullPointerException ex, WebRequest request) {
        logger.error("NullPointerException: {}", ex.getMessage(), ex);

        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Internal Server Error",
            "Se produjo un error interno en el servidor",
            request.getDescription(false).replace("uri=", ""),
            LocalDateTime.now(),
            null
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex, WebRequest request) {
        logger.error("Excepción no controlada: {}", ex.getMessage(), ex);

        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Internal Server Error",
            "Ocurrió un error inesperado. Por favor, contacte al administrador",
            request.getDescription(false).replace("uri=", ""),
            LocalDateTime.now(),
            null
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(org.springframework.web.servlet.NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoHandlerFoundException(
            org.springframework.web.servlet.NoHandlerFoundException ex, WebRequest request) {
        logger.warn("Recurso no encontrado: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            "Not Found",
            "El recurso solicitado no existe",
            request.getDescription(false).replace("uri=", ""),
            LocalDateTime.now(),
            null
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(
            org.springframework.http.converter.HttpMessageNotReadableException ex, WebRequest request) {
        logger.warn("Mensaje no legible: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Bad Request",
            "El formato del cuerpo de la solicitud es inválido",
            request.getDescription(false).replace("uri=", ""),
            LocalDateTime.now(),
            null
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}

// === ARCHIVO: docker/Dockerfile ===
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

// === ARCHIVO: docker/docker-compose.yml ===
# Archivo de configuracion de Docker Compose para orquestar multiples contenedores
# SUPERFICIE DE PRACTICA - El estudiante completara la configuracion

version: '3.8'

services:
  # Servicio principal de la aplicacion Java
  app:
    build:
      context: ..
      dockerfile: docker/Dockerfile
    container_name: contenedores-app
    ports:
      - "8080:8080"
    environment:
      - SPRING_PROFILES_ACTIVE=prod
      - SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/banca
      - SPRING_DATASOURCE_USERNAME=banco_user
      - SPRING_DATASOURCE_PASSWORD=banco_pass
      - SPRING_JPA_HIBERNATE_DDL_AUTO=update
      - JAVA_OPTS=-Xmx512m -Xms256m
    depends_on:
      db:
        condition: service_healthy
    networks:
      - banca-network
    restart: unless-stopped
    healthcheck:
      test: ["CMD", "/healthcheck.sh"]
      interval: 30s
      timeout: 10s
      retries: 3
      start_period: 60s

  # Base de datos PostgreSQL
  db:
    image: postgres:16-alpine
    container_name: contenedores-db
    environment:
      - POSTGRES_DB=banca
      - POSTGRES_USER=banco_user
      - POSTGRES_PASSWORD=banco_pass
    ports:
      - "5432:5432"
    volumes:
      - postgres-data:/var/lib/postgresql/data
    networks:
      - banca-network
    restart: unless-stopped
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U banco_user -d banca"]
      interval: 10s
      timeout: 5s
      retries: 5
      start_period: 30s

  # Volumenes persistentes
volumes:
  postgres-data:
    driver: local

# RedesDocker
networks:
  banca-network:
    driver: bridge

// === ARCHIVO: docker/healthcheck.sh ===
#!/bin/bash

# =============================================================================
# Script de verificación de salud del contenedor
# =============================================================================
# Este script verifica que la aplicación Spring Boot esté funcionando
# correctamente antes de marcar el contenedor como saludable.
#
# Uso: ./healthcheck.sh
#
# Variables de entorno (opcionales):
#   - HEALTH_ENDPOINT: URL del endpoint de health (default: http://localhost:8080/actuator/health)
#   - MAX_RETRIES: Número máximo de intentos (default: 30)
#   - RETRY_INTERVAL: Segundos entre intentos (default: 2)
#   - TIMEOUT: Timeout para cada request en segundos (default: 5)
# =============================================================================

set -euo pipefail

# -----------------------------------------------------------------------------
# Configuración con valores por defecto
# -----------------------------------------------------------------------------
HEALTH_ENDPOINT="${HEALTH_ENDPOINT:-http://localhost:8080/actuator/health}"
MAX_RETRIES="${MAX_RETRIES:-30}"
RETRY_INTERVAL="${RETRY_INTERVAL:-2}"
TIMEOUT="${TIMEOUT:-5}"
DB_HOST="${DB_HOST:-localhost}"
DB_PORT="${DB_PORT:-5432}"
DB_NAME="${DB_NAME:-banca}"

echo "=========================================="
echo "Healthcheck del Contenedor"
echo "=========================================="
echo "Endpoint: ${HEALTH_ENDPOINT}"
echo "Máximo de reintentos: ${MAX_RETRIES}"
echo "Intervalo entre reintentos: ${RETRY_INTERVAL}s"
echo "Timeout por request: ${TIMEOUT}s"
echo "------------------------------------------"

# -----------------------------------------------------------------------------
# Función para verificar la respuesta del health endpoint
# -----------------------------------------------------------------------------
check_health_endpoint() {
    local response
    local http_code
    
    response=$(curl -sf \
        --max-time "${TIMEOUT}" \
        -w "%{http_code}" \
        -o /tmp/health_response.json \
        "${HEALTH_ENDPOINT}" 2>/dev/null || echo "000")
    
    http_code="${response: -3}"
    
    if [ "${http_code}" != "200" ]; then
        echo "[FALLO] El endpoint de health respondió con código ${http_code}"
        return 1
    fi
    
    if [ ! -s /tmp/health_response.json ]; then
        echo "[FALLO] Respuesta vacía del endpoint de health"
        return 1
    fi
    
    # Verificar que el estado global sea UP
    if grep -q '"status":"UP"' /tmp/health_response.json; then
        echo "[ÉXITO] El estado de la aplicación es UP"
        
        # Mostrar componentes verificados
        echo "Componentes verificados:"
        grep -o '"[a-zA-Z]":{"status":"[^"]*"' /tmp/health_response.json | \
            sed 's/"/ /g; s/:{/: /g; s/,/", "/g' | while read -r component; do
                echo "  - $component"
            done
        
        return 0
    else
        local status
        status=$(grep -o '"status":"[^"]*"' /tmp/health_response.json | head -1 || echo '"status":"UNKNOWN"')
        echo "[FALLO] Estado de la aplicación: ${status}"
        return 1
    fi
}

# -----------------------------------------------------------------------------
# Función para verificar la conectividad a la base de datos
# -----------------------------------------------------------------------------
check_database_connectivity() {
    # Verificar si tenemos las variables necesarias
    if [ -z "${DB_HOST}" ] || [ "${DB_HOST}" = "localhost" ]; then
        echo "[INFO] Verificación de DB omitida (DB_HOST no configurado)"
        return 0
    fi
    
    # Intentar conexión TCP al puerto de PostgreSQL
    if command -v nc &> /dev/null; then
        if nc -z -w "${TIMEOUT}" "${DB_HOST}" "${DB_PORT}" 2>/dev/null; then
            echo "[ÉXITO] Conectividad a PostgreSQL (${DB_HOST}:${DB_PORT})"
            return 0
        else
            echo "[FALLO] No se puede conectar a PostgreSQL (${DB_HOST}:${DB_PORT})"
            return 1
        fi
    elif command -v timeout &> /dev/null; then
        if timeout "${TIMEOUT}" bash -c "echo > /dev/tcp/${DB_HOST}/${DB_PORT}" 2>/dev/null; then
            echo "[ÉXITO] Conectividad a PostgreSQL (${DB_HOST}:${DB_PORT})"
            return 0
        else
            echo "[FALLO] No se puede conectar a PostgreSQL (${DB_HOST}:${DB_PORT})"
            return 1
        fi
    else
        echo "[INFO] Verificación de conectividad TCP omitida (nc/timeout no disponibles)"
        return 0
    fi
}

# -----------------------------------------------------------------------------
# Función para verificar recursos del sistema
# -----------------------------------------------------------------------------
check_system_resources() {
    local mem_available
    local mem_total
    local mem_percent
    
    # Verificar memoria disponible
    if [ -f /proc/meminfo ]; then
        mem_available=$(grep MemAvailable /proc/meminfo | awk '{print $2}')
        mem_total=$(grep MemTotal /proc/meminfo | awk '{print $2}')
        
        if [ -n "${mem_available}" ] && [ -n "${mem_total}" ] && [ "${mem_total}" -gt 0 ]; then
            mem_percent=$((100 - (mem_available * 100 / mem_total)))
            echo "[INFO] Uso de memoria: ${mem_percent}%"
            
            if [ "${mem_percent}" -gt 95 ]; then
                echo "[FALLO] Memoria crítica: ${mem_percent}%"
                return 1
            fi
        fi
    fi
    
    # Verificar espacio en disco
    local disk_usage
    disk_usage=$(df -h / | tail -1 | awk '{print $5}' | sed 's/%//')
    
    if [ -n "${disk_usage}" ]; then
        echo "[INFO] Uso de disco: ${disk_usage}%"
        
        if [ "${disk_usage}" -gt 90 ]; then
            echo "[FALLO] Disco crítico: ${disk_usage}%"
            return 1
        fi
    fi
    
    return 0
}

# -----------------------------------------------------------------------------
# Función principal de verificación con reintentos
# -----------------------------------------------------------------------------
main() {
    local attempt=0
    local health_ok=false
    local db_ok=false
    local resources_ok=false
    
    while [ "${attempt}" -lt "${MAX_RETRIES}" ]; do
        attempt=$((attempt + 1))
        
        echo ""
        echo "--- Intento ${attempt} de ${MAX_RETRIES} ---"
        
        # Verificar health endpoint
        if check_health_endpoint; then
            health_ok=true
        else
            health_ok=false
        fi
        
        # Verificar base de datos
        if check_database_connectivity; then
            db_ok=true
        else
            db_ok=false
        fi
        
        # Verificar recursos del sistema
        if check_system_resources; then
            resources_ok=true
        else
            resources_ok=false
        fi
        
        # Evaluar resultado general
        if [ "${health_ok}" = true ] && [ "${db_ok}" = true ] && [ "${resources_ok}" = true ]; then
            echo ""
            echo "=========================================="
            echo "[ÉXITO] Healthcheck completado exitosamente"
            echo "=========================================="
            echo "  - Health endpoint: OK"
            echo "  - Conectividad DB: OK"
            echo "  - Recursos del sistema: OK"
            echo ""
            rm -f /tmp/health_response.json
            exit 0
        fi
        
        echo "[INFO] Esperando ${RETRY_INTERVAL}s antes del siguiente intento..."
        sleep "${RETRY_INTERVAL}"
    done
    
    echo ""
    echo "=========================================="
    echo "[FALLO] Healthcheck fallido después de ${MAX_RETRIES} intentos"
    echo "=========================================="
    echo "Estado final:"
    echo "  - Health endpoint: ${health_ok}"
    echo "  - Conectividad DB: ${db_ok}"
    echo "  - Recursos del sistema: ${resources_ok}"
    echo ""
    
    rm -f /tmp/health_response.json
    exit 1
}

# Ejecutar función principal
main

// === ARCHIVO: k8s/deployment.yml ===
apiVersion: apps/v1
kind: Deployment
metadata:
  name: bancos-contadores-deployment
  labels:
    app: bancos-contadores
    tier: backend
spec:
  replicas: 3
  selector:
    matchLabels:
      app: bancos-contadores
  template:
    metadata:
      labels:
        app: bancos-contadores
        tier: backend
    spec:
      containers:
      - name: bancos-contadores
        image: bancos-contadores:latest
        ports:
        - containerPort: 8080
          name: http
          protocol: TCP
        env:
        - name: SPRING_PROFILES_ACTIVE
          value: "prod"
        - name: SERVER_PORT
          value: "8080"
        resources:
          requests:
            memory: "512Mi"
            cpu: "250m"
          limits:
            memory: "1Gi"
            cpu: "1000m"
        livenessProbe:
          httpGet:
            path: /actuator/health/liveness
            port: 8080
          initialDelaySeconds: 60
          periodSeconds: 10
          timeoutSeconds: 5
          failureThreshold: 3
        readinessProbe:
          httpGet:
            path: /actuator/health/readiness
            port: 8080
          initialDelaySeconds: 30
          periodSeconds: 5
          timeoutSeconds: 3
          failureThreshold: 3
        startupProbe:
          httpGet:
            path: /actuator/health
            port: 8080
          initialDelaySeconds: 10
          periodSeconds: 10
          timeoutSeconds: 5
          failureThreshold: 30
      restartPolicy: Always
---

// === ARCHIVO: k8s/service.yml ===
apiVersion: v1
kind: Service
metadata:
  name: bancos-contadores-service
  labels:
    app: bancos-contadores
spec:
  type: LoadBalancer
  selector:
    app: bancos-contadores
  ports:
  - name: http
    protocol: TCP
    port: 80
    targetPort: 8080
  sessionAffinity: ClientIP
  sessionAffinityConfig:
    clientIP:
      timeoutSeconds: 10800
  externalTrafficPolicy: Cluster
  internalTrafficPolicy: Cluster
---

// === ARCHIVO: k8s/hpa.yml ===
apiVersion: autoscaling/v2
kind: HorizontalPodAutoscaler
metadata:
  name: bancos-contadores-hpa
  labels:
    app: bancos-contadores
spec:
  scaleTargetRef:
    apiVersion: apps/v1
    kind: Deployment
    name: bancos-contadores-deployment
  minReplicas: 2
  maxReplicas: 10
  metrics:
  - type: Resource
    resource:
      name: cpu
      target:
        type: Utilization
        averageUtilization: 70
  - type: Resource
    resource:
      name: memory
      target:
        type: Utilization
        averageUtilization: 80
  behavior:
    scaleDown:
      stabilizationWindowSeconds: 300
      policies:
      - type: Percent
        value: 10
        periodSeconds: 60
      - type: Pods
        value: 2
        periodSeconds: 60
      selectPolicy: Min
    scaleUp:
      stabilizationWindowSeconds: 0
      policies:
      - type: Percent
        value: 100
        periodSeconds: 15
      - type: Pods
        value: 4
        periodSeconds: 15
      selectPolicy: Max

// === ARCHIVO: k8s/configmap.yml ===
apiVersion: v1
kind: ConfigMap
metadata:
  name: contenedores-config
  namespace: default
  labels:
    app: contenedores
    environment: development
data:
  application.yml: |-
    server:
      port: 8080
    spring:
      datasource:
        url: jdbc:postgresql://postgres-service:5432/banca
        username: ${DB_USERNAME}
        password: ${DB_PASSWORD}
        driver-class-name: org.postgresql.Driver
      jpa:
        hibernate:
          ddl-auto: update
        show-sql: false
        properties:
          hibernate:
            dialect: org.hibernate.dialect.PostgreSQLDialect
    management:
      endpoints:
        web:
          exposure:
            include: health,info,metrics
      endpoint:
        health:
          show-details: always
---

// === ARCHIVO: README.md ===
# Proyecto Contenedores - Banca

Backend Spring Boot 3.5 orquestado con Docker y Kubernetes para el dominio bancario.

## Estructura del Proyecto

```
contenedores/
├── pom.xml                          # Maven build con Spring Boot 3.5.6
├── docker/
│   ├── Dockerfile                   # Imagen multi-stage
│   ├── docker-compose.yml           # Orquestación local
│   ├── .dockerignore
│   └── healthcheck.sh               # Verificación de salud
├── k8s/
│   ├── configmap.yml                # Configuración externa
│   ├── deployment.yml               # Deployment de Kubernetes
│   ├── service.yml                  # Servicio ClusterIP
│   └── hpa.yml                      # Auto-scaling horizontal
├── src/
│   └── main/
│       ├── java/com/banca/contenedores/
│       │   ├── Application.java
│       │   ├── domain/
│       │   │   ├── Account.java
│       │   │   └── AccountRepository.java
│       │   ├── application/
│       │   │   └── AccountService.java
│       │   └── infrastructure/
│       │       ├── AccountController.java
│       │       ├── JpaAccountRepository.java
│       │       └── GlobalExceptionHandler.java
│       └── resources/
│           └── application.yml
```

## Requisitos Previos

- Java 21
- Maven 3.9+
- Docker 24+
- kubectl 1.28+
- Minikube o Kind (para desarrollo local con Kubernetes)

## Construcción Local con Maven

```bash
# Compilar el proyecto
mvn clean compile

# Ejecutar tests
mvn test

# Empaquetar JAR ejecutable
mvn clean package -DskipTests
```

## Ejecución con Docker

```bash
# Construir imagen Docker
docker build -t banca/contenedores:latest -f docker/Dockerfile .

# Ejecutar con Docker Compose
cd docker && docker-compose up -d

# Ver logs
docker-compose logs -f

# Detener servicios
docker-compose down
```

La aplicación estará disponible en `http://localhost:8080`

## Endpoints de Salud

- Health: `GET /actuator/health`
- Info: `GET /actuator/info`
- Metrics: `GET /actuator/metrics`

## Despliegue con Kubernetes

```bash
# Iniciar minikube
minikube start

# Aplicar ConfigMap
kubectl apply -f k8s/configmap.yml

# Aplicar Deployment
kubectl apply -f k8s/deployment.yml

# Aplicar Servicio
kubectl apply -f k8s/service.yml

# Aplicar HPA
kubectl apply -f k8s/hpa.yml

# Ver estado de pods
kubectl get pods -l app=contenedores

# Ver logs de la aplicación
kubectl logs -l app=contenedores

# Escalar manualmente
kubectl scale deployment contenedores --replicas=5

# Eliminar recursos
kubectl delete -f k8s/
```

## Variables de Entorno

| Variable | Descripción | Valor por defecto |
|----------|-------------|-------------------|
| DB_USERNAME | Usuario de PostgreSQL | banca |
| DB_PASSWORD | Contraseña de PostgreSQL | banca123 |
| DB_HOST | Host de PostgreSQL | localhost |
| DB_PORT | Puerto de PostgreSQL | 5432 |

## Configuración de Persistencia

El proyecto utiliza PostgreSQL como base de datos. La configuración se maneja via:
- application.yml para desarrollo local
- ConfigMap de Kubernetes para producción
- Variables de entorno para secretos

## Health Checks

- **Liveness Probe**: Verifica que el proceso de la aplicación está activo
- **Readiness Probe**: Verifica que la aplicación puede servir tráfico
- **Startup Probe**: Permite tiempo adicional para inicio de la aplicación

## Referencias

- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/)
- [Kubernetes Documentation](https://kubernetes.io/docs/)
- [Docker Documentation](https://docs.docker.com/)
- [PostgreSQL JDBC Driver](https://jdbc.postgresql.org/)
```
