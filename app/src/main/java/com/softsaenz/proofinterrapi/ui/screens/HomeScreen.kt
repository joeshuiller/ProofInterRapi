package com.softsaenz.proofinterrapi.ui.screens

import android.app.Activity
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
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
import com.softsaenz.proofinterrapi.R
import com.softsaenz.proofinterrapi.ui.viewModels.HomeViewModel

/**
 * Pantalla principal de la aplicación que sirve como Dashboard de navegación.
 *
 * Esta pantalla presenta una interfaz inmersiva a pantalla completa ([Immersive Mode])
 * con un fondo decorativo y dos accesos principales: Tablas y Localidades.
 *
 * ### Características Principales:
 * - **Modo Inmersivo:** Oculta las barras del sistema (estado y navegación) al iniciar para
 * maximizar el espacio visual.
 * - **Animación de Entrada:** El icono principal realiza una transición de escala suave
 * mediante [Animatable].
 * - **Diseño Adaptativo:** Utiliza pesos ([Modifier.weight]) en los botones para asegurar
 * que se distribuyan equitativamente en diferentes anchos de pantalla.
 *
 * @param viewModel Instancia de [HomeViewModel] para gestionar la lógica de negocio.
 * @param onLocationClick Callback para navegar a la sección de Localidades.
 * @param onClick Callback para navegar a la sección de Tablas.
 */
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onLocationClick: () -> Unit,
    onClick: () -> Unit
) {
    val escala = remember { Animatable(0f) }
    val context = LocalContext.current

    // Gestión de UI Inmersiva
    LaunchedEffect(Unit) {
        val window = (context as? Activity)?.window
        window?.let {
            WindowCompat.setDecorFitsSystemWindows(it, false)
            val controller = WindowInsetsControllerCompat(it, it.decorView)
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

        // Animación de escala del icono
        escala.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(durationMillis = 800)
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // 1. Capa de Fondo
        Image(
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // 2. Capa de Contenido
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly // Distribuye espacio equitativamente
        ) {
            // Sección Superior: Logo y Título
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Default.RocketLaunch,
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp)
                        .scale(escala.value),
                    tint = Color.White
                )
                Text(
                    text = "Home",
                    color = Color.White,
                    style = MaterialTheme.typography.headlineMedium
                )
            }

            // Sección Inferior: Botones de Navegación
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MenuButton(
                    text = "Tablas",
                    iconRes = R.drawable.icono,
                    modifier = Modifier.weight(1f),
                    action = onClick
                )
                MenuButton(
                    text = "Localidades",
                    iconRes = R.drawable.icono5,
                    modifier = Modifier.weight(1f),
                    action = onLocationClick
                )
            }
        }
    }
}

/**
 * Componente interno para estandarizar los botones del menú principal.
 */
@Composable
private fun MenuButton(
    text: String,
    iconRes: Int,
    modifier: Modifier = Modifier,
    action: () -> Unit
) {
    Button(
        onClick = action,
        modifier = modifier.height(160.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black.copy(alpha = 0.5f)
        ),
        contentPadding = PaddingValues(16.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = text,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = text, color = Color.White)
        }
    }
}