package com.liceolapaz.dam.pruebalector

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.liceolapaz.dam.pruebalector.databinding.CeldaRegistroDepartamentoBinding
import com.liceolapaz.dam.pruebalector.databinding.CeldaRegistroEmpleadoBinding


class DatosAdapter (val datosEmpleados : ArrayList<Empleado>?, val datosDepartamentos : ArrayList<Departamento>?, val tipo : String, val clickListener : (Any) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var ctx : Context

    override fun getItemViewType(position: Int): Int {
        if(datosEmpleados != null) {
            return R.layout.celda_registro_empleado
        }
        if(datosDepartamentos != null) {
            return R.layout.celda_registro_departamento
        }
        return -1
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        when(viewType) {
            R.layout.celda_registro_departamento -> return DatosViewHolderDepartamento(CeldaRegistroDepartamentoBinding.inflate(LayoutInflater.from(parent.context), parent, false))

            R.layout.celda_registro_empleado -> return DatosViewHolderEmpleado(CeldaRegistroEmpleadoBinding.inflate(LayoutInflater.from(parent.context), parent, false))

            else -> throw java.lang.IllegalArgumentException("El tipo dado no está soportado")
        }

    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        when(holder.itemViewType) {
            R.layout.celda_registro_empleado -> {
                val empleado = datosEmpleados?.get(position)
                Log.i("EMPLEADO", empleado.toString())
                holder as DatosViewHolderEmpleado
                if (empleado != null) {
                    Log.i("ViewHolder", "Registro cargado")
                    holder.cargarDatos(empleado, clickListener)
                }
                else {
                    Log.i("ERROR", "NULL")
                }
            }

            R.layout.celda_registro_departamento -> {
                val departamento = datosDepartamentos?.get(position)
                holder as DatosViewHolderDepartamento
                if (departamento != null) {
                    Log.i("ViewHolder", "Registro cargado")
                    holder.cargarDatos(departamento, clickListener)
                }
                else {
                    Log.i("ERROR", "NULL")
                }
            }
        }
    }

    override fun getItemCount(): Int {
        if(datosDepartamentos != null){
            return datosDepartamentos.size
        }
        if(datosEmpleados != null) {
            return datosEmpleados.size
        }
        return -1
    }
}