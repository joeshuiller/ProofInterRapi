package com.softsaenz.proofinterrapi.data.local.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.softsaenz.proofinterrapi.data.local.entity.VersionEntity

/**
 * Interfaz de Acceso a Datos (DAO) para la entidad [VersionEntity].
 *
 * Define las consultas y comandos SQL necesarios para gestionar el control de
 * versiones local. Room genera la implementación de estos métodos en tiempo
 * de compilación.
 */
@Dao
interface VersionDao {

    /**
     * Recupera todos los registros de la tabla 'version'.
     * Nota: Esta función es síncrona y bloqueante.
     * @return Lista de todas las versiones almacenadas.
     */
    @Query("SELECT * FROM version")
    fun getAllVersion(): List<VersionEntity>

    /**
     * Busca una versión específica por su identificador único.
     * @param id El ID de la versión a consultar.
     * @return La [VersionEntity] encontrada o null si no existe.
     */
    @Query("SELECT * FROM version WHERE id = :id")
    suspend fun getVersionById(id: Int): VersionEntity?

    /**
     * Inserta una versión o la actualiza si ya existe (Upsert).
     * @param version Objeto de versión a persistir.
     * @return El ID de la fila insertada o actualizada.
     */
    @Upsert
    suspend fun upsertVersion(version: VersionEntity): Long

    /**
     * Elimina un registro de versión específico.
     * @param version La entidad a eliminar de la base de datos.
     * @return El número de filas eliminadas.
     */
    @Delete
    suspend fun deleteVersion(version: VersionEntity) : Int

    /**
     * Borra todos los datos de la tabla de versiones (Truncate manual).
     * @return El número total de filas eliminadas.
     */
    @Query("DELETE FROM version")
    suspend fun deleteAll() : Int
}