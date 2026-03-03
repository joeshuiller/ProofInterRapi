package com.softsaenz.proofinterrapi.data.repository

import com.softsaenz.proofinterrapi.data.local.source.TablesDataSource
import com.softsaenz.proofinterrapi.data.local.utilsLocal.ResourceLocal
import com.softsaenz.proofinterrapi.data.local.mapper.TablesMapper
import com.softsaenz.proofinterrapi.domain.dto.TablesDTO
import com.softsaenz.proofinterrapi.domain.repository.TablesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
/**
 * Implementación concreta del contrato [TablesRepository].
 *
 * Esta clase gestiona la persistencia y recuperación de metadatos de tablas,
 * actuando como un puente entre el [TablesDataSource] (fuente de datos física)
 * y la capa de dominio.
 *
 * ### Funcionalidades Clave:
 * - **Mapeo de Capas:** Utiliza [TablesMapper] para convertir Entidades de BD a [TablesDTO] y viceversa.
 * - **Gestión de Estados:** Envuelve las consultas en flujos [Flow] que emiten estados [ResourceLocal]
 * (Loading, Success, Error).
 * - **Seguridad de Hilos:** Fuerza la ejecución de las operaciones de consulta en [Dispatchers.IO].
 *
 * @property tablesData Fuente de datos local o remota que provee los datos crudos.
 * @property tablesMapper Utilidad de transformación para mantener la integridad de los modelos en cada capa.
 */
class TablesRepositoryImpl @Inject constructor(
    private val tablesData: TablesDataSource,
    private val tablesMapper: TablesMapper
): TablesRepository{
    override suspend fun upsertTables(tables: TablesDTO): Long {
        val mapper =  tablesMapper.fromEntity(tables)
        return tablesData.upsertTables(mapper)
    }

    override suspend fun getTablesById(id: Int): Flow<ResourceLocal<TablesDTO?>> =
        flow {
            emit(ResourceLocal.Loading())
            try {
                val dto = tablesData.getTablesById(id)
                val mapper = dto?.let { tablesMapper.toEntity(it) }
                emit(ResourceLocal.Success(mapper))
            } catch (e: Exception) {
                emit(ResourceLocal.Error(e.message ?: "Unknown Error"))
            }
        }.flowOn(Dispatchers.IO)

    override fun getAllTables():  Flow<ResourceLocal<List<TablesDTO>>> =
    flow {
        emit(ResourceLocal.Loading())
        try {
            val dto = tablesData.getAllTables()
            val mapper =  dto.map { dto ->
                tablesMapper.toEntity(dto)
            }
            emit(ResourceLocal.Success(mapper))
        } catch (e: Exception) {
            emit(ResourceLocal.Error(e.message ?: "Unknown Error"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun deleteTables(tables: TablesDTO): Int {
        val mapper =  tablesMapper.fromEntity(tables)
        return tablesData.deleteTables(mapper)
    }

    override suspend fun insertTables(tables: List<TablesDTO>) {
        val mapper =  tables.map{
            tablesMapper.fromEntity(it)
        }
        return tablesData.insertLocations(mapper)
    }
}