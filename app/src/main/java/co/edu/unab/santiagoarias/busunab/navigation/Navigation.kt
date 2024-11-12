package co.edu.unab.santiagoarias.busunab.navigation

import android.window.SplashScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.edu.unab.santiagoarias.busunab.screens.SplashScreen
import co.edu.unab.santiagoarias.busunab.screens.home.Home

import co.edu.unab.santiagoarias.busunab.screens.login.LoginScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = Screens.SplashScreen.name
    ){
        composable(Screens.SplashScreen.name){
            SplashScreen(navController = navController)
        }

        composable(Screens.LoginScreen.name){
            LoginScreen()
        }

        composable(Screens.HomeScreen.name){
            Home(navController = navController)
        }

    }
}