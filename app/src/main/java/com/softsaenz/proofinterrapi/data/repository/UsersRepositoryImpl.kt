package com.softsaenz.proofinterrapi.data.repository

import com.softsaenz.proofinterrapi.data.local.source.UsersDataSource
import com.softsaenz.proofinterrapi.data.local.utilsLocal.ResourceLocal
import com.softsaenz.proofinterrapi.data.local.mapper.UsersMapper
import com.softsaenz.proofinterrapi.domain.dto.UsersDTO
import com.softsaenz.proofinterrapi.domain.repository.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val usersData: UsersDataSource,
    private val usersMapper: UsersMapper
): UsersRepository{
    override suspend fun upsertUsers(users: UsersDTO): Flow<ResourceLocal<Long>> =
        flow {
            emit(ResourceLocal.Loading())
            try {
                val dto = usersMapper.fromEntity(users)
                val mapper = usersData.upsertUsers(dto)
                emit(ResourceLocal.Success(mapper))
            } catch (e: Exception) {
                emit(ResourceLocal.Error(e.message ?: "Unknown Error"))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun getAllUsers(): Flow<ResourceLocal<List<UsersDTO>>> =
        flow {
            emit(ResourceLocal.Loading())
            try {
                val dto = usersData.getAllUsers()
                val mapper =  dto.map { dto ->
                    usersMapper.toEntity(dto)
                }
                emit(ResourceLocal.Success(mapper))
            } catch (e: Exception) {
                emit(ResourceLocal.Error(e.message ?: "Unknown Error"))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun getUsersById(id: Int): Flow<ResourceLocal<UsersDTO?>> =
        flow {
            emit(ResourceLocal.Loading())
            try {
                val dto = usersData.getUsersById(id)
                val mapper = dto?.let { usersMapper.toEntity(it) }
                emit(ResourceLocal.Success(mapper))
            } catch (e: Exception) {
                emit(ResourceLocal.Error(e.message ?: "Unknown Error"))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun deleteUsers(users: UsersDTO): Int {
        val mapper =  usersMapper.fromEntity(users)
        return usersData.deleteUsers(mapper)
    }

    override suspend fun deleteAll(): Int {
        return usersData.deleteAll()
    }
}