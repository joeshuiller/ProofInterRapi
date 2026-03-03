package com.softsaenz.proofinterrapi.domain.repository

import com.softsaenz.proofinterrapi.data.local.utilsLocal.ResourceLocal
import com.softsaenz.proofinterrapi.domain.dto.VersionDTO
import kotlinx.coroutines.flow.Flow
/**
 * Repositorio encargado de gestionar la persistencia y recuperación de versiones de la aplicación.
 *
 * Esta interfaz abstrae las operaciones CRUD (Crear, Leer, Actualizar, Borrar) sobre la entidad
 * [VersionDTO]. Proporciona flujos de datos reactivos ([Flow]) para asegurar que la UI se
 * mantenga sincronizada con el estado real de la base de datos local.
 */
interface VersionRepository {

    /**
     * Recupera la lista completa de versiones almacenadas.
     * @return Un [Flow] que emite el estado de la operación y la lista de versiones.
     */
    fun getAllVersion(): Flow<ResourceLocal<List<VersionDTO>>>

    /**
     * Busca una versión específica mediante su identificador único.
     * @param id El identificador de la versión.
     * @return Un [Flow] con el registro encontrado o nulo si no existe.
     */
    suspend fun getVersionById(id: Int): Flow<ResourceLocal<VersionDTO?>>

    /**
     * Inserta una nueva versión o actualiza una existente (Upsert).
     * @param version El objeto DTO con la información a persistir.
     * @return Un [Flow] que emite el ID del registro afectado.
     */
    suspend fun upsertVersion(version: VersionDTO): Flow<ResourceLocal<Long>>

    /**
     * Elimina una versión específica de la base de datos.
     * @param version El objeto DTO a eliminar.
     * @return El número de filas afectadas (generalmente 1 o 0).
     */
    suspend fun deleteVersion(version: VersionDTO): Int

    /**
     * Elimina todos los registros de versiones de la base de datos.
     * @return El número total de filas eliminadas.
     */
    suspend fun deleteAll(): Int
}