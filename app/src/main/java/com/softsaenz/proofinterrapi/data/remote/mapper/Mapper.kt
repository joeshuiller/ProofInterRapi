package com.softsaenz.proofinterrapi.data.remote.mapper

/**
 * Contrato genérico para la transformación de modelos entre diferentes capas de la arquitectura.
 *
 * Esta interfaz facilita el desacoplamiento al obligar a las implementaciones a definir
 * cómo un objeto de una capa (ej. Red) se convierte en uno de otra (ej. Persistencia).
 *
 * @param D Representa el modelo de Datos o Respuesta (Data/Response).
 * @param E Representa el modelo de Entidad o Dominio (Entity/Domain).
 * @param T Representa el modelo de Destino para tipos secundarios (Target).
 * @param R Representa el modelo de Respuesta para tipos secundarios (Response).
 */
interface Mapper<D, E, T, R> {

    /**
     * Transforma un objeto de la capa de persistencia/dominio hacia la capa de datos/red.
     */
    fun fromEntity(data: E): D

    /**
     * Transforma un objeto de la capa de datos/red hacia la capa de persistencia/dominio.
     */
    fun toEntity(data: D): E

    /**
     * Realiza un mapeo unidireccional desde una respuesta externa hacia un modelo local.
     */
    fun toResponse(data: R): T
}