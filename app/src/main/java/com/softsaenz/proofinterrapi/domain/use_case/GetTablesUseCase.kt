package com.softsaenz.proofinterrapi.domain.use_case

import com.softsaenz.proofinterrapi.BuildConfig
import com.softsaenz.proofinterrapi.data.local.utilsLocal.ResourceLocal
import com.softsaenz.proofinterrapi.domain.dto.TablesDTO
import com.softsaenz.proofinterrapi.domain.repository.TablesRepository
import com.softsaenz.proofinterrapi.ui.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
/**
 * Caso de Uso encargado de recuperar el listado completo de tablas desde el repositorio.
 *
 * Proporciona un flujo de datos reactivo que gestiona automáticamente los estados de la interfaz
 * de usuario. Es ideal para alimentar componentes como [ListIemData].
 *
 * ### Ciclo de vida del flujo:
 * 1. **Carga ([ResourceLocal.Loading]):** Se emite inmediatamente al iniciar la recolección.
 * 2. **Datos ([ResourceLocal.Success]):** Emite la lista de [TablesDTO] obtenida del repositorio.
 * 3. **Error ([ResourceLocal.Error]):** Captura excepciones de red o base de datos,
 * transformándolas en mensajes seguros para el usuario.
 *
 * @author Janes Saenz Puerta
 * @property repository Fuente de datos para las tablas (Local o Remota).
 * @return Un [Flow] que emite estados de tipo [ResourceLocal].
 */
class GetTablesUseCase @Inject constructor(
    private val repository: TablesRepository
) {
    operator fun invoke(): Flow<ResourceLocal<List<TablesDTO>>> = flow {
        try {
            // 1. Emitir estado de carga para avisar a la UI
            emit(ResourceLocal.Loading())
            // 3. Consumo del repositorio y recolección de su flujo
            repository.getAllTables().collect { result ->
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