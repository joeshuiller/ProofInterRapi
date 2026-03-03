package com.softsaenz.proofinterrapi.data.local.source

import com.softsaenz.proofinterrapi.data.local.entity.TablesEntity

interface TablesDataSource {
    suspend fun upsertTables(face: TablesEntity): Long
    suspend fun getTablesById(id: Int): TablesEntity?
    suspend fun getAllTables(): List<TablesEntity>
    suspend fun deleteTables(face: TablesEntity): Int
    suspend fun insertLocations(locations: List<TablesEntity>)
}