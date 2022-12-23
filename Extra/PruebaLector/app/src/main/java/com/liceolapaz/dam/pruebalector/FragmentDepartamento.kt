package com.liceolapaz.dam.pruebalector

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.liceolapaz.dam.pruebalector.databinding.FragmentDepartamentoBinding
import java.util.concurrent.FutureTask

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentDepartamento : Fragment() {
    private lateinit var viewBinding : FragmentDepartamentoBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = FragmentDepartamentoBinding.inflate(layoutInflater)

        viewBinding.btnEnviar.setOnClickListener {
            val dep = Departamtento(viewBinding.fldCod.text.toString().toInt(), viewBinding.fldNom.text.toString(), viewBinding.fldPaisDep.text.toString())
            val resultado = FutureTask(Departamentos(dep)).get()
            Toast.makeText(context, "Departamento: " +resultado, Toast.LENGTH_SHORT).show()
        }

        arguments?.let {}
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_departamento, container, false)
    }

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