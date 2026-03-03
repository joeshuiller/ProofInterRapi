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

@Dao
interface LocationsDao {
    @Query("SELECT * FROM locations ORDER BY name ASC")
    suspend fun getAllLocations(): List<LocationsEntity>

    @Query("SELECT * FROM locations WHERE id = :id")
    suspend fun getLocationsById(id: Int): LocationsEntity?

    @Upsert
    suspend fun upsertLocations(locations: LocationsEntity): Long

    @Delete
    suspend fun deleteLocations(locations: LocationsEntity): Int

    @Query("DELETE FROM locations")
    suspend fun deleteAll(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocations(locations: List<LocationsEntity>)
}