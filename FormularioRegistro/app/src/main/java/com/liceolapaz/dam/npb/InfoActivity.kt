package com.liceolapaz.dam.npb

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class InfoActivity : AppCompatActivity() {
    private lateinit var texto: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        texto = findViewById(R.id.texto)

        val nombre = intent.getStringExtra("Nombre")
        val apellidos = intent.getStringExtra("Apellidos")
        val contrasenha = intent.getStringExtra("Contrasenha")
        val telefono = intent.getStringExtra("Telefono")
        val fechaNacimiento = intent.getStringExtra("FechaNacimiento")
        val email = intent.getStringExtra("Email")
        val botonSexo = intent.getStringExtra("BotonSexo")
        val boletin = intent.getStringExtra("Boletin")
        val pais = intent.getStringExtra("Pais")

        texto.text = "Nombre: $nombre\n" +
                "Apelldios: $apellidos\n" +
                "Contraseña $contrasenha\n" +
                "Telefono: $telefono\n" +
                "Fecha de nacimiento: $fechaNacimiento\n" +
                "Correo electrónico: $email\n" +
                "Pais: $pais\n" +
                "Sexo: $botonSexo\n" +
                "Recibir boletin: $boletin\n"
    }
}