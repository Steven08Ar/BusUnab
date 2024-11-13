package co.edu.unab.santiagoarias.busunab.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.request.ImageRequest
import coil.size.Size
import kotlinx.coroutines.delay
import co.edu.unab.santiagoarias.busunab.R

@Composable
fun SplashScreen(navController: NavController? = null) {
    // Usamos LaunchedEffect para activar la navegación después de un tiempo
    LaunchedEffect(Unit) {
        delay(4000)
        // Solo navega si navController no es nulo
        navController?.navigate("home_screen") {
            // Limpiar la pila para evitar volver a la pantalla de carga
            popUpTo("splash_screen") { inclusive = true }
        }
    }

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(R.drawable.pantalla_carga)
            .decoderFactory(GifDecoder.Factory())
            .size(Size.ORIGINAL)
            .build()
    )

    Image(
        painter = painter,
        contentDescription = "Pantalla de carga",
        modifier = Modifier
            .fillMaxWidth(),
        contentScale = ContentScale.Crop,
        alignment = Alignment.Center
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewSplashScreen() {
    SplashScreen()
}