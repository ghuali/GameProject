package com.angel.gameproject.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao interface JuegosDao { @Insert suspend fun insert(juegos: Juegos)
    @Query("SELECT * FROM juegos")
    suspend fun getAllJuegos(): List<Juegos>
    @Update
    suspend fun update(juegos: Juegos)
    @Delete
    suspend fun delete(juegos: Juegos) }