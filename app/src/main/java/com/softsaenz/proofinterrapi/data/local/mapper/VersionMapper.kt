package com.softsaenz.proofinterrapi.data.local.mapper

import com.softsaenz.proofinterrapi.data.local.entity.VersionEntity
import com.softsaenz.proofinterrapi.domain.dto.VersionDTO
import javax.inject.Inject

/**
 * Clase encargada de la transformación de datos para el control de versiones.
 * * Implementa [MapperDao] para convertir entre el modelo de persistencia local
 * [VersionEntity] y el objeto de transferencia de datos [VersionDTO].
 *
 * ### Responsabilidades:
 * - **fromEntity:** Prepara un DTO para ser guardado como una entidad de base de datos.
 * - **toEntity:** Transforma un registro de la base de datos en un objeto legible por la lógica de negocio.
 * * @constructor Crea una instancia de [VersionMapper] mediante inyección de dependencias.
 */
class VersionMapper @Inject constructor() : MapperDao<VersionEntity, VersionDTO> {

    /**
     * Convierte un [VersionDTO] en una [VersionEntity].
     * *Nota:* En esta implementación, se prioriza el campo 'version' para inserciones.
     */
    override fun fromEntity(data: VersionDTO): VersionEntity {
        return VersionEntity(
            version = data.version
        )
    }

    /**
     * Convierte una [VersionEntity] en un [VersionDTO].
     * Recupera la información completa, incluyendo metadatos de auditoría como el ID y la fecha.
     */
    override fun toEntity(data: VersionEntity): VersionDTO {
        return VersionDTO(
            id = data.id,
            version = data.version,
            createdAt = data.createdAt
        )
    }
}