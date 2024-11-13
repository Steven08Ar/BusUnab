package co.edu.unab.santiagoarias.busunab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.edu.unab.santiagoarias.busunab.screens.routes.RouteOne
import co.edu.unab.santiagoarias.busunab.screens.routes.RouteTwo
import co.edu.unab.santiagoarias.busunab.ui.theme.BusUnabTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusUnabTheme {
                val navController = rememberNavController()
                var selectedItem by remember { mutableStateOf(0) }
                var expanded by remember { mutableStateOf(false) } // Para controlar el estado del menú desplegable

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Bus UNAB") },
                            actions = {
                                Box {
                                    IconButton(onClick = { expanded = true }) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.logo_unab), 
                                            contentDescription = "Perfil",
                                            tint = Color.White
                                        )
                                    }
                                    DropdownMenu(
                                        expanded = expanded,
                                        onDismissRequest = { expanded = false }
                                    ) {
                                        DropdownMenuItem(
                                            text = { Text("Perfil") },
                                            onClick = {
                                                // Acción para ver el perfil del usuario
                                                expanded = false
                                            }
                                        )
                                        DropdownMenuItem(
                                            text = { Text("Cerrar sesión") },
                                            onClick = {
                                                // Acción para cerrar sesión
                                                expanded = false
                                            }
                                        )
                                    }
                                }
                            },
                            colors = TopAppBarDefaults.smallTopAppBarColors(
                                containerColor = Color(0xFF1D1D3E),
                                titleContentColor = Color.White
                            )
                        )
                    },
                    bottomBar = {
                        NavigationBar(
                            containerColor = Color(0xFF1D1D3E),
                        ) {
                            NavigationBarItem(
                                selected = selectedItem == 0,
                                onClick = {
                                    navController.navigate("routeone_screen") {
                                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                    selectedItem = 0
                                },
                                icon = {
                                    Image(
                                        painter = painterResource(id = R.drawable.bus),
                                        contentDescription = "Ruta Uno",
                                        modifier = Modifier.size(24.dp),
                                        colorFilter = ColorFilter.tint(Color.White)
                                    )
                                },
                                label = { Text("Ruta 1") }
                            )
                            NavigationBarItem(
                                selected = selectedItem == 1,
                                onClick = {
                                    navController.navigate("routetwo_screen") {
                                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                    selectedItem = 1
                                },
                                icon = {
                                    Image(
                                        painter = painterResource(id = R.drawable.bus),
                                        contentDescription = "Ruta Dos",
                                        modifier = Modifier.size(24.dp),
                                        colorFilter = ColorFilter.tint(Color.White)
                                    )
                                },
                                label = { Text("Ruta 2") }
                            )
                        }
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        NavHost(navController = navController, startDestination = "routeone_screen") {
                            composable("routeone_screen") {
                                RouteOne(onReady = { })
                            }
                            composable("routetwo_screen") {
                                RouteTwo(onReady = {  })
                            }
                        }
                    }
                }
            }
        }
    }
}
