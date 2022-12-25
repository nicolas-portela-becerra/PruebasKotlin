package com.liceolapaz.dam.pruebalector

import java.sql.Connection
import java.util.concurrent.Callable
import java.util.concurrent.FutureTask

class Departamentos(depart : Departamtento) {
    val conexion = FutureTask(BD()).get()
    val depart = depart
    fun call(): Int {
        val ps = conexion.prepareStatement("Insert INTO Departamento VALUES (?, ?, ?)")

        ps.setInt(1, depart.cod)
        ps.setString(2, depart.nom)
        ps.setString(3, depart.pais)

        return ps.executeUpdate()
    }
}