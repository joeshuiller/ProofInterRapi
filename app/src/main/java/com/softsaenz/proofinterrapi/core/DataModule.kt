package com.softsaenz.proofinterrapi.core

import com.softsaenz.proofinterrapi.data.local.source.LocationsDataSource
import com.softsaenz.proofinterrapi.data.local.source.LocationsDataSourceImpl
import com.softsaenz.proofinterrapi.data.local.source.TablesDataSource
import com.softsaenz.proofinterrapi.data.local.source.TablesDataSourceImpl
import com.softsaenz.proofinterrapi.data.local.source.UsersDataSource
import com.softsaenz.proofinterrapi.data.local.source.UsersDataSourceImpl
import com.softsaenz.proofinterrapi.data.local.source.VersionDataSource
import com.softsaenz.proofinterrapi.data.local.source.VersionDataSourceImpl
import com.softsaenz.proofinterrapi.data.remote.source.RemoteDataSource
import com.softsaenz.proofinterrapi.data.remote.source.RemoteDataSourceImpl
import com.softsaenz.proofinterrapi.data.repository.LocationsRepositoryImpl
import com.softsaenz.proofinterrapi.data.repository.RemoteDataRepositoryImpl
import com.softsaenz.proofinterrapi.data.repository.TablesRepositoryImpl
import com.softsaenz.proofinterrapi.data.repository.UsersRepositoryImpl
import com.softsaenz.proofinterrapi.data.repository.VersionRepositoryImpl
import com.softsaenz.proofinterrapi.domain.repository.LocationsRepository
import com.softsaenz.proofinterrapi.domain.repository.RemoteDataRepository
import com.softsaenz.proofinterrapi.domain.repository.TablesRepository
import com.softsaenz.proofinterrapi.domain.repository.UsersRepository
import com.softsaenz.proofinterrapi.domain.repository.VersionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
/**
 * Módulo de Hilt encargado de la vinculación (Binding) de abstracciones de datos.
 *
 * Este módulo utiliza la anotación [Binds] para delegar a Hilt la resolución de
 * interfaces hacia sus implementaciones específicas. Al ser una clase abstracta,
 * es más eficiente que un módulo de proveedores estándar, ya que genera menos
 * código en tiempo de compilación.
 *
 * ### Responsabilidades:
 * 1. **Repositiorios:** Vincula las interfaces del Dominio con las implementaciones de Datos.
 * 2. **Data Sources:** Vincula las definiciones de fuentes de datos con su lógica técnica (Local/Remota).
 *
 * Instalado en [SingletonComponent] para asegurar una única instancia global de cada recurso.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindRemoteDataRepository(impl: RemoteDataRepositoryImpl): RemoteDataRepository

    @Binds
    @Singleton
    abstract fun bindVersionRepository(impl: VersionRepositoryImpl): VersionRepository

    @Binds
    @Singleton
    abstract fun bindUsersRepository(impl: UsersRepositoryImpl): UsersRepository

    @Binds
    @Singleton
    abstract fun bindTablesRepository(impl: TablesRepositoryImpl): TablesRepository

    @Binds
    @Singleton
    abstract fun bindLocationsRepository(impl: LocationsRepositoryImpl): LocationsRepository


    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(impl: RemoteDataSourceImpl):
            RemoteDataSource

    @Binds
    @Singleton
    abstract fun bindVersionDataSource(impl: VersionDataSourceImpl):
            VersionDataSource

    @Binds
    @Singleton
    abstract fun bindUsersLocalDataSource(impl: UsersDataSourceImpl):
            UsersDataSource

    @Binds
    @Singleton
    abstract fun bindTablesDataSource(impl: TablesDataSourceImpl):
            TablesDataSource
    @Binds
    @Singleton
    abstract fun bindLocationsDataSource(impl: LocationsDataSourceImpl):
            LocationsDataSource

}