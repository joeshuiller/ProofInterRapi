package com.softsaenz.proofinterrapi.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.softsaenz.proofinterrapi.domain.dto.LocationsDTO
import com.softsaenz.proofinterrapi.domain.dto.TablesDTO
/**
 * Renderiza una lista vertical de ubicaciones utilizando un scroll eficiente (Lazy Loading).
 * * Este componente actúa como el contenedor principal para mostrar una colección de datos
 * de tipo [LocationsDTO]. Utiliza [LazyColumn] para gestionar la memoria de forma óptima,
 * recomponiendo solo los elementos que entran en el viewport del usuario.
 *
 * ### Comportamiento del Scroll:
 * - Mantiene su propio [scrollState] mediante [rememberLazyListState], lo que permite
 * reaccionar a cambios de posición o implementar scroll programático en el futuro.
 * - Aplica un [PaddingValues] de 16.dp alrededor de todo el contenido de la lista
 * para evitar que los elementos toquen los bordes de la pantalla.
 *
 * @author Janes Saenz Puerta
 * @param data Lista de objetos [LocationsDTO] que contienen la información de cada ubicación.
 * @param modifier [Modifier] externo para configurar el layout, tamaño o comportamiento desde el padre.
 */
@Composable
fun ListLocationsIemData(
    data: List<LocationsDTO>,
    modifier: Modifier,
){
    val scrollState = rememberLazyListState()
    LazyColumn(
        state = scrollState,
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        itemsIndexed(data) { index, images ->
            DataLocationItem(
                images
            )
        }
    }
}