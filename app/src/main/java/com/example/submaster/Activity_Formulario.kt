package com.example.submaster

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class Activity_Formulario : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

        // Inicialización de vistas
        val editNombre: EditText = findViewById(R.id.txtNombre)
        val datePickerAdquisicion: DatePicker = findViewById(R.id.datePickerAdquisicion)
        val datePickerCobro: DatePicker = findViewById(R.id.datePickerCobro)
        val spinnerFrecuencia: Spinner = findViewById(R.id.spinnerFrecuencia)
        val spinnerTipoDebito: Spinner = findViewById(R.id.spinnerTipoDebito)
        val radioGroupTipoServicio: RadioGroup = findViewById(R.id.rdbTiposer)
        val editTarjeta: EditText = findViewById(R.id.txtTarjeta)
        val botonAgregar: Button = findViewById(R.id.btnAgregar)
        val botonCancelar: Button = findViewById(R.id.btnCancelar)

        // Opciones para los Spinners
        val opcionesFrecuencia = listOf("Diario", "Semanal", "Mensual", "Anual")
        val opcionesTipoDebito = listOf("Crédito", "Débito", "Otro")

        // Configuración de los Spinners
        val adapterFrecuencia = ArrayAdapter(this, android.R.layout.simple_spinner_item, opcionesFrecuencia)
        adapterFrecuencia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFrecuencia.adapter = adapterFrecuencia

        val adapterTipoDebito = ArrayAdapter(this, android.R.layout.simple_spinner_item, opcionesTipoDebito)
        adapterTipoDebito.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTipoDebito.adapter = adapterTipoDebito

        // Configuración del botón "Agregar"
        botonAgregar.setOnClickListener {
            val nombre = editNombre.text.toString().trim()
            val fechaAdquisicion = obtenerFechaDesdeDatePicker(datePickerAdquisicion)
            val fechaCobro = obtenerFechaDesdeDatePicker(datePickerCobro)
            val frecuencia = spinnerFrecuencia.selectedItem.toString()
            val tipoDebito = spinnerTipoDebito.selectedItem.toString()
            val tipoServicioId = radioGroupTipoServicio.checkedRadioButtonId
            val tipoServicio = findViewById<RadioButton>(tipoServicioId).text.toString()

            val tarjeta = editTarjeta.text.toString().trim()

            // Validaciones
            if (nombre.isEmpty() ||
                tipoServicio.isEmpty() ||
                tarjeta.length != 4 ||
                frecuencia.isEmpty() ||
                tipoDebito.isEmpty() ||
                !esFechaValida(datePickerAdquisicion) || // Validar si la fecha de adquisición es válida
                !esFechaValida(datePickerCobro) || //Validar si la fecha de cobro es válida
                !esTipoServicioValido(tipoServicioId)) { // Validar si el tipo de servicio es válido
                Toast.makeText(this, "Por favor, completa todos los campos correctamente.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Servicio agregado con éxito.", Toast.LENGTH_SHORT).show()
                finish()
            }

        }

        // Configuración del botón "Cancelar"
        botonCancelar.setOnClickListener {
            finish() // Cierra la actividad
        }
    }

    // Función para obtener una fecha formateada desde un DatePicker
    private fun obtenerFechaDesdeDatePicker(datePicker: DatePicker): String {
        val day = datePicker.dayOfMonth
        val month = datePicker.month + 1 // Los meses son base 0
        val year = datePicker.year
        return String.format(Locale.getDefault(), "%02d/%02d/%04d", day, month, year)
    }

    fun esTipoServicioValido(tipoServicioId: Int): Boolean {
        return tipoServicioId != -1 // Si no se selecciona ningún RadioButton, el valor será -1
    }

    // Función para validar si la fecha es la predeterminada (por ejemplo, la fecha actual o nula)
    fun esFechaValida(datePicker: DatePicker): Boolean {
        val day = datePicker.dayOfMonth
        val month = datePicker.month + 1 // Los meses son base 0
        val year = datePicker.year
        return !(day == 1 && month == 1 && year == Calendar.getInstance().get(Calendar.YEAR)) // Verifica si la fecha no es la predeterminada
    }

}
