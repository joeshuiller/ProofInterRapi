package com.softsaenz.proofinterrapi.domain.use_case

import com.softsaenz.proofinterrapi.BuildConfig
import com.softsaenz.proofinterrapi.data.remote.dtos.response.TablesResponse
import com.softsaenz.proofinterrapi.data.remote.utilsApi.Resource
import com.softsaenz.proofinterrapi.domain.repository.RemoteDataRepository
import com.softsaenz.proofinterrapi.ui.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
/**
 * Caso de Uso encargado de recuperar el esquema de tablas desde una fuente de datos remota.
 *
 * Actúa como mediador entre el [RemoteDataRepository] y la UI, transformando la respuesta
 * técnica en estados de [Resource] que la interfaz puede interpretar fácilmente.
 *
 * ### Flujo de Operación:
 * 1. **Carga ([Resource.Loading]):** Se notifica a la UI que la petición de red ha comenzado.
 * 2. **Sincronización:** Recolecta los datos del repositorio y los propaga hacia arriba.
 * 3. **Seguridad ([Resource.Error]):** En caso de fallo de red o error de API, mapea la
 * excepción a un mensaje genérico para evitar la fuga de información sensible (PII).
 *
 * @author Janes Saenz Puerta
 * @property repository Repositorio encargado de la comunicación con el servicio remoto.
 * @return Un [Flow] que emite la lista de [TablesResponse] encapsulada en un estado [Resource].
 */
class GetSchemaUseCase @Inject constructor(
    private val repository: RemoteDataRepository
) {
    operator fun invoke(): Flow<Resource<List<TablesResponse?>>> = flow {
        try {
            // 1. Emitir estado de carga para avisar a la UI
            emit(Resource.Loading())
            // 3. Consumo del repositorio y recolección de su flujo
            repository.getSchema().collect { result ->
                emit(result)
            }

        } catch (e: Exception) {
            // 4. Captura de errores técnicos (Vulnerabilidad 10062)
            // Se transforma la excepción en un mensaje amigable para el usuario.
            val errorDescription = when (e) {
                is java.io.IOException -> Constants.IOEXCEPTION
                is retrofit2.HttpException -> "Error en el servicio (${e.code()})."
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