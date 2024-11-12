package co.edu.unab.santiagoarias.busunab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import co.edu.unab.santiagoarias.busunab.screens.SplashScreen
import co.edu.unab.santiagoarias.busunab.screens.home.Home
import co.edu.unab.santiagoarias.busunab.screens.login.LoginScreen
import com.google.android.gms.maps.GoogleMap
import com.google.maps.android.compose.GoogleMap

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "splash_screen") {
                composable("splash_screen") { SplashScreen(navController) }
                composable("login_screen") { LoginScreen() }
                composable("home_screen") { Home(navController) }
                composable("google_maps") { MyGoogleMaps() }
            }
        }
    }
}

@Composable
fun MyGoogleMaps(){
    GoogleMap(modifier = Modifier.fillMaxSize())
}