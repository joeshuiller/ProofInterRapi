package com.softsaenz.proofinterrapi.domain.use_case

import com.softsaenz.proofinterrapi.BuildConfig
import com.softsaenz.proofinterrapi.data.local.utilsLocal.ResourceLocal
import com.softsaenz.proofinterrapi.data.remote.dtos.response.LocationsResponse
import com.softsaenz.proofinterrapi.data.remote.dtos.response.TablesResponse
import com.softsaenz.proofinterrapi.data.remote.utilsApi.Resource
import com.softsaenz.proofinterrapi.domain.dto.LocationsDTO
import com.softsaenz.proofinterrapi.domain.repository.LocationsRepository
import com.softsaenz.proofinterrapi.domain.repository.RemoteDataRepository
import com.softsaenz.proofinterrapi.ui.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
/**
 * Caso de Uso encargado de recuperar todas las ubicaciones almacenadas en la base de datos local.
 *
 * Actúa como la fuente de verdad para la interfaz de usuario en modo offline o caché.
 * Proporciona un flujo reactivo que emite estados de carga, éxito o error, facilitando
 * la implementación de Skeletons o Loading Overlays en la vista.
 *
 * ### Gestión de Estados:
 * - **Loading:** Notifica el inicio de la consulta a la base de datos.
 * - **Success:** Emite la lista de [LocationsDTO] recuperada.
 * - **Error:** Maneja fallos de lectura o corrupción de datos mediante mensajes seguros.
 *
 * @author Janes Saenz Puerta
 * @property repository Repositorio de ubicaciones que gestiona el acceso al DAO.
 * @return Un [Flow] de tipo [ResourceLocal] con la colección de ubicaciones.
 */
class DataDBLocationsUseCase @Inject constructor(
    private val repository: LocationsRepository
) {
    operator fun invoke(): Flow<ResourceLocal<List<LocationsDTO>>> = flow {
        try {
            // 1. Emitir estado de carga para avisar a la UI
            emit(ResourceLocal.Loading())
            // 3. Consumo del repositorio y recolección de su flujo
            repository.getAllLocations().collect { result ->
                emit(result)
            }

        } catch (e: Exception) {
            // 4. Captura de errores técnicos (Vulnerabilidad 10062)
            // Se transforma la excepción en un mensaje amigable para el usuario.
            val errorDescription = when (e) {
                is IOException -> Constants.IOEXCEPTION
                is HttpException -> "Error en el servicio (${e.code()})."
                else -> Constants.GENERIC
            }

            emit(ResourceLocal.Error(errorDescription))

            // Registro de logs controlado solo para desarrollo
            if (BuildConfig.DEBUG) {
                e.printStackTrace()
            }
        }
    }
}