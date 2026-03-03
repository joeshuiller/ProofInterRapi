package com.softsaenz.proofinterrapi.data.local.source

import com.softsaenz.proofinterrapi.data.local.entity.UsersEntity


interface UsersDataSource {
    suspend fun upsertUsers(users: UsersEntity): Long
    suspend fun getAllUsers(): List<UsersEntity>
    suspend fun getUsersById(id: Int): UsersEntity?
    suspend fun deleteUsers(users: UsersEntity): Int
    suspend fun deleteAll(): Int
}