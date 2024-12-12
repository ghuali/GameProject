package com.angel.gameproject.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tipoJuegos")
data class TipoJuegos( @PrimaryKey(autoGenerate = true)
                 val id: Int = 0,
                 val nombre: String,)