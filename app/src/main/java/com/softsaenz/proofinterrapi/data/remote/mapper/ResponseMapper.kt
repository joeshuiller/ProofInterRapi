package com.softsaenz.proofinterrapi.data.remote.mapper

import com.google.gson.annotations.SerializedName
import com.softsaenz.proofinterrapi.data.remote.dtos.response.LocationsResponse
import com.softsaenz.proofinterrapi.data.remote.dtos.response.TablesResponse
import com.softsaenz.proofinterrapi.domain.dto.LocationsDTO
import com.softsaenz.proofinterrapi.domain.dto.TablesDTO
import javax.inject.Inject

class ResponseMapper @Inject constructor() : Mapper<TablesResponse, TablesDTO, LocationsDTO, LocationsResponse> {
    override fun fromEntity(data: TablesDTO): TablesResponse {
        return TablesResponse(
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

    override fun toEntity(data: TablesResponse): TablesDTO {
        return TablesDTO(
            name = data.name.toString(),
            pk = data.pk.toString(),
            queryCreation = data.queryCreation.toString(),
            batchSize = data.batchSize.toString(),
            filter = data.filter.toString(),
            error = data.error.toString(),
            numberFields = data.numberFields.toString(),
            methodApp = data.methodApp.toString(),
            syncUpdateDate = data.syncUpdateDate.toString()
        )
    }

    override fun toResponse(data: LocationsResponse): LocationsDTO {
        return LocationsDTO(
            localityId = data.localityId,
            locationTypeId = data.locationTypeId,
            idAncestorPGradod = data.idAncestorPGradod,
            idAncestorSGradod = data.idAncestorSGradod,
            ancestorNameSGrade = data.ancestorNameSGrade,
            idAncestorTGradod = data.idAncestorTGradod,
            ancestorNameTGrade = data.ancestorNameTGrade,
            name = data.name,
            nameCut = data.nameCut,
            ancestorNamePGrade = data.ancestorNamePGrade,
            fullName = data.fullName,
            nameTypeLocation = data.nameTypeLocation,
            assignedZone = data.assignedZone,
            assignedOriginalZone = data.assignedOriginalZone,
            dispoLocality = data.dispoLocality,
            zoneName = data.zoneName,
            postcode = data.postcode,
            indicative = data.indicative,
            serviceCenterID = data.serviceCenterID,
            registrationState = data.registrationState,
            typesLocations = data.typesLocations,
            allowsPickup = data.allowsPickup,
            maxPickupTime = data.maxPickupTime,
            geoReferencing = data.geoReferencing,
            valuePickup = data.valuePickup,
            allowsPreShipments = data.allowsPreShipments,
            deliveryLabel = data.deliveryLabel,
            minPickupTime = data.minPickupTime,
            abbreviationCity = data.abbreviationCity,
        )
    }
}