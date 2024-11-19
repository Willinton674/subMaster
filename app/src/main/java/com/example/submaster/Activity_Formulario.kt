package com.example.submaster

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Activity_Formulario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_formulario)

        // Configuración el botones
        val btnLista = findViewById<Button>(R.id.btn_lista)
        val btnEstadistica = findViewById<Button>(R.id.btn_estadistica)

        // Configurar el evento de clic del botón "Lista"
        btnLista.setOnClickListener {
            // Abre MainActivity
            val intent = Intent(this@Activity_Formulario, MainActivity::class.java)
            startActivity(intent)
        }

        // Configurar el evento de clic del botón "Estadisticas"
        btnEstadistica.setOnClickListener {
            // Abre Activity_Estadistica
            val intent = Intent(this@Activity_Formulario, Activity_Estadistica::class.java)
            startActivity(intent)
        }
    }
}