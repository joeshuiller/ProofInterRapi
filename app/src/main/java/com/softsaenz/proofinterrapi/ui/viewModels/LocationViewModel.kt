package com.softsaenz.proofinterrapi.ui.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softsaenz.proofinterrapi.data.local.utilsLocal.ResourceLocal
import com.softsaenz.proofinterrapi.domain.dto.LocationsDTO
import com.softsaenz.proofinterrapi.domain.use_case.DataDBLocationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val dataDBLocations: DataDBLocationsUseCase,
) : ViewModel() {

    // Estado privado mutable usando el patrón ResourceLocal
    private val _uiState = MutableStateFlow<ResourceLocal<List<LocationsDTO>>>(ResourceLocal.Loading())

    // Estado público inmutable expuesto a la View
    val uiState: StateFlow<ResourceLocal<List<LocationsDTO>>> = _uiState.asStateFlow()

    // Flag de éxito simplificado para lógica de UI rápida (ej. ocultar botones)
    var isSuccess by mutableStateOf(false)
        private set

    init {
        loadLocations()
    }

    /**
     * Inicia la recolección del flujo de datos desde la DB.
     * Al ser un Flow, no necesitamos volver a llamar a esta función si los datos cambian.
     */
    private fun loadLocations() {
        viewModelScope.launch {
            dataDBLocations().collect { result ->
                // Actualizamos el estado de éxito basado en el tipo de recurso
                isSuccess = result is ResourceLocal.Success

                // Emitimos el nuevo estado a la UI
                _uiState.value = result
            }
        }
    }
}