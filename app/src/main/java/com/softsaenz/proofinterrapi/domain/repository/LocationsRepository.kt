package com.softsaenz.proofinterrapi.domain.repository

import com.softsaenz.proofinterrapi.data.local.utilsLocal.ResourceLocal
import com.softsaenz.proofinterrapi.domain.dto.LocationsDTO
import kotlinx.coroutines.flow.Flow

/**
 * Repositorio de abstracción para la gestión de ubicaciones geográficas o físicas.
 *
 * Define las operaciones CRUD para la entidad [LocationsDTO]. Proporciona una interfaz
 * reactiva mediante [Flow], permitiendo que la capa de dominio observe cambios en las
 * ubicaciones de forma asíncrona y segura.
 */
interface LocationsRepository {

    /**
     * Recupera el listado completo de ubicaciones almacenadas.
     * @return Un [Flow] que emite estados de [ResourceLocal] con la lista de ubicaciones.
     */
    suspend fun getAllLocations(): Flow<ResourceLocal<List<LocationsDTO>>>

    /**
     * Obtiene una ubicación específica mediante su identificador.
     * @param id Identificador único de la ubicación.
     * @return Un [Flow] con el resultado envuelto en [ResourceLocal].
     */
    suspend fun getLocationsById(id: Int): Flow<ResourceLocal<LocationsDTO?>>

    /**
     * Inserta una nueva ubicación o actualiza una existente (Upsert).
     * @param location Objeto de datos de la ubicación a persistir.
     * @return El ID del registro insertado o afectado.
     */
    suspend fun upsertLocations(location: LocationsDTO): Long

    /**
     * Elimina una ubicación específica de la base de datos.
     * @param locations El objeto DTO a eliminar.
     * @return El número de registros eliminados (típicamente 1).
     */
    suspend fun deleteLocations(locations: LocationsDTO): Int

    /**
     * Borra permanentemente todas las ubicaciones de la base de datos local.
     * @return El total de registros eliminados.
     */
    suspend fun deleteAll(): Int
}