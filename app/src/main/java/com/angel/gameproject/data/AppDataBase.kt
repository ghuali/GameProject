package com.angel.gameproject.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Juegos::class, TipoJuegos::class,Plataformas::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun juegosDao(): JuegosDao
    abstract fun tipojuegosDao(): TipoJuegosDao
    abstract fun plataformasDao(): PlataformasDao