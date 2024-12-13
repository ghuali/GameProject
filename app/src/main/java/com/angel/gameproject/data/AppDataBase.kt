package com.angel.gameproject.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Juegos::class, TipoJuegos::class,Plataformas::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun juegosDao(): JuegosDao
    abstract fun tipojuegosDao(): TipoJuegosDao
    abstract fun plataformasDao(): PlataformasDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // Crear una instancia única de la base de datos
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "task_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}