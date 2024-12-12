package com.angel.gameproject.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao interface TipoJuegosDao {
    @Insert
    suspend fun insert(tipoJuegos: TipoJuegos)
    @Query("SELECT * FROM tipoJuegos")
    suspend fun getAllTipos(): List<TipoJuegos>
    @Update
    suspend fun update(tipoJuegos: TipoJuegos)
    @Delete
    suspend fun delete(tipoJuegos: TipoJuegos)
}