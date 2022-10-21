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

        terTablero = findViewById(R.id.tablero)
        terTablero.setOnCasillaSeleccionadaListener { fila, columna ->
            lblCasilla.text = "Última casilla seleccionada: ($fila, $columna, ${terTablero.getCasilla(fila, columna)})"
        }

        /*terTablero.setOnWinnerListener {codigo ->
            if(codigo == 0) {
            lblCasilla.text = "Empate"
            }
            else if(codigo == 1) {
                lblCasilla.text = "Gana la X"
            }
            else if(codigo == 2) {
                lblCasilla.text = "Gana el O"
            }
        }*/


        /*lblTurnoJugador = findViewById(R.id.turnoJugador)
        println(terTablero.getFichaActiva())
        if(terTablero.getFichaActiva() == 2) {
            lblTurnoJugador.setText("Jugador 1 (CRUZ)")
        }
        else if(terTablero.getFichaActiva() == 1) {
            lblTurnoJugador.setText("Jugador 2 (CIRCULO)")
        }*/


        btnRestart = findViewById(R.id.btnRestart)
        btnRestart.setOnClickListener {
            terTablero.limpiarCasillas()
        }
    }
}