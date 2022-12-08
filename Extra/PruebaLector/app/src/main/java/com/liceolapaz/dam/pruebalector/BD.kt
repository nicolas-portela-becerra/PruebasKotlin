package com.liceolapaz.dam.pruebalector

import android.util.Log
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException


class BD {
    companion object {

        @JvmStatic
        fun getConexion(): Connection? {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver")
                //return DriverManager.getConnection("jdbc:mysql://10.0.0.2:3306/dam22?user=root&password=1234")
                return DriverManager.getConnection("jdbc:mysql://10.0.0.2:3306/dam22", "root", "1234")
            }catch (ex : SQLException) {
                ex.printStackTrace()
            }
            return null
        }
    }
}
