package com.softsaenz.proofinterrapi.data.remote.mapper

import com.softsaenz.proofinterrapi.data.remote.dtos.request.AuthRequest
import com.softsaenz.proofinterrapi.domain.dto.AppControlDTO
import com.softsaenz.proofinterrapi.domain.dto.AuthDTO
import javax.inject.Inject

class AuthRemoteDataMapper @Inject constructor() : Mapper<AuthRequest, AuthDTO, AppControlDTO, String>{
    override fun fromEntity(dto: AuthDTO): AuthRequest {
        return AuthRequest(
            mac = dto.mac,
            users = dto.users,
            path = dto.path,
            nameApplication = dto.nameApplication,
            password = dto.password
        )
    }

    override fun toEntity(domain: AuthRequest): AuthDTO {
        return AuthDTO(
            mac = domain.mac,
            users = domain.users,
            path = domain.path,
            nameApplication = domain.nameApplication,
            password = domain.password
        )
    }

    override fun toResponse(data: String): AppControlDTO {
        return AppControlDTO(
            version = data,
        )
    }

}