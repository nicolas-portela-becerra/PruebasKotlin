package com.npb.pruebaroom.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.npb.pruebaroom.R
import com.npb.pruebaroom.databinding.ListCellBinding
import com.npb.pruebaroom.model.User

class ListAdapter() : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var userList = emptyList<User>()

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_cell, parent, false))
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val cellBinding = ListCellBinding.bind(holder.itemView)
        val currentItem = userList[position]

        cellBinding.tvId.text = currentItem.id.toString()
        cellBinding.tvNombre.text = currentItem.firstName
        cellBinding.tvApellido.text = currentItem.lastName
        cellBinding.tvEdad.text = currentItem.age.toString()

        cellBinding.root.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
        
    }

    fun setData(user : List<User>) {
        this.userList = user
        notifyDataSetChanged()
    }
}

