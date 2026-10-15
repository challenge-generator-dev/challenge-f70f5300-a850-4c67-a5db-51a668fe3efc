# Diseñar y orquestar contenedores en un proyecto Java

En el dominio de la banca, se requiere implementar y orquestar contenedores para un proyecto de backend utilizando Java. Debes considerar las diferencias, ventajas y desventajas entre Docker y Kubernetes. El sistema debe soportar al menos dos herramientas de contenerización/orquestación y ser capaz de manejar la alta disponibilidad y escalabilidad requerida por el negocio. Los contenedores deben ser idempotentes y tolerantes a fallos, asegurando que las operaciones se completen correctamente incluso en condiciones de error.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | implementaPlataformasContenedores |
| **Nivel** | advanced-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 30 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Configurar entorno de desarrollo con Docker

**Objetivo:** Establecer un entorno de desarrollo utilizando Docker para el proyecto Java.

**Tiempo estimado:** 5 horas

**Instrucciones:**

- Identificar las dependencias necesarias para el proyecto Java.
- Crear un Dockerfile que configure el entorno de ejecución.
- Verificar que el contenedor se inicia correctamente y que todas las dependencias están disponibles.

**Entregable:** Dockerfile y script de inicio del contenedor.

<details>
<summary>Pistas de conocimiento</summary>

- Considera las mejores prácticas para la creación de Dockerfiles.
- Piensa en cómo asegurar que el contenedor sea idempotente.

</details>

### Fase 2: Orquestar contenedores con Kubernetes

**Objetivo:** Implementar la orquestación de contenedores utilizando Kubernetes.

**Tiempo estimado:** 10 horas

**Instrucciones:**

- Crear un archivo de configuración de Kubernetes (YAML) para desplegar el contenedor.
- Configurar servicios y balanceadores de carga para asegurar la alta disponibilidad.
- Verificar que los contenedores se despliegan correctamente y que el balanceo de carga funciona como se espera.

**Entregable:** Archivo de configuración de Kubernetes y verificación de despliegue.

<details>
<summary>Pistas de conocimiento</summary>

- Considera las ventajas y desventajas de usar Kubernetes frente a otras herramientas de orquestación.
- Piensa en cómo manejar la alta disponibilidad y escalabilidad.

</details>

### Fase 3: Optimizar y asegurar la tolerancia a fallos

**Objetivo:** Optimizar la configuración y asegurar que el sistema es tolerante a fallos.

**Tiempo estimado:** 10 horas

**Instrucciones:**

- Identificar puntos de fallo potenciales en la configuración.
- Implementar estrategias de recuperación y reintento para manejar fallos.
- Verificar que el sistema continúa operando correctamente ante fallos temporales.

**Entregable:** Configuración optimizada y verificación de tolerancia a fallos.

<details>
<summary>Pistas de conocimiento</summary>

- Considera diferentes estrategias de recuperación y reintento.
- Piensa en cómo asegurar que el sistema es tolerante a fallos.

</details>

### Fase 4: Documentar y comunicar el diseño

**Objetivo:** Documentar el diseño y comunicarlo a diferentes audiencias.

**Tiempo estimado:** 5 horas

**Instrucciones:**

- Crear documentación técnica que describa el diseño y la configuración.
- Preparar una presentación para comunicar el diseño a audiencias técnicas y de negocio.
- Verificar que la documentación y la presentación son claras y efectivas.

**Entregable:** Documentación técnica y presentación del diseño.

<details>
<summary>Pistas de conocimiento</summary>

- Considera las mejores prácticas para la documentación técnica.
- Piensa en cómo comunicar efectivamente el diseño a diferentes audiencias.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es la contenerización y orquestación de contenedores en el contexto de un proyecto Java?
- **paraQueSirve**: ¿Para qué sirve utilizar Docker y Kubernetes en un proyecto de backend con Java?
- **comoSeUsa**: ¿Cómo se usa Docker y Kubernetes para contenerizar y orquestar contenedores en un proyecto Java?
- **erroresComunes**: ¿Cuáles son los errores comunes al implementar y orquestar contenedores con Docker y Kubernetes?
- **queDecisionesImplica**: ¿Qué decisiones implica la elección entre Docker y Kubernetes para un proyecto de backend con Java?

## Criterios de Evaluacion

- Configurar correctamente un entorno de desarrollo con Docker.
- Implementar la orquestación de contenedores con Kubernetes.
- Optimizar la configuración y asegurar la tolerancia a fallos.
- Documentar y comunicar efectivamente el diseño.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
