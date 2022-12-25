package com.liceolapaz.dam.pruebalector

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.liceolapaz.dam.pruebalector.databinding.FragmentEmpleadoBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.FutureTask

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentEmpleado : Fragment() {
    private lateinit var viewBinding : FragmentEmpleadoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = FragmentEmpleadoBinding.inflate(layoutInflater)

        viewBinding.btnEnviar.setOnClickListener {

        }

        arguments?.let {}
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_empleado, container, false)
    }

    //ACTIVITY LISTENER
    //Setear el click listener del boton del fragment cuando se pulsa desde la activity
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = resources.getIdentifier("btnEnviar", "id", context?.packageName)
        val e = view.findViewById<View>(id)
        e.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val emp = Empleado(viewBinding.fldCod.text.toString().toInt(), viewBinding.fldNom.text.toString(), viewBinding.fldApellidos.text.toString(),
                    viewBinding.fldPuesto.text.toString(), viewBinding.fldSalario.text.toString().toInt(), viewBinding.fldCodDep.text.toString().toInt())
                if(Empleados(emp).call() == 1) {
                    Toast.makeText(context, "Empleado insertado", Toast.LENGTH_SHORT).show()
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