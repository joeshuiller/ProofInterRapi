package com.softsaenz.proofinterrapi.data.local.source

import com.softsaenz.proofinterrapi.data.local.entity.LocationsEntity
import com.softsaenz.proofinterrapi.data.local.entity.TablesEntity
import com.softsaenz.proofinterrapi.data.local.repository.LocationsDao
import javax.inject.Inject

/**
 * Implementación concreta de [LocationsDataSource] utilizando Room.
 * * Esta clase sirve como un envoltorio (wrapper) sobre [LocationsDao]. Su principal
 * responsabilidad es delegar las operaciones de persistencia de ubicaciones al DAO
 * generado por Room, asegurando que las llamadas se realicen dentro de un contexto
 * de corrutinas para no bloquear el hilo principal.
 *
 * ### Características:
 * - **Inyección de Dependencias:** Utiliza [Dagger Hilt] para obtener la instancia del DAO.
 * - **Abstracción de Persistencia:** Aísla al resto de la aplicación de los detalles
 * técnicos de la librería Room.
 * * @property apiDao El Objeto de Acceso a Datos (DAO) que maneja las sentencias SQL.
 */
class LocationsDataSourceImpl @Inject constructor(
    private val apiDao: LocationsDao
) : LocationsDataSource {

    /**
     * Recupera todas las ubicaciones de la base de datos de forma asíncrona.
     */
    override suspend fun getAllLocations(): List<LocationsEntity> =
        apiDao.getAllLocations()

    /**
     * Busca una ubicación específica por su identificador único.
     */
    override suspend fun getLocationsById(id: Int): LocationsEntity? =
        apiDao.getLocationsById(id)

    /**
     * Inserta una nueva ubicación o actualiza una existente.
     * @return El ID de la fila afectada.
     */
    override suspend fun upsertLocations(locations: LocationsEntity): Long =
        apiDao.upsertLocations(locations)

    /**
     * Elimina físicamente una ubicación de la tabla.
     */
    override suspend fun deleteLocations(locations: LocationsEntity): Int =
        apiDao.deleteLocations(locations)

    /**
     * Inserta una lista masiva de ubicaciones, optimizando la transacción en la DB.
     */
    override suspend fun insertLocations(locations: List<LocationsEntity>) {
        apiDao.insertLocations(locations)
    }

    /**
     * Limpia la tabla de ubicaciones por completo.
     * @return El número total de registros eliminados.
     */
    override suspend fun deleteAll(): Int =
        apiDao.deleteAll()
}