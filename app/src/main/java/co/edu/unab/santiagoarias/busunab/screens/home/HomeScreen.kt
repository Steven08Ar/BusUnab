package co.edu.unab.santiagoarias.busunab.screens.home

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column



import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import co.edu.unab.santiagoarias.busunab.R
import co.edu.unab.santiagoarias.busunab.screens.login.LoginScreen

@Composable
fun HomeScreen(navController: NavController) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
        ) { innerPadding ->
            Column(  modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(painter = painterResource(id = R.drawable.logo_unab), contentDescription = null, modifier = Modifier.size(200.dp))
                    Text(
                        fontSize = 40.sp,
                        text = "BUS"
                    )
                    Text(
                        fontSize = 40.sp,
                        text = "UNAB"
                    )
                    Text(text = "Tu ruta más rápida al campus")
                }

                Card (
                    modifier = Modifier.weight(1f),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF22263E)
                    ),
                    shape = RoundedCornerShape(topStart = 26.dp,  topEnd = 26.dp)

                ) {
                    Column(
                        modifier = Modifier.padding(80.dp),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Button(
                            onClick = {
                                navController.navigate("login_screen")},
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF4942CE
                                )
                            )
                        ) {
                            Text(text = "Ingresar")
                        }
                        OutlinedButton(
                            onClick = {
                                navController.navigate("register_screen")
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Registrarse",
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
}


