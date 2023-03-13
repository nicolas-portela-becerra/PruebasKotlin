package com.npb.pruebaroom.fragments.list

import android.app.AlertDialog
import android.graphics.ColorSpace.Model
import android.location.GnssAntennaInfo.Listener
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.npb.pruebaroom.R
import com.npb.pruebaroom.databinding.FragmentListBinding
import com.npb.pruebaroom.databinding.FragmentUpdateBinding
import com.npb.pruebaroom.viewmodel.ViewModelUser

class ListFragment : Fragment() {
    private lateinit var mUserViewModel : ViewModelUser
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val listBinding = FragmentListBinding.bind(inflater.inflate(R.layout.fragment_list, container, false))

        //RECYCLERVIEW
        val adapter = ListAdapter()
        val recyclerView = listBinding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        //UserViewModel
        mUserViewModel = ViewModelProvider(this).get(ViewModelUser::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })
        listBinding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        //Tipico menu de arriba a la derecha
        /*val menu: MenuHost = requireActivity()
        menu.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                requireActivity().supportInvalidateOptionsMenu()
                //menu.clear()
                menuInflater.inflate(R.menu.delete_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem) : Boolean {
                if(menuItem.itemId == R.id.menuDelete) {
                    deleteAllUsers()
                }
                return false
            }
        })*/

        return listBinding.root;
    }

    private fun deleteAllUsers() {
        val builder = AlertDialog.Builder(this.requireContext().applicationContext)
        builder.setPositiveButton("Si") {_, _ ->
            mUserViewModel.deleteAllUsers()
            Toast.makeText(context, "Usuarios eliminados", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No") {_, _ -> }
        builder.setTitle("Borrar todos los usuarios?")
        builder.setMessage("Estas seguro de que quieres borrar todos los usuarios?")
        builder.create()
        builder.show()
    }
}