package com.softsaenz.proofinterrapi.data.local.mapper

import com.softsaenz.proofinterrapi.data.local.entity.LocationsEntity
import com.softsaenz.proofinterrapi.domain.dto.LocationsDTO
import javax.inject.Inject

class LocationsMapper @Inject constructor() : MapperDao<LocationsEntity, LocationsDTO>{
    override fun fromEntity(data: LocationsDTO): LocationsEntity {
        return LocationsEntity(
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
            abbreviationCity = data.abbreviationCity
        )
    }
    override fun toEntity(data: LocationsEntity): LocationsDTO {
        return LocationsDTO(
            id = data.id,
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
            createdAt = data.createdAt,
        )
    }

}
