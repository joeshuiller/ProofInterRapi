package com.softsaenz.proofinterrapi.data.local.mapper

import com.softsaenz.proofinterrapi.data.local.entity.VersionEntity
import com.softsaenz.proofinterrapi.domain.dto.VersionDTO
import javax.inject.Inject

class VersionMapper @Inject constructor() : MapperDao<VersionEntity, VersionDTO> {
    override fun fromEntity(data: VersionDTO): VersionEntity {
        return VersionEntity(
            version = data.version
        )
    }
    override fun toEntity(data: VersionEntity): VersionDTO {
        return VersionDTO(
            id = data.id,
            version = data.version,
            createdAt = data.createdAt
        )
    }
}