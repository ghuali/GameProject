package com.angel.gameproject.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Plataformas")
data class Plataformas(
    @PrimaryKey(autoGenerate = true) val idPlataformas: Int = 0,
    val tituloPlataformas: String
)