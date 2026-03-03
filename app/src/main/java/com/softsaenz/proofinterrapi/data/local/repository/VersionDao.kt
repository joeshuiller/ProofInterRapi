package com.softsaenz.proofinterrapi.data.local.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.softsaenz.proofinterrapi.data.local.entity.VersionEntity

@Dao
interface VersionDao {

    @Query("SELECT * FROM version")
    fun getAllVersion(): List<VersionEntity>

    @Query("SELECT * FROM version WHERE id = :id")
    suspend fun getVersionById(id: Int): VersionEntity?

    @Upsert
    suspend fun upsertVersion(version: VersionEntity): Long

    @Delete
    suspend fun deleteVersion(version: VersionEntity) : Int

    @Query("DELETE FROM version")
    suspend fun deleteAll() : Int
}