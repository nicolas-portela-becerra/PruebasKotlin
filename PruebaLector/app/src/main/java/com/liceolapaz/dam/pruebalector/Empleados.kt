package com.liceolapaz.dam.pruebalector

import android.content.Context
import android.widget.Toast
import java.sql.Connection
import java.sql.SQLException
import java.util.concurrent.Callable
import java.util.concurrent.FutureTask

class Empleados(empleado : Empleado, ctx : Context) {
    val ctx = ctx
    val conexion = BD().call(ctx)
    val empleado = empleado

    fun call(): Int {
        if(conexion != null) {
            val ps = conexion.prepareStatement("INSERT INTO Empleado VALUES (?, ?, ?, ?, ?, ?)")

            ps.setInt(1, empleado.cod)
            ps.setString(2, empleado.nom)
            ps.setString(3, empleado.apellido)
            ps.setString(4, empleado.puesto)
            ps.setInt(5, empleado.salario)
            ps.setInt(5, empleado.codDepartamento)
            return ps.executeUpdate()
        }
        return -1
    }

}