package com.example.pruebaroom.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tablaModelo")
data class Modelo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id:Int = 0,
    @ColumnInfo(name = "nombre") val nombre:String,
    @ColumnInfo(name = "capitulo") val capitulo:Int
)
