package com.liceolapaz.dam.pruebalector

import java.sql.Connection
import java.sql.DriverManager
import java.util.concurrent.Callable


class BD {
    fun call(): Connection {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver")
        }
        catch (e : ClassNotFoundException) {
            e.printStackTrace()
        }
        return DriverManager.getConnection("jdbc:mysql://192.168.1.176/practicaev1", "nico", "1234")
    }
}
