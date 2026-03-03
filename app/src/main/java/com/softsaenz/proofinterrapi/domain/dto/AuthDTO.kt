package com.softsaenz.proofinterrapi.domain.dto

/**
 * Objeto de Transferencia de Datos (DTO) para la autenticación de usuarios.
 *
 * Encapsula las credenciales de acceso y los metadatos del entorno (MAC, ruta, aplicación)
 * necesarios para validar la identidad del usuario ante el servidor o el sistema local.
 *
 * @property mac Dirección física del dispositivo o identificador único de hardware.
 * @property nameApplication Nombre del cliente o módulo que solicita la autenticación.
 * @property path Ruta de acceso o endpoint relacionado con la sesión.
 * @property users Nombre de usuario, alias o correo electrónico de acceso.
 * @property password Contraseña o token secreto del usuario (Campo sensible).
 */
data class AuthDTO(
     val mac: String?,
     val nameApplication: String,
     val path: String?,
     val users: String,
     val password: String
)