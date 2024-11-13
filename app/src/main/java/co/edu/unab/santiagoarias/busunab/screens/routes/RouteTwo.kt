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
fun RouteTwo(onReady: (GoogleMap) -> Unit) {
    val context = LocalContext.current
    val mapView = remember { MapView(context) }
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    // Manage the MapView's lifecycle
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

    // Display the MapView
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        AndroidView(factory = {
            mapView.apply {
                getMapAsync { googleMap ->
                    val zoomLevel = 15f
                    val unab = LatLng(7.116816, -73.105240)
                    val clinicabga = LatLng(7.111070694562188, -73.1099959549434)
                    val monterrey = LatLng(7.099716677835819, -73.10820209114532)
                    val diamante = LatLng(7.090265371960638, -73.10893950564171)
                    val provenza = LatLng(  7.088531167835387, -73.10828041380849)
                    val ciencias = LatLng(7.071381295834735, -73.11340044272866)
                    val caracoli = LatLng(7.071798384561213, -73.10508361627966)
                    val cacique = LatLng(7.099687910743462, -73.10710933162277)
                    val garabatos = LatLng(7.114640723884454, -73.10817294502381)
                    val arturoCalle = LatLng(7.117517469924212, -73.10756867984698)
                    val susuki = LatLng(7.091072470126063, -73.10810746666378)
                    val cañaveral = LatLng(77.064911645756182, -73.10026000463817)
                    val puenteunab = LatLng(7.116832491307159, -73.10436903333576)

                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(unab, zoomLevel))

                    googleMap.addMarker(
                        MarkerOptions()
                            .position(unab)
                            .title("UNAB")
                            .snippet("En la UUU")
                    )
                    googleMap.addMarker(
                        MarkerOptions()
                            .position(clinicabga)
                            .title("Parada 1")
                            .snippet("Clínica Bucaramanga")
                    )
                    googleMap.addMarker(
                        MarkerOptions()
                            .position(monterrey)
                            .title("Parada 2")
                            .snippet("Torres de Monterrey")
                    )
                    googleMap.addMarker(
                        MarkerOptions()
                            .position(diamante)
                            .title("Parada 3")
                            .snippet("Diamante II")
                    )
                    googleMap.addMarker(
                        MarkerOptions()
                            .position(provenza)
                            .title("Parada 4")
                            .snippet("Puente Provenza")
                    )
                    googleMap.addMarker(
                        MarkerOptions()
                            .position(ciencias)
                            .title("Parada 5")
                            .snippet("Facultad de Ciencias de la Salud")
                    )
                    googleMap.addMarker(
                        MarkerOptions()
                            .position(caracoli)
                            .title("Parada 6")
                            .snippet("CC. Parque Caracolí")
                    )
                    googleMap.addMarker(
                        MarkerOptions()
                            .position(cacique)
                            .title("Parada 7")
                            .snippet("CC. Cacique")
                    )
                    googleMap.addMarker(
                        MarkerOptions()
                            .position(garabatos)
                            .title("Parada 8")
                            .snippet("Garabatos")
                    )
                    googleMap.addMarker(
                        MarkerOptions()
                            .position(arturoCalle)
                            .title("Parada 9")
                            .snippet("Arturo Calle")
                    )
                    googleMap.addMarker(
                        MarkerOptions()
                            .position(susuki)
                            .title("Parada 9")
                            .snippet("Motocenter Suzuki")
                    )
                    googleMap.addMarker(
                        MarkerOptions()
                            .position(cañaveral)
                            .title("Parada 9")
                            .snippet("CC. Cañaveral")
                    )
                    googleMap.addMarker(
                        MarkerOptions()
                            .position(puenteunab)
                            .title("Parada 9")
                            .snippet("Puente Peatonal UNAB")
                    )
                    onReady(googleMap)
                }
            }
        }, modifier = Modifier.fillMaxSize())
    }
}