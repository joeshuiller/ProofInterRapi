package com.softsaenz.proofinterrapi.ui.utils

import com.google.gson.Gson


/**
 * Utilidad Singleton para la serialización y deserialización de objetos en formato JSON.
 *
 * Este objeto centraliza el uso de [Gson] para garantizar una configuración consistente
 * en toda la aplicación. Se utiliza principalmente para transformar Entidades y DTOs
 * en cadenas de texto legibles por la base de datos local o servicios web.
 * * ### Responsabilidades:
 * 1. **Serialización:** Convertir objetos complejos de Kotlin en un [String] JSON.
 * 2. **Deserialización Segura:** Recuperar objetos desde un [String], manejando errores de parseo de forma silenciosa.
 *
 * @author Janes Saenz Puerta
 */
object JsonParser {

    /** Instancia única de [Gson] compartida por toda la aplicación. */
    val gson = Gson()

    /**
     * Convierte cualquier objeto de datos en su representación JSON.
     *
     * @param data El objeto que se desea serializar (ej. [AuthResponse]).
     * @return Una cadena de texto en formato JSON.
     * @param T El tipo del objeto de entrada.
     */
    fun <T> toJson(data: T): String = gson.toJson(data)

    /**
     * Intenta convertir una cadena JSON de vuelta a un objeto de tipo [T].
     *
     * Utiliza el modificador [inline] y [reified] para capturar el tipo de clase
     * en tiempo de ejecución, eliminando la necesidad de pasar `T::class.java` manualmente.
     * * ### Seguridad PII:
     * En caso de que el JSON esté corrupto o sea incompatible, la función captura la
     * excepción y retorna `null` para evitar cierres inesperados (Crashes) en producción.
     *
     * @param json La cadena de texto que contiene el JSON.
     * @return El objeto deserializado de tipo [T], o `null` si el proceso falla.
     */
    inline fun <reified T> fromJson(json: String): T? {
        return try {
            gson.fromJson(json, T::class.java)
        } catch (e: Exception) {
            // Nota: En un entorno DDD, se recomienda loguear este error mediante Timber.e()
            // solo en modo Debug (Vulnerabilidad 10062).
            null
        }
    }
}