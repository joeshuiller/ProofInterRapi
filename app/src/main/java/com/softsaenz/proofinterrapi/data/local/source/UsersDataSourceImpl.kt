package com.softsaenz.proofinterrapi.data.local.source

import com.softsaenz.proofinterrapi.data.local.entity.UsersEntity
import com.softsaenz.proofinterrapi.data.local.repository.UsersDao
import javax.inject.Inject

/**
 * Implementación concreta de [UsersDataSource] para la persistencia local de usuarios.
 *
 * Esta clase actúa como un envoltorio (wrapper) sobre [UsersDao], delegando todas
 * las operaciones de base de datos a los métodos generados por Room. Se integra
 * con Hilt mediante inyección de dependencias para asegurar una instancia única
 * y eficiente del DAO.
 *
 * @property apiDao Instancia del DAO de usuarios (inyectada por Hilt).
 */
class UsersDataSourceImpl @Inject constructor(
    private val apiDao: UsersDao
) : UsersDataSource {

    /**
     * Inserta un usuario o lo actualiza si el conflicto de clave primaria existe.
     * @return El ID de la fila afectada.
     */
    override suspend fun upsertUsers(users: UsersEntity): Long = apiDao.upsertUsers(users)

    /**
     * Recupera todos los usuarios almacenados en la tabla local.
     */
    override suspend fun getAllUsers(): List<UsersEntity> = apiDao.getAllUsers()

    /**
     * Busca un usuario específico por su ID.
     */
    override suspend fun getUsersById(id: Int): UsersEntity? = apiDao.getUsersById(id)

    /**
     * Elimina un registro de usuario específico.
     * @return El número de filas eliminadas (habitualmente 1).
     */
    override suspend fun deleteUsers(users: UsersEntity): Int = apiDao.deleteUsers(users)

    /**
     * Borra la totalidad de los registros de la tabla de usuarios.
     * @return El número total de filas eliminadas.
     */
    override suspend fun deleteAll(): Int = apiDao.deleteAll()
}