package com.liceolapaz.dam.pruebalector

import java.sql.Connection
import java.util.concurrent.Callable
import java.util.concurrent.FutureTask

class Empleados(empleado : Empleado) : Callable<Int> {
    val conexion = FutureTask(BD()).get()
    val empleado = empleado

    override fun call(): Int {
        val ps = conexion.prepareStatement("INSERT INTO Empleado VALUES (?, ?, ?, ?, ?, ?)")

        ps.setInt(1, empleado.cod)
        ps.setString(2, empleado.nom)
        ps.setString(3, empleado.apellido)
        ps.setString(4, empleado.puesto)
        ps.setInt(5, empleado.salario)
        ps.setInt(5, empleado.codDepartamento)
        return 1
    }

}