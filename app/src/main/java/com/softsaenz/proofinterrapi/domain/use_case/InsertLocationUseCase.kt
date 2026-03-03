package com.softsaenz.proofinterrapi.domain.use_case

import com.softsaenz.proofinterrapi.domain.dto.LocationsDTO
import com.softsaenz.proofinterrapi.domain.repository.LocationsRepository
import timber.log.Timber
import javax.inject.Inject
/**
 * Caso de Uso para la inserción o actualización (Upsert) de una ubicación específica.
 *
 * Actúa como puente entre la lógica de interfaz y el [LocationsRepository].
 * Utiliza un flujo de datos ([Flow]) para emitir el estado de la operación, permitiendo
 * que la UI reaccione de forma asíncrona a los cambios en la capa de datos.
 *
 * ### Características Principales:
 * - **Logging Seguro:** Utiliza [Timber] para trazabilidad en capas de depuración.
 * - **Gestión de Estados:** Envuelve el resultado en [ResourceLocal] para manejar la carga y los errores.
 * - **Aislamiento de Hilos:** Forzado a ejecutarse en [Dispatchers.IO] para proteger el hilo de UI.
 *
 * @author Janes Saenz Puerta
 * @param location El objeto [LocationsDTO] con la información de la ubicación a persistir.
 * @return Long.
 */
class InsertLocationUseCase @Inject constructor(
    private val repository: LocationsRepository
) {
    suspend operator fun invoke(table: LocationsDTO): Long {
        Timber.e("table de red: $table")
        return  repository.upsertLocations(table)
    }
}