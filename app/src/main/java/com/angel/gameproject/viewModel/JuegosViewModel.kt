package com.angel.gameproject.viewModel

import androidx.lifecycle.ViewModel
import com.angel.gameproject.data.Juegos
import com.angel.gameproject.data.JuegosDao
import com.angel.gameproject.data.PlataformasDao
import com.angel.gameproject.data.TipoJuegosDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class JuegosViewModel(
    private val juegosDao: JuegosDao,
    private val tipoJuegosDao: TipoJuegosDao,
    private val plataformasDao: PlataformasDao
) : ViewModel() {
    private val _juegos = MutableStateFlow<List<Juegos>>(emptyList())

    val juegos: StateFlow<List<Juegos>> = _juegos

    private val _tiposJuegos = MutableStateFlow<Map<Int, String>>(emptyMap())
    val tipoJuegos: StateFlow<Map<Int, String>> = _tiposJuegos
}