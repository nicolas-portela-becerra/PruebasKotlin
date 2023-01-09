package com.liceolapaz.dam.pruebalector

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException


class BD {
    fun call(ctx : Context): Connection? {
        val ctx = ctx
        try {
            return DriverManager.getConnection("jdbc:mysql://192.168.1.176/practicaev1", "nico", "1234")
        }
        catch (e : SQLException) {
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(ctx, "Error en la conexión con la base de datos", Toast.LENGTH_SHORT).show()
            }
        }
        return null
    }
}
