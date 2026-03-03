package com.softsaenz.proofinterrapi.domain.use_case

import com.softsaenz.proofinterrapi.BuildConfig
import com.softsaenz.proofinterrapi.data.remote.dtos.response.AuthResponse
import com.softsaenz.proofinterrapi.data.remote.utilsApi.Resource
import com.softsaenz.proofinterrapi.domain.dto.AuthDTO
import com.softsaenz.proofinterrapi.domain.repository.RemoteDataRepository
import com.softsaenz.proofinterrapi.ui.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


/**
 * Caso de Uso encargado de orquestar el proceso de autenticación remota.
 *
 * Siguiendo los principios de **Clean Architecture**, este componente actúa como
 * un mediador entre la capa de presentación ([ViewModel]) y la capa de datos ([RemoteDataRepository]).
 *
 * * ### Reglas de Negocio Implementadas:
 * 1. **Validación de Campos:** Verifica que los datos obligatorios no estén vacíos.
 * 2. **Gestión de Excepciones:** Captura fallos de infraestructura (Timeout, 404, No Internet)
 * y los mapea a estados de error controlados.
 *
 * @property repository Contrato del repositorio para el acceso a datos remotos.
 * @author Janes Saenz Puerta
 */
class AuthRemoteDataUseCase @Inject constructor(
    private val repository: RemoteDataRepository
) {

    /**
     * Ejecuta la lógica de autenticación mediante el operador [invoke].
     *
     * Utiliza un bloque [try-catch] para garantizar que el flujo de datos no se rompa
     * ante excepciones inesperadas de la capa de datos.
     *
     * @param credentials Objeto de transferencia de datos ([AuthDTO]) con las credenciales.
     * @return Un flujo reactivo [Flow] con estados de [Resource].
     */
    operator fun invoke(credentials: AuthDTO): Flow<Resource<AuthResponse?>> = flow {
        try {
            // 1. Emitir estado de carga para avisar a la UI
            emit(Resource.Loading())

            // 2. Validaciones locales de negocio (Fail-fast)
            if (credentials.nameApplication.isBlank()) {
                emit(Resource.Error(Constants.NAME_APPLICATION))
                return@flow
            }

            if (credentials.users.isBlank()) {
                emit(Resource.Error(Constants.USERS))
                return@flow
            }

            // 3. Consumo del repositorio y recolección de su flujo
            repository.authenticationUsersApp(credentials).collect { result ->
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