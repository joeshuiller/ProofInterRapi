package com.softsaenz.proofinterrapi.data.remote.source

import com.softsaenz.proofinterrapi.data.remote.api.ApiUrl
import com.softsaenz.proofinterrapi.data.remote.dtos.request.AuthRequest
import com.softsaenz.proofinterrapi.data.remote.dtos.response.AuthResponse
import com.softsaenz.proofinterrapi.data.remote.dtos.response.LocationsResponse
import com.softsaenz.proofinterrapi.data.remote.dtos.response.TablesResponse
import retrofit2.Response
import javax.inject.Inject

/**
 * Implementación concreta de la fuente de datos remota [RemoteDataSource].
 *
 * Esta clase actúa como un envoltorio (wrapper) sobre el servicio de API [ApiUrl].
 * Su responsabilidad es ejecutar las peticiones de red de forma asíncrona utilizando
 * corrutinas de Kotlin.
 *
 * ### Características:
 * - **Abstracción:** Aísla el uso de Retrofit del resto de la capa de datos.
 * - **Inyección de Dependencias:** Utiliza [Dagger Hilt] para recibir la instancia
 * configurada del servicio de red.
 *
 * @property apiService Interfaz de Retrofit que contiene las definiciones de los endpoints.
 */
class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiUrl
) : RemoteDataSource {

    /**
     * Ejecuta la petición de autenticación en el servidor.
     * @param data Objeto con las credenciales del usuario.
     * @return [Response] con los datos de sesión o error del servidor.
     */
    override suspend fun authenticationUsersApp(data: AuthRequest): Response<AuthResponse?> =
        apiService.authenticationUsersApp(data)

    /**
     * Obtiene una cadena de configuración para el control de la aplicación.
     * @return Un [String] opcional con la información de control.
     */
    override suspend fun storeAppControl(): String? = apiService.storeAppControl()

    /**
     * Solicita al servidor el listado de ubicaciones recolectadas.
     * @return [Response] que contiene una lista de objetos de ubicación.
     */
    override suspend fun obtainCollectedLocations(): Response<List<LocationsResponse?>> =
        apiService.obtainCollectedLocations()

    /**
     * Recupera el esquema de tablas necesario para la sincronización dinámica.
     * @return [Response] con la estructura de metadatos de las tablas.
     */
    override suspend fun getSchema(): Response<List<TablesResponse?>> =
        apiService.getSchema()
}