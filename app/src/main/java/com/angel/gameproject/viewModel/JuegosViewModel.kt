package com.angel.gameproject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angel.gameproject.data.Juegos
import com.angel.gameproject.data.JuegosDao
import com.angel.gameproject.data.Plataformas
import com.angel.gameproject.data.PlataformasDao
import com.angel.gameproject.data.TipoJuegos
import com.angel.gameproject.data.TipoJuegosDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class JuegosViewModel(
    private val juegosDao: JuegosDao,
    private val tipoJuegosDao: TipoJuegosDao,
    private val plataformasDao: PlataformasDao
) : ViewModel() {
    private val _juegos = MutableStateFlow<List<Juegos>>(emptyList())

    val juegos: StateFlow<List<Juegos>> = _juegos

    private val _tiposJuegos = MutableStateFlow<List<TipoJuegos>>(emptyList())
    val tipoJuegos: StateFlow<List<TipoJuegos>> = _tiposJuegos

    private val _plataformas = MutableStateFlow<List<Plataformas>>(emptyList())
    val plataformas: StateFlow<List<Plataformas>> = _plataformas

    init {
        viewModelScope.launch {
            // Carga los tipos de juegos desde la base de datos
            _tiposJuegos.value = tipoJuegosDao.getAllTipos()

            // Carga las plataformas desde la base de datos
            _plataformas.value = plataformasDao.getAllPlataformas()
        }
    }
}