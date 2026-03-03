package com.softsaenz.proofinterrapi.data.local.source

import com.softsaenz.proofinterrapi.data.local.entity.LocationsEntity

/**
 * Fuente de datos local para la gestión de la entidad de ubicaciones.
 *
 * Define las operaciones fundamentales de persistencia sobre la tabla de [LocationsEntity].
 * Proporciona métodos para consultas individuales, masivas y gestión del ciclo de vida
 * de los datos (CRUD).
 * * Todas las funciones son de suspensión ([suspend]), lo que garantiza que las
 * operaciones de disco se ejecuten de forma asíncrona en un hilo de E/S (I/O).
 */
interface LocationsDataSource {

    /**
     * Recupera el listado completo de ubicaciones almacenadas localmente.
     * @return Una lista con todos los objetos [LocationsEntity].
     */
    suspend fun getAllLocations(): List<LocationsEntity>

    /**
     * Busca una ubicación específica mediante su identificador único.
     * @param id Identificador numérico de la ubicación.
     * @return La [LocationsEntity] encontrada o null si no existe el registro.
     */
    suspend fun getLocationsById(id: Int): LocationsEntity?

    /**
     * Inserta una ubicación o la actualiza si ya existe (Upsert).
     * @param locations Entidad de ubicación a persistir.
     * @return El ID de la fila insertada o afectada (Long).
     */
    suspend fun upsertLocations(locations: LocationsEntity): Long

    /**
     * Elimina un registro de ubicación específico de la base de datos.
     * @param locations La entidad completa a eliminar.
     * @return Cantidad de registros eliminados (habitualmente 1).
     */
    suspend fun deleteLocations(locations: LocationsEntity): Int

    /**
     * Realiza una inserción masiva de múltiples ubicaciones en una sola operación.
     * Ideal para procesos de sincronización inicial desde el servidor.
     * @param locations Lista de entidades a persistir.
     */
    suspend fun insertLocations(locations: List<LocationsEntity>)

    /**
     * Elimina de forma definitiva todos los registros de la tabla de ubicaciones.
     * @return Cantidad total de registros eliminados.
     */
    suspend fun deleteAll(): Int
}