package com.softsaenz.proofinterrapi.domain.use_case

import com.softsaenz.proofinterrapi.BuildConfig
import com.softsaenz.proofinterrapi.data.local.utilsLocal.ResourceLocal
import com.softsaenz.proofinterrapi.domain.dto.VersionDTO
import com.softsaenz.proofinterrapi.domain.repository.VersionRepository
import com.softsaenz.proofinterrapi.ui.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject
/**
 * Caso de Uso encargado de recuperar el historial completo de versiones desde el almacenamiento local.
 *
 * Actúa como un mediador entre el [VersionRepository] y el ViewModel. Proporciona un flujo
 * de datos reactivo que emite una lista de [VersionDTO] envuelta en un estado [ResourceLocal].
 *
 * ### Características:
 * - **Reactividad:** Al devolver un [Flow], la UI se actualizará automáticamente si los datos
 * en la base de datos cambian (si el DAO está configurado para observar cambios).
 * - **Seguridad de Datos:** Filtra y mapea excepciones técnicas para evitar la exposición
 * de detalles internos del sistema o errores de red crudos.
 *
 * @author Janes Saenz Puerta
 * @return Un [Flow] que emite estados de [ResourceLocal.Success] con la lista de versiones
 * o [ResourceLocal.Error] en caso de falla.
 */
class VersionGetUseCase @Inject constructor(
    private val repository: VersionRepository
) {
    operator fun invoke(): Flow<ResourceLocal<List<VersionDTO>>> {
        try {
            // 1. Ejecutamos la llamada al repositorio DAO
            val data = repository.getAllVersion()
            return data
        } catch (e: Exception) {
            // 2. Captura de errores técnicos (IOException, HttpException, etc.)
            // Nota de Seguridad: No emitir e.message directamente si contiene PII (Vulnerabilidad 10062)
            val errorMessage = when (e) {
                is UnknownHostException -> Constants.UNKNOWNHOST
                is HttpException -> "Error en el servidor (${e.code()})"
                else -> Constants.GENERIC
            }

            return flow {
                emit(ResourceLocal.Error(errorMessage))
            }

            if (BuildConfig.DEBUG) {
                e.printStackTrace() // Solo visible en desarrollo
            }
        }
    }
}