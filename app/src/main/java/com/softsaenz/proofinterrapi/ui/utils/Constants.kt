package com.softsaenz.proofinterrapi.ui.utils

/**
 * Almacén centralizado de literales y mensajes estáticos de la aplicación.
 *
 * Este objeto [Constants] se utiliza para mantener la consistencia de los mensajes
 * de validación y etiquetas en toda la interfaz de usuario, facilitando futuras
 * tareas de internacionalización (L10n) o cambios de redacción solicitados por negocio.
 * * ### Buenas Prácticas:
 * 1. **Centralización:** Evita el uso de "Hardcoded Strings" en los ViewModels o Composables.
 * 2. **Mantenibilidad:** Un solo cambio aquí actualiza todas las alertas de la App.
 *
 * @author Janes Saenz Puerta
 */
object Constants {

    /** * Mensaje de error mostrado cuando el campo de nombre de la aplicación
     * en formularios de registro o configuración se encuentra vacío.
     */
    const val NAME_APPLICATION = "El Nombre Aplicacion es obligatorio"

    /** * Validación requerida para el campo de identificación o alias del hincha.
     * Se dispara cuando el usuario intenta avanzar sin ingresar sus credenciales.
     */
    const val USERS = "El Usuario es obligatorio"
    const val UNKNOWNHOST = "Sin conexión al servidor"
    const val GENERIC = "Ocurrió un error inesperado al sincronizar"
    const val  IOEXCEPTION = "No se pudo conectar al servidor. Revisa tu internet."
}