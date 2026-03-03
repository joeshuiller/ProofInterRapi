package com.softsaenz.proofinterrapi.ui.screens

import android.app.Activity
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.softsaenz.proofinterrapi.R
import com.softsaenz.proofinterrapi.ui.component.AlertPopup
import com.softsaenz.proofinterrapi.ui.viewModels.SplashViewModel
import kotlin.let

/**
 * Pantalla de inicio (Splash Screen) con animaciones y lógica de enrutamiento.
 *
 * Esta función es la primera cara que ve el hincha de Homecenter. Combina una
 * experiencia visual inmersiva (Edge-to-Edge y ocultamiento de barras del sistema)
 * con la reactividad a los estados de sesión provistos por el ViewModel.
 * * ### Funcionalidades:
 * 1. **Animación de Entrada:** Escala el icono central usando [Animatable] para un efecto suave.
 * 2. **Modo Inmersivo:** Oculta las barras de sistema (Status y Navigation) mediante [WindowInsetsControllerCompat].
 * 3. **Observación de Estado:** Utiliza [snapshotFlow] para monitorear cuándo el [SplashViewModel]
 * determina el destino final (Auth o Home).
 *
 * @author Janes Saenz Puerta
 * @param viewModel Instancia del ViewModel inyectada por Hilt para validar la sesión.
 * @param onTransition Lambda que ejecuta la navegación hacia el destino decidido.
 */
@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
    onTransition: (String) -> Unit
) {
    // Control de la animación de escala para el icono central
    val escala = remember { Animatable(0f) }
    // Estado para controlar la visibilidad del popup de error
    var showPopup by remember { mutableStateOf(true) }
    val context = LocalContext.current
    val window = (context as? Activity)?.window
    val validate = viewModel.validateAppControl
    val validateApp = viewModel.validateApp
    if (!validate.isEmpty()) AlertPopup(
        show = showPopup,
        onConfirm = { showPopup = false },
        onDismiss = { showPopup = false },
        text = "Error",
        errorMessage = validate,
        type = "error"
    )
    if (validateApp.isEmpty())AlertPopup(
        show = showPopup,
        onConfirm = { showPopup = false },
        onDismiss = { showPopup = false },
        text = "Success",
        errorMessage = validateApp,
        type = "success"
    )
    // Efecto lanzado al iniciar la composición de la pantalla
    LaunchedEffect(key1 = true) {
        // Configuración de pantalla completa (Immersive Mode)
        window?.let {
            WindowCompat.setDecorFitsSystemWindows(it, false)
            val controller = WindowInsetsControllerCompat(it, it.decorView)
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

        // Ejecución de la animación de escala (duración: 800ms)
        escala.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(durationMillis = 800)
        )

        /**
         * Observador reactivo del destino de navegación.
         * Cuando [SplashViewModel.startDestination] deja de ser null, se dispara la transición.
         */
        snapshotFlow { viewModel.startDestination }.collect { destino ->
            if (destino != null) {
                onTransition(destino)
            }
        }
    }

    // Estructura visual de la pantalla
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Fondo de pantalla que cubre todo el área disponible
        Image(
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Contenedor vertical para el logo y el nombre de la aplicación
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Default.RocketLaunch,
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .scale(escala.value), // Aplicación del valor animado de escala
                tint = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "PROOF INTER RAPI",
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}