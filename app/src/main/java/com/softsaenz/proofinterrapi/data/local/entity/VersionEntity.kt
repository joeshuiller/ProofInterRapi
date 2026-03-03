package com.softsaenz.proofinterrapi.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidad de persistencia para el control de versiones local.
 * * Esta clase define la estructura de la tabla 'version' en SQLite a través de Room.
 * Se utiliza para registrar el historial de versiones de la aplicación o del
 * esquema de datos, permitiendo rastrear cuándo se realizó cada entrada.
 *
 * @property id Identificador único autogenerado por Room (Clave Primaria).
 * @property version Cadena de texto que identifica la versión (ej. "1.0.2" o "v2").
 * @property createdAt Timestamp en milisegundos que indica el momento de creación del registro.
 */
@Entity(tableName = "version")
data class VersionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val version: String,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
)