package com.softsaenz.proofinterrapi.ui.utils

/**
 * Representa los destinos de navegación de la aplicación de forma tipada.
 * Ayuda a evitar errores de escritura y centraliza las claves del grafo.
 */
sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Auth : Screen("auth")
    object MainTabs : Screen("main_tabs")
    object Tables : Screen("tables")
    object Locations : Screen("locations")
}