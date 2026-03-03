package com.softsaenz.proofinterrapi.domain.dto

/**
 * Objeto de Transferencia de Datos (DTO) para la entidad de Usuario.
 *
 * Esta clase representa la estructura de un usuario tal como se almacena en la base de datos
 * local o se recibe de una fuente externa. Se utiliza para transportar información de perfil
 * y credenciales básicas a través de las capas de la aplicación.
 *
 * @property id Identificador único autogenerado (Primary Key). Por defecto es 0 para nuevas inserciones.
 * @property users El nombre de usuario o identificador de cuenta (Alias/Login).
 * @property name Nombre completo o descriptivo de la persona.
 * @property identification Documento de identidad o número de registro oficial.
 * @property createdAt Marca de tiempo (Unix timestamp) que indica cuándo se creó el registro.
 */
data class UsersDTO(
    val id: Int = 0,
    val users: String,
    val name: String,
    val identification: String,
    val createdAt: Long = 0L
)
