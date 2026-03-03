package com.softsaenz.proofinterrapi.data.local.source

import com.softsaenz.proofinterrapi.data.local.entity.TablesEntity
import com.softsaenz.proofinterrapi.data.local.repository.TablesDao
import javax.inject.Inject

/**
 * Implementación concreta de [TablesDataSource] para la persistencia de metadatos de tablas.
 *
 * Esta clase actúa como un puente entre la abstracción de la fuente de datos y
 * el objeto de acceso a datos (DAO) de Room. Se encarga de ejecutar las operaciones
 * de lectura y escritura de forma asíncrona.
 *
 * @property apiDao Instancia del DAO de tablas, inyectada automáticamente por Hilt.
 */
class TablesDataSourceImpl @Inject constructor(
    private val apiDao: TablesDao
) : TablesDataSource {

    /**
     * Inserta una tabla o la actualiza si ya existe en la base de datos.
     * @param table Entidad de la tabla a procesar.
     * @return El ID generado o afectado (Long).
     */
    override suspend fun upsertTables(table: TablesEntity): Long = apiDao.upsertTables(table)

    /**
     * Recupera una tabla específica filtrada por su identificador único.
     */
    override suspend fun getTablesById(id: Int): TablesEntity? = apiDao.getTablesById(id)

    /**
     * Obtiene el listado completo de todas las tablas almacenadas localmente.
     */
    override suspend fun getAllTables(): List<TablesEntity> = apiDao.getAllTables()

    /**
     * Elimina un registro de tabla específico de la base de datos.
     */
    override suspend fun deleteTables(tables: TablesEntity): Int = apiDao.deleteTables(tables)

    /**
     * Realiza una inserción masiva de entidades de tabla.
     * @param tables Lista de entidades a persistir en una sola transacción.
     */
    override suspend fun insertLocations(tables: List<TablesEntity>) {
        apiDao.insertLocations(tables)
    }
}