package com.softsaenz.proofinterrapi.data.remote.dtos.response

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("Usuario")
    val users: String,
    @SerializedName("Identificacion")
    val identification: String?,
    @SerializedName("Nombre")
    val name: String?,
    @SerializedName("Apellido1")
    val firstSurname: String?,
    @SerializedName("Apellido2")
    val secondSurname: String?,
    @SerializedName("Cargo")
    val jobTitle: String?,
    @SerializedName("Aplicaciones")
    val applications: String?,
    @SerializedName("Ubicaciones")
    val locations: String?,
    @SerializedName("MensajeResultado")
    val messageResult: Int,
    @SerializedName("IdLocalidad")
    val localityID: String?,
    @SerializedName("NombreLocalidad")
    val nameLocality: String?,
    @SerializedName("NomRol")
    val nomRol: String?,
    @SerializedName("IdRol")
    val idRol: String?,
    @SerializedName("TokenJWT")
    val tokenJWT: String?,
    @SerializedName("ModulosApp")
    val modulosApp: String?,
)
