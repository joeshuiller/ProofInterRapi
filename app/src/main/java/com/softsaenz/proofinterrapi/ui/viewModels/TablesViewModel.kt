package com.softsaenz.proofinterrapi.ui.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softsaenz.proofinterrapi.data.local.utilsLocal.ResourceLocal
import com.softsaenz.proofinterrapi.domain.dto.TablesDTO
import com.softsaenz.proofinterrapi.domain.use_case.GetTablesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
/**
 * ViewModel responsable de la gestión y exposición del estado de las tablas locales.
 * * Actúa como un puente reactivo entre la persistencia de datos ([GetTablesUseCase])
 * y la capa de presentación. Utiliza un flujo de estados inmutables para garantizar
 * que la interfaz de usuario responda correctamente a los cambios en la base de datos.
 *
 * ### Responsabilidades:
 * 1. **Observación Continua:** Se suscribe al flujo de datos local para detectar actualizaciones en tiempo real.
 * 2. **Gestión de Estados:** Mapea los resultados de la base de datos a los estados [ResourceLocal] (Loading, Success, Error).
 * 3. **Seguridad de Hilos:** Ejecuta la recolección de datos dentro del [viewModelScope], evitando bloqueos en el hilo principal.
 *
 * @property getables Caso de uso que encapsula la lógica de consulta a la base de datos de tablas.
 */
@HiltViewModel
class TablesViewModel @Inject constructor(
    private val getables: GetTablesUseCase,
) : ViewModel() {

    // Estado privado mutable (Fuente de verdad única)
    private val _uiState = MutableStateFlow<ResourceLocal<List<TablesDTO>>>(ResourceLocal.Loading())

    // Estado público inmutable para la View
    val uiState: StateFlow<ResourceLocal<List<TablesDTO>>> = _uiState.asStateFlow()

    // Estado simplificado para lógica de UI rápida
    var isSuccess by mutableStateOf(false)
        private set

    init {
        observeTables()
    }

    /**
     * Inicia la observación reactiva del repositorio local.
     * Al ser un Flow, se mantendrá actualizado ante cualquier cambio en Room.
     */
    private fun observeTables() {
        viewModelScope.launch {
            getables().collect { result ->
                // Actualizamos el flag de éxito según el tipo de recurso recibido
                isSuccess = result is ResourceLocal.Success

                // Emitimos el estado actualizado
                _uiState.value = result
            }
        }
    }
}
