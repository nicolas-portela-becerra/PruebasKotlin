package com.liceolapaz.dam.pruebalector

import android.content.Context
import android.widget.Toast
import java.sql.Connection
import java.sql.SQLException
import java.util.concurrent.Callable
import java.util.concurrent.FutureTask

class Departamentos(depart : Departamtento, ctx : Context) {
    val ctx = ctx
    val conexion = BD().call(ctx)
    val depart = depart

    fun call(): Int {
        if(conexion != null) {
            val ps = conexion.prepareStatement("Insert INTO Departamento VALUES (?, ?, ?)")

            ps.setInt(1, depart.cod)
            ps.setString(2, depart.nom)
            ps.setString(3, depart.pais)

            return ps.executeUpdate()
        }
        return -1
    }
}