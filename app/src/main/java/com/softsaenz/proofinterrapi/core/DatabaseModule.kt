package com.softsaenz.proofinterrapi.core

import android.content.Context
import androidx.room.Room
import com.softsaenz.proofinterrapi.data.local.repository.LocationsDao
import com.softsaenz.proofinterrapi.data.local.repository.TablesDao
import com.softsaenz.proofinterrapi.data.local.repository.UsersDao
import com.softsaenz.proofinterrapi.data.local.repository.VersionDao
import com.softsaenz.proofinterrapi.data.local.source.DbData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): DbData {
        return Room.databaseBuilder(
            context,
            DbData::class.java,
            "proof_inter_rapi_db"
        ).build()
    }

    @Provides
    fun provideUsersDao(db: DbData): UsersDao {
        return db.usersDao()
    }

    @Provides
    fun provideFavoritesDao(db: DbData): TablesDao {
        return db.tablesDao()
    }

    @Provides
    fun provideFaceDao(db: DbData): LocationsDao {
        return db.locationsDao()
    }

    @Provides
    fun provideVersionDao(db: DbData): VersionDao {
        return db.versionDao()
    }


}