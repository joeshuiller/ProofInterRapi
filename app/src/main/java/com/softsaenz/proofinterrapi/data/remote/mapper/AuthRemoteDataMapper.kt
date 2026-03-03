package com.softsaenz.proofinterrapi.data.remote.mapper

import com.softsaenz.proofinterrapi.data.remote.dtos.request.AuthRequest
import com.softsaenz.proofinterrapi.domain.dto.AppControlDTO
import com.softsaenz.proofinterrapi.domain.dto.AuthDTO
import javax.inject.Inject
/**
 * Clase especializada en el mapeo de datos de autenticación y control de la aplicación.
 * * Implementa la interfaz genérica [Mapper] para gestionar la conversión entre:
 * 1. Peticiones de Autenticación ([AuthDTO] <-> [AuthRequest]).
 * 2. Datos de Control del Sistema (Convierte un [String] de versión a [AppControlDTO]).
 * * Esta clase es crítica para el proceso de Login, asegurando que las credenciales
 * se empaqueten correctamente antes de ser enviadas a través de la red.
 */
class AuthRemoteDataMapper @Inject constructor() : Mapper<AuthRequest, AuthDTO, AppControlDTO, String>{
    /**
     * Prepara los datos locales para ser enviados al servidor como una petición de red.
     * @param dto El objeto de datos de autenticación proveniente de la UI o Dominio.
     * @return Un objeto [AuthRequest] compatible con la interfaz de Retrofit.
     */
    override fun fromEntity(dto: AuthDTO): AuthRequest {
        return AuthRequest(
            mac = dto.mac,
            users = dto.users,
            path = dto.path,
            nameApplication = dto.nameApplication,
            password = dto.password
        )
    }
    /**
     * Transforma una petición de red de vuelta a un formato DTO de dominio.
     */
    override fun toEntity(domain: AuthRequest): AuthDTO {
        return AuthDTO(
            mac = domain.mac,
            users = domain.users,
            path = domain.path,
            nameApplication = domain.nameApplication,
            password = domain.password
        )
    }
    /**
     * Transforma una respuesta cruda (String) en un objeto estructurado de control.
     * @param data Cadena de texto recibida (generalmente representa el número de versión).
     * @return Un [AppControlDTO] que encapsula la versión del sistema.
     */
    override fun toResponse(data: String): AppControlDTO {
        return AppControlDTO(
            version = data,
        )
    }

}