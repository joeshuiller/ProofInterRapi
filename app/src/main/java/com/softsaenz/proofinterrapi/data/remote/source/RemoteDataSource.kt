package com.softsaenz.proofinterrapi.data.remote.source

import com.softsaenz.proofinterrapi.data.remote.dtos.request.AuthRequest
import com.softsaenz.proofinterrapi.data.remote.dtos.response.AuthResponse
import com.softsaenz.proofinterrapi.data.remote.dtos.response.LocationsResponse
import com.softsaenz.proofinterrapi.data.remote.dtos.response.TablesResponse
import retrofit2.Response

/**
 * Contrato para la fuente de datos remota de la aplicación.
 *
 * Define las operaciones de comunicación con el backend, incluyendo procesos
 * de autenticación, sincronización de catálogos y obtención de metadatos estructurales.
 * Todas las funciones son de suspensión ([suspend]) para garantizar una ejecución
 * asíncrona no bloqueante.
 */
interface RemoteDataSource {

    /**
     * Valida las credenciales del usuario y solicita una sesión activa.
     * @param data Objeto con las credenciales y contexto del dispositivo.
     * @return [Response] envuelto en Retrofit con los datos del usuario autenticado.
     */
    suspend fun authenticationUsersApp(data: AuthRequest): Response<AuthResponse?>

    /**
     * Recupera parámetros de control y configuración dinámica desde el servidor.
     * @return Un [String] opcional con la configuración cruda o nulo si falla.
     */
    suspend fun storeAppControl(): String?

    /**
     * Descarga el listado completo de ubicaciones disponibles para recolección.
     * @return [Response] con la lista de objetos [LocationsResponse].
     */
    suspend fun obtainCollectedLocations(): Response<List<LocationsResponse?>>

    /**
     * Solicita la definición de las tablas necesarias para la base de datos local.
     * Útil para sistemas que construyen su esquema de persistencia de forma dinámica.
     * @return [Response] con la lista de metadatos de tablas [TablesResponse].
     */
    suspend fun getSchema(): Response<List<TablesResponse?>>
}