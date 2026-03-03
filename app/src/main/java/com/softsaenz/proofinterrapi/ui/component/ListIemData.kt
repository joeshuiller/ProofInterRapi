package com.softsaenz.proofinterrapi.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.softsaenz.proofinterrapi.domain.dto.TablesDTO
/**
 * Un contenedor de lista optimizado para mostrar una colección de tablas o entidades de datos.
 * * Utiliza [LazyColumn] para garantizar un rendimiento fluido, renderizando solo los elementos
 * visibles en pantalla. Es ideal para manejar listas dinámicas donde el número de elementos
 * puede ser considerable.
 *
 * ### Características del Componente:
 * - **Estado de Scroll:** Gestionado por [rememberLazyListState] para persistencia durante recomposiciones.
 * - **Diseño Adaptativo:** El [modifier] permite integrar la lista en diferentes layouts (Scaffolds, Sheets, etc.).
 * - **Padding de Contenido:** Utiliza [PaddingValues] para asegurar que el contenido no quede pegado
 * a los bordes físicos de la pantalla, manteniendo el scroll de borde a borde.
 *
 * @author Janes Saenz Puerta
 * @param data Lista de objetos [TablesDTO] que alimentan la interfaz.
 * @param modifier Modificador para configurar el comportamiento y dimensiones del contenedor.
 */
@Composable
fun ListIemData(
    data: List<TablesDTO>,
    modifier: Modifier,
){
    val scrollState = rememberLazyListState()
    LazyColumn(
        state = scrollState,
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        itemsIndexed(data) { index, images ->
            DataItem(
                images
            )
        }
    }
}