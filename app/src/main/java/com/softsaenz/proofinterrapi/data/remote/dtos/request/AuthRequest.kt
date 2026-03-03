package com.softsaenz.proofinterrapi.data.remote.dtos.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthRequest(
    @SerialName("Mac")
    val mac: String?,
    @SerialName("NomAplicacion")
    val nameApplication: String,
    @SerialName("Path")
    val path: String?,
    @SerialName("Usuario")
    val users: String,
    @SerialName("Password")
    val password: String
)