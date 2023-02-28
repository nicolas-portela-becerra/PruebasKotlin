package com.example.pruebaroom.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pruebaroom.data.database.entities.Modelo

@Dao
interface ModeloDao {
    @Query("SELECT * FROM tablaModelo")
    suspend fun getAllModelos():List<Modelo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllModelos(modelos:List<Modelo>)
}