package com.softsaenz.proofinterrapi.data.local.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.softsaenz.proofinterrapi.data.local.entity.LocationsEntity
import com.softsaenz.proofinterrapi.data.local.entity.TablesEntity
import com.softsaenz.proofinterrapi.data.local.entity.UsersEntity
import com.softsaenz.proofinterrapi.data.local.entity.VersionEntity
import com.softsaenz.proofinterrapi.data.local.repository.LocationsDao
import com.softsaenz.proofinterrapi.data.local.repository.TablesDao
import com.softsaenz.proofinterrapi.data.local.repository.UsersDao
import com.softsaenz.proofinterrapi.data.local.repository.VersionDao

@Database(
    entities = [
        TablesEntity::class,
        UsersEntity::class,
        LocationsEntity::class,
        VersionEntity::class],
    version = 2,
    exportSchema = false
)
abstract class DbData: RoomDatabase() {
    abstract fun usersDao(): UsersDao
    abstract fun tablesDao():TablesDao
    abstract fun locationsDao():LocationsDao
    abstract fun versionDao():VersionDao
}