package com.liceolapaz.dam.pruebalector

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.liceolapaz.dam.pruebalector.databinding.FragmentEmpleadoBinding
import java.util.concurrent.FutureTask

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentEmpleado : Fragment() {
    private lateinit var viewBinding : FragmentEmpleadoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = FragmentEmpleadoBinding.inflate(layoutInflater)

        viewBinding.btnEnviar.setOnClickListener {
            val emp = Empleado(viewBinding.fldCod.text.toString().toInt(), viewBinding.fldNom.text.toString(), viewBinding.fldApellidos.text.toString(), viewBinding.fldPuesto.text.toString(), viewBinding.fldSalario.text.toString().toInt(), viewBinding.fldCodDep.text.toString().toInt())
            val resultado = FutureTask(Empleados(emp)).get()
            Toast.makeText(context, "Empledado: " + resultado, Toast.LENGTH_SHORT).show()
        }

        arguments?.let {}
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_empleado, container, false)
    }

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