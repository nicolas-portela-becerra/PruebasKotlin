package com.liceolapaz.dam.pruebalector


import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.zxing.client.android.Intents
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import com.liceolapaz.dam.pruebalector.databinding.ActivityMainBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            // Si no tiene el permiso, solicita al usuario que lo otorgue
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.INTERNET), 1)
        }

        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager.isDefaultNetworkActive) {
            // La aplicación tiene acceso a Internet

            viewBinding.txtInternet.setText("Hay internet")
        } else {
            // La aplicación no tiene acceso a Internet
            viewBinding.txtInternet.setText("No hay internet")
        }

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

        //val conexion = BD.getConexionMYSQL()
        //val conexion = BD.getConexionPOSTGRE()
        var pool = Executors.newSingleThreadExecutor()
        var hilo = BD()
        val conexion = pool.submit(hilo).get()
        if(!(conexion.isEmpty())){
            var string = ""
            for(s in conexion) {
                string += s
            }
            viewBinding.resultados.text = string
        }
        else {
            viewBinding.resultados.setText("Resulatados vacio")
        }

        viewBinding.btnEscaner.setOnClickListener {
            val opciones = ScanOptions()
            opciones.setPrompt("Coloca el codigo dentro del recuadro")
            inicarEscaner.launch(opciones)
        }
    }
}