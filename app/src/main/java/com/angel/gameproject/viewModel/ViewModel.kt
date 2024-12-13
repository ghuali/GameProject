package com.angel.gameproject.viewModel

import com.angel.gameproject.data.JuegosDao
import com.angel.gameproject.data.PlataformasDao
import com.angel.gameproject.data.TipoJuegosDao

class ViewModel(
    private val juegosDao: JuegosDao,
    private val tipoJuegosDao: TipoJuegosDao,
    private val plataformasDao: PlataformasDao
)