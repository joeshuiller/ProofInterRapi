package com.softsaenz.proofinterrapi.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.softsaenz.proofinterrapi.domain.dto.TablesDTO
/**
 * Componente de interfaz para representar un registro individual de tablas o procesos.
 *
 * Renderiza los datos de un [TablesDTO] dentro de un contenedor elevado ([Card]).
 * La información se distribuye horizontalmente, priorizando el contenido textual
 * a la izquierda y dejando una acción de navegación (affordance) a la derecha.
 *
 * ### Datos Visualizados:
 * - **Nombre:** Identificador principal del registro.
 * - **Método de Aplicación:** Detalles sobre la ejecución o proceso.
 * - **Tamaño de Lote (Batch Size):** Información cuantitativa asociada.
 *
 * @author Janes Saenz Puerta
 * @param img Objeto de datos [TablesDTO] que contiene la información técnica a mostrar.
 * @see MaterialTheme Para la gestión de colores y tipografías aplicadas.
 */
@Composable
fun DataItem (
    img: TablesDTO,
) {
    Card(
    modifier = Modifier
    .fillMaxWidth()
    .padding(8.dp)
    .clickable {  },
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    colors = CardDefaults.cardColors(
    containerColor = MaterialTheme.colorScheme.surface
    )
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Información del producto
            Column(modifier = Modifier.weight(1f)) {

                Text(
                    text = img.name.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = img.methodApp.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = img.batchSize.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            // Icono indicador
            IconButton(
                onClick = {
                    img
                }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Ver detalle",
                    modifier = Modifier,
                    tint = Color.Gray,
                )
            }
        }
    }
}