package co.edu.unab.santiagoarias.busunab.screens.home

import android.window.SplashScreen
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun Home(navController : NavController){
    Text(text= "Estamos en Home")
}