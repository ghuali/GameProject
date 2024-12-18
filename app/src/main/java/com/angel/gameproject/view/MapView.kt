package com.angel.gameproject.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.angel.gameproject.viewModel.JuegosViewModel
import com.utsman.osmandcompose.DefaultMapProperties
import com.utsman.osmandcompose.ZoomButtonVisibility
import com.utsman.osmandcompose.rememberCameraState
import org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase
import org.osmdroid.tileprovider.tilesource.XYTileSource
import org.osmdroid.util.GeoPoint
import org.osmdroid.util.MapTileIndex

@Composable
fun MapView(onNavigateBack: () -> Unit) {

        // Botón para volver a la vista principal
    Button(
        onClick = onNavigateBack,
        colors = ButtonDefaults.buttonColors(Color(0xFFFF5722))
    ) {
            Text("Volver a Inicio")
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
fun MyMapView(modifier: Modifier = Modifier, viewModel: JuegosViewModel) {

    // define camera state
    val cameraState = rememberCameraState {
        geoPoint = GeoPoint(28.957375205489004, -13.554245657440829)
        zoom = 17.0
    }

    var mapProperties by remember {
        mutableStateOf(DefaultMapProperties)
    }

    SideEffect {
        mapProperties = mapProperties
            //.copy(isTilesScaledToDpi = true)
            //.copy(tileSources = TileSourceFactory.MAPNIK)
            .copy(tileSources = GoogleSat)
            .copy(isEnableRotationGesture = true)
            .copy(zoomButtonVisibility = ZoomButtonVisibility.SHOW_AND_FADEOUT)
    }
}
