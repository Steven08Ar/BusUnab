package co.edu.unab.santiagoarias.busunab

import android.content.Intent
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.*
import com.google.firebase.firestore.FirebaseFirestore

class AuthActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()


    private fun initializeFirebase(onInitialized: () -> Unit) {
        FirebaseApp.initializeApp(this)?.let {
            onInitialized()
        }
    }

    private fun saveUserDetailsToFirestore(user: FirebaseUser?) {
        user?.let {
            val newUser = hashMapOf(
                "email" to user.email,
            )

            db.collection("users").document(user.uid).set(newUser)
                .addOnSuccessListener {
                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error al guardar datos: ${e.message}", Toast.LENGTH_SHORT).show()
                }
         }
    }
}