package com.npb.pruebaroom.fragments.update

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.room.Update
import com.npb.pruebaroom.R
import com.npb.pruebaroom.databinding.FragmentUpdateBinding
import com.npb.pruebaroom.model.User
import com.npb.pruebaroom.viewmodel.ViewModelUser

class UpdateFragment : Fragment() {
    private lateinit var mUserViewModel : ViewModelUser

    private val args by navArgs<UpdateFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val updateBinding = FragmentUpdateBinding.bind(inflater.inflate(R.layout.fragment_update, container, false))

        mUserViewModel = ViewModelProvider(this).get(ViewModelUser::class.java)

        updateBinding.etUpdateNombre.setText(args.currentUser.firstName)
        updateBinding.etUpdateApellido.setText(args.currentUser.lastName)
        updateBinding.etUpdateEdad.setText(args.currentUser.age.toString())

        updateBinding.btnGuardar.setOnClickListener {
            updateItem(updateBinding)
        }

        return updateBinding.root
    }
    private fun updateItem(updateBinding : FragmentUpdateBinding) {
        val name = updateBinding.etUpdateNombre.text.toString()
        val lastName = updateBinding.etUpdateApellido.text.toString()
        val age = Integer.parseInt(updateBinding.etUpdateEdad.text.toString())

        if(controlInputs(name, lastName, age)) {
            val updatedUser = User(args.currentUser.id, name, lastName, age)
            mUserViewModel.updateUser(updatedUser)

            Toast.makeText(context, "Usuario actualizado", Toast.LENGTH_SHORT).show()
            
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
    }

    private fun controlInputs(nombre: String, apellido: String, edad: Int) : Boolean {
        if (TextUtils.isEmpty(nombre)) {
            Toast.makeText(context, "El nombre no puede estar vacio", Toast.LENGTH_SHORT).show()
            return false
        }
        if (TextUtils.isEmpty(apellido)) {
            Toast.makeText(context, "El apellido no puede estar vacio", Toast.LENGTH_SHORT).show()
            return false
        }
        if (TextUtils.isEmpty(edad.toString())) {
            Toast.makeText(context, "La edad no puede estar vacia", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}