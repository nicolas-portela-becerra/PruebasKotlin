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
import androidx.viewbinding.ViewBindings
import com.liceolapaz.dam.pruebalector.databinding.FragmentDepartamentoBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.SQLException

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentDepartamento : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_departamento, container, false)
    }

    //ACTIVITY LISTENER
    //Setear el click listener del boton del fragment cuando se pulsa desde la activity
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cod = view.findViewById<EditText>(resources.getIdentifier("fldCod", "id", context?.packageName))
        val nom = view.findViewById<EditText>(resources.getIdentifier("fldNom", "id", context?.packageName))
        val pais = view.findViewById<EditText>(resources.getIdentifier("fldPaisDep", "id", context?.packageName))
        val progress = view.findViewById<ProgressBar>(resources.getIdentifier("progressbar", "id", context?.packageName))
        val e = view.findViewById<View>(resources.getIdentifier("btnEnviar", "id", context?.packageName))
        e.setOnClickListener {
            if(!cod.text.isEmpty() and !nom.text.isEmpty() and !pais.text.isEmpty()) {
                progress.visibility = View.VISIBLE
                lifecycleScope.launch(Dispatchers.IO) {
                    val depart = Departamento(cod.text.toString().toInt(), nom.text.toString(), pais.text.toString())
                    if (Departamentos(depart, requireContext()).call() == 1) {
                        Handler(Looper.getMainLooper()).post {
                            Toast.makeText(context, "Departamento insertado", Toast.LENGTH_SHORT).show()
                        }

                    }
                    withContext(Dispatchers.Main) {
                        progress.visibility = View.GONE
                    }
                }
            }
            else {
                progress.visibility = View.GONE
                Toast.makeText(context, "Los campos deben estar rellenados", Toast.LENGTH_SHORT).show()
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