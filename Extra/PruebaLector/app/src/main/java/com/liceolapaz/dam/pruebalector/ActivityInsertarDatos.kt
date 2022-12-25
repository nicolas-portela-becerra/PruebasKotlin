package com.liceolapaz.dam.pruebalector


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.google.zxing.client.android.Intents
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import com.liceolapaz.dam.pruebalector.databinding.ActivityInsertarDatosBinding
import androidx.fragment.app.add

class ActivityInsertarDatos : AppCompatActivity() {
    private lateinit var viewBinding : ActivityInsertarDatosBinding
    private var x1 : Float = 0F
    private var y1 : Float = 0F
    private var x2: Float = 0F
    private var y2: Float = 0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityInsertarDatosBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)


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
                viewBinding.fldEscaneo.text = result.contents
            }
        }

        viewBinding.spTablas.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    if(viewBinding.spTablas.selectedItem.toString().equals("departamento", true)) {
                        add<FragmentDepartamento>(R.id.fragmentContainer)
                    }
                    if(viewBinding.spTablas.selectedItem.toString().equals("empleado", true)) {
                        add<FragmentEmpleado>(R.id.fragmentContainer)
                    }
                }
            }
        }

        viewBinding.btnEscaner.setOnClickListener {
            val opciones = ScanOptions()
            opciones.setPrompt("Coloca el codigo dentro del recuadro")
            inicarEscaner.launch(opciones)
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
                if (x1 + 200 > x2) {
                    startActivity(Intent(this@ActivityInsertarDatos, ActivityRecuperarDatos::class.java))
                    this.finish()
                }
            }
        }
        return false
    }
}

