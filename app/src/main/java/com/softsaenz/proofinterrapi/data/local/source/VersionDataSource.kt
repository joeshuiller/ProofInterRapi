package com.softsaenz.proofinterrapi.data.local.source

import com.softsaenz.proofinterrapi.data.local.entity.VersionEntity

/**
 * Contrato para la fuente de datos encargada de la persistencia de versiones.
 *
 * Define las operaciones CRUD (Crear, Leer, Actualizar, Borrar) sobre la entidad
 * [VersionEntity]. Esta información es vital para el control de migraciones,
 * estados de sincronización y auditoría interna de la aplicación.
 */
interface VersionDataSource {

    /**
     * Recupera el listado completo de registros de versión almacenados.
     * @return Lista de [VersionEntity].
     */
    fun getAllVersion(): List<VersionEntity>

    /**
     * Busca un registro de versión específico por su identificador único.
     * @param id Identificador de la versión.
     * @return La [VersionEntity] encontrada o null si no existe.
     */
    suspend fun getVersionById(id: Int): VersionEntity?

    /**
     * Inserta o actualiza un registro de versión.
     * Si el ID ya existe, se reemplaza la información previa.
     * @param version Entidad de versión a persistir.
     * @return El ID numérico del registro procesado (Long).
     */
    suspend fun upsertVersion(version: VersionEntity): Long

    /**
     * Elimina un registro de versión específico de la base de datos.
     * @param version Entidad a eliminar.
     * @return Cantidad de filas afectadas (habitualmente 1).
     */
    suspend fun deleteVersion(version: VersionEntity): Int

    /**
     * Borra todos los registros de la tabla de versiones.
     * @return Cantidad total de registros eliminados.
     */
    suspend fun deleteAll(): Int
}