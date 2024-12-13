package com.angel.gameproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.angel.gameproject.data.AppDatabase
import com.angel.gameproject.ui.theme.GameProjectTheme
import com.angel.gameproject.view.view
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
            view(juegosViewModel)

                }
            }
        }