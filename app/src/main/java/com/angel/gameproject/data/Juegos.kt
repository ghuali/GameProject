package com.angel.gameproject.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "Juegos",
    foreignKeys = [
        ForeignKey(
            entity = TipoJuegos::class,
            parentColumns = ["id"],
            childColumns = ["idTipo"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = TipoJuegos::class,
            parentColumns = ["id"],
            childColumns = ["idPlataformas"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Juegos(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombreJuegos: String,
    val Precio: String,
    val idTipo: Int,
    val idPlataformas: Int
)