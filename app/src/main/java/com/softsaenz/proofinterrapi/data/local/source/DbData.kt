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

/**
 * Definición central de la base de datos local de la aplicación utilizando Room.
 * * Esta clase abstracta es la encargada de configurar el motor de persistencia,
 * registrando las entidades que componen el esquema y proporcionando los
 * métodos de acceso para los Data Access Objects (DAOs).
 *
 * ### Configuración:
 * - **Entidades:** Gestiona [TablesEntity], [UsersEntity], [LocationsEntity] y [VersionEntity].
 * - **Versión:** Actualmente en la versión 2.
 * - **Exportación:** El esquema no se exporta a archivos externos ([exportSchema] = false).
 *
 * @see RoomDatabase
 */
@Database(
    entities = [
        TablesEntity::class,
        UsersEntity::class,
        LocationsEntity::class,
        VersionEntity::class],
    version = 2,
    exportSchema = false
)
abstract class DbData : RoomDatabase() {

    /** Proveedor de operaciones para la tabla de Usuarios. */
    abstract fun usersDao(): UsersDao

    /** Proveedor de operaciones para la tabla de Metadatos de Tablas. */
    abstract fun tablesDao(): TablesDao

    /** Proveedor de operaciones para la tabla de Ubicaciones Logísticas. */
    abstract fun locationsDao(): LocationsDao

    /** Proveedor de operaciones para el control de versiones de sincronización. */
    abstract fun versionDao(): VersionDao
}