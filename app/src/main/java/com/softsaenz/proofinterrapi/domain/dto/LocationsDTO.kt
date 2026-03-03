package com.softsaenz.proofinterrapi.domain.dto
/**
 * Objeto de Transferencia de Datos (DTO) detallado para la gestión de ubicaciones y logística.
 *
 * Este modelo centraliza la información geográfica, jerárquica y operativa de una localidad.
 * Incluye soporte para niveles de ancestros (P, S, T Grado), estados de asignación de zonas,
 * y parámetros específicos para la gestión de servicios de recolección (Pickup).
 *
 * @property localityId Identificador único de la localidad.
 * @property name Nombre descriptivo de la ubicación.
 * @property fullName Nombre completo incluyendo jerarquía o contexto.
 * @property abbreviationCity Siglas o abreviatura de la ciudad (ej: "BOG", "MED").
 * @property idAncestorPGradod Identificador del ancestro de primer grado (Padre).
 * @property idAncestorSGradod Identificador del ancestro de segundo grado (Abuelo).
 * @property assignedZone Indica si la ubicación tiene una zona operativa asignada.
 * @property valuePickup Costo asociado al servicio de recolección en esta zona.
 * @property allowsPickup Define si la ubicación está habilitada para retiros físicos.
 * @property createdAt Marca de tiempo de registro en la base de datos local.
 */
data class LocationsDTO(
    val id: Int = 0,
    val localityId: String,
    val locationTypeId: String,
    val idAncestorPGradod: String?,
    val idAncestorSGradod: String?,
    val ancestorNameSGrade: String?,
    val idAncestorTGradod: String?,
    val ancestorNameTGrade: String?,
    val name: String,
    val nameCut: String,
    val ancestorNamePGrade: String?,
    val fullName: String,
    val nameTypeLocation: String?,
    val assignedZone: Boolean,
    val assignedOriginalZone: Boolean,
    val dispoLocality: Boolean,
    val zoneName: String?,
    val postcode: String,
    val indicative: String?,
    val serviceCenterID: Int,
    val registrationState: Int,
    val typesLocations: String?,
    val allowsPickup: Boolean?,
    val maxPickupTime: String?,
    val geoReferencing: Boolean,
    val valuePickup: Double,
    val allowsPreShipments: Boolean,
    val deliveryLabel: String?,
    val minPickupTime: Int?,
    val abbreviationCity: String,
    val createdAt: Long = 0L
)
