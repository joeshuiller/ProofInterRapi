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
import com.softsaenz.proofinterrapi.domain.dto.LocationsDTO
import com.softsaenz.proofinterrapi.domain.dto.TablesDTO
/**
 * Un componente de tarjeta interactivo que muestra los detalles básicos de una ubicación.
 *
 * Este Composable visualiza la información contenida en un objeto [LocationsDTO] dentro de una
 * [Card]. El diseño está organizado de forma horizontal ([Row]), permitiendo que la información
 * textual ocupe el espacio principal mientras que una acción de navegación se sitúa al final.
 *
 * ### Composición de la UI:
 * 1. **Contenedor Principal ([Card]):** Proporciona elevación y una superficie de color [MaterialTheme.colorScheme.surface].
 * 2. **Cuerpo de Texto ([Column]):** Muestra el nombre, la abreviatura de la ciudad y el nombre corto (nameCut).
 * 3. **Acción Visual ([IconButton]):** Un icono de flecha que indica que el elemento es expandible o navegable.
 *
 * @author Janes Saenz Puerta
 * @param img El objeto de datos [LocationsDTO] que contiene la información a mostrar.
 */
@Composable
fun DataLocationItem (
    img: LocationsDTO,
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
                    text = img.abbreviationCity.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = img.nameCut.toString(),
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