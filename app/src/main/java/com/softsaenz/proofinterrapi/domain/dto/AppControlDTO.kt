package com.softsaenz.proofinterrapi.domain.dto

/**
 * Objeto de Transferencia de Datos (DTO) para el control y versión de la aplicación.
 *
 * Este modelo se utiliza para recibir metadatos globales desde el backend, permitiendo
 * gestionar flujos de actualización obligatoria o compatibilidad de esquemas de datos.
 * * ### Casos de Uso:
 * 1. **Update Check:** Validar si la versión instalada coincide con la mínima requerida.
 * 2. **Sincronización:** Determinar si es necesario refrescar el esquema de [Room] (Tablas).
 *
 * @property version Cadena de texto que representa la versión actual del sistema en el servidor.
 * @author Janes Sáenz Puerta
 */
data class AppControlDTO(
    val version: String,
)
