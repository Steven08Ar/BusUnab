package co.edu.unab.santiagoarias.busunab.navigation

import android.window.SplashScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.edu.unab.santiagoarias.busunab.screens.SplashScreen
import co.edu.unab.santiagoarias.busunab.screens.home.HomeScreen


import co.edu.unab.santiagoarias.busunab.screens.login.LoginScreen
import co.edu.unab.santiagoarias.busunab.screens.login.RecoverScreen
import co.edu.unab.santiagoarias.busunab.screens.register.RegisterScreen
import co.edu.unab.santiagoarias.busunab.screens.routes.RouteOne

@Composable
fun Navigation(){
    var navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = Screens.SplashScreen.name
    ){
        composable("splash_screen") { SplashScreen(navController) }
        composable("login_screen") {
            LoginScreen(
                navController = navController,
                onNavigateBack = { navController.popBackStack() },
                onRegisterClick = { navController.navigate("register_screen") },
                onLoginSuccess = { navController.navigate("home_screen") }
            )
        }
        composable("register_screen") { RegisterScreen(
            navController = navController, onNavigateBack = { navController.popBackStack() }) }
        composable("recover_screen") { RecoverScreen(navController) }
        composable("home_screen") { HomeScreen(navController) }
        composable("routeone_screen") { RouteOne(onReady = { googleMap -> }) }
        composable("routetwo_screen") { RouteOne(onReady = { googleMap -> }) }
    }
}