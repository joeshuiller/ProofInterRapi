package com.softsaenz.proofinterrapi.data.local.source

import com.softsaenz.proofinterrapi.data.local.entity.VersionEntity
import com.softsaenz.proofinterrapi.data.local.repository.VersionDao
import javax.inject.Inject

class VersionDataSourceImpl @Inject constructor(
    private val apiService: VersionDao
) : VersionDataSource {
    override fun getAllVersion(): List<VersionEntity> {
        return apiService.getAllVersion()
    }

    override suspend fun getVersionById(id: Int): VersionEntity? {
        return apiService.getVersionById(id)
    }

    override suspend fun upsertVersion(version: VersionEntity): Long {
        val dao = apiService.upsertVersion(version)
        return  dao
    }

    override suspend fun deleteVersion(version: VersionEntity): Int {
        val dao = apiService.deleteVersion(version)
        return  dao
    }

    override suspend fun deleteAll(): Int {
        val dao = apiService.deleteAll()
        return  dao
    }
}