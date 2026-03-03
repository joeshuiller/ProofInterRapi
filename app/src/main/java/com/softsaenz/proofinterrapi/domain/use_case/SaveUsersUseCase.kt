package com.softsaenz.proofinterrapi.domain.use_case

import com.softsaenz.proofinterrapi.BuildConfig
import com.softsaenz.proofinterrapi.data.local.utilsLocal.ResourceLocal
import com.softsaenz.proofinterrapi.domain.dto.TablesDTO
import com.softsaenz.proofinterrapi.domain.dto.UsersDTO
import com.softsaenz.proofinterrapi.domain.repository.TablesRepository
import com.softsaenz.proofinterrapi.domain.repository.UsersRepository
import com.softsaenz.proofinterrapi.ui.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
/**
 * Caso de Uso para la persistencia de usuarios en el almacenamiento local.
 *
 * Coordina el proceso de guardado (Upsert) de un [UsersDTO]. A diferencia de otros casos de uso,
 * este emite un estado inicial de carga para permitir que la UI muestre un indicador visual
 * (como un [LoadingOverlay]) mientras se procesa la transacción.
 *
 * ### Ciclo de Vida del Flujo:
 * 1. **onStart**: Emite [ResourceLocal.Loading].
 * 2. **Execution**: Llama al repositorio para realizar la operación.
 * 3. **Success/Error**: Emite el resultado final o captura excepciones mapeándolas a [Constants].
 *
 * @author Janes Saenz Puerta
 * @param users El objeto de datos del usuario a persistir.
 * @return Un [Flow] reactivo con el estado de la operación.
 */
class SaveUsersUseCase @Inject constructor(
    private val repository: UsersRepository
) {
    operator fun invoke(users: UsersDTO): Flow<ResourceLocal<Long>> = flow {
        try {
            // 1. Emitir estado de carga para avisar a la UI
            emit(ResourceLocal.Loading())
            // 3. Consumo del repositorio y recolección de su flujo
            repository.upsertUsers(users).collect { result ->
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