package com.example.submaster

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class Activity_Formulario : AppCompatActivity() {

    val editnombre: EditText = findViewById(R.id.txtNombre)
    val editfecha_A: EditText = findViewById(R.id.txtFecha_A)
    val editfecha_C: EditText = findViewById(R.id.txtFecha_B)
    val editfrecuencia: EditText = findViewById(R.id.txtCobro)
    val spinnertipoDebito: Spinner = findViewById(R.id.TipoDebito)
    val radiotipo_ser: RadioGroup = findViewById(R.id.rdbTiposer)
    val editTarjeta: EditText = findViewById(R.id.txtTarjeta)

    val boton_aceptar: Button = findViewById(R.id.btnAgregar)
    val boton_cancelar: Button = findViewById(R.id.btnCancelar)

    // Configurar opciones del Spinner
    // val opciones = listOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_formulario)

        boton_aceptar.setOnClickListener(View.OnClickListener {
            val nombre = editnombre.text.toString().trim()
            val fecha_a = editfecha_A.text.toString().trim()
            val fecha_c = editfecha_C.text.toString().trim()
            val frecuencia = editfrecuencia.text.toString().trim()
            // val tipodebito = spinnertipoDebito.selectedItem.toString()
            val tipo_ser_id = radiotipo_ser.checkedRadioButtonId
            val tiposer = if (tipo_ser_id != -1) {
                findViewById<RadioButton>(tipo_ser_id).text.toString()
            } else {
                "" // Si ningún valor del radiobutton fue seleccionado devuelve cadena vacia
            }
            val tarjeta = editTarjeta.text.toString().trim()

            if (nombre.isEmpty() ||
                !esFechaValida(fecha_a) ||
                !esFechaValida(fecha_c) ||
                frecuencia.isEmpty() ||
                tiposer.isEmpty() ||
                tarjeta.isEmpty()){

                Toast.makeText(this, "Completa todos los campos.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Todos los campos fueron completados.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun esFechaValida(fecha:String): Boolean {
        return try {
            val formato = SimpleDateFormat("dd/mm/yyy", Locale.getDefault())
            formato.isLenient = false
            formato.parse(fecha) != null
        } catch (e: Exception) {
            false
        }
    }


}