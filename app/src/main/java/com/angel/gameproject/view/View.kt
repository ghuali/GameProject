package com.angel.gameproject.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    var
}