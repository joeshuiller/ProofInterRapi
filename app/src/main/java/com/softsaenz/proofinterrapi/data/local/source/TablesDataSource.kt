package com.softsaenz.proofinterrapi.data.local.source

import com.softsaenz.proofinterrapi.data.local.entity.TablesEntity

/**
 * Fuente de datos local para la gestión de la estructura y metadatos de tablas.
 *
 * Esta interfaz define las operaciones de persistencia necesarias para administrar
 * las entidades [TablesEntity]. Se utiliza principalmente para el control de
 * sincronización dinámica y el almacenamiento de esquemas de base de datos.
 *
 * Todas las funciones son de suspensión ([suspend]), asegurando que las operaciones
 * de disco se ejecuten de forma asíncrona.
 */
interface TablesDataSource {

    /**
     * Inserta o actualiza una entrada de tabla en la base de datos.
     * @param face Entidad de la tabla a persistir.
     * @return El ID de la fila procesada.
     */
    suspend fun upsertTables(face: TablesEntity): Long

    /**
     * Recupera una tabla específica mediante su identificador único.
     * @param id Identificador de la tabla.
     * @return La [TablesEntity] encontrada o null si no existe.
     */
    suspend fun getTablesById(id: Int): TablesEntity?

    /**
     * Obtiene el listado completo de tablas registradas localmente.
     * @return Lista de todas las entidades [TablesEntity].
     */
    suspend fun getAllTables(): List<TablesEntity>

    /**
     * Elimina un registro de tabla específico de la persistencia.
     * @param face Entidad a eliminar.
     * @return Cantidad de registros afectados.
     */
    suspend fun deleteTables(face: TablesEntity): Int

    /**
     * Realiza una inserción masiva de registros de tablas.
     * @param locations Lista de entidades [TablesEntity] a insertar.
     */
    suspend fun insertLocations(locations: List<TablesEntity>)
}