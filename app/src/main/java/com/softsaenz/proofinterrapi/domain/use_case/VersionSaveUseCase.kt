package com.softsaenz.proofinterrapi.domain.use_case

import com.softsaenz.proofinterrapi.BuildConfig
import com.softsaenz.proofinterrapi.data.local.utilsLocal.ResourceLocal
import com.softsaenz.proofinterrapi.domain.dto.VersionDTO
import com.softsaenz.proofinterrapi.domain.repository.VersionRepository
import com.softsaenz.proofinterrapi.ui.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
/**
 * Caso de Uso encargado de persistir o actualizar la información de versión en la base de datos local.
 *
 * Sigue el patrón "upsert" (Update or Insert): si el registro existe lo actualiza,
 * de lo contrario, lo crea. Utiliza un patrón de repositorio para la abstracción de datos
 * y devuelve un flujo [Flow] encapsulado en [ResourceLocal] para gestionar estados de carga,
 * éxito y error de forma reactiva.
 *
 * ### Flujo de Errores:
 * - Filtra excepciones de red y de servidor para evitar fugas de información sensible (PII).
 * - Mapea excepciones técnicas a mensajes constantes definidos en [Constants].
 *
 * @author Janes Saenz Puerta
 * @property repository Repositorio de versiones que actúa como fuente de verdad.
 */
class VersionSaveUseCase @Inject constructor(
    private val repository: VersionRepository
) {
    suspend operator fun invoke(version: VersionDTO): Flow<ResourceLocal<Long>> {
        try {
            // 1. Ejecutamos la llamada al repositorio DAO
            val data = repository.upsertVersion(version)
            return data
        } catch (e: Exception) {
            // 2. Captura de errores técnicos (IOException, HttpException, etc.)
            // Nota de Seguridad: No emitir e.message directamente si contiene PII (Vulnerabilidad 10062)
            val errorMessage = when (e) {
                is java.net.UnknownHostException -> Constants.UNKNOWNHOST
                is retrofit2.HttpException -> "Error en el servidor (${e.code()})"
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