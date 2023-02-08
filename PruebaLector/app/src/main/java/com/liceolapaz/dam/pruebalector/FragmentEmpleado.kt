package com.liceolapaz.dam.pruebalector

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.liceolapaz.dam.pruebalector.databinding.FragmentEmpleadoBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.SQLException
import java.util.concurrent.FutureTask

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentEmpleado : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_empleado, container, false)
    }

    //ACTIVITY LISTENER
    //Setear el click listener del boton del fragment cuando se pulsa desde la activity
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        //ELEMENTOS DEL FRAGMENT
        val cod = view.findViewById<EditText>(resources.getIdentifier("fldCod", "id", context?.packageName))
        val nom = view.findViewById<EditText>(resources.getIdentifier("fldNom", "id", context?.packageName))
        val apellidos = view.findViewById<EditText>(resources.getIdentifier("fldApellidos", "id", context?.packageName))
        val puesto = view.findViewById<EditText>(resources.getIdentifier("fldPuesto", "id", context?.packageName))
        val salario = view.findViewById<EditText>(resources.getIdentifier("fldSalario", "id", context?.packageName))
        val codDep = view.findViewById<EditText>(resources.getIdentifier("fldCodDep", "id", context?.packageName))
        val progress = view.findViewById<ProgressBar>(resources.getIdentifier("progressbar", "id", context?.packageName))
        val e = view.findViewById<View>(resources.getIdentifier("btnEnviar", "id", context?.packageName))
        //ELEMENTOS DEL FRAGMENT
        e.setOnClickListener {
            progress.visibility = View.VISIBLE
            if(!cod.text.isEmpty() and !cod.text.isEmpty() and !cod.text.isEmpty() and !cod.text.isEmpty() and !cod.text.isEmpty() and !cod.text.isEmpty()){
                lifecycleScope.launch(Dispatchers.IO) {
                    val emp = Empleado(cod.text.toString().toInt(), nom.text.toString(), apellidos.text.toString(), puesto.text.toString(), salario.text.toString().toInt(), codDep.text.toString().toInt())
                    val resultado = Empleados(emp, requireContext()).call()
                    if(resultado == 1) {
                        Handler(Looper.getMainLooper()).post {
                            Toast.makeText(context, "Empleado insertado", Toast.LENGTH_SHORT).show()
                        }
                    }
                    withContext(Dispatchers.Main) {
                        progress.visibility = View.GONE
                    }
                }
            }
            else {
                //TODO("Entender por que el fragment departamento si funciona pero este no")
                Handler(Looper.getMainLooper()).post {
                    progress.visibility = View.GONE
                    Toast.makeText(context, "Todos los campos deben estar llenos", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    //ACTIVITY LISTENER

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentEmpleado().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}