package com.softsaenz.proofinterrapi.data.local.source

import com.softsaenz.proofinterrapi.data.local.entity.LocationsEntity

interface LocationsDataSource {
    suspend fun getAllLocations(): List<LocationsEntity>
    suspend fun getLocationsById(id: Int): LocationsEntity?
    suspend fun upsertLocations(locations: LocationsEntity): Long
    suspend fun deleteLocations(locations: LocationsEntity): Int
    suspend fun insertLocations(locations: List<LocationsEntity>)
    suspend fun deleteAll(): Int
}