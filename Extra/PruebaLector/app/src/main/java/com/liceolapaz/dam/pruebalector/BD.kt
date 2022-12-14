package com.liceolapaz.dam.pruebalector

import android.os.StrictMode
import android.util.Log
import com.zaxxer.hikari.HikariDataSource
import java.sql.DriverManager
import java.sql.SQLException
import java.util.concurrent.Callable

class BD : Callable<ArrayList<String>>{

    companion object {
        fun getConexionMYSQL(): ArrayList<String> {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val array = arrayListOf<String>()
            //DAO buscar
            //Metodo para el main y dentro de ese metodo la conexion a la base de datos
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance()
                Log.i(":::", "Driver bien")
            } catch (e: ClassNotFoundException) {
                Log.i(":::", "Error en el driver")
                e.printStackTrace()
            }
            try {
                //var conexion = DriverManager.getConnection("jdbc:mysql://10.0.2.2/dam22", "nico", "1234")
                var conexion = DriverManager.getConnection("jdbc:mysql://192.168.19.47/dam22", "nico", "1234")
                if(!(conexion == null)) {
                    Log.i(":::", "Conexion buena")
                    val ps = conexion.prepareStatement("SELECT * FROM alumno")
                    Log.i(":::", "Ejecutar preparedStatement")
                    val resultados = ps.executeQuery()
                    Log.i(":::", "Resultados")


                    while(resultados.next()) {
                        Log.i(":::", "Resultados: " + resultados.getInt(1))
                        array.add(resultados.getInt(1).toString())
                        Log.i(":::", "Resultados: " + resultados.getString(2))
                        array.add(resultados.getString(2))
                        Log.i(":::", "Resultados: " + resultados.getString(3))
                        array.add(resultados.getString(3))
                    }
                    return array
                }
            }
            catch (e: SQLException) {
                Log.i(":::", "F total")
                e.printStackTrace()
            }
            return array
        }
        fun getConexionPOSTGRE(): ArrayList<String> {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance()
                Log.i(":::", "Driver bien")
            } catch (e: ClassNotFoundException) {
                Log.i(":::", "Error en el driver")
                e.printStackTrace()
            }
            val array = arrayListOf<String>()
            // create a dataSource
            val dataSource = HikariDataSource()

            // set the jdbcUrl
            dataSource.jdbcUrl = "jdbc:mysql://192.168.19.47/dam22"

            // set the username
            dataSource.username = "nico"

            // set the password
            dataSource.password = "1234"


            // get a connection
            val connection = dataSource.connection

            // the query is only prepared not executed
            val query = connection.prepareStatement("SELECT * FROM alumno")

            // the query is executed and results are fetched
            val resultados = query.executeQuery()


            while (resultados.next()) {
                Log.i(":::", "Resultados: " + resultados.getInt(1))
                array.add(resultados.getInt(1).toString())
                Log.i(":::", "Resultados: " + resultados.getString(2))
                array.add(resultados.getString(2))
                Log.i(":::", "Resultados: " + resultados.getString(3))
                array.add(resultados.getString(3))
            }

            return array
        }

    }

    override fun call(): ArrayList<String> {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        val array = arrayListOf<String>()
        //DAO buscar
        //Metodo para el main y dentro de ese metodo la conexion a la base de datos
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance()
            Log.i(":::", "Driver bien")
        } catch (e: ClassNotFoundException) {
            Log.i(":::", "Error en el driver")
            e.printStackTrace()
        }
        try {
            //var conexion = DriverManager.getConnection("jdbc:mysql://10.0.2.2/dam22", "nico", "1234")
            var conexion = DriverManager.getConnection("jdbc:mysql://192.168.19.47/dam22", "nico", "1234")
            if(!(conexion == null)) {
                Log.i(":::", "Conexion buena")
                val ps = conexion.prepareStatement("SELECT * FROM alumno")
                Log.i(":::", "Ejecutar preparedStatement")
                val resultados = ps.executeQuery()
                Log.i(":::", "Resultados")


                while(resultados.next()) {
                    Log.i(":::", "Resultados: " + resultados.getInt(1))
                    array.add(resultados.getInt(1).toString())
                    Log.i(":::", "Resultados: " + resultados.getString(2))
                    array.add(resultados.getString(2))
                    Log.i(":::", "Resultados: " + resultados.getString(3))
                    array.add(resultados.getString(3))
                }
                return array
            }
        }
        catch (e: SQLException) {
            Log.i(":::", "F total")
            e.printStackTrace()
        }
        return array
    }
}
