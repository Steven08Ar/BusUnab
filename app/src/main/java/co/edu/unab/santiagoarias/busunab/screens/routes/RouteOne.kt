package co.edu.unab.santiagoarias.busunab.screens.routes

import android.os.Bundle
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

@Composable
fun RouteOne(onReady: (GoogleMap) -> Unit) {
    val context = LocalContext.current
    val mapView = remember { MapView(context) }
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    DisposableEffect(lifecycle, mapView) {
        val lifecycleObserver = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> mapView.onCreate(Bundle())
                Lifecycle.Event.ON_START -> mapView.onStart()
                Lifecycle.Event.ON_RESUME -> mapView.onResume()
                Lifecycle.Event.ON_PAUSE -> mapView.onPause()
                Lifecycle.Event.ON_STOP -> mapView.onStop()
                Lifecycle.Event.ON_DESTROY -> mapView.onDestroy()
                else -> {}
            }
        }

        lifecycle.addObserver(lifecycleObserver)

        onDispose {
            lifecycle.removeObserver(lifecycleObserver)
            mapView.onDestroy()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AndroidView(factory = {
            mapView.apply {
                getMapAsync { googleMap ->
                    val zoomLevel = 15f
                    val unab = LatLng(7.116816, -73.105240)
                    val puenteUnab = LatLng(7.116832491307159, -73.10436903333576)
                    val csu = LatLng(7.11261930192156, -73.10537010648864)
                    val terraOne = LatLng(7.110894390437146, -73.10605202274883)
                    val terraTwo = LatLng(7.109698512210283, -73.10541800664015)
                    val clinicaBga = LatLng(7.110909120332861, -73.10997509015263)
                    val gratamira = LatLng(7.115523012222855, -73.11084366726257)
                    val sanPio = LatLng(7.118520445030375, -73.11126629280423)
                    val casona = LatLng(7.121234415427968, -73.11103005907758)
                    val arturoCalle = LatLng(7.115639606078648, -73.10773895309947)

                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(unab, zoomLevel))

                    googleMap.addMarker(
                        MarkerOptions()
                            .position(unab)
                            .title("UNAB")
                            .snippet("En la UUU")
                    )
                    googleMap.addMarker(
                        MarkerOptions()
                            .position(puenteUnab)
                            .title("Parada 1")
                            .snippet("Puente peatonal UNAB")
                    )
                    googleMap.addMarker(
                        MarkerOptions()
                            .position(csu)
                            .title("Parada 2")
                            .snippet("Centro de Servicios Universitarios")
                    )
                    googleMap.addMarker(
                        MarkerOptions()
                            .position(terraOne)
                            .title("Parada 3")
                            .snippet("Calle 45 con Cra. 55")
                    )
                    googleMap.addMarker(
                        MarkerOptions()
                            .position(terraTwo)
                            .title("Parada 4")
                            .snippet("Calle 56 con Cra. 46")
                    )
                    googleMap.addMarker(
                        MarkerOptions()
                            .position(clinicaBga)
                            .title("Parada 5")
                            .snippet("Clínica Bucaramanga")
                    )
                    googleMap.addMarker(
                        MarkerOptions()
                            .position(gratamira)
                            .title("Parada 6")
                            .snippet("Gratamira")
                    )
                    googleMap.addMarker(
                        MarkerOptions()
                            .position(sanPio)
                            .title("Parada 7")
                            .snippet("Parque San Pio")
                    )
                    googleMap.addMarker(
                        MarkerOptions()
                            .position(casona)
                            .title("Parada 8")
                            .snippet("Campus Rafael Ardila Duarte")
                    )
                    googleMap.addMarker(
                        MarkerOptions()
                            .position(arturoCalle)
                            .title("Parada 9")
                            .snippet("Arturo Calle")
                    )
                    onReady(googleMap)
                }
            }
        }, modifier = Modifier.fillMaxSize())
    }
}
