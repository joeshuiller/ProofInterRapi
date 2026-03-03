package com.softsaenz.proofinterrapi.core

import com.softsaenz.proofinterrapi.BuildConfig
import com.softsaenz.proofinterrapi.data.remote.api.ApiUrl
import com.softsaenz.proofinterrapi.data.remote.utilsApi.ApiKeyInterceptor
import com.softsaenz.proofinterrapi.ui.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
/**
 * Módulo de inyección de dependencias (Hilt) para la capa de red.
 *
 * Configura y provee los componentes necesarios para la comunicación con la API REST.
 * Utiliza [Retrofit] como cliente de alto nivel y [OkHttpClient] para la gestión de
 * transporte, interceptores y tiempos de espera (timeouts).
 *
 * ### Componentes Clave:
 * - **BaseUrl:** Centralizada mediante [BuildConfig] para facilitar cambios de entorno.
 * - **Logging:** Interceptor de nivel [BODY] para trazabilidad total en desarrollo.
 * - **Seguridad:** Incluye un [ApiKeyInterceptor] para la autenticación de peticiones.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    /**
     * URL base del servidor, extraída de la configuración de compilación.
     */
    const val baseUrl = BuildConfig.URL_API

    /**
     * Provee un interceptor para registrar los logs de las peticiones y respuestas.
     * @return [HttpLoggingInterceptor] configurado a nivel de cuerpo (BODY).
     */
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    /**
     * Provee el cliente HTTP configurado con interceptores y timeouts.
     * @param loggingInterceptor Interceptor de logs para depuración.
     * @return [OkHttpClient] con 30 segundos de espera en conexión y lectura.
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(ApiKeyInterceptor())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    /**
     * Provee la instancia de Retrofit configurada con GSON para el parseo de JSON.
     * @param okHttpClient Cliente HTTP pre-configurado.
     */
    @Provides
    @Singleton
    fun provideVersionRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    /**
     * Crea y provee la implementación de la interfaz de la API.
     * @param retrofit Instancia de Retrofit inyectada.
     * @return Implementación de [ApiUrl] lista para ser usada en los DataSources.
     */
    @Provides
    @Singleton
    fun provideApiAuth( retrofit: Retrofit): ApiUrl {
        return retrofit.create(ApiUrl::class.java)
    }
}