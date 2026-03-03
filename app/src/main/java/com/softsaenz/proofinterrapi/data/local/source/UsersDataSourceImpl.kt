package com.softsaenz.proofinterrapi.data.local.source

import com.softsaenz.proofinterrapi.data.local.entity.UsersEntity
import com.softsaenz.proofinterrapi.data.local.repository.UsersDao
import javax.inject.Inject

class UsersDataSourceImpl @Inject constructor(
    private val apiDao: UsersDao
) : UsersDataSource {


    override suspend fun upsertUsers(users: UsersEntity): Long {
        return apiDao.upsertUsers(users)
    }

    override suspend fun getAllUsers(): List<UsersEntity> {
        return apiDao.getAllUsers()
    }

    override suspend fun getUsersById(id: Int): UsersEntity? {
        return apiDao.getUsersById(id)
    }

    override suspend fun deleteUsers(users: UsersEntity): Int {
        val dao = apiDao.deleteUsers(users)
        return  dao
    }

    override suspend fun deleteAll(): Int {
        val dao = apiDao.deleteAll()
        return  dao
    }
}