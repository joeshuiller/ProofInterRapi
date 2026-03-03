package com.softsaenz.proofinterrapi.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "version")
data class VersionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val version: String,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
)
