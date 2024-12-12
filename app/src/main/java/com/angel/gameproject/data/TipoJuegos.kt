package com.angel.gameproject.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TiposJuegos")
data class TipoJuegos(
    @PrimaryKey(autoGenerate = true) val idTipoJuegos: Int = 0,
    val tituloTipoJuegos: String
)