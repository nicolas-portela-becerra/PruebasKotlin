package com.liceolapaz.dam.pruebalector

import android.content.Context

class Departamentos(depart : Departamento, ctx : Context) {
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