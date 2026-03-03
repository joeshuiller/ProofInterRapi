package com.softsaenz.proofinterrapi.data.local.source

import com.softsaenz.proofinterrapi.data.local.entity.UsersEntity


/**
 * Fuente de datos local para la gestión de usuarios.
 *
 * Define las operaciones fundamentales de persistencia sobre la tabla de usuarios.
 * Todas las funciones son de suspensión ([suspend]), lo que garantiza que las
 * operaciones de entrada/salida (I/O) en la base de datos no bloqueen el hilo
 * principal de la aplicación.
 */
interface UsersDataSource {

    /**
     * Inserta un usuario en la base de datos o lo actualiza si ya existe (Upsert).
     * @param users Entidad de usuario a persistir.
     * @return El ID de la fila insertada o el número de filas afectadas.
     */
    suspend fun upsertUsers(users: UsersEntity): Long

    /**
     * Obtiene el listado completo de usuarios registrados localmente.
     * @return Una lista con todos los objetos [UsersEntity].
     */
    suspend fun getAllUsers(): List<UsersEntity>

    /**
     * Busca un usuario específico mediante su identificador único.
     * @param id Identificador numérico del usuario.
     * @return La [UsersEntity] encontrada o null si no existe el registro.
     */
    suspend fun getUsersById(id: Int): UsersEntity?

    /**
     * Elimina un registro de usuario específico de la persistencia.
     * @param users La entidad completa a eliminar.
     * @return Cantidad de registros eliminados (habitualmente 1).
     */
    suspend fun deleteUsers(users: UsersEntity): Int

    /**
     * Elimina de forma definitiva todos los registros de la tabla de usuarios.
     * @return Cantidad total de registros eliminados.
     */
    suspend fun deleteAll(): Int
}