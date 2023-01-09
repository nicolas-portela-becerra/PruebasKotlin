package com.liceolapaz.dam.pruebalector

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.liceolapaz.dam.pruebalector.databinding.CeldaRegistroDepartamentoBinding
import com.liceolapaz.dam.pruebalector.databinding.CeldaRegistroEmpleadoBinding
import kotlinx.coroutines.delay

class DatosViewHolderDepartamento(private val binding: CeldaRegistroDepartamentoBinding) : RecyclerView.ViewHolder(binding.root) {

    fun cargarDatos(departamento : Departamento, clickListener : (Departamento) -> Unit) {
        binding.txtCod.text = departamento.cod.toString()

        binding.txtNom.setText(departamento.nom)

        binding.txtPais.setText(departamento.pais)

        itemView.setOnClickListener {clickListener(departamento)}
    }
}