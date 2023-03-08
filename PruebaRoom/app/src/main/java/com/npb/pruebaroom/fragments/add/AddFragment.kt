package com.npb.pruebaroom.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.npb.pruebaroom.R
import com.npb.pruebaroom.data.User
import com.npb.pruebaroom.database.ViewModelUser

class AddFragment : Fragment() {
    private lateinit var userViewModel : ViewModelUser
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_add, container, false)
        userViewModel = ViewModelProvider(this).get(ViewModelUser::class.java)
        view.findViewById<Button>(R.id.btnGuardar).setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val nombre = view?.findViewById<EditText>(R.id.etNombre)?.text.toString()
        val apellido = view?.findViewById<EditText>(R.id.etApellido)?.text.toString()
        val edad = view?.findViewById<EditText>(R.id.etEdad)?.text.toString()
        
        if (controlInputs(nombre, apellido, edad)) {
            val user = User(0, nombre, apellido, Integer.parseInt(edad))
            userViewModel.addUser(user)
            Toast.makeText(context, "Usuario añadido", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
    }

    private fun controlInputs(nombre: String, apellido: String, edad: String) : Boolean {
        if (TextUtils.isEmpty(nombre)) {
            Toast.makeText(context, "El nombre no puede estar vacio", Toast.LENGTH_SHORT).show()
            return false
        }
        if (TextUtils.isEmpty(apellido)) {
            Toast.makeText(context, "El apellido no puede estar vacio", Toast.LENGTH_SHORT).show()
            return false
        }
        if (TextUtils.isEmpty(edad)) {
            Toast.makeText(context, "La edad no puede estar vacia", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}