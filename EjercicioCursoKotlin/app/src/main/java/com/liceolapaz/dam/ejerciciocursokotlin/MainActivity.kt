package com.liceolapaz.dam.ejerciciocursokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

val TAG = ":::TAG"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        botDeSguridad()
    }

    private fun botDeSguridad() {
        val yo = Persona("Nico", 21, arrayListOf("musica", "programar", "ordenadores"))

        if(!yo.name.equals("Nico")) {
            Log.d(TAG, "Error en el nombre")
        }
        else {
            Log.d(TAG, "Bienvenido ${yo.name}")
            if(yo.age < 18){
                Log.d(TAG, "Menor de edad")
            }
            else if(yo.age > 65) {
                Log.d(TAG, "Demasiado mayor")
            }
            else {
               for (hobbie: String in yo.hobbies) {
                   Log.d(TAG, hobbie)
               }
            }
        }
    }
}

data class Persona(
    val name: String,
    val age: Int,
    val hobbies: ArrayList<String>
    )