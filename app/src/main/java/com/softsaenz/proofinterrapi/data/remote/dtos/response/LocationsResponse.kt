package com.softsaenz.proofinterrapi.data.remote.dtos.response

import com.google.gson.annotations.SerializedName

/**
 * Modelo de respuesta para la entidad de Ubicaciones (Locations) proveniente de la API.
 * * Esta clase actúa como el contrato de recepción de datos de red. Contiene información
 * detallada sobre geografía, jerarquías de ancestros (padres, abuelos), reglas de
 * negocio para recolecciones (pickups) y metadatos de zonificación logística.
 * * Utiliza [SerializedName] para asegurar que el mapeo desde el JSON sea robusto,
 * independientemente del nombre de la propiedad en el código fuente de Kotlin.
 */
data class LocationsResponse(
    // Identificadores y Nombres básicos
    @SerializedName("IdLocalidad") val localityId: String,
    @SerializedName("Nombre") val name: String,
    @SerializedName("NombreCorto") val nameCut: String,
    @SerializedName("NombreCompleto") val fullName: String,
    @SerializedName("AbreviacionCiudad") val abbreviationCity: String,

    // Jerarquía de Ancestros (Niveles Geográficos)
    @SerializedName("IdAncestroPGrado") val idAncestorPGradod: String?,
    @SerializedName("NombreAncestroPGrado") val ancestorNamePGrade: String?,
    @SerializedName("IdAncestroSGrado") val idAncestorSGradod: String?,
    @SerializedName("NombreAncestroSGrado") val ancestorNameSGrade: String?,
    @SerializedName("IdAncestroTGrado") val idAncestorTGradod: String?,
    @SerializedName("NombreAncestroTGrado") val ancestorNameTGrade: String,

    // Logística y Zonificación
    @SerializedName("AsignadoEnZona") val assignedZone: Boolean,
    @SerializedName("NombreZona") val zoneName: String?,
    @SerializedName("IdCentroServicio") val serviceCenterID: Int,
    @SerializedName("CodigoPostal") val postcode: String,

    // Configuración de Pickup (Recogida)
    @SerializedName("PermiteRecogida") val allowsPickup: Boolean?,
    @SerializedName("HoraMinRecogida") val minPickupTime: Int?,
    @SerializedName("HoraMaxRecogida") val maxPickupTime: String?,
    @SerializedName("ValorRecogida") val valuePickup: Double,

    // Otros Flags de Operación
    @SerializedName("SeGeorreferencia") val geoReferencing: Boolean,
    @SerializedName("PermitePreEnviosPunto") val allowsPreShipments: Boolean,
    @SerializedName("EstadoRegistro") val registrationState: Int,
    // ... otros campos incluidos en la clase

    @SerializedName("IdTipoLocalidad") val locationTypeId: String,
    @SerializedName("NombreTipoLocalidad") val nameTypeLocation: String?,
    @SerializedName("AsignadoEnZonaOrig") val assignedOriginalZone: Boolean,
    @SerializedName("DispoLocalidad") val dispoLocality: Boolean,
    @SerializedName("Indicativo") val indicative: String?,
    @SerializedName("TiposLocalidades") val typesLocations: String?,
    @SerializedName("EtiquetaEntrega") val deliveryLabel: String?,
)