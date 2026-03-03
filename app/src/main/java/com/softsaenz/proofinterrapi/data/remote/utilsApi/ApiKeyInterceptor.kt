package com.softsaenz.proofinterrapi.data.remote.utilsApi

import com.softsaenz.proofinterrapi.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

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