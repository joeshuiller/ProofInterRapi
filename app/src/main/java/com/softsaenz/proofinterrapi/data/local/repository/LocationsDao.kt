package com.softsaenz.proofinterrapi.data.local.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.softsaenz.proofinterrapi.data.local.entity.LocationsEntity
import com.softsaenz.proofinterrapi.data.local.entity.TablesEntity
import kotlinx.coroutines.flow.Flow

/**
 * Objeto de Acceso a Datos (DAO) para la entidad [LocationsEntity].
 * * Administra la persistencia de los datos geográficos y logísticos. Proporciona
 * capacidades de ordenamiento alfabético nativo y soporte para sincronización
 * masiva (batch) desde el servidor.
 * * Todas las funciones se ejecutan en el hilo de E/S (I/O) mediante corrutinas.
 */
@Dao
interface LocationsDao {

    /**
     * Recupera todas las ubicaciones ordenadas alfabéticamente (A-Z).
     * Ideal para poblar Spinners, Autocompletes o listas de selección.
     */
    @Query("SELECT * FROM locations ORDER BY name ASC")
    suspend fun getAllLocations(): List<LocationsEntity>

    /**
     * Busca una ubicación específica por su clave primaria.
     * @return [LocationsEntity] o null si el ID no existe localmente.
     */
    @Query("SELECT * FROM locations WHERE id = :id")
    suspend fun getLocationsById(id: Int): LocationsEntity?

    /**
     * Inserta o actualiza una sola ubicación de forma atómica.
     * @return El ID de la fila afectada.
     */
    @Upsert
    suspend fun upsertLocations(locations: LocationsEntity): Long

    /**
     * Elimina un registro de ubicación específico.
     * @return 1 si se eliminó con éxito, 0 en caso contrario.
     */
    @Delete
    suspend fun deleteLocations(locations: LocationsEntity): Int

    /**
     * Vacía la tabla de ubicaciones. Se usa comúnmente antes de una
     * resincronización total o al cerrar la sesión.
     */
    @Query("DELETE FROM locations")
    suspend fun deleteAll(): Int

    /**
     * Realiza una inserción masiva optimizada. Si una ubicación ya existe,
     * se reemplaza con la nueva versión del servidor.
     * @param locations Lista de entidades provenientes del [RemoteDataSource].
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocations(locations: List<LocationsEntity>)
}