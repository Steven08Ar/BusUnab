package co.edu.unab.santiagoarias.busunab.screens.register

import android.provider.CalendarContract.Colors
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import co.edu.unab.santiagoarias.busunab.navigation.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.time.format.TextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController, onNavigateBack: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    val auth = FirebaseAuth.getInstance()
    val context = LocalContext.current
    val db = FirebaseFirestore.getInstance()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1D1D3E))
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {


            Text(
                text = "← Back",
                color = Color.White,
                fontSize = 18.sp,
                modifier = Modifier.clickable { onNavigateBack() }
            )


       Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

        Text(
            text = "Crear una nueva cuenta",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo Electrónico") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color(0xFF2D2D5E),
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.LightGray,
                cursorColor = Color.White,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color(0xFF2D2D5E),
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.LightGray,
                cursorColor = Color.White,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = {
                Text("Confirmar Contraseña")
            },
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color(0xFF2D2D5E),
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.LightGray,
                cursorColor = Color.White,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        )



        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (password == confirmPassword) {
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val user = auth.currentUser
                                user?.let {
                                    val newUser = mapOf("email" to email, "userId" to user.uid)
                                    db.collection("users").document(user.uid).set(newUser)
                                        .addOnSuccessListener {
                                            Toast.makeText(
                                                context,
                                                "Registro exitoso",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            navController.navigate("login_screen")
                                        }
                                        .addOnFailureListener {
                                            Toast.makeText(
                                                context,
                                                "Error al registrar el usuario",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                }
                            } else {
                                Toast.makeText(
                                    context,
                                    "Error: ${task.exception?.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                } else {
                    Toast.makeText(context, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5D5FEF))
        ) {
            Text("Registrarse", color = Color.White)
        }

        Spacer(modifier = Modifier.height(10.dp))

        TextButton(onClick = {
            navController.navigate("login_screen")
        }) {
            Text("¿Ya tienes una cuenta? Ingresa Aquí", color = Color.White)
            }
        }
    }
}