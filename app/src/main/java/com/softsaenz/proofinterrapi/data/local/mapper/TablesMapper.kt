package com.softsaenz.proofinterrapi.data.local.mapper

import com.softsaenz.proofinterrapi.data.local.entity.TablesEntity
import com.softsaenz.proofinterrapi.domain.dto.TablesDTO
import javax.inject.Inject

/**
 * Clase encargada de la transformación de datos para metadatos de tablas y esquemas.
 * * Implementa [MapperDao] para mediar entre:
 * 1. [TablesEntity]: El modelo rígido de Room que vive en SQLite.
 * 2. [TablesDTO]: El modelo flexible que viaja por los Repositorios y UseCases.
 * * Este mapper es vital para el motor de sincronización, ya que gestiona las
 * consultas SQL dinámicas y los parámetros de carga por lotes (Batch).
 */
class TablesMapper @Inject constructor() : MapperDao<TablesEntity, TablesDTO> {

    /**
     * Convierte un [TablesDTO] en una [TablesEntity] para persistencia.
     * Transforma todos los campos a String para asegurar la compatibilidad con
     * el esquema de Room definido para metadatos.
     */
    override fun fromEntity(data: TablesDTO): TablesEntity {
        return TablesEntity(
            name = data.name.toString(),
            pk = data.pk.toString(),
            queryCreation = data.queryCreation.toString(),
            batchSize = data.batchSize.toString(),
            filter = data.filter.toString(),
            error = data.error,
            numberFields = data.numberFields.toString(),
            methodApp = data.methodApp.toString(),
            syncUpdateDate = data.syncUpdateDate
        )
    }

    /**
     * Transforma una [TablesEntity] recuperada de la DB en un [TablesDTO].
     * Recupera metadatos de auditoría como el [id] autogenerado y la fecha [createdAt].
     */
    override fun toEntity(data: TablesEntity): TablesDTO {
        return TablesDTO(
            id = data.id,
            name = data.name,
            pk = data.pk,
            queryCreation = data.queryCreation,
            batchSize = data.batchSize,
            filter = data.filter,
            error = data.error,
            numberFields = data.numberFields,
            methodApp = data.methodApp,
            syncUpdateDate = data.syncUpdateDate.toString(),
            createdAt = data.createdAt,
        )
    }
}