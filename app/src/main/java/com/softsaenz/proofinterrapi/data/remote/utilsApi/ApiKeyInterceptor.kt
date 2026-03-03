package com.softsaenz.proofinterrapi.data.remote.utilsApi

import com.softsaenz.proofinterrapi.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
/**
 * Interceptor de OkHttp encargado de la inyección automática de cabeceras de seguridad y contexto.
 *
 * Esta clase actúa en la capa de transporte de red, modificando la petición original
 * para añadir los parámetros de autenticación y origen requeridos por el backend.
 * * ### Seguridad y Configuración:
 * Los valores se extraen directamente de [BuildConfig], lo que permite manejar
 * diferentes credenciales según el entorno (Desarrollo, QA, Producción) sin
 * modificar el código fuente.
 *
 * @see Interceptor
 * @see OkHttpClient
 */
class ApiKeyInterceptor() : Interceptor {
    val users = BuildConfig.USERS
    val id = BuildConfig.ID
    val idUsers = BuildConfig.ID_USERS
    val idCenter = BuildConfig.ID_CENTER_SERVICES
    val nameCenter = BuildConfig.NAME_CENTER_SERVICES
    val idApplication = BuildConfig.ID_APPLICATION


    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestWithHeader = originalRequest.newBuilder()
            .header("Usuario", users)
            .header("Identificacion", id)
            .header("Accept", "text/json")
            .header("IdUsuario", idUsers)
            .header("IdCentroServicio", idCenter)
            .header("NombreCentroServicio", nameCenter)
            .header("IdAplicativoOrigen", idApplication)
            .header("Content-Type", "application/json")
            .method(originalRequest.method, originalRequest.body)
            .build()

        return chain.proceed(requestWithHeader)
    }
}