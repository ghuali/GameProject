package com.angel.gameproject.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.angel.gameproject.data.AppDatabase
import com.angel.gameproject.viewModel.JuegosViewModel

@Composable
fun view(juegosViewModel:JuegosViewModel) {
    val tipoJuegos by juegosViewModel.tipoJuegos.collectAsState()
    val plataformas by juegosViewModel.plataformas.collectAsState()
    val juegos by juegosViewModel.juegos.collectAsState()

    var newJuegosNombre by remember { mutableStateOf("") }
    var newJuegosPrecio by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF2196F3))
            .padding(16.dp)
            .padding(vertical = 50.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Añadir juego Nuevo", fontSize = 20.sp)
        OutlinedTextField(
            value =
        )
    }
}