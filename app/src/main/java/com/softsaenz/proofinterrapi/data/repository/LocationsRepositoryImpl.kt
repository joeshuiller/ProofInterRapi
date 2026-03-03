package com.softsaenz.proofinterrapi.data.repository

import com.softsaenz.proofinterrapi.data.local.mapper.LocationsMapper
import com.softsaenz.proofinterrapi.data.local.source.LocationsDataSource
import com.softsaenz.proofinterrapi.data.local.utilsLocal.ResourceLocal
import com.softsaenz.proofinterrapi.domain.dto.LocationsDTO
import com.softsaenz.proofinterrapi.domain.repository.LocationsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LocationsRepositoryImpl @Inject constructor(
    private val locationsData: LocationsDataSource,
    private val locationsMapper: LocationsMapper
): LocationsRepository {
    override suspend fun getAllLocations(): Flow<ResourceLocal<List<LocationsDTO>>> =
        flow {
            emit(ResourceLocal.Loading())
            try {
                val dto = locationsData.getAllLocations()
                val mapper =  dto.map { dto ->
                    locationsMapper.toEntity(dto)
                }
                emit(ResourceLocal.Success(mapper))
            } catch (e: Exception) {
                emit(ResourceLocal.Error(e.message ?: "Unknown Error"))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun getLocationsById(id: Int): Flow<ResourceLocal<LocationsDTO?>> =
        flow {
            emit(ResourceLocal.Loading())
            try {
                val dto = locationsData.getLocationsById(id)
                val mapper = dto?.let { locationsMapper.toEntity(it) }
                emit(ResourceLocal.Success(mapper))
            } catch (e: Exception) {
                emit(ResourceLocal.Error(e.message ?: "Unknown Error"))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun upsertLocations(locations: LocationsDTO): Long {
        val mapper =  locationsMapper.fromEntity(locations)
        return locationsData.upsertLocations(mapper)
    }

    override suspend fun deleteLocations(locations: LocationsDTO): Int {
        val mapper =  locationsMapper.fromEntity(locations)
        return locationsData.deleteLocations(mapper)
    }

    override suspend fun deleteAll(): Int {
        return locationsData.deleteAll()
    }
}