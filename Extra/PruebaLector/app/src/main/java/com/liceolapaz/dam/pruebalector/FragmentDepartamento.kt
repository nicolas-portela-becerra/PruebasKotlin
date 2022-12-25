package com.liceolapaz.dam.pruebalector

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.liceolapaz.dam.pruebalector.databinding.FragmentDepartamentoBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.FutureTask

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentDepartamento : Fragment() {
    private lateinit var viewBinding : FragmentDepartamentoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = FragmentDepartamentoBinding.inflate(layoutInflater)

        arguments?.let {}
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_departamento, container, false)
    }

    //ACTIVITY LISTENER
    //Setear el click listener del boton del fragment cuando se pulsa desde la activity
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = resources.getIdentifier("btnEnviar", "id", context?.packageName)
        val e = view.findViewById<View>(id)
        e.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val depart = Departamtento(viewBinding.fldCod.toString().toInt(), viewBinding.fldNom.toString(), viewBinding.fldPaisDep.toString())
                if(Departamentos(depart).call() == 1) {
                    Toast.makeText(context, "Departamento insertado", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    //ACTIVITY LISTENER

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentDepartamento().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}