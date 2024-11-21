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
        val opcionesFrecuencia = listOf("Seleciona una Opción","Diario", "Semanal", "Mensual", "Anual")
        val opcionesTipoDebito = listOf("Seleciona una Opción","Crédito", "Débito", "Otro")

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
            val frecuencia = spinnerFrecuencia.selectedItem.toString()
            val tipoDebito = spinnerTipoDebito.selectedItem.toString()
            val tipoServicioId = radioGroupTipoServicio.checkedRadioButtonId
            val tarjeta = editTarjeta.text.toString().trim()

            // Validación de nombre
            if (nombre.isEmpty()) {
                Toast.makeText(this, "Por favor, ingresa un nombre válido.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Obtener las fechas desde los DatePickers
            val fechaAdquisicionCalendar = Calendar.getInstance().apply {
                set(datePickerAdquisicion.year, datePickerAdquisicion.month, datePickerAdquisicion.dayOfMonth)
            }

            val fechaCobroCalendar = Calendar.getInstance().apply {
                set(datePickerCobro.year, datePickerCobro.month, datePickerCobro.dayOfMonth)
            }

            // Validación de fechas
            if (!validarFechas(fechaAdquisicionCalendar,fechaCobroCalendar)) {
                Toast.makeText(this, "Por favor, selecciona fechas válidas.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validación de frecuencia
            if (frecuencia == "Seleciona una Opción") {
                Toast.makeText(this, "Por favor, selecciona una frecuencia válida.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validación de tipo de débito
            if (tipoDebito == "Seleciona una Opción") {
                Toast.makeText(this, "Por favor, selecciona un tipo de débito válido.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validación de RadioGroup (Tipo de servicio)
            if (tipoServicioId == -1) {
                Toast.makeText(this, "Por favor, selecciona un tipo de servicio.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validación de tarjeta
            if (tarjeta.isEmpty() || tarjeta.length != 4) {
                Toast.makeText(this, "Por favor, ingresa los últimos 4 dígitos de la tarjeta.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Crear un nuevo objeto Service
            val nuevoServicio = Service(nombre, fechaAdquisicionCalendar.toString(), fechaCobroCalendar.toString(), frecuencia, tipoDebito, tipoServicioId.toString(), tarjeta)

            // Crear un Intent para enviar el nuevo servicio a MainActivity
            val intent = Intent()
            intent.putExtra("nuevo_servicio", nuevoServicio)
            setResult(RESULT_OK, intent)

            // Si todas las validaciones pasan
            Toast.makeText(this, "Servicio agregado con éxito.", Toast.LENGTH_SHORT).show()
            finish()
        }


        // Configuración del botón "Cancelar"
        botonCancelar.setOnClickListener {
            finish() // Cierra la actividad
        }
    }

    fun validarFechas(fechaAdquisicion: Calendar, fechaCobro: Calendar): Boolean {
        val fechaActual = Calendar.getInstance()

        // Verifica que la fecha de cobro sea posterior a la actual
        if (fechaCobro.before(fechaActual)) {
            Toast.makeText(this, "La fecha de cobro no puede ser anterior a hoy.", Toast.LENGTH_SHORT).show()
            return false
        }

        // Verificar que la fecha de cobro sea posterior a la de adquisición
        if (fechaCobro.before(fechaAdquisicion)) {
            Toast.makeText(this, "La fecha de cobro debe ser posterior a la de adquisición.", Toast.LENGTH_SHORT).show()
            return false
        }

        return true // Las fechas son válidas
    }

}

