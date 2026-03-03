package com.softsaenz.proofinterrapi.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidad de persistencia que almacena metadatos y esquemas para la sincronización dinámica.
 * * Esta clase define la tabla 'tables_query', la cual actúa como un diccionario de
 * configuración para las operaciones de base de datos. Permite almacenar desde
 * sentencias SQL de creación hasta parámetros de paginación de la API.
 *
 * @property id Identificador único autoincremental (Clave Primaria).
 * @property name Nombre de la tabla de destino en la base de datos local.
 * @property pk Nombre de la clave primaria de la tabla referenciada.
 * @property queryCreation Sentencia SQL necesaria para crear o inicializar la tabla.
 * @property batchSize Cantidad de registros a procesar por cada petición de red.
 * @property filter Cláusulas adicionales para filtrar los datos durante la sincronización.
 * @property error Registro de incidencias o mensajes de error del último intento de sincronización.
 * @property numberFields Cantidad de columnas o campos esperados.
 * @property methodApp Identificador del método o endpoint de la API asociado.
 * @property syncUpdateDate Fecha de la última actualización exitosa.
 * @property createdAt Marca de tiempo local de cuando se registró esta configuración.
 */
@Entity(tableName = "tables_query")
data class TablesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name_tabla")
    val name: String?,
    @ColumnInfo(name = "pk")
    val pk: String?,
    @ColumnInfo(name = "query_creation")
    val queryCreation: String?,
    @ColumnInfo(name = "batch_size")
    val batchSize: String?,
    @ColumnInfo(name = "filter")
    val filter: String?,
    @ColumnInfo(name = "error")
    val error: String?,
    @ColumnInfo(name = "number_fields")
    val numberFields: String?,
    @ColumnInfo(name = "method_app")
    val methodApp: String?,
    @ColumnInfo(name = "sync_update_date")
    val syncUpdateDate: String?,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
)