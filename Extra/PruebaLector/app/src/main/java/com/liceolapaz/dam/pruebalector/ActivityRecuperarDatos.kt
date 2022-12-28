package com.liceolapaz.dam.pruebalector

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.navigation.NavigationView
import com.liceolapaz.dam.pruebalector.databinding.ActivityRecuperarDatosBinding
import com.liceolapaz.dam.pruebalector.databinding.ActivityRecuperarDatosNavBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActivityRecuperarDatos : AppCompatActivity() {
    private lateinit var binding : ActivityRecuperarDatosNavBinding
    private var x1 : Float = 0F
    private var y1 : Float = 0F
    private var x2: Float = 0F
    private var y2: Float = 0F
    private lateinit var datos : ArrayList<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecuperarDatosNavBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.include.toolbar)
        showNav()

        binding.include.btnResultados.setOnClickListener {
            lifecycleScope.launch {
                if(binding.include.fldTablas.text.isEmpty()){
                    Toast.makeText(this@ActivityRecuperarDatos, "Introduce un nombre para buscar", Toast.LENGTH_SHORT).show()
                }
                else {
                    binding.include.progressbar.visibility = View.VISIBLE
                    lifecycleScope.launch(Dispatchers.IO) {
                        datos = LeerBd(binding.include.fldTablas.text.toString(), this@ActivityRecuperarDatos).call()
                        if(datos.isNotEmpty()) {
                            if(binding.include.fldTablas.text.toString().equals("departamento", true)) {
                                mostrarDepartamentos(datos)
                            }
                            if(binding.include.fldTablas.text.toString().equals("empleado", true)) {
                                mostrarEmpleados(datos)
                            }
                        }
                        withContext(Dispatchers.Main) {
                            binding.include.progressbar.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    private fun showNav() {
        val toggle = ActionBarDrawerToggle(this, binding.drawerLayout, binding.include.toolbar, R.string.open, R.string.close)
        toggle.isDrawerIndicatorEnabled = true
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        binding.navView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { item ->
            when (item.toString()) {
                "Insertar datos"-> {
                    startActivity(Intent(this@ActivityRecuperarDatos, ActivityInsertarDatos::class.java))
                    this.finish()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
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
                if ((x1 <= 40F) and (x2 <= 200F)) {
                    showNav()
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

