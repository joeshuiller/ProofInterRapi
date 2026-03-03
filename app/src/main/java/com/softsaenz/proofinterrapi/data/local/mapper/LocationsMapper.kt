package com.softsaenz.proofinterrapi.data.local.mapper

import com.softsaenz.proofinterrapi.data.local.entity.LocationsEntity
import com.softsaenz.proofinterrapi.domain.dto.LocationsDTO
import javax.inject.Inject
/**
 * Mapper especializado en la transformación de datos de ubicación y logística.
 * * Implementa [MapperDao] para gestionar la conversión entre:
 * 1. [LocationsEntity]: El modelo de persistencia local (Room).
 * 2. [LocationsDTO]: El objeto de transporte de datos utilizado en Repositorios y ViewModels.
 * * Esta clase es responsable de mapear la jerarquía de ancestros (niveles geográficos),
 * las reglas de negocio para recogidas y los metadatos de zonificación.
 * * @constructor Crea una instancia de [LocationsMapper] mediante inyección de dependencias.
 */
class LocationsMapper @Inject constructor() : MapperDao<LocationsEntity, LocationsDTO>{
    /**
     * Transforma un objeto de datos [LocationsDTO] en una entidad de persistencia [LocationsEntity].
     * Se utiliza para preparar los datos antes de guardarlos en SQLite.
     */
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
    /**
     * Transforma una entidad [LocationsEntity] recuperada de la base de datos en un [LocationsDTO].
     * Incluye metadatos de auditoría como el [id] interno y la fecha de creación [createdAt].
     */
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
