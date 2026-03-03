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

/**
 * Módulo de inyección de dependencias (Hilt) para la capa de datos persistentes.
 *
 * Este objeto proporciona las instancias necesarias para interactuar con la base de datos
 * local [Room]. Al estar instalado en [SingletonComponent], garantiza que la base de datos
 * y sus conexiones se mantengan como una única instancia (Singleton) durante todo el
 * ciclo de vida de la aplicación.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /**
     * Construye y proporciona la instancia única de la base de datos.
     * * @param context Contexto de la aplicación inyectado por Hilt.
     * @return Instancia configurada de [DbData] con el nombre "proof_inter_rapi_db".
     */
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): DbData {
        return Room.databaseBuilder(
            context,
            DbData::class.java,
            "proof_inter_rapi_db"
        ).build()
    }

    /**
     * Provee el objeto de acceso a datos (DAO) para la gestión de usuarios.
     */
    @Provides
    fun provideUsersDao(db: DbData): UsersDao = db.usersDao()

    /**
     * Provee el DAO para la gestión de tablas/favoritos.
     * Nota: Vincula la lógica de favoritos con la entidad de tablas.
     */
    @Provides
    fun provideFavoritesDao(db: DbData): TablesDao = db.tablesDao()

    /**
     * Provee el DAO para la gestión de ubicaciones.
     */
    @Provides
    fun provideFaceDao(db: DbData): LocationsDao = db.locationsDao()

    /**
     * Provee el DAO para el control de versiones de la base de datos o aplicación.
     */
    @Provides
    fun provideVersionDao(db: DbData): VersionDao = db.versionDao()
}