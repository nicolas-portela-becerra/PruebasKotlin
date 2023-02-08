package com.liceolapaz.dam.pruebalector

import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.liceolapaz.dam.pruebalector.databinding.CeldaRegistroEmpleadoBinding

class DatosViewHolderEmpleado(private val binding: CeldaRegistroEmpleadoBinding) : RecyclerView.ViewHolder(binding.root) {

    fun cargarDatos(empleado : Empleado, clickListener : (Empleado) -> Unit) {
        binding.txtEmpleadoCod.text = empleado.cod.toString()

        binding.txtEmpleadoNom.text = empleado.nom

        binding.txtEmpleadoApellidos.text = empleado.apellido

        binding.txtEmpleadoPuesto.text = empleado.puesto

        binding.txtEmpleadoSalario.text = empleado.salario.toString()

        binding.txtEmpleadoCodDep.text = empleado.codDepartamento.toString()

        itemView.setOnClickListener {clickListener(empleado)}
    }
}