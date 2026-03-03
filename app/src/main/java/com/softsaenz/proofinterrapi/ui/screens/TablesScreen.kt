package com.softsaenz.proofinterrapi.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.softsaenz.proofinterrapi.data.local.utilsLocal.ResourceLocal
import com.softsaenz.proofinterrapi.ui.component.AlertPopup
import com.softsaenz.proofinterrapi.ui.component.ListIemData
import com.softsaenz.proofinterrapi.ui.component.ShowSkeleton
import com.softsaenz.proofinterrapi.ui.viewModels.TablesViewModel


/**
 * Pantalla encargada de listar los esquemas de tablas sincronizados localmente.
 *
 * Utiliza [TablesViewModel] para observar el flujo de datos desde la base de datos Room.
 * La UI reacciona a estados de carga, éxito o error mediante el sellado de [ResourceLocal].
 *
 * * ### Componentes de Interfaz:
 * 1. **Carga Estática:** Implementa [ShowSkeleton] para mejorar la UX durante la consulta inicial.
 * 2. **Lista de Datos:** Delega el renderizado de cada fila a [ListIemData].
 * 3. **Gestión de Errores:** Utiliza [AlertPopup] para notificar inconsistencias en la persistencia.
 *
 * @author Janes Saenz Puerta
 * @param viewModel Instancia del ViewModel inyectada que gestiona el estado de las tablas.
 * @param onBack Callback para la navegación hacia atrás.
 */
@Composable
fun TablesScreen(
    viewModel: TablesViewModel,
    onBack: () -> Unit
) {
    // Observación del estado con respeto al ciclo de vida de la Activity/Fragment
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    // Control de visibilidad para diálogos de error
    var showPopup by remember { mutableStateOf(true) }

    /**
     * Orquestación declarativa de la interfaz basada en el estado.
     */
    when (val resource = state) {
        is ResourceLocal.Loading -> {
            // Efecto Shimmer mientras se procesa la consulta en el hilo IO
            ShowSkeleton()
        }

        is ResourceLocal.Success -> {
            // Verificación de integridad de los datos antes de renderizar
            resource.data?.let { tablesList ->
                ListIemData(
                    data = tablesList,
                    modifier = Modifier.fillMaxSize()
                )
            } ?: run {
                // Estado fallback: No se encontraron tablas sincronizadas
                AlertPopup(
                    show = showPopup,
                    onConfirm = { showPopup = false },
                    onDismiss = { showPopup = false },
                    text = "Error de Datos",
                    errorMessage = "Error desconocido al leer tablas",
                    type = "error"
                )
            }
        }

        is ResourceLocal.Error -> {
            // Manejo de excepciones (ej. Base de datos corrupta o migración fallida)
            AlertPopup(
                show = showPopup,
                onConfirm = { showPopup = false },
                onDismiss = { showPopup = false },
                text = "Error de Datos",
                errorMessage = resource.message ?: "Error desconocido al leer tablas",
                type = "error"
            )
        }
    }
}