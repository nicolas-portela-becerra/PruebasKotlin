package com.example.pruebaroom.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pruebaroom.data.database.dao.ModeloDao
import com.example.pruebaroom.data.database.entities.Modelo

@Database(entities = [Modelo::class], version = 1)
abstract class ModeloDatabase : RoomDatabase() {
    abstract fun getModeloDao():ModeloDao
}