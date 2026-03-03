package com.softsaenz.proofinterrapi.domain.dto
/**
 * Objeto de Transferencia de Datos (DTO) para el control de versiones.
 *
 * Esta clase se utiliza para persistir y consultar el historial de versiones
 * del sistema. Permite realizar un seguimiento de cuándo se aplicaron
 * cambios estructurales o actualizaciones de datos en el almacenamiento local.
 *
 * @property id Identificador único del registro de versión.
 * @property version Cadena de texto que representa la versión (ej: "1.0.4", "v2-alpha").
 * @property createdAt Marca de tiempo (Unix timestamp) del momento en que se registró la versión.
 */
data class VersionDTO(
    val id: Int = 0,
    val version: String,
    val createdAt: Long = 0L
)