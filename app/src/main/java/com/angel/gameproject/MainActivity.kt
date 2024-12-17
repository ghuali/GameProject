package com.angel.gameproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.angel.gameproject.data.AppDatabase
import com.angel.gameproject.view.MainView
import com.angel.gameproject.viewModel.JuegosViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = AppDatabase.getDatabase(this)
        val juegosDao = database.juegosDao()
        val tipoJuegosDao = database.tipojuegosDao()
        val plataformasDao = database.plataformasDao()

        val juegosViewModel = JuegosViewModel(juegosDao, tipoJuegosDao, plataformasDao)
        setContent {
            MainView(juegosViewModel = juegosViewModel)

                }
            }
        }