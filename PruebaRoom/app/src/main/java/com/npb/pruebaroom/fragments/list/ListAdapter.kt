package com.npb.pruebaroom.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.npb.pruebaroom.R
import com.npb.pruebaroom.model.User

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var userList = emptyList<User>()
    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_cell, parent, false))
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.findViewById<TextView>(R.id.tvId).text = currentItem.id.toString()
        holder.itemView.findViewById<TextView>(R.id.tvNombre).text = currentItem.firstName
        holder.itemView.findViewById<TextView>(R.id.tvApellido).text = currentItem.lastName
        holder.itemView.findViewById<TextView>(R.id.tvEdad).text = currentItem.age.toString()

        holder.itemView.findViewById<ConstraintLayout>(R.id.cellLayout).setOnClickListener {
            
        }
    }

    fun setData(user : List<User>) {
        this.userList = user
        notifyDataSetChanged()
    }
}