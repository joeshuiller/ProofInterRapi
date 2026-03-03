package com.softsaenz.proofinterrapi.data.remote.api

import com.softsaenz.proofinterrapi.data.remote.dtos.request.AuthRequest
import com.softsaenz.proofinterrapi.data.remote.dtos.response.AuthResponse
import com.softsaenz.proofinterrapi.data.remote.dtos.response.LocationsResponse
import com.softsaenz.proofinterrapi.data.remote.dtos.response.TablesResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiUrl {
    @POST(
        "FtEntregaElectronica/MultiCanales/ApiSeguridadPruebas/api/Seguridad/AuthenticaUsuarioApp"
    )
    suspend fun authenticationUsersApp( @Body data: AuthRequest): Response<AuthResponse?>

    @GET(
        "apicontrollerpruebas/api/ParametrosFramework/ConsultarParametrosFramework/VPStoreAppControl"
    )
    suspend fun storeAppControl(): String?

    @GET(
        "apicontrollerpruebas/api/ParametrosFramework/ObtenerLocalidadesRecogidas"
    )
    suspend fun obtainCollectedLocations(): Response<List<LocationsResponse?>>

    @GET(
        "apicontrollerpruebas/api/SincronizadorDatos/ObtenerEsquema/true"
    )
    suspend fun getSchema(): Response<List<TablesResponse?>>
}