package com.angel.gameproject.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao interface PlataformasDao {
    @Insert
    suspend fun insert(plataformas: Plataformas)
    @Query("SELECT * FROM plataformas")
    suspend fun getAllPlataformas(): List<Plataformas>
    @Update
    suspend fun update(plataformas: Plataformas)
    @Delete
    suspend fun delete(plataformas: Plataformas)
}