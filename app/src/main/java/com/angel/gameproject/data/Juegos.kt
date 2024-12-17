package com.angel.gameproject.data


import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "Juegos",
    foreignKeys = [
        ForeignKey(
            entity = TipoJuegos::class,
            parentColumns = ["id"],
            childColumns = ["idTipoJuegos"],
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
    val idTipoJuegos: Int,
    val idPlataformas: Int
)