package com.liceolapaz.dam.pruebalector

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.liceolapaz.dam.pruebalector.databinding.ActivityRecuperarDatosBinding
import kotlinx.coroutines.launch

class ActivityRecuperarDatos : AppCompatActivity() {
    private lateinit var binding : ActivityRecuperarDatosBinding
    private var x1 : Float = 0F
    private var y1 : Float = 0F
    private var x2: Float = 0F
    private var y2: Float = 0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecuperarDatosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnResultados.setOnClickListener {
            lifecycleScope.launch {
                binding.progressbar.visibility = View.VISIBLE
                TODO("Arreglar esta funcion no se como")
                /*binding.resultados = withContext(Dispatchers.IO) {
                    val datos = LeerBd(binding.fldTablas.text.toString(), this@ActivityRecuperarDatos).call()
                    if(binding.fldTablas.text.toString().equals("departamento", true)) {
                        mostrarDepartamentos(datos)
                    }
                    else {
                        mostrarEmpleados(datos)
                    }
                }*/
                binding.progressbar.visibility = View.GONE
            }
        }
    }
    override fun onTouchEvent(touchEvent: MotionEvent): Boolean {
        when (touchEvent.action) {
            MotionEvent.ACTION_DOWN -> {
                x1 = touchEvent.x
                y1 = touchEvent.y
            }
            MotionEvent.ACTION_UP -> {
                x2 = touchEvent.x
                y2 = touchEvent.y
                if (x1 + 200 < x2) {
                    startActivity(Intent(this@ActivityRecuperarDatos, ActivityInsertarDatos::class.java))
                    this.finish()
                }
            }
        }
        return false
    }

    private fun mostrarEmpleados(datos: ArrayList<Any>?): String {
        var string  = ""
        if (datos != null) {
            for(o : Any in datos) {
                o as Empleado
                string += o.toString() + "\n"
            }
        }
        return string
    }

    private fun mostrarDepartamentos(datos: ArrayList<Any>?) : String {
        var string  = ""
        if (datos != null) {
            for(o : Any in datos) {
                o as Departamtento
                string += o.toString() + "\n"
            }
        }
        return string
    }
}

