package com.softsaenz.proofinterrapi.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.softsaenz.proofinterrapi.ui.screens.HomeScreen
import com.softsaenz.proofinterrapi.ui.screens.LocationScreen
import com.softsaenz.proofinterrapi.ui.screens.LoginScreen
import com.softsaenz.proofinterrapi.ui.screens.SplashScreen
import com.softsaenz.proofinterrapi.ui.screens.TablesScreen
import com.softsaenz.proofinterrapi.ui.utils.Screen
import com.softsaenz.proofinterrapi.ui.viewModels.HomeViewModel
import com.softsaenz.proofinterrapi.ui.viewModels.LocationViewModel
import com.softsaenz.proofinterrapi.ui.viewModels.LoginViewModel
import com.softsaenz.proofinterrapi.ui.viewModels.TablesViewModel

/**
 * Grafo de navegación centralizado (NavGraph).
 * * Se encarga de la orquestación de pantallas y la gestión del backstack.
 * Aplica lógica de navegación segura para evitar duplicidad de destinos en la pila.
 *
 * @author Janes Saenz Puerta
 * @param navController Controlador para gestionar las transiciones.
 * @param modifier Ajustes de layout provenientes del contenedor padre.
 */
@Composable
fun RouteNavigation(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = Screen.Splash.route
    ) {
        // --- SECCIÓN: BIENVENIDA ---
        composable(Screen.Splash.route) {
            SplashScreen(onTransition = { destino ->
                navController.navigate(destino) {
                    // Limpieza total del splash para que el usuario no pueda volver atrás
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            })
        }

        // --- SECCIÓN: AUTENTICACIÓN ---
        composable(Screen.Auth.route) {
            val viewModel = hiltViewModel<LoginViewModel>()
            LoginScreen(
                viewModel = viewModel,
                onLoginSuccess = {
                    navController.navigate(Screen.MainTabs.route) {
                        // Al entrar al Main, limpiamos el flujo de Login
                        popUpTo(Screen.Auth.route) { inclusive = true }
                    }
                }
            )
        }

        // --- SECCIÓN: MÓDULOS PRINCIPALES ---
        composable(Screen.MainTabs.route) {
            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(
                viewModel = viewModel,
                onLocationClick = {
                    navController.navigate(Screen.Locations.route) {
                        // Al entrar al Main, limpiamos el flujo de Login
                        popUpTo(Screen.Locations.route) { inclusive = true }
                    }
                },
                onClick = {
                    navController.navigate(Screen.Tables.route) {
                        // Al entrar al Main, limpiamos el flujo de Login
                        popUpTo(Screen.Tables.route) { inclusive = true }
                    }
                }

            )
        }

        composable(Screen.Tables.route) {
            val viewModel = hiltViewModel<TablesViewModel>() // Considerar si requiere su propio ViewModel
            TablesScreen( // Asumiendo que existe una pantalla específica para tablas
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable(Screen.Locations.route) {
            val viewModel = hiltViewModel<LocationViewModel>()
            LocationScreen( // Asumiendo que existe una pantalla específica para locaciones
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}