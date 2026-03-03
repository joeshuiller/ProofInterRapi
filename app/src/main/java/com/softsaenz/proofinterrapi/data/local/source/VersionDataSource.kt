package com.softsaenz.proofinterrapi.data.local.source

import com.softsaenz.proofinterrapi.data.local.entity.VersionEntity

interface VersionDataSource {
    fun getAllVersion(): List<VersionEntity>
    suspend fun getVersionById(id: Int): VersionEntity?
    suspend fun upsertVersion(version: VersionEntity): Long
    suspend fun deleteVersion(version: VersionEntity): Int
    suspend fun deleteAll(): Int
}