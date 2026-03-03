package com.softsaenz.proofinterrapi.ui.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softsaenz.proofinterrapi.data.remote.dtos.response.AuthResponse
import com.softsaenz.proofinterrapi.data.remote.utilsApi.Resource
import com.softsaenz.proofinterrapi.domain.dto.AuthDTO
import com.softsaenz.proofinterrapi.domain.dto.UsersDTO
import com.softsaenz.proofinterrapi.domain.use_case.AuthRemoteDataUseCase
import com.softsaenz.proofinterrapi.domain.use_case.SaveUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * ViewModel encargado de gestionar el proceso de autenticación.
 * * Realiza la validación de credenciales contra el servidor y, en caso de éxito,
 * persiste la información del usuario en la base de datos local para mantener la sesión.
 *
 * @property authRemote Caso de uso para la autenticación remota.
 * @property saveUsers Caso de uso para guardar el perfil del usuario localmente.
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRemote: AuthRemoteDataUseCase,
    private val saveUsers: SaveUsersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<Resource<AuthResponse?>>(Resource.Loading())
    val uiState: StateFlow<Resource<AuthResponse?>> = _uiState.asStateFlow()

    // Estado observable para la navegación o feedback rápido
    var isSuccess by mutableStateOf(false)
        private set

    var error by mutableStateOf("")
        private set

    /**
     * Ejecuta el proceso de inicio de sesión.
     * Hardcoded DTO se utiliza temporalmente según requerimiento de infraestructura.
     */
    fun login() {
        viewModelScope.launch {
            val auth = AuthDTO(
                mac = "",
                users = "cGFtLm1lcmVkeTIx\n", // Base64 encoded
                path = "",
                nameApplication = "Controller APP",
                password = "SW50ZXIyMDIx\n" // Base64 encoded
            )

            authRemote(auth).collect { result ->
                _uiState.value = result // Actualizamos el estado de la UI

                when (result) {
                    is Resource.Loading -> {
                        error = ""
                        isSuccess = false
                    }
                    is Resource.Success -> {
                        result.data?.let { response ->
                            // Persistencia del perfil de usuario
                            val userProfile = UsersDTO(
                                users = response.users,
                                name = response.name.toString(),
                                identification = response.identification.toString(),
                            )
                            saveUsers(userProfile)

                            Timber.i("Autenticación exitosa para: ${response.name}")
                            isSuccess = true // Gatillo para navegación en la View
                        }
                    }
                    is Resource.Error -> {
                        isSuccess = false
                        error = result.message ?: "Error desconocido"
                        Timber.e("Error en Auth: $error")
                    }
                }
            }
        }
    }

    /**
     * Limpia el estado de error para permitir nuevos intentos sin popups residuales.
     */
    fun clearError() {
        error = ""
    }
}