package com.liceolapaz.dam.firebasedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.liceolapaz.dam.firebasedemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val viewBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        val nombre = viewBinding.nombre.text.toString()
        val apellido = viewBinding.apellido.text.toString()

        viewBinding.boton.setOnClickListener {
            guardarFireBase(nombre, apellido)
        }
    }

    private fun guardarFireBase(nombre: String, apellido: String) {
        val db = FirebaseFirestore.getInstance()

        val usuarios : MutableMap<String, Any> = HashMap()
        usuarios["nombre"] = nombre
        usuarios["apellido"] = apellido
        
        db.collection("usuarios").add(usuarios)
            .addOnSuccessListener {
                Toast.makeText(this, "Insercion correcta", Toast.LENGTH_SHORT).show()
        }
            .addOnFailureListener {
                Toast.makeText(this, "Error al insertar", Toast.LENGTH_SHORT).show()
            }
    }


}