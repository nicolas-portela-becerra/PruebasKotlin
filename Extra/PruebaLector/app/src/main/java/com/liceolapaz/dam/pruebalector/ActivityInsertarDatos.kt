package com.liceolapaz.dam.pruebalector

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.google.android.material.navigation.NavigationView
import com.google.zxing.client.android.Intents
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import com.liceolapaz.dam.pruebalector.databinding.ActivityInsertarDatosNavBinding


class ActivityInsertarDatos : AppCompatActivity() {
    private lateinit var binding : ActivityInsertarDatosNavBinding
    private var x1 : Float = 0F
    private var y1 : Float = 0F
    private var x2: Float = 0F
    private var y2: Float = 0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertarDatosNavBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.include.toolbar)
        showNav()

        //Abrir el escaner
        val inicarEscaner = registerForActivityResult(ScanContract()) { result: ScanIntentResult ->
            //Recuperar los datos obtenidos del escaner
            if (result.contents == null) {
                val originalIntent = result.originalIntent
                if (originalIntent == null) {
                    //Mensaje al cerrar el escaner manualmente
                    Toast.makeText(this@ActivityInsertarDatos, "Cancelado", Toast.LENGTH_LONG).show()
                } else if (originalIntent.hasExtra(Intents.Scan.MISSING_CAMERA_PERMISSION)) {
                    //Error si el usuario no concede los permisos de la camara
                    Toast.makeText(this@ActivityInsertarDatos, "No hay permiso para acceder a la cámara", Toast.LENGTH_LONG).show()
                }
            } else {
                //Toast.makeText(this@MainActivity, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
                binding.include.fldEscaneo.text = result.contents
            }
        }

        binding.include.spTablas.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    if(binding.include.spTablas.selectedItem.toString().equals("departamento", true)) {
                        add<FragmentDepartamento>(R.id.fragmentContainer)
                    }
                    if(binding.include.spTablas.selectedItem.toString().equals("empleado", true)) {
                        add<FragmentEmpleado>(R.id.fragmentContainer)
                    }
                }
            }
        }

        binding.include.btnEscaner.setOnClickListener {
            val opciones = ScanOptions()
            opciones.setPrompt("Coloca el codigo dentro del recuadro")
            inicarEscaner.launch(opciones)
        }
    }

    private fun showNav() {
        val toggle = ActionBarDrawerToggle(this, binding.drawerLayout, binding.include.toolbar, R.string.open, R.string.close)
        toggle.isDrawerIndicatorEnabled = true
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        binding.navView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { item ->
            Log.i(":::", "Listener")
            Log.i(":::", "Item: " + item)
            when (item.toString()) {
                "Recuperar datos"-> {
                    Log.i(":::", "recuperar")
                    startActivity(Intent(this@ActivityInsertarDatos, ActivityRecuperarDatos::class.java))
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
}

