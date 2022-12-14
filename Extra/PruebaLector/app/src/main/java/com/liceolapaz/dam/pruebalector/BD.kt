package com.liceolapaz.dam.pruebalector


import android.util.Log
import com.zaxxer.hikari.HikariDataSource
import java.sql.DriverManager
import java.sql.SQLException
import java.sql.SQLNonTransientConnectionException
import java.util.concurrent.Callable


class BD : Callable<ArrayList<String>> {
        fun getConexionMYSQL(): ArrayList<String> {
            val array = arrayListOf<String>()
            //DAO buscar
            //Metodo para el main y dentro de ese metodo la conexion a la base de datos
            try {
                Class.forName("com.mysql.cj.jdbc.Driver")
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            }
            try {
                //var conexion = DriverManager.getConnection("jdbc:mysql://10.0.2.2/dam22", "nico", "1234")
                val conexion = DriverManager.getConnection("jdbc:mysql://192.168.1.176/dam22", "nico", "1234")
                if(!(conexion == null)) {
                    val ps = conexion.prepareStatement("SELECT * FROM alumno")
                    val resultados = ps.executeQuery()


                    while(resultados.next()) {
                        //array.add(resultados.getInt(1).toString())
                        array.add(resultados.getString(2))
                        array.add(resultados.getString(3))
                    }
                    return array
                }
            }
            catch (e: SQLNonTransientConnectionException) {
                e.printStackTrace()
            }
            catch (e1 : SQLException) {
                e1.printStackTrace()
            }
            return array
        }

    override fun call(): ArrayList<String> {
        val data = HikariDataSource()
        data.jdbcUrl = "jdbc:mysql://192.168.1.176/dam22"
        data.username = "nico"
        data.password = "1234"
        val conexion = data.connection

        Log.i(":::", "Conexion hecha")
        // the query is only prepared not executed
        val query = conexion.prepareStatement("SELECT * FROM alumno")

        // the query is executed and results are fetched
        val result = query.executeQuery()

        // an empty list for holding the results
        var lista = ArrayList<String>()
        while (result.next()) {
            var s = result.getString(3)
            lista.add(s)
            println(s)
        }

        return lista
    }
}
