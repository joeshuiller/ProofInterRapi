package com.softsaenz.proofinterrapi.data.local.source

import com.softsaenz.proofinterrapi.data.local.entity.VersionEntity
import com.softsaenz.proofinterrapi.data.local.repository.VersionDao
import javax.inject.Inject

/**
 * Implementación concreta de [VersionDataSource] que utiliza Room para la persistencia.
 *
 * Esta clase actúa como un envoltorio sobre [VersionDao], delegando las operaciones
 * de base de datos a los métodos generados por Room. Al ser inyectada mediante Hilt,
 * facilita la gestión del ciclo de vida de la conexión a la base de datos.
 *
 * @property apiService Instancia del DAO de versiones (Nota: el nombre sugiere una API,
 * pero funcionalmente actúa sobre la persistencia local).
 */
class VersionDataSourceImpl @Inject constructor(
    private val apiService: VersionDao
) : VersionDataSource {

    /**
     * Recupera todos los registros de versión de forma síncrona.
     */
    override fun getAllVersion(): List<VersionEntity> = apiService.getAllVersion()

    /**
     * Busca una versión específica por ID usando corrutinas.
     */
    override suspend fun getVersionById(id: Int): VersionEntity? = apiService.getVersionById(id)

    /**
     * Inserta o actualiza un registro de versión.
     */
    override suspend fun upsertVersion(version: VersionEntity): Long = apiService.upsertVersion(version)

    /**
     * Elimina un registro de versión específico.
     */
    override suspend fun deleteVersion(version: VersionEntity): Int = apiService.deleteVersion(version)

    /**
     * Limpia la tabla de versiones por completo.
     */
    override suspend fun deleteAll(): Int = apiService.deleteAll()
}