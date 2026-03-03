package com.softsaenz.proofinterrapi.data.remote.dtos.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Modelo de petición (Request) para el proceso de autenticación.
 * * Esta clase define la estructura del JSON que se envía al servidor para validar
 * la identidad del usuario. Incluye identificadores de hardware, el nombre del
 * aplicativo de origen y las credenciales de acceso.
 *
 * @property mac Dirección física del dispositivo (MAC Address) para auditoría y seguridad.
 * @property nameApplication Corresponde a "NomAplicacion". Identifica qué cliente solicita el acceso.
 * @property path Ruta o contexto específico de la petición en el servidor.
 * @property users Corresponde a "Usuario". El nombre de usuario o alias de la cuenta.
 * @property password Corresponde a "Password". Contraseña en texto plano (se recomienda envío sobre HTTPS).
 */
@Serializable
data class AuthRequest(
    @SerialName("Mac") val mac: String?,
    @SerialName("NomAplicacion") val nameApplication: String,
    @SerialName("Path") val path: String?,
    @SerialName("Usuario") val users: String,
    @SerialName("Password") val password: String
)