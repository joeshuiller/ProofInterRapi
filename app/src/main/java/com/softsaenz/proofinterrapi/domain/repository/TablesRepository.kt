package com.softsaenz.proofinterrapi.domain.repository

import com.softsaenz.proofinterrapi.data.local.entity.TablesEntity
import com.softsaenz.proofinterrapi.data.local.utilsLocal.ResourceLocal
import com.softsaenz.proofinterrapi.domain.dto.TablesDTO
import kotlinx.coroutines.flow.Flow

/**
 * Repositorio encargado de la gestión de datos persistentes para las tablas de la aplicación.
 *
 * Provee una abstracción sobre las operaciones de base de datos para [TablesDTO].
 * Utiliza una combinación de funciones de suspensión para operaciones transaccionales y
 * [Flow] para la observación reactiva de datos.
 */
interface TablesRepository {

    /**
     * Inserta o actualiza una tabla de forma individual.
     * @param table Objeto de datos a persistir.
     * @return El ID generado o afectado por la operación.
     */
    suspend fun upsertTables(table: TablesDTO): Long

    /**
     * Busca una tabla específica por su identificador único.
     * @param id Identificador de la tabla.
     * @return Un [Flow] que emite el estado de la búsqueda y el objeto encontrado.
     */
    suspend fun getTablesById(id: Int): Flow<ResourceLocal<TablesDTO?>>

    /**
     * Obtiene todas las tablas almacenadas en la base de datos.
     * @return Un [Flow] reactivo con la lista de [TablesDTO].
     */
    fun getAllTables(): Flow<ResourceLocal<List<TablesDTO>>>

    /**
     * Elimina un registro de tabla específico.
     * @param table Objeto a eliminar.
     * @return Cantidad de filas eliminadas.
     */
    suspend fun deleteTables(table: TablesDTO): Int

    /**
     * Inserta una colección de tablas en una operación por lotes (Batch).
     * @param tables Lista de objetos de tablas a insertar.
     */
    suspend fun insertTables(tables: List<TablesDTO>)
}