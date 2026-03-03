package com.softsaenz.proofinterrapi.data.remote.source

import com.softsaenz.proofinterrapi.data.remote.api.ApiUrl
import com.softsaenz.proofinterrapi.data.remote.dtos.request.AuthRequest
import com.softsaenz.proofinterrapi.data.remote.dtos.response.AuthResponse
import com.softsaenz.proofinterrapi.data.remote.dtos.response.LocationsResponse
import com.softsaenz.proofinterrapi.data.remote.dtos.response.TablesResponse
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiUrl
): RemoteDataSource {
    override suspend fun authenticationUsersApp(data: AuthRequest): Response<AuthResponse?> {
        return apiService.authenticationUsersApp(data)
    }

    override suspend fun storeAppControl(): String? {
        return apiService.storeAppControl()
    }

    override suspend fun obtainCollectedLocations(): Response<List<LocationsResponse?>> {
        return apiService.obtainCollectedLocations()
    }

    override suspend fun getSchema(): Response<List<TablesResponse?>> {
        return apiService.getSchema()
    }
}