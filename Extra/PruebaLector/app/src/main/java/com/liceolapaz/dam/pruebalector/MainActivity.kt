package com.liceolapaz.dam.pruebalector


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.client.android.Intents
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import com.liceolapaz.dam.pruebalector.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding : ActivityMainBinding

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


        viewBinding.btnEscaner.setOnClickListener() {
            val opciones = ScanOptions()
            opciones.setPrompt("Coloca el codigo dentro del recuadro")
            inicarEscaner.launch(opciones)
        }
    }
}