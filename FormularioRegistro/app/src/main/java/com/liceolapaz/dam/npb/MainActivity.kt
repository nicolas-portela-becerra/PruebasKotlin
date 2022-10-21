package com.liceolapaz.dam.npb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private lateinit var cmbPais: Spinner
    private lateinit var fldNombre: EditText
    private lateinit var fldApellidos: EditText
    private lateinit var fldContrasenha: EditText
    private lateinit var fldRepetirContrasenha: EditText
    private lateinit var fldTelefono: EditText
    private lateinit var fldFechaNacimiento: EditText
    private lateinit var fldEmail: EditText
    private lateinit var grbGrupo1: RadioGroup
    private lateinit var mensajeError: TextView

    //private lateinit var rbHombre : RadioButton
    //private lateinit var rbMujer : RadioButton
    private lateinit var chkBoletin: CheckBox
    private lateinit var btnRegistro: Button
    private lateinit var btnSalir: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cmbPais = findViewById(R.id.cmbPais)
        val adaptador = ArrayAdapter.createFromResource(
            this,
            R.array.paises,
            android.R.layout.simple_spinner_item
        )
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        cmbPais.adapter = adaptador
        var item = ""
        cmbPais.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                 item = (parent.getItemAtPosition(position) as Object).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        fldNombre = findViewById(R.id.fldNombre)
        fldApellidos = findViewById(R.id.fldApellidos)
        fldContrasenha = findViewById(R.id.fldContrasenha)
        fldRepetirContrasenha = findViewById(R.id.fldRepetirContrasenha)
        fldTelefono = findViewById(R.id.fldTelefono)
        fldFechaNacimiento = findViewById(R.id.fldFechaNacimiento)
        fldEmail = findViewById(R.id.fldEmail)
        grbGrupo1 = findViewById(R.id.grbGrupo1)
        var grbOpcion = ""
        grbGrupo1.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbHombre ->
                    grbOpcion = "Hombre"
                R.id.rbMujer ->
                    grbOpcion = "Mujer"
            }
        }
        chkBoletin = findViewById(R.id.chkBoletin)
        var boletin = ""
        chkBoletin.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked)
                boletin = "Sí"
            else
                boletin = "No"
        }
        mensajeError = findViewById(R.id.mensajeError)
        btnRegistro = findViewById(R.id.btnRegistro)
        btnSalir = findViewById(R.id.btnSalir)
        btnSalir.setOnClickListener {
                exitProcess(0)
        }

        btnRegistro.setOnClickListener {
            if(fldNombre.text.toString().isEmpty()){
                mensajeError.setText("Es obligatorio rellenar el nombre")
            }
            else if(fldApellidos.text.toString().isEmpty()){
                mensajeError.setText("Es obligatorio rellenar los apellidos")
            }
            else if(fldContrasenha.text.toString().isEmpty()){
                mensajeError.setText("Es obligatorio rellenar la contraseña")
            }
            else if(fldContrasenha.text.toString() != fldRepetirContrasenha.text.toString()){
                mensajeError.setText("Las contraseñas no son iguales")
            }
            else if(!fldFechaNacimiento.text.isEmpty()){
                if(!comprobarFecha(fldFechaNacimiento.text.toString())){
                    mensajeError.setText("Fecha inválida")
                }
            }
            else if(fldEmail.text.toString().isEmpty()){
                mensajeError.setText("Es obligatorio rellenar el correo electrónico")
            }
            else if(grbOpcion == ""){
                mensajeError.setText("Es obligatorio elegir un sexo")
            }
            else{
                val intent = Intent(this@MainActivity, InfoActivity::class.java)

                intent.putExtra("Nombre", fldNombre.text.toString())
                intent.putExtra("Apellidos", fldApellidos.text.toString())
                intent.putExtra("Contrasenha", fldContrasenha.text.toString())
                intent.putExtra("RepetirContrasenha", fldRepetirContrasenha.text.toString())
                intent.putExtra("Telefono", fldTelefono.text.toString())
                intent.putExtra("FechaNacimiento", fldFechaNacimiento.text.toString())
                intent.putExtra("Email", fldEmail.text.toString())
                intent.putExtra("BotonSexo", grbOpcion)
                intent.putExtra("Boletin", boletin)
                intent.putExtra("Pais", item)

                startActivity(intent)
            }
        }
    }

    private fun comprobarFecha(toString: String): Boolean {
        var partes = toString.split("/")
        if(partes.size > 3){
            return false
        }
        else{
            var dia = Integer.parseInt(partes[0])
            var mes = Integer.parseInt(partes[1])
            var anho = Integer.parseInt(partes[2])

            System.out.println("$dia, $mes, $anho")

            if(anho > 0 || anho < 22) {
                if(mes > 1 || mes < 12) {
                    if(dia > 1 || dia < 31){
                        return true
                    }
                    return false
                }
                return false
            }
            return false
        }
        return false

    }
}