package com.liceolapaz.dam.tresenrayanpb

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView

class TresEnRaya : View {
    companion object{
        const val VACIA = 0
        const val FICHA_O = 1
        const val FICHA_X = 2
    }

    private lateinit var lblTurnoJugador : TextView
    private lateinit var lblCasilla : TextView
    private lateinit var btnRestart : Button

    private val tablero = Array(3){Array(3){0}}
    private var fichaActiva = FICHA_X
    private var xColor = Color.RED
    private var oColor = Color.BLUE
    private val pBorde = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.BLACK
        strokeWidth = 4f
    }
    private val pMarcaO = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 10f
    }
    private val pMarcaX = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 10f
    }

    private lateinit var listener : OnCasillaSeleccionadaListener
    constructor(ctx: Context) : super(ctx)

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.TresEnRaya, 0, 0).apply {
            try {
                oColor = getColor(R.styleable.TresEnRaya_ocolor, Color.BLUE)
                xColor = getColor(R.styleable.TresEnRaya_xcolor, Color.RED)
            } finally {
                recycle()
            }
        }
    }

    constructor(ctx: Context, attrs: AttributeSet, defStyleAttr: Int) : super(ctx, attrs, defStyleAttr) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.TresEnRaya, 0, 0).apply {
            try {
                oColor = getColor(R.styleable.TresEnRaya_ocolor, Color.BLUE)
                xColor = getColor(R.styleable.TresEnRaya_xcolor, Color.RED)
            } finally {
                recycle()
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var ancho = calcularAncho(widthMeasureSpec)
        var alto = calcularAlto(heightMeasureSpec)

        if(ancho < alto) {
            alto = ancho
        } else {
            ancho = alto
        }

        setMeasuredDimension(ancho, alto)
    }

    private fun calcularAlto(limitesSpec: Int): Int {
        var res = 100 //Alto por defecto
        val modo = MeasureSpec.getMode(limitesSpec)
        val limite = MeasureSpec.getSize(limitesSpec)

        if (modo == MeasureSpec.AT_MOST) {
            res = limite
        } else if (modo == MeasureSpec.EXACTLY) {
            res = limite
        }

        return res
    }

    private fun calcularAncho(limitesSpec: Int): Int {
        var res = 100 //Ancho por defecto
        val modo = MeasureSpec.getMode(limitesSpec)
        val limite = MeasureSpec.getSize(limitesSpec)

        if (modo == MeasureSpec.AT_MOST) {
            res = limite
        } else if (modo == MeasureSpec.EXACTLY) {
            res = limite
        }

        return res
    }

    fun limpiarCasillas() {
        for(i in 0..2) {
            for (j in 0..2) {
                //Cambia todas las casillas a valor 0 directamente
                tablero[i][j] = VACIA
            }
        }
        this.invalidate()
    }

    fun setCasilla(fil: Int, col: Int, valor: Int) {
        if(tablero[fil][col] == 0) {
            tablero[fil][col] = valor
        }
    }

    fun getCasilla(fil: Int, col: Int) : Int {
        return tablero[fil][col]
    }

    fun alternarFichaActiva() {
        fichaActiva = if (fichaActiva == FICHA_O) FICHA_X else FICHA_O
    }

    override fun onDraw(canvas: Canvas?) {
        //Obtenemos las dimensiones del control
        val alto = measuredHeight
        val ancho = measuredWidth

        //Lineas
        canvas!!.drawLine((ancho / 3).toFloat(), 0f, (ancho / 3).toFloat(), alto.toFloat(), pBorde)
        canvas.drawLine(
            (2 * ancho / 3).toFloat(), 0f,
            (2 * ancho / 3).toFloat(),
            alto.toFloat(), pBorde
        )

        canvas.drawLine(0f, (alto / 3).toFloat(), ancho.toFloat(), (alto / 3).toFloat(), pBorde)
        canvas.drawLine(
            0f,
            (2 * alto / 3).toFloat(),
            ancho.toFloat(),
            (2 * alto / 3).toFloat(), pBorde
        )

        //Marco
        canvas.drawRect(0f, 0f, ancho.toFloat(), alto.toFloat(), pBorde)

        //Marcas
        pMarcaO.color = oColor
        pMarcaX.color = xColor

        //Casillas Seleccionadas
        for (fil in 0..2) {
            for (col in 0..2) {
                if (getCasilla(fil, col) == FICHA_X) {
                    //Cruz
                    canvas.drawLine(
                        col * (ancho / 3) + ancho / 3 * 0.1f,
                        fil * (alto / 3) + alto / 3 * 0.1f,
                        col * (ancho / 3) + ancho / 3 * 0.9f,
                        fil * (alto / 3) + alto / 3 * 0.9f,
                        pMarcaX
                    )
                    canvas.drawLine(
                        col * (ancho / 3) + ancho / 3 * 0.1f,
                        fil * (alto / 3) + alto / 3 * 0.9f,
                        col * (ancho / 3) + ancho / 3 * 0.9f,
                        fil * (alto / 3) + alto / 3 * 0.1f,
                        pMarcaX
                    )
                } else if (getCasilla(fil, col) == FICHA_O) {
                    //Circulo
                    canvas.drawCircle(
                        (col * (ancho / 3) + ancho / 6).toFloat(),
                        (fil * (alto / 3) + alto / 6).toFloat(),
                        ancho / 6 * 0.8f, pMarcaO
                    )
                }
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val fil = (event!!.y / (measuredHeight / 3)).toInt()
        val col = (event.x / (measuredWidth / 3)).toInt()
        if(getCasilla(fil, col) !=  0) {
            for(i in 0..2) {
                for(j in 0..2) {
                    println("Posicion " + i + ", " + j + ": " + tablero[i][j])
                }
            }
            return false
        }
        else {
            //Actualizamos el tablero
            setCasilla(fil, col, fichaActiva)

            //Lanzamos el evento de pulsación
            // listener.onCasillaSeleccionada(checkGanador(), tableroLleno(), fichaActiva)

            //Alternar ficha automaticamente
            alternarFichaActiva()

            //Alternar texto de jugador activa
            if(fichaActiva == FICHA_X) {
                lblTurnoJugador.text = "Turno de la X"
            }
            else if(fichaActiva == FICHA_O) {
                lblTurnoJugador.text = "Turno del O"
            }

            //Refrescamos el control
            this.invalidate()

            if(tableroLleno() == 0) {
                lblCasilla.text = "Empate"
                lblTurnoJugador.text = ""
                btnRestart.isEnabled = true
                fichaActiva == FICHA_X
            }
            //Control de estados del tablero
            if(checkGanador() == FICHA_O) {
                btnRestart.isEnabled = true
                lblTurnoJugador.text = ""
                lblCasilla.text = "Ganan las O"
                setOnTouchListener { view, motionEvent -> true }
            }
            if(checkGanador() == FICHA_X) {
                btnRestart.isEnabled = true
                lblTurnoJugador.text = ""
                lblCasilla.text = "Ganan las X"
                setOnTouchListener { view, motionEvent -> true }
            }
        }
        return super.onTouchEvent(event)
    }

    fun controlarBotonRestart(btnRestar : Button) {
        this.btnRestart = btnRestar
    }
    fun setEstadoTablero(lblCasilla : TextView) {
        this.lblCasilla = lblCasilla
    }
    fun setMensajeJugador(lblTurnoJugador: TextView){
      this.lblTurnoJugador = lblTurnoJugador
    }

    fun tableroLleno(): Int {
        var lleno = 0
        for(i in 0..2) {
            for(j in 0..2) {
                if(tablero[i][j] != 0) {
                    lleno++
                    if(lleno == 9) {
                        return 0
                    }
                }
            }
        }
        return -1
    }

    fun checkGanador(): Int {
        var contadorFilasX = 0
        for(i in 0..2){
            contadorFilasX = 0
            for(j in 0..2){
                if(tablero[i][j] == 2){
                    contadorFilasX++
                    if(contadorFilasX == 3){
                        return FICHA_X
                    }
                }
                else {
                    contadorFilasX = 0
                }
            }
        }
        var contadorColumnasX = 0
        for(i in 0..2){
            contadorColumnasX = 0
            for(j in 0..2){
                if(tablero[j][i] == FICHA_X){
                    contadorColumnasX++
                    if(contadorColumnasX == 3){
                        return FICHA_X
                    }
                }
                else {
                    contadorColumnasX = 0
                }
            }
        }
        if(tablero[0][0] and tablero[1][1] and tablero[2][2] == FICHA_X) {
            return FICHA_X
        }
        if(tablero[0][2] and tablero[1][1] and tablero[2][0] == FICHA_X) {
            return FICHA_X
        }

        var contadorFilasO = 0
        for(i in 0..2){
            contadorFilasO = 0
            for(j in 0..2){
                if(tablero[i][j] == FICHA_O){
                    contadorFilasO++
                    if(contadorFilasO == 3){
                        return FICHA_O
                    }
                }
                else {
                    contadorFilasO = 0
                }
            }
        }
        var contadorColumnasO = 0
        for(i in 0..2){
            contadorColumnasO = 0
            for(j in 0..2){
                if(tablero[j][i] == FICHA_O){
                    contadorColumnasO++
                    if(contadorColumnasO == 3){
                        return FICHA_O
                    }
                }
                else {
                    contadorColumnasO = 0
                }
            }
        }
        if(tablero[0][0] and tablero[1][1] and tablero[2][2] == FICHA_O) {
            return FICHA_O
        }
        if(tablero[0][2] and tablero[1][1] and tablero[2][0] == FICHA_O) {
            return FICHA_O
        }
        return -1
    }

    fun setOnCasillaSeleccionadaListener(seleccion: (Int, Int, Int) -> Unit) {
        listener = object:OnCasillaSeleccionadaListener {
            override fun onCasillaSeleccionada(ganador: Int, lleno: Int, fichaActiva: Int) {
                seleccion(ganador, lleno, fichaActiva)
            }
        }
    }
}