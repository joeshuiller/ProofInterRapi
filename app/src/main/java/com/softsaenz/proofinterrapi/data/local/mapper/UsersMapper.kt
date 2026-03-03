package com.softsaenz.proofinterrapi.data.local.mapper

import com.softsaenz.proofinterrapi.data.local.entity.UsersEntity
import com.softsaenz.proofinterrapi.domain.dto.UsersDTO
import javax.inject.Inject

/**
 * Clase encargada de la transformación de datos entre la capa de Persistencia y Datos.
 * * Implementa la interfaz [MapperDao] para convertir:
 * 1. Entidades de base de datos ([UsersEntity]) en Objetos de Transferencia ([UsersDTO]).
 * 2. Objetos de transferencia en entidades listas para persistir.
 * * Es fundamental para proteger la lógica de negocio de cambios estructurales en las
 * tablas de la base de datos.
 */
class UsersMapper @Inject constructor() : MapperDao<UsersEntity, UsersDTO> {

    /**
     * Transforma un [UsersDTO] en una [UsersEntity] para su almacenamiento.
     * @param data El objeto de transporte con la información del usuario.
     * @return Una entidad configurada para ser procesada por el DAO.
     */
    override fun fromEntity(data: UsersDTO): UsersEntity {
        return UsersEntity(
            users = data.users,
            name = data.name,
            identification = data.identification
        )
    }

    /**
     * Convierte un registro de base de datos ([UsersEntity]) en un [UsersDTO].
     * @param data La entidad recuperada por Room.
     * @return Un DTO que incluye metadatos como el ID y la fecha de creación.
     */
    override fun toEntity(data: UsersEntity): UsersDTO {
        return UsersDTO(
            id = data.id,
            users = data.users.toString(),
            name = data.name.toString(),
            identification = data.identification.toString(),
            createdAt = data.createdAt
        )
    }
}