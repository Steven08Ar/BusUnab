package co.edu.unab.santiagoarias.busunab.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onNavigateBack: () -> Unit,
    onRegisterClick: () -> Unit,
    onLoginSuccess: () -> Unit // Nueva función para navegar al éxito del login
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1D1D3E))
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Botón de regreso
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "← Back",
                color = Color.White,
                fontSize = 18.sp,
                modifier = Modifier.clickable { onNavigateBack() }
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Título
        Text(
            text = "Ingresar",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Ingresa para continuar",
            color = Color.LightGray,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Campo de entrada de correo electrónico
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo Electrónico") },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFF2D2D5E),
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.LightGray
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de entrada de contraseña
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFF2D2D5E),
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.LightGray
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Mostrar mensaje de error si ocurre un problema en la autenticación
        errorMessage?.let { message ->
            Text(
                text = message,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        // Botón de inicio de sesión
        Button(
            onClick = {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            onLoginSuccess() // Llamar a esta función si el inicio de sesión es exitoso
                        } else {
                            errorMessage = "Error de autenticación: ${task.exception?.localizedMessage}"
                        }
                    }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFF4B4BFF))
        ) {
            Text(text = "Ingresar", color = Color.White, fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Texto de recuperación de contraseña
        Text(
            text = "¿Olvidaste tu contraseña? Recupérala!",
            color = Color.LightGray,
            modifier = Modifier.clickable { /* Acción de recuperar contraseña */ }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Texto para registrarse
        Text(
            text = "¿Aún no tienes cuenta? Regístrate!",
            color = Color.LightGray,
            modifier = Modifier.clickable { onRegisterClick() }
        )
    }
}