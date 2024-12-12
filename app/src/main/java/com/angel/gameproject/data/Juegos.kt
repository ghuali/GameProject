package com.angel.gameproject.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Juegos")
data class Juegos(
    @PrimaryKey(autoGenerate = true) val idJuegos: Int = 0,
    @ColumnInfo(name = "Título") val tituloJuegos: String,
    @ColumnInfo(name = "Precio") val precio: Double,
    val idTipojuego: Int,
    val idPlataformas: Int
)