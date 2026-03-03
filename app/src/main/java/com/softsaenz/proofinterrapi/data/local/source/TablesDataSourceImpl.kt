package com.softsaenz.proofinterrapi.data.local.source

import com.softsaenz.proofinterrapi.data.local.entity.TablesEntity
import com.softsaenz.proofinterrapi.data.local.repository.TablesDao
import javax.inject.Inject

class TablesDataSourceImpl @Inject constructor(
    private val apiDao: TablesDao
) : TablesDataSource{
    override suspend fun upsertTables(table: TablesEntity): Long {
        val dao = apiDao.upsertTables(table)
        return  dao
    }

    override suspend fun getTablesById(id: Int): TablesEntity? {
        return apiDao.getTablesById(id)
    }

    override suspend fun getAllTables(): List<TablesEntity> {
        return apiDao.getAllTables()
    }

    override suspend fun deleteTables(tables: TablesEntity): Int {
        val dao = apiDao.deleteTables(tables)
        return  dao
    }

    override suspend fun insertLocations(tables: List<TablesEntity>) {
        val dao = apiDao.insertLocations(tables)
        return  dao
    }
}