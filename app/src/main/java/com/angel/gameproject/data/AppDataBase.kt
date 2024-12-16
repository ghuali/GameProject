package com.angel.gameproject.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [Juegos::class, TipoJuegos::class,Plataformas::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun juegosDao(): JuegosDao
    abstract fun tipojuegosDao(): TipoJuegosDao
    abstract fun plataformasDao(): PlataformasDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        @OptIn(DelicateCoroutinesApi::class)
        fun getDatabase(context: Context): AppDatabase {
            // Crear una instancia única de la base de datos
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "Juegos_database"
                ).build()
                INSTANCE = instance

                GlobalScope.launch {
                    datos(instance.tipojuegosDao(), instance.plataformasDao())
                }


                instance
            }
        }

        private suspend fun datos(tipoJuegosDao: TipoJuegosDao,plataformasDao: PlataformasDao) {

            val tipojuegos = listOf(
                TipoJuegos(nombre = "Disparos"),
                TipoJuegos(nombre = "Accion"),
                TipoJuegos(nombre = "Rpg"),
                TipoJuegos(nombre = "Puzzle"),
                TipoJuegos(nombre = "Familiar"),
                TipoJuegos(nombre = "Souls"),
            )

            val tiposFromDb = tipoJuegosDao.getAllTipos()

            if (tiposFromDb.isEmpty()) {
                tipojuegos.forEach { tipoJuego ->
                    tipoJuegosDao.insert(tipoJuego)
                }
            }

            val plataformas = listOf(
                Plataformas(tituloPlataformas = "Playstation"),
                Plataformas(tituloPlataformas = "Xbox"),
                Plataformas(tituloPlataformas = "Nintendo"),
                Plataformas(tituloPlataformas = "Pc"),
            )

            val plataformasFromDb = plataformasDao.getAllPlataformas()

            if (plataformasFromDb.isEmpty()) {
                plataformas.forEach { plataforma ->
                    plataformasDao.insert(plataforma)
                }
            }
        }
    }
}