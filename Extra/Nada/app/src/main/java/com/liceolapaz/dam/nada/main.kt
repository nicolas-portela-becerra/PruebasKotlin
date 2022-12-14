package com.liceolapaz.dam.nada

import android.util.Log
import java.sql.DriverManager



fun main() : ArrayList<String>{

    val jdbcUrl = "jdbc:mysql://192.168.1.176/dam22"

    // get the connection
    val connection = DriverManager
        .getConnection(jdbcUrl, "nico", "1234")

    // prints true if the connection is valid
    println(connection.isValid(0))

    // the query is only prepared not executed
    val query = connection.prepareStatement("SELECT * FROM alumno")

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