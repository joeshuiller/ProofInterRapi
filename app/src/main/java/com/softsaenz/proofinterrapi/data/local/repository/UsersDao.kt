package com.softsaenz.proofinterrapi.data.local.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.softsaenz.proofinterrapi.data.local.entity.UsersEntity

/**
 * Objeto de Acceso a Datos (DAO) para la gestión de la tabla 'users'.
 * * Proporciona los métodos necesarios para persistir, consultar y eliminar
 * la información de los usuarios autenticados en el sistema local.
 * Implementa un ordenamiento alfabético por defecto para facilitar su
 * visualización en listas de selección.
 */
@Dao
interface UsersDao {

    /**
     * Obtiene todos los usuarios registrados, ordenados alfabéticamente por nombre.
     * @return Lista de [UsersEntity] en orden ascendente (A-Z).
     */
    @Query("SELECT * FROM users ORDER BY name ASC")
    suspend fun getAllUsers(): List<UsersEntity>

    /**
     * Recupera un usuario específico mediante su identificador interno.
     * @param id Identificador único de la base de datos.
     * @return El objeto [UsersEntity] o null si no se encuentra.
     */
    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getUsersById(id: Int): UsersEntity?

    /**
     * Inserta un nuevo usuario o actualiza uno existente si hay conflicto de ID.
     * @param users La entidad de usuario a procesar.
     * @return El ID (rowId) del registro insertado o actualizado.
     */
    @Upsert
    suspend fun upsertUsers(users: UsersEntity): Long

    /**
     * Elimina un registro de usuario específico de la tabla.
     * @param users La entidad a borrar.
     * @return El número de filas afectadas (habitualmente 1).
     */
    @Delete
    suspend fun deleteUsers(users: UsersEntity): Int

    /**
     * Opción de "limpieza total": elimina todos los usuarios de la base de datos.
     * @return Cantidad total de registros eliminados.
     */
    @Query("DELETE FROM users")
    suspend fun deleteAll(): Int
}