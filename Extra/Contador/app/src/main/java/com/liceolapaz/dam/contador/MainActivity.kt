package com.liceolapaz.dam.contador

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.liceolapaz.dam.contador.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMas.setOnClickListener {
            val numero = binding.fldContador.text.toString().toInt() + 1
            binding.fldContador.setText(numero.toString())
        }

        binding.btnMenos.setOnClickListener {
            val numero = binding.fldContador.text.toString().toInt() - 1
            binding.fldContador.setText(numero.toString())
        }
    }


}