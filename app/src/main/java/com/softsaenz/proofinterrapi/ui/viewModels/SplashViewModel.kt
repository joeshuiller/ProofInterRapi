package com.softsaenz.proofinterrapi.ui.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softsaenz.proofinterrapi.data.local.utilsLocal.ResourceLocal
import com.softsaenz.proofinterrapi.data.remote.utilsApi.Resource
import com.softsaenz.proofinterrapi.domain.dto.AppControlDTO
import com.softsaenz.proofinterrapi.domain.dto.VersionDTO
import com.softsaenz.proofinterrapi.domain.use_case.AppControlUseCase
import com.softsaenz.proofinterrapi.domain.use_case.SaveUsersUseCase
import com.softsaenz.proofinterrapi.domain.use_case.VersionGetUseCase
import com.softsaenz.proofinterrapi.domain.use_case.VersionSaveUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
/**
 * ViewModel orquestador del flujo de arranque y control de versiones (Startup Flow).
 * * Este componente es responsable de garantizar que la aplicación esté sincronizada
 * con los parámetros del servidor antes de permitir el acceso al usuario.
 *
 * ### Responsabilidades Principales:
 * 1. **Handshake de Red:** Consulta al VPS de OVH mediante [AppControlUseCase] para validar la versión oficial.
 * 2. **Validación de Persistencia:** Compara la versión remota con la local almacenada en Room a través de [VersionGetUseCase].
 * 3. **Sincronización:** Actualiza la base de datos local si existe una discrepancia de versiones mediante [VersionSaveUseCase].
 * 4. **Enrutamiento Dinámico:** Define el [startDestination] evaluando la presencia de una sesión activa (Token).
 *
 * @param appControl Caso de uso para obtener metadatos globales del sistema.
 * @param versionSave Caso de uso para actualizar la versión en la persistencia local.
 * @param versionGetUse Caso de uso para recuperar el historial de versiones local.
 */
@HiltViewModel
class SplashViewModel @Inject constructor(
    private val appControl: AppControlUseCase,
    private val versionSave: VersionSaveUseCase,
    private val versionGetUse: VersionGetUseCase
) : ViewModel() {

    // Estado reactivo para la navegación (Single Source of Truth para el Splash)
    var startDestination by mutableStateOf<String?>(null)
        private set

    // Estados informativos para la UI (Logs visuales opcionales)
    var statusMessage by mutableStateOf("")
        private set
    var validateAppControl by mutableStateOf("")
        private set
    var validateApp by mutableStateOf("")
        private set
    init {
        checkAppConfiguration()
    }

    private fun checkAppConfiguration() {
        viewModelScope.launch {
            appControl().collect { result ->
                when (result) {
                    is Resource.Loading -> statusMessage = "Conectando con el servidor..."
                    is Resource.Success -> {
                        result.data?.let { remoteData ->
                            handleVersionValidation(remoteData)
                        } ?: proceedToRouting()
                    }
                    is Resource.Error -> {
                        Timber.e("Error de red: ${result.message}")
                        statusMessage = "Modo offline activado"
                        proceedToRouting()
                    }
                }
            }
        }
    }

    private suspend fun handleVersionValidation(remoteData: AppControlDTO) {
        versionGetUse().collect { result ->
            if (result is ResourceLocal.Success) {
                val localVersion = result.data?.lastOrNull()?.version ?: ""

                if (localVersion != remoteData.version) {
                    statusMessage = "Actualizando base de datos..."
                    validateAppControl = "Error version no coinciden "
                    updateLocalVersion(remoteData)
                } else {
                    validateApp = "Configuración validada"
                    statusMessage = "Configuración validada"
                    proceedToRouting()
                }
            } else if (result is ResourceLocal.Error) {
                updateLocalVersion(remoteData)
            }
        }
    }

    private suspend fun updateLocalVersion(data: AppControlDTO) {
        versionSave(VersionDTO(version = data.version)).collect { result ->
            if (result is ResourceLocal.Success) proceedToRouting()
        }
    }

    private fun proceedToRouting() {
        viewModelScope.launch {
            // Garantiza que el logo del Splash sea visible (UX/Branding)
            delay(2000)

            // Lógica de decisión de sesión
            // En el futuro, esto debería venir de un SessionManager o PreferenceUseCase
            val isUserLoggedIn = false

            startDestination = if (isUserLoggedIn) "main_tabs" else "auth"
            Timber.i("Navegación decidida: $startDestination")
        }
    }
}