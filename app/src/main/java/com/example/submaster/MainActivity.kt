package com.example.submaster

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Forzar el modo claro
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_main)

        // Configuración el botones
        val btnAgregar = findViewById<Button>(R.id.btn_agregar)
        val btnEstadistica = findViewById<Button>(R.id.btn_estadistica)

        // Configurar el evento de clic del botón "Agregar"
        btnAgregar.setOnClickListener {
            // Abre Activity_Formulario
            val intent = Intent(this@MainActivity, Activity_Formulario::class.java)
            startActivity(intent)
        }

        // Configurar el evento de clic del botón "Estadisticas"
        btnEstadistica.setOnClickListener {
            // Abre Activity_Estadistica
            val intent = Intent(this@MainActivity, Activity_Estadistica::class.java)
            startActivity(intent)
        }

    }
}
