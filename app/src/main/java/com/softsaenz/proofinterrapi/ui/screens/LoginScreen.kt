package com.softsaenz.proofinterrapi.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.softsaenz.proofinterrapi.R
import com.softsaenz.proofinterrapi.data.remote.utilsApi.Resource
import com.softsaenz.proofinterrapi.ui.component.AlertPopup
import com.softsaenz.proofinterrapi.ui.component.LoadingOverlay
import com.softsaenz.proofinterrapi.ui.viewModels.LoginViewModel

/**
 * Pantalla de autenticación principal de la aplicación.
 *
 * Este componente utiliza un patrón de estado reactivo para gestionar el ciclo de vida
 * de la autenticación. Se integra con [LoginViewModel] para procesar las credenciales
 * y responder a los cambios de estado provenientes del VPS de OVH.
 *
 * * ### Características:
 * 1. **Manejo de Estados:** Reacciona dinámicamente a [Resource] mediante un bloque `when`.
 * 2. **UI Inmersiva:** Utiliza una imagen de fondo con escalado [ContentScale.Crop] para soporte multi-dispositivo.
 * 3. **Feedback Visual:** Implementa [LoadingOverlay] y [AlertPopup] para informar al hincha sobre el proceso.
 *
 * @param viewModel Instancia de [LoginViewModel] provista por Hilt.
 * @param onLoginSuccess Callback ejecutado tras una autenticación exitosa para navegar al Home.
 */
@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onLoginSuccess: () -> Unit
) {
    // Observa el estado del flujo de UI respetando el ciclo de vida de Android
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    // Estado para controlar la visibilidad del popup de error
    var showPopup by remember { mutableStateOf(true) }
    var error = viewModel.error
    if (!error.isEmpty())AlertPopup(
        show = showPopup,
        onConfirm = { showPopup = false },
        onDismiss = { showPopup = false },
        text = "Error de Acceso",
        errorMessage = error,
        type = "error"
    )
    /**
     * Orquestador de lógica según el estado del Recurso.
     * Basado en el patrón Sealed Class de [Resource].
     */
    when (val resource = state) {
        is Resource.Loading -> {
            // Muestra un indicador de carga mientras el repositorio consulta el servidor
            LoadingOverlay("Verificando credenciales...")
        }
        is Resource.Success -> {
            // Efecto secundario: Navegación exitosa
            LaunchedEffect(Unit) {
                onLoginSuccess()
            }
        }
        is Resource.Error -> {
            // Notificación de error al usuario (PII: No mostrar trazas técnicas sensibles)
            AlertPopup(
                show = showPopup,
                onConfirm = { showPopup = false },
                onDismiss = { showPopup = false },
                text = "Error de Acceso",
                errorMessage = resource.message ?: "Ocurrió un problema inesperado",
                type = "error"
            )
        }
    }

    // Estructura visual de la pantalla
    Box(modifier = Modifier.fillMaxSize()) {
        // Fondo visual de la campaña "Yo Pongo la Casa"
        Image(
            painter = painterResource(id = R.drawable.fondo1),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Acción de inicio de sesión
            Button(
                onClick = { viewModel.login() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xB4FCFCFC), // Fondo semi-transparente
                    contentColor = Color.Black
                )
            ) {
                Text("Iniciar Sesión", style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}