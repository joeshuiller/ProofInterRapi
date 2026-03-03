package com.softsaenz.proofinterrapi.data.remote.dtos.response

import com.google.gson.annotations.SerializedName

/**
 * Modelo de respuesta de red para la configuración y metadatos de las tablas.
 *
 * Esta clase representa el JSON crudo recibido desde el endpoint de esquemas.
 * Contiene las instrucciones necesarias para que la aplicación sepa cómo
 * estructurar su persistencia local, manejar filtros y gestionar la
 * sincronización por lotes.
 *
 * @property name Corresponde a "NombreTabla". Nombre de la entidad en el servidor.
 * @property pk Corresponde a "Pk". Definición de la clave primaria.
 * @property queryCreation Corresponde a "QueryCreacion". Sentencia SQL para crear la tabla localmente.
 * @property batchSize Corresponde a "BatchSize". Tamaño de descarga por cada petición.
 * @property filter Corresponde a "Filtro". Cláusula SQL para filtrar datos de la API.
 * @property error Corresponde a "Error". Mensaje de error reportado por el servidor para esta tabla.
 * @property numberFields Corresponde a "NumeroCampos". Cantidad de columnas esperadas.
 * @property methodApp Corresponde a "MetodoApp". Estrategia de aplicación de datos en el cliente.
 * @property syncUpdateDate Corresponde a "FechaActualizacionSincro". Marca de la última sincronización.
 */
data class TablesResponse(
    @SerializedName("NombreTabla") val name: String?,
    @SerializedName("Pk") val pk: String?,
    @SerializedName("QueryCreacion") val queryCreation: String?,
    @SerializedName("BatchSize") val batchSize: String?,
    @SerializedName("Filtro") val filter: String?,
    @SerializedName("Error") val error: String?,
    @SerializedName("NumeroCampos") val numberFields: String?,
    @SerializedName("MetodoApp") val methodApp: String?,
    @SerializedName("FechaActualizacionSincro") val syncUpdateDate: String?
)
