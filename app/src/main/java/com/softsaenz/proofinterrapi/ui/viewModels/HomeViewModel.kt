package com.softsaenz.proofinterrapi.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softsaenz.proofinterrapi.data.remote.dtos.response.LocationsResponse
import com.softsaenz.proofinterrapi.data.remote.dtos.response.TablesResponse
import com.softsaenz.proofinterrapi.data.remote.mapper.ResponseMapper
import com.softsaenz.proofinterrapi.data.remote.utilsApi.Resource
import com.softsaenz.proofinterrapi.domain.use_case.GetLocationsUseCase
import com.softsaenz.proofinterrapi.domain.use_case.GetSchemaUseCase
import com.softsaenz.proofinterrapi.domain.use_case.InsertLocationUseCase
import com.softsaenz.proofinterrapi.domain.use_case.InsertTablesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
/**
 * ViewModel encargado de la lógica de sincronización y gestión de la pantalla principal.
 * * Implementa una estrategia de "Sincronización en Cascada":
 * 1. Obtiene y persiste el esquema de tablas ([GetSchemaUseCase]).
 * 2. Tras el éxito, procede a obtener las ubicaciones recolectadas ([GetLocationsUseCase]).
 *
 * @property insertLocation Caso de uso para persistir una ubicación individual.
 * @property responseMapper Utilidad para transformar DTOs de red en Entidades de persistencia.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val insertLocation: InsertLocationUseCase,
    private val responseMapper: ResponseMapper,
    private val getSchema: GetSchemaUseCase,
    private val insertTables: InsertTablesUseCase,
    private val getLocations: GetLocationsUseCase
) : ViewModel() {

    init {
        syncInitialData()
    }

    /**
     * Inicia el flujo de sincronización de la aplicación.
     */
    private fun syncInitialData() {
        viewModelScope.launch {
            getSchema().collect { result ->
                when (result) {
                    is Resource.Loading -> Timber.d("Sincronizando esquemas de tablas...")
                    is Resource.Success -> {
                        result.data?.let { saveTablesList(it) }
                        // Delay estratégico para no saturar el bus de datos inmediatamente
                        delay(2000)
                        fetchLocations()
                    }
                    is Resource.Error -> {
                        Timber.e("Fallo en sincronización de tablas: ${result.message}")
                    }
                }
            }
        }
    }

    /**
     * Obtiene la lista de ubicaciones desde el servidor remoto.
     */
    private fun fetchLocations() {
        viewModelScope.launch {
            getLocations().collect { result ->
                when (result) {
                    is Resource.Loading -> Timber.d("Obteniendo ubicaciones de recolección...")
                    is Resource.Success -> {
                        result.data?.let { saveLocationList(it) }
                    }
                    is Resource.Error -> {
                        Timber.e("Fallo en sincronización de ubicaciones: ${result.message}")
                    }
                }
            }
        }
    }

    /**
     * Procesa y persiste la lista de tablas en la base de datos local.
     */
    private fun saveTablesList(data: List<TablesResponse?>) {
        viewModelScope.launch {
            data.filterNotNull().forEach { dto ->
                val entity = responseMapper.toEntity(dto)
                insertTables(entity)
            }
            Timber.i("Sincronización de tablas completada con éxito.")
        }
    }

    /**
     * Procesa y persiste la lista de ubicaciones en la base de datos local.
     */
    private fun saveLocationList(data: List<LocationsResponse?>) {
        viewModelScope.launch {
            data.filterNotNull().forEach { dto ->
                val response = responseMapper.toResponse(dto)
                insertLocation(response)
            }
            Timber.i("Sincronización de ubicaciones completada con éxito.")
        }
    }
}