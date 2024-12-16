package com.angel.gameproject.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
    var selectedTipoJuegos by remember { mutableStateOf("") }

    var expanded by remember { mutableStateOf(false) }


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
            value = newJuegosNombre,
            onValueChange = { newJuegosNombre = it },
            label = { Text("Nombre del juego") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFFFFFFFF),
                focusedContainerColor = Color(0xFFFFFFFF)
            )
        )
        Text("Añadir Precio del juego Nuevo", fontSize = 20.sp)
        OutlinedTextField(
            value = newJuegosPrecio,
            onValueChange = { newJuegosPrecio = it },
            label = { Text("Precio del juego") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFFFFFFFF),
                focusedContainerColor = Color(0xFFFFFFFF)
            )
        )
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
        }
    }
}