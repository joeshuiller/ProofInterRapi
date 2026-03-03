package com.softsaenz.proofinterrapi.domain.use_case

import com.softsaenz.proofinterrapi.BuildConfig
import com.softsaenz.proofinterrapi.data.remote.utilsApi.Resource
import com.softsaenz.proofinterrapi.domain.dto.AppControlDTO
import com.softsaenz.proofinterrapi.domain.repository.RemoteDataRepository
import com.softsaenz.proofinterrapi.ui.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Caso de Uso encargado de obtener los parámetros de control y versión de la aplicación.
 *
 * Actúa como un mediador entre la capa de presentación ([ViewModel]) y el repositorio
 * de datos remotos. Su función principal es proveer la información técnica necesaria
 * para validar la integridad y vigencia de la versión instalada frente al servidor de OVH.
 * * ### Responsabilidades:
 * 1. **Validación de Versión:** Provee los datos para que el [SplashViewModel] decida
 * si la App debe forzar una actualización.
 * 2. **Sincronización Inicial:** Sirve como disparador para comprobar cambios en el
 * esquema de datos o configuraciones de la campaña.
 *
 * @property repository Contrato del repositorio para acceder a la fuente de datos remota.
 * @author Tu Nombre / Equipo Hinchada Homecenter
 */
class AppControlUseCase @Inject constructor(
    private val repository: RemoteDataRepository
) {

    /**
     * Ejecuta la lógica de obtención de control mediante el operador [invoke].
     * * Al ser una operación de solo lectura y delegación directa, retorna el [Flow]
     * generado por el repositorio, permitiendo el manejo reactivo de estados ([Resource]).
     *
     * @return Un flujo que emite:
     * - [Resource.Loading]: Mientras se consulta el VPS.
     * - [Resource.Success]: Si se obtiene el objeto [AppControlDTO] con la versión.
     * - [Resource.Error]: Si hay fallos de conectividad o respuesta del servidor.
     * * @see Resource
     * @see AppControlDTO
     */
    suspend operator fun invoke(): Flow<Resource<AppControlDTO?>> {
        try {
            // 1. Ejecutamos la llamada al repositorio (VPS OVH)
            return repository.storeAppControl()
        } catch (e: Exception) {
            // 2. Captura de errores técnicos (IOException, HttpException, etc.)
            // Nota de Seguridad: No emitir e.message directamente si contiene PII (Vulnerabilidad 10062)
            val errorMessage = when (e) {
                is java.net.UnknownHostException -> Constants.UNKNOWNHOST
                is retrofit2.HttpException -> "Error en el servidor (${e.code()})"
                else -> Constants.GENERIC
            }

            return flow {
                emit(Resource.Error(errorMessage))
            }

            if (BuildConfig.DEBUG) {
                e.printStackTrace() // Solo visible en desarrollo
            }
        }
    }
}