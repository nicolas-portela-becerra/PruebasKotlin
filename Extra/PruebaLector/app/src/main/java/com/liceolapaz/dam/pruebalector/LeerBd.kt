package com.liceolapaz.dam.pruebalector

import android.content.Context
import android.widget.Toast
import java.util.concurrent.Callable
import java.util.concurrent.FutureTask

class LeerBd(palabra: String, ctx : Context) {
    val palabra = palabra
    val ctx = ctx

    fun call(): ArrayList<Any> {
        val conexion = BD().call()
        var datos = arrayListOf<Any>()
        if((conexion) != null) {
            val ps = conexion.prepareStatement("SELECT * FROM " + palabra)
            val resultados = ps.executeQuery()

            if(palabra.equals("empleado", true)) {
                while(resultados.next()){
                    var empleado = Empleado(resultados.getInt(1), resultados.getString(2), resultados.getString(3), resultados.getString(4), resultados.getInt(5), resultados.getInt(6))
                    datos.add(empleado as Any)
                }
            }
            if(palabra.equals("departamento", true)) {
                while(resultados.next()){
                    var departamtento = Departamtento(resultados.getInt(1), resultados.getString(2), resultados.getString(3))
                    datos.add(departamtento as Any)
                }
            }
        }
        else {
            Toast.makeText(ctx, "Error en la conexión", Toast.LENGTH_SHORT).show()
        }

        return datos
    }
}
