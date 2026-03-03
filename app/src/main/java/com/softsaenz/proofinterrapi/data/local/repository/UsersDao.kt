package com.softsaenz.proofinterrapi.data.local.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.softsaenz.proofinterrapi.data.local.entity.UsersEntity

@Dao
interface UsersDao {

    @Query("SELECT * FROM users ORDER BY name ASC")
    suspend fun getAllUsers(): List<UsersEntity>

    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getUsersById(id: Int): UsersEntity?

    @Upsert
    suspend fun upsertUsers(users: UsersEntity): Long

    @Delete
    suspend fun deleteUsers(users: UsersEntity): Int

    @Query("DELETE FROM users")
    suspend fun deleteAll(): Int
}