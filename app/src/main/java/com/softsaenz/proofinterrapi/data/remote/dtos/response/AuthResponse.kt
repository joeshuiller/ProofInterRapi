package com.softsaenz.proofinterrapi.data.remote.dtos.response

import com.google.gson.annotations.SerializedName
/**
 * Modelo de respuesta final del servidor tras un proceso de autenticación exitoso.
 * * Contiene el perfil detallado del usuario, su ubicación operativa, el rol asignado
 * dentro del sistema y el token de sesión (JWT) necesario para la comunicación
 * segura con otros endpoints.
 *
 * @property users Corresponde a "Usuario". El identificador de cuenta del usuario.
 * @property identification Documento de identidad del usuario.
 * @property name Nombre(s) del usuario.
 * @property firstSurname Primer apellido.
 * @property secondSurname Segundo apellido.
 * @property jobTitle Cargo u ocupación dentro de la organización.
 * @property messageResult Código numérico que indica el resultado de la operación (ej: 1 para éxito).
 * @property tokenJWT Token de portador (Bearer) para la autorización de cabeceras.
 * @property idRol Identificador único del rol para la gestión de permisos en la UI.
 */
data class AuthResponse(
    // Identidad Personal
    @SerializedName("Usuario") val users: String,
    @SerializedName("Identificacion") val identification: String?,
    @SerializedName("Nombre") val name: String?,
    @SerializedName("Apellido1") val firstSurname: String?,
    @SerializedName("Apellido2") val secondSurname: String?,

    // Perfil y Permisos
    @SerializedName("Cargo") val jobTitle: String?,
    @SerializedName("Aplicaciones") val applications: String?,
    @SerializedName("Ubicaciones") val locations: String?,
    @SerializedName("NomRol") val nomRol: String?,
    @SerializedName("IdRol") val idRol: String?,
    @SerializedName("ModulosApp") val modulosApp: String?,

    // Contexto de Sesión
    @SerializedName("MensajeResultado") val messageResult: Int,
    @SerializedName("TokenJWT") val tokenJWT: String?,

    // Ubicación Operativa
    @SerializedName("IdLocalidad") val localityID: String?,
    @SerializedName("NombreLocalidad") val nameLocality: String?
)