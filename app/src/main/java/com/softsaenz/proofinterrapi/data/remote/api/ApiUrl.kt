package com.softsaenz.proofinterrapi.data.remote.api

import com.softsaenz.proofinterrapi.data.remote.dtos.request.AuthRequest
import com.softsaenz.proofinterrapi.data.remote.dtos.response.AuthResponse
import com.softsaenz.proofinterrapi.data.remote.dtos.response.LocationsResponse
import com.softsaenz.proofinterrapi.data.remote.dtos.response.TablesResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Definición de los servicios de API REST para la aplicación.
 * * Esta interfaz es utilizada por Retrofit para generar la implementación de las
 * peticiones de red. Define los puntos de entrada para la seguridad,
 * configuración del framework y sincronización de datos operativos.
 * * ### Endpoints:
 * - **Seguridad:** Autenticación de usuarios y validación de credenciales.
 * - **Framework:** Consulta de parámetros de control de la aplicación.
 * - **Logística:** Obtención de catálogos de ubicaciones y esquemas de tablas.
 */
interface ApiUrl {

    /**
     * Envía las credenciales al servidor para iniciar una sesión.
     * @param data Objeto [AuthRequest] con usuario, password y metadatos.
     * @return [Response] que contiene el perfil del usuario y el Token JWT.
     */
    @POST("FtEntregaElectronica/MultiCanales/ApiSeguridadPruebas/api/Seguridad/AuthenticaUsuarioApp")
    suspend fun authenticationUsersApp(@Body data: AuthRequest): Response<AuthResponse?>

    /**
     * Consulta parámetros globales para el control de la versión de la App.
     * @return Una cadena de texto con la configuración o nulo si no está disponible.
     */
    @GET("apicontrollerpruebas/api/ParametrosFramework/ConsultarParametrosFramework/VPStoreAppControl")
    suspend fun storeAppControl(): String?

    /**
     * Recupera el listado de localidades habilitadas para procesos de recogida.
     * @return [Response] con una lista de modelos de respuesta de ubicación.
     */
    @GET("apicontrollerpruebas/api/ParametrosFramework/ObtenerLocalidadesRecogidas")
    suspend fun obtainCollectedLocations(): Response<List<LocationsResponse?>>

    /**
     * Obtiene la estructura SQL y metadatos para la creación de tablas locales.
     * @return [Response] con la lista de esquemas de tablas para sincronización.
     */
    @GET("apicontrollerpruebas/api/SincronizadorDatos/ObtenerEsquema/true")
    suspend fun getSchema(): Response<List<TablesResponse?>>
}