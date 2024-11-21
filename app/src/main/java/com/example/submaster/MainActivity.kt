package com.example.submaster

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {

    private lateinit var listaDeServicios: MutableList<Service> // Lista mutable para almacenar los servicios
    private lateinit var adapter: ArrayAdapter<Service> // El adaptador para la lista

    companion object {
        const val REQUEST_CODE_FORMULARIO = 1 // Definir la constante para la solicitud
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        // Inicializas tu ListView
        val listaServicios: ListView = findViewById(R.id.lista_elementos)

        // Inicializar la lista y el adaptador
        listaDeServicios = mutableListOf()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaDeServicios)
        listaServicios.adapter = adapter

        // Configuración el botones
        val btnAgregar = findViewById<Button>(R.id.btn_agregar)
        val btnEstadistica = findViewById<Button>(R.id.btn_estadistica)

        // Configurar el evento de clic del botón "Agregar"
        btnAgregar.setOnClickListener {
            // Abre Activity_Formulario
            val intent = Intent(this@MainActivity, Activity_Formulario::class.java)
            startActivityForResult(intent, REQUEST_CODE_FORMULARIO) // Usar el código de solicitud
        }

        // Configurar el evento de clic del botón "Estadisticas"
        btnEstadistica.setOnClickListener {
            // Abre Activity_Estadistica
            val intent = Intent(this@MainActivity, Activity_Estadistica::class.java)
            startActivity(intent)
        }

        // Agregar el OnItemClickListener para manejar el clic en un item de la lista
        listaServicios.setOnItemClickListener { parent, view, position, id ->
            // Obtener el servicio que fue clickeado
            val servicioSeleccionado = parent.getItemAtPosition(position) as Service

            // Crear el fragmento y pasar los datos
            val detallesDialogFragment = DetallesDialogFragment()

            // Crear un Bundle para pasar el objeto Service al fragmento
            val bundle = Bundle()
            bundle.putSerializable("service", servicioSeleccionado)

            // Asignar el Bundle al fragmento
            detallesDialogFragment.arguments = bundle

            // Realizar el cambio de fragmento
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, detallesDialogFragment) // Usar el contenedor adecuado
            transaction.addToBackStack(null)  // Agrega la transacción al back stack para poder regresar
            transaction.commit()  // Ejecuta la transacción
        }
    }

    // Recibir el resultado del formulario
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_FORMULARIO && resultCode == RESULT_OK) {
            val servicioNuevo = data?.getSerializableExtra("nuevo_servicio") as? Service
            servicioNuevo?.let {
                listaDeServicios.add(it)
                adapter.notifyDataSetChanged() // Notificar que la lista se actualizó
            }
        }
    }
}
