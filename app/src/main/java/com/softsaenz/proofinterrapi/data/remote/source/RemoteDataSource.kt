package com.softsaenz.proofinterrapi.data.remote.source

import com.softsaenz.proofinterrapi.data.remote.dtos.request.AuthRequest
import com.softsaenz.proofinterrapi.data.remote.dtos.response.AuthResponse
import com.softsaenz.proofinterrapi.data.remote.dtos.response.LocationsResponse
import com.softsaenz.proofinterrapi.data.remote.dtos.response.TablesResponse
import retrofit2.Response

interface RemoteDataSource  {
    suspend fun authenticationUsersApp( data: AuthRequest): Response<AuthResponse?>
    suspend fun storeAppControl(): String?
    suspend fun obtainCollectedLocations(): Response<List<LocationsResponse?>>
    suspend fun getSchema(): Response<List<TablesResponse?>>
}