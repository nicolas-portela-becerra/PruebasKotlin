package com.liceolapaz.dam.tresenrayanpb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var lblCasilla : TextView
    private lateinit var terTablero : TresEnRaya
    private lateinit var btnRestart : Button
    private lateinit var lblTurnoJugador : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lblCasilla = findViewById(R.id.lblCasilla)
        lblTurnoJugador = findViewById(R.id.turnoJugador)
        terTablero = findViewById(R.id.tablero)
        btnRestart = findViewById(R.id.btnRestart)

        terTablero.setMensajeJugador(lblTurnoJugador)
        terTablero.setEstadoTablero(lblCasilla)
        terTablero.controlarBotonRestart(btnRestart)

        btnRestart.setOnClickListener {
            terTablero.limpiarCasillas()
            btnRestart.isEnabled = false
            lblCasilla.text = ""
            lblTurnoJugador.text = "Turno de la X"
        }
    }
}