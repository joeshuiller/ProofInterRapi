package com.softsaenz.proofinterrapi.data.local.source

import com.softsaenz.proofinterrapi.data.local.entity.LocationsEntity
import com.softsaenz.proofinterrapi.data.local.entity.TablesEntity
import com.softsaenz.proofinterrapi.data.local.repository.LocationsDao
import javax.inject.Inject

class LocationsDataSourceImpl @Inject constructor(
    private val apiDao: LocationsDao
) : LocationsDataSource {
    override suspend fun getAllLocations(): List<LocationsEntity> {
        return apiDao.getAllLocations()
    }

    override suspend fun getLocationsById(id: Int): LocationsEntity? {
        return apiDao.getLocationsById(id)
    }

    override suspend fun upsertLocations(locations: LocationsEntity): Long {
        val dao = apiDao.upsertLocations(locations)
        return  dao
    }

    override suspend fun deleteLocations(locations: LocationsEntity): Int {
        val dao = apiDao.deleteLocations(locations)
        return  dao
    }

    override suspend fun insertLocations(locations: List<LocationsEntity>) {
        val dao = apiDao.insertLocations(locations)
        return dao
    }

    override suspend fun deleteAll(): Int {
        val dao = apiDao.deleteAll()
        return  dao
    }
}