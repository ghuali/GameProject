package com.angel.gameproject.viewModel

import android.util.Log
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
import kotlinx.coroutines.flow.StateFlow
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

    private val _juegosUI = MutableStateFlow<List<JuegoUI>>(emptyList())
    val juegosUI: StateFlow<List<JuegoUI>> get() = _juegosUI


    init {
        viewModelScope.launch {
            // Carga los tipos de juegos desde la base de datos
            _tiposJuegos.value = tipoJuegosDao.getAllTipos()

            // Carga las plataformas desde la base de datos
            _plataformas.value = plataformasDao.getAllPlataformas()

            loadJuegos()
        }
    }

    private fun loadJuegos() {
        viewModelScope.launch(Dispatchers.IO) {
            val juegos = juegosDao.getAllJuegos()
            _juegosUI.value = juegos.map { juego ->
                JuegoUI(
                    id = juego.id,
                    nombre = juego.nombreJuegos,
                    precio = juego.Precio,
                    tipo = juegosDao.getTipoById(juego.idTipoJuegos)?.nombre ?: "Desconocido",
                    plataforma = juegosDao.getPlataformaById(juego.idPlataformas)?.tituloPlataformas ?: "Desconocido"
                )
            }
        }
    }

    fun addJuego(juego: Juegos) {
        viewModelScope.launch(Dispatchers.IO) {
            juegosDao.insert(juego)

            _juegos.value = juegosDao.getAllJuegos()
            loadJuegos()
        }
    }
    fun deleteJuego(juegoId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            juegosDao.deleteById(juegoId)
            loadJuegos()
        }
    }
    fun updateJuego(juegoId: Int, nombre: String, precio: String, tipoId: Int, plataformaId: Int) {

        if (nombre.isEmpty() || precio.isEmpty() || tipoId == 0 || plataformaId == 0) {
            Log.e("Actualizar Juego", "Los campos no pueden estar vacíos.")
            return  // Aquí podemos salir de la función si hay un campo inválido
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {

                val juegoActualizado = Juegos(
                    id = juegoId,
                    nombreJuegos = nombre,
                    Precio = precio,
                    idTipoJuegos = tipoId,
                    idPlataformas = plataformaId
                )


                juegosDao.update(juegoActualizado)


                loadJuegos()
            } catch (e: Exception) {
                Log.e("Actualizar Juego", "Error al actualizar el juego")
            }
        }
    }



}
