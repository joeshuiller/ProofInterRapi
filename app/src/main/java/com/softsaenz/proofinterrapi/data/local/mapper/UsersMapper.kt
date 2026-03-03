package com.softsaenz.proofinterrapi.data.local.mapper

import com.softsaenz.proofinterrapi.data.local.entity.UsersEntity
import com.softsaenz.proofinterrapi.domain.dto.UsersDTO
import javax.inject.Inject

class UsersMapper @Inject constructor() : MapperDao<UsersEntity, UsersDTO> {
    override fun fromEntity(data: UsersDTO): UsersEntity {
        return UsersEntity(
            users = data.users,
            name = data.name,
            identification = data.identification
        )
    }

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