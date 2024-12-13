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
                TipoJuegos()
            )
        }
    }
}