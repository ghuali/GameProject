package com.angel.gameproject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angel.gameproject.data.Juegos
import com.angel.gameproject.data.JuegosDao
import com.angel.gameproject.data.Plataformas
import com.angel.gameproject.data.PlataformasDao
import com.angel.gameproject.data.TipoJuegos
import com.angel.gameproject.data.TipoJuegosDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class JuegoUI(
    val id: Int,
    val nombre: String,
    val precio: String,
    val tipo: String,
    val plataforma: String
)

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

    val juegosUI: StateFlow<List<JuegoUI>> = combine(
        _juegos, _tiposJuegos, _plataformas
    ) { juegos, tipos, plataformas ->
        juegos.map { juego ->
            JuegoUI(
                id = juego.id,
                nombre = juego.nombreJuegos,
                precio = juego.Precio,
                tipo = tipos.find { it.id == juego.idTipoJuegos }?.nombre ?: "Desconocido",
                plataforma = plataformas.find { it.idPlataformas == juego.idPlataformas }?.tituloPlataformas
                    ?: "Desconocido"
            )
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        viewModelScope.launch {
            // Carga los tipos de juegos desde la base de datos
            _tiposJuegos.value = tipoJuegosDao.getAllTipos()

            // Carga las plataformas desde la base de datos
            _plataformas.value = plataformasDao.getAllPlataformas()
        }
    }
    fun addJuego(juego: Juegos) {
        viewModelScope.launch(Dispatchers.IO) {
            juegosDao.insert(juego)
            // Actualiza la lista de juegos después de insertar
            _juegos.value = juegosDao.getAllJuegos()
        }
    }
}
