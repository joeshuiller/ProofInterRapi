package com.softsaenz.proofinterrapi.domain.repository

import com.softsaenz.proofinterrapi.data.local.utilsLocal.ResourceLocal
import com.softsaenz.proofinterrapi.domain.dto.UsersDTO
import kotlinx.coroutines.flow.Flow

/**
 * Repositorio de abstracción para la gestión de persistencia de usuarios.
 *
 * Proporciona los métodos necesarios para realizar operaciones CRUD sobre la entidad [UsersDTO].
 * El uso de [Flow] permite que las capas superiores observen los cambios en los datos de usuario
 * en tiempo real, mientras que [ResourceLocal] garantiza un manejo consistente de los estados
 * de la base de datos (Carga, Éxito y Error).
 */
interface UsersRepository {

    /**
     * Inserta un nuevo usuario o actualiza uno existente.
     * @param users Datos del usuario a persistir.
     * @return Un [Flow] que emite el estado de la operación y el ID resultante.
     */
    suspend fun upsertUsers(users: UsersDTO): Flow<ResourceLocal<Long>>

    /**
     * Recupera la lista completa de usuarios registrados.
     * @return Un [Flow] con la colección de [UsersDTO].
     */
    suspend fun getAllUsers(): Flow<ResourceLocal<List<UsersDTO>>>

    /**
     * Obtiene un usuario específico basado en su ID único.
     * @param id Identificador del usuario.
     * @return Un [Flow] que emite el usuario encontrado o nulo.
     */
    suspend fun getUsersById(id: Int): Flow<ResourceLocal<UsersDTO?>>

    /**
     * Elimina un registro de usuario específico.
     * @param users El objeto usuario a eliminar.
     * @return El número de filas afectadas en la base de datos.
     */
    suspend fun deleteUsers(users: UsersDTO): Int

    /**
     * Vacía la tabla de usuarios por completo.
     * @return El número total de registros eliminados.
     */
    suspend fun deleteAll(): Int
}