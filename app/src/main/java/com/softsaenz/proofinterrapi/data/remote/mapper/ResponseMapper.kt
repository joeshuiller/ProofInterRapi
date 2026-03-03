package com.softsaenz.proofinterrapi.data.remote.mapper

import com.google.gson.annotations.SerializedName
import com.softsaenz.proofinterrapi.data.remote.dtos.response.LocationsResponse
import com.softsaenz.proofinterrapi.data.remote.dtos.response.TablesResponse
import com.softsaenz.proofinterrapi.domain.dto.LocationsDTO
import com.softsaenz.proofinterrapi.domain.dto.TablesDTO
import javax.inject.Inject
/**
 * Clase encargada de la transformación de modelos entre la capa de Red y la capa de Datos/Dominio.
 * * Implementa la interfaz genérica [Mapper] para centralizar la lógica de conversión de:
 * 1. Metadatos de Tablas ([TablesResponse] <-> [TablesDTO]).
 * 2. Información de Ubicaciones ([LocationsResponse] -> [LocationsDTO]).
 * * Esta clase es fundamental para mantener el desacoplamiento: si la API cambia un nombre
 * de campo, solo se debe modificar este Mapper y no toda la lógica de negocio.
 */
class ResponseMapper @Inject constructor() : Mapper<TablesResponse, TablesDTO, LocationsDTO, LocationsResponse> {
    /**
     * Convierte un objeto de dominio local en un modelo de respuesta (preparación para envío).
     * @param data El DTO proveniente de la base de datos o lógica interna.
     * @return Un objeto [TablesResponse] listo para ser procesado o simulado como respuesta.
     */
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
    /**
     * Transforma la respuesta de la API en un objeto de persistencia local.
     * @param data La respuesta cruda obtenida del servidor.
     * @return Un [TablesDTO] configurado para ser almacenado en Room.
     */
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
    /**
     * Mapea la respuesta detallada de ubicaciones al modelo de datos local.
     * @param data Respuesta de red con la información geográfica y logística.
     * @return Un [LocationsDTO] con todos los campos validados y listos para persistir.
     */
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