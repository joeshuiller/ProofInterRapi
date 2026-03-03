package com.softsaenz.proofinterrapi.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.softsaenz.proofinterrapi.data.local.utilsLocal.ResourceLocal
import com.softsaenz.proofinterrapi.ui.component.AlertPopup
import com.softsaenz.proofinterrapi.ui.component.ListLocationsIemData
import com.softsaenz.proofinterrapi.ui.component.ShowSkeleton
import com.softsaenz.proofinterrapi.ui.viewModels.LocationViewModel

/**
 * Pantalla de visualización de ubicaciones recolectadas.
 *
 * Esta pantalla consume datos de la persistencia local mediante el [LocationViewModel].
 * Implementa un flujo de estados basado en [ResourceLocal] para gestionar la carga
 * asíncrona de datos desde la base de datos Room de la Hinchada App.
 *
 * * ### Comportamiento de la UI:
 * 1. **Carga:** Muestra un efecto de Skeleton ([ShowSkeleton]) mientras se consultan los registros.
 * 2. **Éxito:** Renderiza una lista de ítems mediante [ListLocationsIemData].
 * 3. **Error:** Despliega un [AlertPopup] informativo.
 *
 * @param viewModel Instancia de [LocationViewModel] (inyectada usualmente por Hilt).
 * @param onBack Callback para gestionar la navegación hacia atrás en el NavHost.
 */
@Composable
fun LocationScreen(
    viewModel: LocationViewModel,
    onBack: () -> Unit
) {
    // Recolección de estado segura para el ciclo de vida (UI State)
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    // Estado local para controlar la visibilidad de alertas
    var showPopup by remember { mutableStateOf(true) }

    /**
     * Orquestador de UI basado en el estado del Recurso Local.
     */
    when (val resource = state) {
        is ResourceLocal.Loading -> {
            // Feedback visual de carga (Shimmer/Skeleton Effect)
            ShowSkeleton()
        }

        is ResourceLocal.Success -> {
            // Validación de nulabilidad y renderizado de la lista
            resource.data?.let { locationsList ->
                ListLocationsIemData(
                    data = locationsList,
                    modifier = Modifier.fillMaxSize()
                )
            } ?: run {
                // Estado vacío: El hincha aún no tiene ubicaciones sincronizadas
                AlertPopup(
                    show = showPopup,
                    onConfirm = { showPopup = false },
                    onDismiss = { showPopup = false },
                    text = "Error de Persistencia",
                    errorMessage = "No se pudieron cargar las ubicaciones locales.",
                    type = "error"
                )
            }
        }

        is ResourceLocal.Error -> {
            // Manejo de excepciones de lectura en la DB local
            AlertPopup(
                show = showPopup,
                onConfirm = { showPopup = false },
                onDismiss = { showPopup = false },
                text = "Error de Persistencia",
                errorMessage = resource.message ?: "No se pudieron cargar las ubicaciones locales.",
                type = "error"
            )
        }
    }
}