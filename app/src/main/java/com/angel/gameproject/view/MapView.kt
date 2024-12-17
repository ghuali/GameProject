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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase
import org.osmdroid.tileprovider.tilesource.XYTileSource

@Composable
fun MapView(onNavigateBack: () -> Unit) {

    val GoogleSat: OnlineTileSourceBase = object : XYTileSource(
        "Google-Sat",
        0, 19, 256, ".png", arrayOf(
            "https://mt0.google.com",
            "https://mt1.google.com",
            "https://mt2.google.com",
            "https://mt3.google.com",
        )
    )

        // Botón para volver a la vista principal
    Button(
        onClick = onNavigateBack,
        colors = ButtonDefaults.buttonColors(Color(0xFFFF5722))
    ) {
            Text("Volver a Inicio")
        }
    }
