package com.liceolapaz.dam.pruebalector


import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.zxing.client.android.Intents
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import com.liceolapaz.dam.pruebalector.databinding.ActivityMainBinding
import androidx.fragment.app.add
import androidx.fragment.app.findFragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding : ActivityMainBinding
    private lateinit var fragment : Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)


        //Abrir el escaner
        val inicarEscaner = registerForActivityResult(ScanContract()) { result: ScanIntentResult ->
            //Recuperar los datos obtenidos del escaner
            if (result.contents == null) {
                val originalIntent = result.originalIntent
                if (originalIntent == null) {
                    //Mensaje al cerrar el escaner manualmente
                    Toast.makeText(this@MainActivity, "Cancelado", Toast.LENGTH_LONG).show()
                } else if (originalIntent.hasExtra(Intents.Scan.MISSING_CAMERA_PERMISSION)) {
                    //Error si el usuario no concede los permisos de la camara
                    Toast.makeText(this@MainActivity, "No hay permiso para acceder a la cámara", Toast.LENGTH_LONG).show()
                }
            } else {
                //Toast.makeText(this@MainActivity, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
                viewBinding.fldEscaneo.text = result.contents
            }
        }

        viewBinding.spTablas.onItemSelectedListener = object : OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

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

        viewBinding.btnResultados.setOnClickListener {
            lifecycleScope.launch {
                viewBinding.progressbar.visibility = View.VISIBLE
                viewBinding.resultados.text = withContext(Dispatchers.IO) {
                    val datos = LeerBd(viewBinding.fldTablas.text.toString(), this@MainActivity).call()
                    if(viewBinding.fldTablas.text.toString().equals("departamento", true)) {
                        mostrarDepartamentos(datos)
                    }
                    else {
                        mostrarEmpleados(datos)
                    }
                }
                viewBinding.progressbar.visibility = View.GONE
            }
        }
    }

    private fun mostrarEmpleados(datos: ArrayList<Object>?): String {
        var string  = ""
        if (datos != null) {
            for(o : Object in datos) {
                o as Empleado
                string += o.toString() + "\n"
            }
        }
        return string
    }

    private fun mostrarDepartamentos(datos: ArrayList<Object>?) : String {
        var string  = ""
        if (datos != null) {
            for(o : Object in datos) {
                o as Departamtento
                string += o.toString() + "\n"
            }
        }
        return string
    }
}

