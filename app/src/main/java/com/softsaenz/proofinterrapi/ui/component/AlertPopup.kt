package com.softsaenz.proofinterrapi.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
/**
 * Un componente de diálogo modal que muestra mensajes de retroalimentación crítica o informativa.
 *
 * [AlertPopup] encapsula un [AlertDialog] para estandarizar la forma en que la aplicación
 * comunica errores o éxitos. El diálogo es persistente; no se cierra al tocar fuera de él,
 * obligando al usuario a interactuar con los botones de acción.
 *
 * ### Tipos de Alerta:
 * - **"error"**: Muestra un icono de error ([Icons.Default.Error]) en color rojo.
 * - **"success"**: Muestra un icono de cohete ([Icons.Default.RocketLaunch]) en color verde.
 *
 * @author Janes Saenz Puerta
 * @param show Controla la visibilidad del diálogo.
 * @param onConfirm Callback ejecutado al presionar el botón "Entendido" (acción primaria).
 * @param onDismiss Callback ejecutado al presionar el botón "Cerrar" (acción secundaria).
 * @param text Título principal del diálogo.
 * @param errorMessage Cuerpo del mensaje o descripción detallada del evento.
 * @param type Define el estilo visual y el icono del diálogo ("error" o "success").
 */
@Composable
fun AlertPopup(
    show: Boolean,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    text:String,
    errorMessage: String,
    type:String
) {
    if (show) {
        AlertDialog(
            onDismissRequest = { /* No cerrar si es error crítico */ },
            title = { Text(text) },
            text = { Text(errorMessage) },
            confirmButton = {
                TextButton(onClick = onConfirm) {
                    Text("Entendido")
                }
            },
            dismissButton = {
                OutlinedButton(onClick = onDismiss) { Text("Cerrar") }
            },
            icon = {
                if (type == "error") Icon(Icons.Default.Error, contentDescription = null, tint = Color.Red)
                if (type == "success") Icon(Icons.Default.RocketLaunch, contentDescription = null, tint = Color.Green)
            }
        )
    }

}