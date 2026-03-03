package com.softsaenz.proofinterrapi.data.remote.utilsApi
/**
 * Un contenedor genérico para datos que provienen de una fuente remota o local.
 * * Se utiliza para comunicar el estado de una operación asíncrona a la interfaz de usuario,
 * permitiendo manejar estados de carga, éxito y error de manera estructurada.
 *
 * @param T El tipo de datos que contiene el recurso (ej. [AuthResponse]).
 * @property data El valor resultante de la operación si fue exitosa.
 * @property message Un mensaje descriptivo en caso de error o advertencia.
 */
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    /** Indica que la operación fue exitosa y contiene los datos solicitados. */
    class Success<T>(data: T) : Resource<T>(data)
    /** Indica que ocurrió un problema durante la operación. */
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    /** Indica que la operación está en curso (ej. esperando respuesta del VPS de OVH). */
    class Loading<T>(data: T? = null) : Resource<T>(data)
}