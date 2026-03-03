package com.softsaenz.proofinterrapi.data.local.mapper

/**
 * Contrato genérico para el mapeo de datos entre la capa de Persistencia (DAO)
 * y la capa de Dominio/Datos (DTO).
 *
 * Esta interfaz asegura que las entidades de base de datos no se filtren hacia
 * las capas superiores, manteniendo la arquitectura limpia y permitiendo
 * cambios en el esquema de la DB sin afectar la lógica de negocio.
 *
 * @param D Representa el Objeto de Transferencia de Datos (DTO) o modelo de Dominio.
 * @param E Representa la Entidad (Entity) específica de Room/SQLite.
 */
interface MapperDao<D, E> {

    /**
     * Transforma una Entidad de base de datos en un objeto de Dominio/DTO.
     * Se utiliza generalmente al LEER datos de la base de datos.
     * @param data La entidad proveniente del DAO.
     * @return El objeto DTO equivalente.
     */
    fun fromEntity(data: E): D

    /**
     * Transforma un objeto de Dominio/DTO en una Entidad de base de datos.
     * Se utiliza generalmente al ESCRIBIR o ACTUALIZAR datos en la base de datos.
     * @param data El objeto DTO que contiene la información.
     * @return La entidad lista para ser procesada por Room.
     */
    fun toEntity(data: D): E
}