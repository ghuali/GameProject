package com.angel.gameproject.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MapView() {
    // Aquí va el contenido de la vista de mapas
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text("Vista de Mapa")
        // Agrega tu lógica o componentes de mapa aquí
    }
}