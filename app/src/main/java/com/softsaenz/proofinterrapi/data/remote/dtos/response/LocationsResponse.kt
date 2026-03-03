package com.softsaenz.proofinterrapi.data.remote.dtos.response

import com.google.gson.annotations.SerializedName

data class LocationsResponse(
    @SerializedName("IdLocalidad")
    val localityId: String,
    @SerializedName("IdTipoLocalidad")
    val locationTypeId: String,
    @SerializedName("IdAncestroPGrado")
    val idAncestorPGradod: String?,
    @SerializedName("IdAncestroSGrado")
    val idAncestorSGradod: String?,
    @SerializedName("NombreAncestroSGrado")
    val ancestorNameSGrade: String?,
    @SerializedName("IdAncestroTGrado")
    val idAncestorTGradod: String?,
    @SerializedName("NombreAncestroTGrado")
    val ancestorNameTGrade: String,
    @SerializedName("Nombre")
    val name: String,
    @SerializedName("NombreCorto")
    val nameCut: String,
    @SerializedName("NombreAncestroPGrado")
    val ancestorNamePGrade: String?,
    @SerializedName("NombreCompleto")
    val fullName: String,
    @SerializedName("NombreTipoLocalidad")
    val nameTypeLocation: String?,
    @SerializedName("AsignadoEnZona")
    val assignedZone: Boolean,
    @SerializedName("AsignadoEnZonaOrig")
    val assignedOriginalZone: Boolean,
    @SerializedName("DispoLocalidad")
    val dispoLocality: Boolean,
    @SerializedName("NombreZona")
    val zoneName: String?,
    @SerializedName("CodigoPostal")
    val postcode: String,
    @SerializedName("Indicativo")
    val indicative: String?,
    @SerializedName("IdCentroServicio")
    val serviceCenterID: Int,
    @SerializedName("EstadoRegistro")
    val registrationState: Int,
    @SerializedName("TiposLocalidades")
    val typesLocations: String?,
    @SerializedName("PermiteRecogida")
    val allowsPickup: Boolean?,
    @SerializedName("HoraMaxRecogida")
    val maxPickupTime: String?,
    @SerializedName("SeGeorreferencia")
    val geoReferencing: Boolean,
    @SerializedName("ValorRecogida")
    val valuePickup: Double,
    @SerializedName("PermitePreEnviosPunto")
    val allowsPreShipments: Boolean,
    @SerializedName("EtiquetaEntrega")
    val deliveryLabel: String?,
    @SerializedName("HoraMinRecogida")
    val minPickupTime: Int?,
    @SerializedName("AbreviacionCiudad")
    val abbreviationCity: String,
)
