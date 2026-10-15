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