package co.edu.unab.santiagoarias.busunab.screens.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import co.edu.unab.santiagoarias.busunab.screens.*
import co.edu.unab.santiagoarias.busunab.screens.login.LoginScreen
import co.edu.unab.santiagoarias.busunab.screens.login.RecoverScreen
import co.edu.unab.santiagoarias.busunab.screens.register.RegisterScreen
import co.edu.unab.santiagoarias.busunab.screens.routes.RouteOne
import co.edu.unab.santiagoarias.busunab.screens.routes.RouteTwo

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "splash_screen") {
        composable("splash_screen") { SplashScreen(navController) }
        composable("login_screen") {
            LoginScreen(
                navController = navController,
                onNavigateBack = { navController.popBackStack() },
                onRegisterClick = { navController.navigate("register_screen") },
                onLoginSuccess = { navController.navigate("home_screen") }
            )
        }
        composable("register_screen") { RegisterScreen(navController = navController, onNavigateBack = { navController.popBackStack() }) }
        composable("recover_screen") { RecoverScreen(navController) }
        composable("home_screen") { HomeScreen(navController) }
        composable("routeone_screen") { RouteOne(onReady = { /* Opcional: inicialización de Google Map si es necesario */ }) }
        composable("routetwo_screen") { RouteTwo(onReady = { /* Opcional: inicialización de Google Map si es necesario */})}
        }
}