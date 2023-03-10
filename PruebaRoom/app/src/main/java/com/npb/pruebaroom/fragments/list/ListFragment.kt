package com.npb.pruebaroom.fragments.list

import android.graphics.ColorSpace.Model
import android.location.GnssAntennaInfo.Listener
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.npb.pruebaroom.R
import com.npb.pruebaroom.viewmodel.ViewModelUser

class ListFragment : Fragment() {
    private lateinit var mUserViewModel : ViewModelUser
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view =  inflater.inflate(R.layout.fragment_list, container, false)

        //RECYCLERVIEW
        val adapter = ListAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        //UserViewModel
        mUserViewModel = ViewModelProvider(this).get(ViewModelUser::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })
        view.findViewById<FloatingActionButton>(R.id.floatingActionButton).setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        return view;
    }
}