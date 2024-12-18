package com.angel.gameproject.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.angel.gameproject.R
import com.angel.gameproject.viewModel.JuegosViewModel
import com.utsman.osmandcompose.DefaultMapProperties
import com.utsman.osmandcompose.OpenStreetMap
import com.utsman.osmandcompose.ZoomButtonVisibility
import com.utsman.osmandcompose.rememberCameraState
import org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.tileprovider.tilesource.XYTileSource
import org.osmdroid.util.GeoPoint
import org.osmdroid.util.MapTileIndex

@Composable
fun MapView(onNavigateBack: () -> Unit) {
    // Contenedor principal
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD))
    ) {
        // Mapa interactivo
        MyMapView(
            modifier = Modifier.fillMaxSize()
        )

        // Botón flotante para volver
        FloatingActionButton(
            onClick = onNavigateBack,
            containerColor = Color(0xFFF700FF),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Text("Volver", color = Color.White)
        }
    }
}


val GoogleSat: OnlineTileSourceBase = object : XYTileSource(
    "Google-Sat",
    0, 19, 256, ".png", arrayOf(
        "https://mt0.google.com",
        "https://mt1.google.com",
        "https://mt2.google.com",
        "https://mt3.google.com"
    )
) { override fun getTileURLString(aTile: Long): String {
    return baseUrl + "/vt/lyrs=s&x=" + MapTileIndex.getX(aTile) + "&y=" + MapTileIndex.getY(
        aTile)
}
}

@Composable
fun MyMapView(modifier: Modifier = Modifier) {

    TileSourceFactory.addTileSource(GoogleSat)


    val cameraState = rememberCameraState {
        geoPoint = GeoPoint(28.992986562609960,  -13.495383991854991)
        zoom = 15.0 // optional, default is 5.0
    }

    // define properties with remember with default value
    var mapProperties by remember {
        mutableStateOf(DefaultMapProperties)
    }

    // setup mapProperties in side effect
    SideEffect {
        mapProperties = mapProperties
            //.copy(isTilesScaledToDpi = true)
            .copy(tileSources = TileSourceFactory.MAPNIK)
            .copy(isEnableRotationGesture = true)
            .copy(zoomButtonVisibility = ZoomButtonVisibility.SHOW_AND_FADEOUT)
    }

    OpenStreetMap(
        modifier = Modifier.fillMaxSize(),
        cameraState = cameraState,
        properties = mapProperties // add properties
    ){
        val marcador by remember { mutableStateOf(R.drawable.icono) }
    }


}