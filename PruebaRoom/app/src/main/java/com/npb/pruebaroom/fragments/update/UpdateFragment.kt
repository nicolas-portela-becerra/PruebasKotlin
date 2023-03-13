package com.npb.pruebaroom.fragments.update

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
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
        //TOOLBAR
        setHasOptionsMenu(true)
        
        return updateBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menuDelete) {
            deleteUser()
        }
        return super.onContextItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(context)
        builder.setPositiveButton("Si") {_, _ ->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(context, "Usuario ${args.currentUser.firstName} eliminado", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") {_, _ -> }
        builder.setTitle("Borrar ${args.currentUser.firstName}?")
        builder.setMessage("Estas seguro de que quieres borrar a ${args.currentUser.firstName}")
        builder.create()
        builder.show()
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