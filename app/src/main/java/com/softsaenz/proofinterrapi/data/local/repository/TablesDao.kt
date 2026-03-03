package com.softsaenz.proofinterrapi.data.local.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.softsaenz.proofinterrapi.data.local.entity.TablesEntity

/**
 * Objeto de Acceso a Datos (DAO) para la gestión de la tabla 'tables_query'.
 * * Esta interfaz define las operaciones necesarias para administrar los metadatos
 * de sincronización y esquemas de tablas. Es una pieza fundamental para la
 * arquitectura de "esquema dinámico" de la aplicación.
 * * Proporciona métodos para inserción masiva, actualización inteligente y
 * consultas de metadatos específicos.
 */
@Dao
interface TablesDao {

    /**
     * Realiza un "Insert or Update" atómico de un registro de tabla.
     * @param face Objeto [TablesEntity] con la definición de la tabla.
     * @return El ID de la fila afectada.
     */
    @Upsert
    suspend fun upsertTables(face: TablesEntity): Long

    /**
     * Inserta una lista masiva de definiciones de tablas.
     * Si hay conflictos, reemplaza el registro existente.
     * @param locations Lista de entidades a persistir.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocations(locations: List<TablesEntity>)

    /**
     * Recupera una definición de tabla específica por su identificador.
     */
    @Query("SELECT * FROM tables_query WHERE id = :id")
    suspend fun getTablesById(id: Int): TablesEntity?

    /**
     * Obtiene el catálogo completo de consultas y metadatos de tablas.
     */
    @Query("SELECT * FROM tables_query")
    suspend fun getAllTables(): List<TablesEntity>

    /**
     * Elimina un registro de tabla específico.
     * @return Cantidad de registros eliminados.
     */
    @Delete
    suspend fun deleteTables(face: TablesEntity): Int
}