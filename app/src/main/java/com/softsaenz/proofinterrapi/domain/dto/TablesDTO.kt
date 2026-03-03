package com.softsaenz.proofinterrapi.domain.dto


/**
 * Objeto de Transferencia de Datos (DTO) para la gestión de metadatos de tablas.
 *
 * Esta clase almacena la configuración necesaria para la creación dinámica y sincronización
 * de tablas en la base de datos local. Incluye definiciones de esquemas, tamaños de lote
 * para la red y estados de error.
 *
 * @property id Identificador único del registro de metadatos.
 * @property name Nombre de la tabla en la base de datos.
 * @property pk Definición o nombre de la clave primaria.
 * @property queryCreation Sentencia SQL completa utilizada para generar la tabla.
 * @property batchSize Cantidad de registros a procesar por cada petición de sincronización.
 * @property filter Cláusula de filtrado (SQL WHERE) para la obtención de datos.
 * @property error Último mensaje de error registrado durante la operación con esta tabla.
 * @property numberFields Cantidad total de campos o columnas que componen la tabla.
 * @property methodApp Estrategia o método de aplicación definido para el procesamiento de datos.
 * @property syncUpdateDate Fecha de la última sincronización exitosa.
 * @property createdAt Marca de tiempo de creación del registro de metadatos.
 */
data class TablesDTO(
    val id: Int = 0,
    val name: String?,
    val pk: String?,
    val queryCreation: String?,
    val batchSize: String?,
    val filter: String?,
    val error: String?,
    val numberFields: String?,
    val methodApp: String?,
    val syncUpdateDate: String,
    val createdAt: Long = 0L
)
