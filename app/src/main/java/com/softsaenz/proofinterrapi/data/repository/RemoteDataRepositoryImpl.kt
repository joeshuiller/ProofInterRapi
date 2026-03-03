package com.softsaenz.proofinterrapi.data.repository

import com.softsaenz.proofinterrapi.data.remote.mapper.AuthRemoteDataMapper
import com.softsaenz.proofinterrapi.data.remote.dtos.response.AuthResponse
import com.softsaenz.proofinterrapi.data.remote.dtos.response.LocationsResponse
import com.softsaenz.proofinterrapi.data.remote.dtos.response.TablesResponse
import com.softsaenz.proofinterrapi.data.remote.source.RemoteDataSource
import com.softsaenz.proofinterrapi.data.remote.utilsApi.Resource
import com.softsaenz.proofinterrapi.domain.dto.AppControlDTO
import com.softsaenz.proofinterrapi.domain.dto.AuthDTO
import com.softsaenz.proofinterrapi.domain.repository.RemoteDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject
import kotlin.collections.List
import kotlin.collections.map
/**
 * Implementación concreta del contrato [RemoteDataRepository].
 *
 * Esta clase orquestra la comunicación con el servidor remoto a través de [RemoteDataSource].
 * Su responsabilidad principal es gestionar el ciclo de vida de las peticiones de red,
 * envolviendo los resultados en un estado [Resource] (Loading, Success, Error).
 *
 * ### Características:
 * - **Mapeo de Capas:** Utiliza [AuthRemoteDataMapper] para desacoplar los modelos de red de los modelos de dominio.
 * - **Manejo de Reactividad:** Devuelve [Flow] para permitir una observación asíncrona de los datos.
 * - **Seguridad de Hilos:** Garantiza que todas las llamadas de red se ejecuten en el despachador [Dispatchers.IO].
 *
 * @property remoteData Fuente de datos remota (Retrofit Service).
 * @property authMapper Utilidad para convertir DTOs de dominio a modelos de petición de API.
 */
class RemoteDataRepositoryImpl @Inject constructor(
    private val remoteData: RemoteDataSource,
    private val authMapper: AuthRemoteDataMapper
): RemoteDataRepository {
    override suspend fun authenticationUsersApp(data: AuthDTO): Flow<Resource<AuthResponse?>> =
        flow {
            emit(Resource.Loading())
            try {
                val mapper = authMapper.fromEntity(data)
                val dto: Response<AuthResponse?> = remoteData.authenticationUsersApp(mapper)
                if (dto.isSuccessful) {
                    emit(Resource.Success(dto.body()))
                } else {
                    emit(Resource.Error("El servidor respondió con éxito pero sin datos"))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: "Unknown Error"))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun storeAppControl():  Flow<Resource<AppControlDTO?>> =
        flow {
            emit(Resource.Loading())
            try {
                val dto = remoteData.storeAppControl()
                val mapper = dto?.let { authMapper.toResponse(it) }
                emit(Resource.Success(mapper))
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: "Unknown Error"))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun obtainCollectedLocations(): Flow<Resource<List<LocationsResponse?>>> =
        flow {
            emit(Resource.Loading())
            try {
                val dto: Response<List<LocationsResponse?>> = remoteData.obtainCollectedLocations()
                if (dto.isSuccessful) {
                    emit(Resource.Success(dto.body()!!))
                } else {
                    emit(Resource.Error("El servidor respondió con éxito pero sin datos"))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: "Unknown Error"))
            }
        }.flowOn(Dispatchers.IO)


    override suspend fun getSchema(): Flow<Resource<List<TablesResponse?>>> =
        flow {
            emit(Resource.Loading())
            try {
                val dto: Response<List<TablesResponse?>> = remoteData.getSchema()
                if (dto.isSuccessful) {
                    emit(Resource.Success(dto.body()!!))
                } else {
                    emit(Resource.Error("El servidor respondió con éxito pero sin datos"))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: "Unknown Error"))
            }
        }.flowOn(Dispatchers.IO)
}