package com.softsaenz.proofinterrapi.domain.repository

import com.softsaenz.proofinterrapi.data.remote.dtos.response.AuthResponse
import com.softsaenz.proofinterrapi.data.remote.dtos.response.LocationsResponse
import com.softsaenz.proofinterrapi.data.remote.dtos.response.TablesResponse
import com.softsaenz.proofinterrapi.data.remote.utilsApi.Resource
import com.softsaenz.proofinterrapi.domain.dto.AppControlDTO
import com.softsaenz.proofinterrapi.domain.dto.AuthDTO
import kotlinx.coroutines.flow.Flow

/**
 * Contrato para la gestión de operaciones de datos remotos.
 *
 * Esta interfaz define los métodos necesarios para la comunicación con el backend
 * alojado en el VPS de OVH. Al usar [Flow] y [Resource], permite un flujo de datos
 * reactivo y el manejo de estados de carga, éxito y error en la UI.
 * * ### Responsabilidades:
 * 1. **Autenticación:** Gestión de acceso de usuarios de la Hinchada.
 * 2. **Sincronización:** Obtención de esquemas de tablas y configuraciones de control.
 * 3. **Geolocalización:** Recuperación de puntos de recolección o ubicaciones.
 *
 * @author Janes Saenz Puerta
 */
interface RemoteDataRepository {

    /**
     * Valida las credenciales del usuario contra el servicio de autenticación.
     *
     * @param data Objeto [AuthDTO] con la información de login.
     * @return Un flujo que emite el estado de la sesión y la respuesta [AuthResponse].
     */
    suspend fun authenticationUsersApp(data: AuthDTO): Flow<Resource<AuthResponse?>>

    /**
     * Recupera las configuraciones de control de la aplicación (Flags, versiones, mantenimientos).
     *
     * @return Un flujo con la configuración [AppControlDTO] para determinar el comportamiento de la App.
     */
    suspend fun storeAppControl(): Flow<Resource<AppControlDTO?>>

    /**
     * Obtiene el listado de ubicaciones registradas para la campaña.
     *
     * @return Un flujo que contiene una lista de [LocationsResponse], útil para el mapeo de puntos.
     */
    suspend fun obtainCollectedLocations(): Flow<Resource<List<LocationsResponse?>>>

    /**
     * Recupera la estructura o metadatos de las tablas necesarias para la persistencia local.
     * * Es vital para asegurar que el esquema de [Room] coincida con el servidor de Laravel.
     *
     * @return Un flujo con la lista de definiciones de tablas [TablesResponse].
     */
    suspend fun getSchema(): Flow<Resource<List<TablesResponse?>>>
}