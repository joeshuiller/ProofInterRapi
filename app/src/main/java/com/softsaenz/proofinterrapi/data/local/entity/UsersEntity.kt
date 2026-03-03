package com.softsaenz.proofinterrapi.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidad de persistencia que representa a un usuario en la base de datos local.
 * * Esta clase define la tabla 'users', la cual almacena el perfil del usuario
 * autenticado. Se utiliza para mantener la sesión activa y mostrar información
 * personalizada en la interfaz de usuario sin necesidad de consultas constantes
 * al servidor.
 *
 * @property id Identificador único autoincremental (Clave Primaria).
 * @property users El nombre de cuenta o alias del usuario (proveniente de la API).
 * @property name Nombre completo o descriptivo del usuario.
 * @property identification Documento de identidad o número de registro.
 * @property createdAt Fecha y hora de creación del registro local (Auditoría).
 */
@Entity(tableName = "users")
data class UsersEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val users: String?,
    val name: String?,
    val identification: String?,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
)