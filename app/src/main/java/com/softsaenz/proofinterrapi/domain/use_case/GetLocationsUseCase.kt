package com.softsaenz.proofinterrapi.domain.use_case

import com.softsaenz.proofinterrapi.BuildConfig
import com.softsaenz.proofinterrapi.data.remote.dtos.response.LocationsResponse
import com.softsaenz.proofinterrapi.data.remote.dtos.response.TablesResponse
import com.softsaenz.proofinterrapi.data.remote.utilsApi.Resource
import com.softsaenz.proofinterrapi.domain.repository.RemoteDataRepository
import com.softsaenz.proofinterrapi.ui.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
/**
 * Caso de Uso encargado de recuperar el listado de ubicaciones recolectadas desde el servidor.
 *
 * Actúa como un puente entre el [RemoteDataRepository] y la capa de presentación.
 * Gestiona el ciclo de vida de la petición mediante estados [Resource], asegurando que
 * la UI reciba una notificación de carga antes de intentar obtener los datos.
 *
 * ### Gestión de Errores y Seguridad:
 * - Implementa una política de "Zero Leak" para errores técnicos, cumpliendo con la
 * directiva de seguridad contra la vulnerabilidad 10062 (evitar exposición de PII).
 * - Mapea excepciones de red y HTTP a mensajes amigables predefinidos en [Constants].
 *
 * @author Janes Saenz Puerta
 * @property repository Repositorio de datos remotos para el acceso a la red.
 * @return Un [Flow] reactivo que emite estados de [Resource.Loading], [Resource.Success] o [Resource.Error].
 */
class GetLocationsUseCase @Inject constructor(
    private val repository: RemoteDataRepository
) {
    operator fun invoke(): Flow<Resource<List<LocationsResponse?>>> = flow {
        try {
            // 1. Emitir estado de carga para avisar a la UI
            emit(Resource.Loading())
            // 3. Consumo del repositorio y recolección de su flujo
            repository.obtainCollectedLocations().collect { result ->
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

            emit(Resource.Error(errorDescription))

            // Registro de logs controlado solo para desarrollo
            if (BuildConfig.DEBUG) {
                e.printStackTrace()
            }
        }
    }
}