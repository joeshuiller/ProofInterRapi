package com.softsaenz.proofinterrapi.domain.use_case

import com.softsaenz.proofinterrapi.domain.dto.TablesDTO
import com.softsaenz.proofinterrapi.domain.repository.TablesRepository
import javax.inject.Inject
/**
 * Caso de Uso para la inserción o actualización individual de una tabla.
 *
 * Implementa la lógica de "Upsert" (Update or Insert) para un objeto [TablesDTO].
 * Este componente es ideal para formularios de creación o edición donde se requiere
 * feedback inmediato sobre el éxito o fracaso de la operación.
 *
 * ### Comportamiento:
 * - **Reactividad:** Emite estados de [ResourceLocal] para que la UI reaccione a la carga y errores.
 * - **Hilo de ejecución:** La operación se desplaza al despachador de IO ([Dispatchers.IO]) para
 * evitar bloqueos en el hilo principal.
 *
 * @author Janes Saenz Puerta
 * @param table El objeto [TablesDTO] que se desea guardar.
 * @return Long
 */
class InsertTablesUseCase @Inject constructor(
    private val repository: TablesRepository
) {
    suspend operator fun invoke(table: TablesDTO): Long {
        return  repository.upsertTables(table)
    }
}