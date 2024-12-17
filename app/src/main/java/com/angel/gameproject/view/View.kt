package com.angel.gameproject.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.rememberCoroutineScope
import com.angel.gameproject.data.Juegos
import com.angel.gameproject.data.Plataformas
import com.angel.gameproject.viewModel.JuegoUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun view(juegosViewModel:JuegosViewModel) {
    val tipoJuegos by juegosViewModel.tipoJuegos.collectAsState()
    val plataformas by juegosViewModel.plataformas.collectAsState()
    val juegos by juegosViewModel.juegos.collectAsState()
    val juegosUI by juegosViewModel.juegosUI.collectAsState()
    val scope = rememberCoroutineScope()

    var newJuegosNombre by remember { mutableStateOf("") }
    var newJuegosPrecio by remember { mutableStateOf("") }
    var selectedTipoJuegos by remember { mutableStateOf("") }
    var newTipoId by remember { mutableStateOf(0) }
    var selectedPlataformas by remember { mutableStateOf("") }
    var newPlataformasId by remember { mutableStateOf(0) }

    var isEditing by remember { mutableStateOf(false) }

    var expanded by remember { mutableStateOf(false) }
    var expanded2 by remember { mutableStateOf(false) }

    var juegoSelecionado by remember { mutableStateOf<JuegoUI?>(null) }

    Scaffold(
        bottomBar = { BottomNavigationBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            view(juegosViewModel = juegosViewModel)
        }


    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF2196F3))
            .padding(16.dp)
            .padding(vertical = 50.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Añadir juego Nuevo", fontSize = 10.sp)
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
            OutlinedTextField(
                value = selectedTipoJuegos,
                onValueChange = {},
                label = { Text("Seleccionar Tipo") },
                readOnly = true,
                modifier = Modifier.menuAnchor().fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFFFFFFF),
                    focusedContainerColor = Color(0xFFFFFFFF)
                )
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                tipoJuegos.forEach { tipo ->
                    DropdownMenuItem(
                        text = { Text(tipo.nombre) },
                        onClick = {
                            selectedTipoJuegos = tipo.nombre
                            newTipoId = tipo.id
                            expanded = false
                        }
                    )
                }
            }
        }
        ExposedDropdownMenuBox(
            expanded = expanded2,
            onExpandedChange = { expanded2 = !expanded2 }
        ) {
            OutlinedTextField(
                value = selectedPlataformas,
                onValueChange = {},
                label = { Text("Seleccionar Plataformas") },
                readOnly = true,
                modifier = Modifier.menuAnchor().fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFFFFFFF),
                    focusedContainerColor = Color(0xFFFFFFFF)
                )
            )
            DropdownMenu(
                expanded = expanded2,
                onDismissRequest = { expanded2 = false }
            ) {
                plataformas.forEach { plataormas ->
                    DropdownMenuItem(
                        text = { Text(plataormas.tituloPlataformas) },
                        onClick = {
                            selectedPlataformas = plataormas.tituloPlataformas
                            newPlataformasId = plataormas.idPlataformas
                            expanded2 = false
                        }
                    )
                }
            }
        }
        Row {
            Button(
                onClick = {
                    scope.launch(Dispatchers.IO) {
                        val tipoid = newTipoId.toInt()
                        val plataformasid = newPlataformasId.toInt()
                        if (newJuegosNombre.isNotEmpty() && newJuegosPrecio.isNotEmpty() && newTipoId != 0 && newPlataformasId != 0) {
                            val newJuego = Juegos(
                                nombreJuegos = newJuegosNombre,
                                Precio = newJuegosPrecio,
                                idTipoJuegos = tipoid,
                                idPlataformas = plataformasid
                            )
                            juegosViewModel.addJuego(newJuego)

                        }
                    }
                },
                modifier = Modifier.padding(8.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFFFF9800))
            ) {
                Text("Añadir")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Lista de Juegos", fontSize = 22.sp)
        juegosUI.forEach { juego ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        juegoSelecionado = juego // Puedes actualizar estados aquí
                        isEditing = true
                        newJuegosNombre = juego.nombre
                        newJuegosPrecio = juego.precio
                        selectedTipoJuegos = juego.tipo
                        selectedPlataformas = juego.plataforma
                    }
                    .padding(vertical = 4.dp)
                ){

                    Text(text = "Juego: ${juego.nombre}, Precio: ${juego.precio}, Tipo: ${juego.tipo}, Plataforma: ${juego.plataforma}")
                }

            }
        if (isEditing && juegoSelecionado != null) {
            Spacer(modifier = Modifier.height(16.dp))
            Text("Editar su Juego seleccionado: ${juegoSelecionado?.nombre}")

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = {
                    juegoSelecionado?.let { juego ->
                        scope.launch {
                            // Llamada al ViewModel para eliminar el juego
                            juegosViewModel.deleteJuego(juego.id)
                            // Resetear la interfaz
                            juegoSelecionado = null
                            isEditing = false
                        }
                    }
                }, colors = ButtonDefaults.buttonColors(Color(0xFFFF9800))) {
                    Text("Borrar")
                }

                Button(onClick = {
                    scope.launch {
                        juegoSelecionado?.let { juego ->
                            // Verificar que los campos no estén vacíos o nulos
                            if (newJuegosNombre.isNotEmpty() && newJuegosPrecio.isNotEmpty() && newTipoId != 0 && newPlataformasId != 0) {
                                // Llamada al ViewModel para actualizar el juego
                                juegosViewModel.updateJuego(
                                    juegoId = juego.id,
                                    nombre = newJuegosNombre,
                                    precio = newJuegosPrecio,
                                    tipoId = newTipoId,
                                    plataformaId = newPlataformasId
                                )
                                // Salir del modo de edición
                                isEditing = false
                                juegoSelecionado = null
                            } else {
                                // Aquí puedes mostrar un mensaje de error si algo está vacío
                                Log.e("Actualizar Juego", "Uno o más campos están vacíos o inválidos.")
                            }
                        }
                    }
                }) {
                    Text("Guardar Cambios")
                }


            }
        }
    }
}

@Composable
fun BottomNavigationBar() {

}
