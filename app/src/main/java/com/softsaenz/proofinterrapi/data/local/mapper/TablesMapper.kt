package com.softsaenz.proofinterrapi.data.local.mapper

import com.softsaenz.proofinterrapi.data.local.entity.TablesEntity
import com.softsaenz.proofinterrapi.domain.dto.TablesDTO
import javax.inject.Inject

class TablesMapper @Inject constructor() : MapperDao<TablesEntity, TablesDTO>{
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