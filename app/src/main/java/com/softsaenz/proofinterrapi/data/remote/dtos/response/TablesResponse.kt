package com.softsaenz.proofinterrapi.data.remote.dtos.response

import com.google.gson.annotations.SerializedName

data class TablesResponse(
    @SerializedName("NombreTabla")
    val name: String?,
    @SerializedName("Pk")
    val pk: String?,
    @SerializedName("QueryCreacion")
    val queryCreation: String?,
    @SerializedName("BatchSize")
    val batchSize: String?,
    @SerializedName("Filtro")
    val filter: String?,
    @SerializedName("Error")
    val error: String?,
    @SerializedName("NumeroCampos")
    val numberFields: String?,
    @SerializedName("MetodoApp")
    val methodApp: String?,
    @SerializedName("FechaActualizacionSincro")
    val syncUpdateDate: String?
)
