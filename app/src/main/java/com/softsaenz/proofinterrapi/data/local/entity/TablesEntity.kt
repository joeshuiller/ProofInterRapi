package com.softsaenz.proofinterrapi.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tables_query")
data class TablesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name_tabla")
    val name: String?,
    @ColumnInfo(name = "pk")
    val pk: String?,
    @ColumnInfo(name = "query_creation")
    val queryCreation: String?,
    @ColumnInfo(name = "batch_size")
    val batchSize: String?,
    @ColumnInfo(name = "filter")
    val filter: String?,
    @ColumnInfo(name = "error")
    val error: String?,
    @ColumnInfo(name = "number_fields")
    val numberFields: String?,
    @ColumnInfo(name = "method_app")
    val methodApp: String?,
    @ColumnInfo(name = "sync_update_date")
    val syncUpdateDate: String?,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
)