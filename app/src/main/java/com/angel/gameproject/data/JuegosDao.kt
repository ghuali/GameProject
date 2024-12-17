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
    suspend fun delete(juegos: Juegos)

    @Query("SELECT * FROM tipojuegos WHERE id = :id")
    suspend fun getTipoById(id: Int): TipoJuegos?

    @Query("SELECT * FROM plataformas WHERE idPlataformas = :id")
    suspend fun getPlataformaById(id: Int): Plataformas?

    @Query("DELETE FROM Juegos WHERE id = :juegoId")
    suspend fun deleteById(juegoId: Int)
}
