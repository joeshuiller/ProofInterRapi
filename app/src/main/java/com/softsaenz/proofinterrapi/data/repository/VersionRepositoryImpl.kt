package com.softsaenz.proofinterrapi.data.repository

import com.softsaenz.proofinterrapi.domain.dto.VersionDTO
import com.softsaenz.proofinterrapi.data.local.source.VersionDataSource
import com.softsaenz.proofinterrapi.data.local.utilsLocal.ResourceLocal
import com.softsaenz.proofinterrapi.data.local.mapper.VersionMapper
import com.softsaenz.proofinterrapi.domain.repository.VersionRepository
import com.softsaenz.proofinterrapi.ui.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
/**
 * Implementación concreta de [VersionRepository].
 *
 * Esta clase actúa como mediadora entre la fuente de datos local ([VersionDataSource])
 * y la lógica de negocio. Se encarga de:
 * 1. **Mapeo de Datos:** Transforma entidades de base de datos a DTOs y viceversa.
 * 2. **Gestión de Hilos:** Asegura que las operaciones pesadas ocurran en [Dispatchers.IO].
 * 3. **Encapsulamiento de Estados:** Envuelve los resultados en [ResourceLocal] para
 * comunicar estados de carga y error.
 *
 * @property versionData Fuente de datos primaria (generalmente un DAO).
 * @property versionMapper Utilidad para convertir modelos entre capas.
 */
class VersionRepositoryImpl @Inject constructor(
    private val versionData: VersionDataSource,
    private val versionMapper: VersionMapper
) : VersionRepository {

    override fun getAllVersion(): Flow<ResourceLocal<List<VersionDTO>>> = flow {
        emit(ResourceLocal.Loading())
        try {
            val entities = versionData.getAllVersion()
            // Transformamos la lista de la DB a una lista de DTOs para el dominio
            val dtos = entities.map { versionMapper.toEntity(it) }
            emit(ResourceLocal.Success(dtos))
        } catch (e: Exception) {
            emit(ResourceLocal.Error(Constants.GENERIC)) // Error seguro
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getVersionById(id: Int): Flow<ResourceLocal<VersionDTO?>> = flow {
        emit(ResourceLocal.Loading())
        try {
            // Convertimos el DTO a un modelo que la base de datos entienda
            val id = versionData.getVersionById(id)
            val mapper = id?.let { versionMapper.toEntity(it) }
            emit(ResourceLocal.Success(mapper))
        } catch (e: Exception) {
            emit(ResourceLocal.Error(Constants.GENERIC))
        }
    }.flowOn(Dispatchers.IO)


    override suspend fun upsertVersion(version: VersionDTO): Flow<ResourceLocal<Long>> = flow {
        emit(ResourceLocal.Loading())
        try {
            // Convertimos el DTO a un modelo que la base de datos entienda
            val dbModel = versionMapper.fromEntity(version)
            val id = versionData.upsertVersion(dbModel)
            emit(ResourceLocal.Success(id))
        } catch (e: Exception) {
            emit(ResourceLocal.Error(Constants.GENERIC))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun deleteVersion(version: VersionDTO): Int {
        val dbModel = versionMapper.fromEntity(version)
        return versionData.deleteVersion(dbModel)
    }

    override suspend fun deleteAll(): Int = versionData.deleteAll()
}