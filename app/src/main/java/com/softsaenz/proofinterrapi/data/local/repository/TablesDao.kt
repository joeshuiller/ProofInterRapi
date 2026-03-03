package com.softsaenz.proofinterrapi.data.local.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.softsaenz.proofinterrapi.data.local.entity.TablesEntity

@Dao
interface TablesDao {
    @Upsert
    suspend fun upsertTables(face: TablesEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocations(locations: List<TablesEntity>)

    @Query("SELECT * FROM tables_query WHERE id = :id")
    suspend fun getTablesById(id: Int): TablesEntity?

    @Query("SELECT * FROM tables_query")
    suspend fun getAllTables(): List<TablesEntity>

    @Delete
    suspend fun deleteTables(face: TablesEntity): Int
}