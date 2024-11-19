package com.example.submaster

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Activity_Estadistica : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estadistica)

        // Configuración el botones
        val btnLista = findViewById<Button>(R.id.btn_lista)
        val btnAgregar = findViewById<Button>(R.id.btn_agregar)

        // Configurar el evento de clic del botón "Lista"
        btnLista.setOnClickListener {
            // Abre MainActivity
            val intent = Intent(this@Activity_Estadistica, MainActivity::class.java)
            startActivity(intent)
        }

        // Configurar el evento de clic del botón "Agregar"
        btnAgregar.setOnClickListener {
            // Abre Activity_Formulario
            val intent = Intent(this@Activity_Estadistica, Activity_Formulario::class.java)
            startActivity(intent)
        }
    }
}