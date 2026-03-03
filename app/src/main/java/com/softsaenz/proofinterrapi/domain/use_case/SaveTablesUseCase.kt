package com.softsaenz.proofinterrapi.domain.use_case

import com.softsaenz.proofinterrapi.domain.dto.TablesDTO
import com.softsaenz.proofinterrapi.domain.repository.TablesRepository
import javax.inject.Inject
/**
 * Caso de Uso para la persistencia masiva de registros de tablas en la base de datos local.
 *
 * Este componente procesa una lista completa de [TablesDTO], permitiendo sincronizar
 * o guardar múltiples registros en una sola transacción a través del [TablesRepository].
 *
 * ### Consideraciones Técnicas:
 * - **Operación Suspendida:** Utiliza `suspend` para asegurar que la inserción masiva
 * no bloquee el hilo principal (UI Thread).
 * - **Gestión de Estados:** Se ha refactorizado para devolver un [Flow], permitiendo
 * notificar a la UI sobre el estado de la operación (Carga, Éxito o Error).
 *
 * @author Janes Saenz Puerta
 * @param table Lista de objetos [TablesDTO] que se desean persistir.
 * @return  Void
 */
class SaveTablesUseCase @Inject constructor(
    private val repository: TablesRepository
) {

}