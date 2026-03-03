package com.softsaenz.proofinterrapi.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "locations")
data class LocationsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "locality_id")
    val localityId: String,
    @ColumnInfo(name = "location_type_id")
    val locationTypeId: String,
    @ColumnInfo(name = "id_ancestor_pgradod")
    val idAncestorPGradod: String?,
    @ColumnInfo(name = "id_ancestor_sgradod")
    val idAncestorSGradod: String?,
    @ColumnInfo(name = "ancestor_name_sgrade")
    val ancestorNameSGrade: String?,
    @ColumnInfo(name = "id_ancestor_tgradod")
    val idAncestorTGradod: String?,
    @ColumnInfo(name = "ancestor_name_tgrade")
    val ancestorNameTGrade: String?,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "name_cut")
    val nameCut: String,
    @ColumnInfo(name = "ancestor_name_pgrade")
    val ancestorNamePGrade: String?,
    @ColumnInfo(name = "full_name")
    val fullName: String,
    @ColumnInfo(name = "name_type_location")
    val nameTypeLocation: String?,
    @ColumnInfo(name = "assigned_zone")
    val assignedZone: Boolean,
    @ColumnInfo(name = "assigned_original_zone")
    val assignedOriginalZone: Boolean,
    @ColumnInfo(name = "dispo_locality")
    val dispoLocality: Boolean,
    @ColumnInfo(name = "zone_name")
    val zoneName: String?,
    @ColumnInfo(name = "post_code")
    val postcode: String,
    @ColumnInfo(name = "indicative")
    val indicative: String?,
    @ColumnInfo(name = "service_center_id")
    val serviceCenterID: Int,
    @ColumnInfo(name = "registration_state")
    val registrationState: Int,
    @ColumnInfo(name = "types_locations")
    val typesLocations: String?,
    @ColumnInfo(name = "allows_pickup")
    val allowsPickup: Boolean?,
    @ColumnInfo(name = "max_pickup_time")
    val maxPickupTime: String?,
    @ColumnInfo(name = "geo_referencing")
    val geoReferencing: Boolean,
    @ColumnInfo(name = "value_pickup")
    val valuePickup: Double,
    @ColumnInfo(name = "allows_pre_shipments")
    val allowsPreShipments: Boolean,
    @ColumnInfo(name = "delivery_label")
    val deliveryLabel: String?,
    @ColumnInfo(name = "min_pickup_time")
    val minPickupTime: Int?,
    @ColumnInfo(name = "abbreviation_city")
    val abbreviationCity: String,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
)