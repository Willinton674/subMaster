package com.example.submaster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import java.text.SimpleDateFormat

class DetallesDialogFragment : DialogFragment() {

    private var servicio: Service? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflamos el layout del DialogFragment
        val view = inflater.inflate(R.layout.activity_detalles_dialog_fragment, container, false)

        // Vinculamos los TextViews
        val nombreTextView = view.findViewById<TextView>(R.id.nombreDetalle)
        val fechaAdquisicionTextView = view.findViewById<TextView>(R.id.fechaAdquisicionDetalle)
        val fechaCobroTextView = view.findViewById<TextView>(R.id.fechaCobroDetalle)
        val frecuenciaTextView = view.findViewById<TextView>(R.id.frecuenciaDetalle)
        val tipoDebitoTextView = view.findViewById<TextView>(R.id.tipoDebitoDetalle)
        val tarjetaTextView = view.findViewById<TextView>(R.id.tarjetaDetalle)
        val tipoServicioTextView = view.findViewById<TextView>(R.id.tipoServicioDetalle) // Nuevo TextView

        // Establecemos los valores en los TextViews
        servicio?.let {
            nombreTextView.text = it.nombre
            fechaAdquisicionTextView.text = it.fechaAdquisicion
            fechaCobroTextView.text = it.fechaCobro
            frecuenciaTextView.text = it.frecuencia
            tipoDebitoTextView.text = it.tipoDebito
            tarjetaTextView.text = it.tarjeta
            tipoServicioTextView.text = it.tipoServicio // Establecemos el tipoServicio
        }

        // Vinculamos los botones
        val btnEditar = view.findViewById<Button>(R.id.btnEditar)
        val btnEliminar = view.findViewById<Button>(R.id.btnBorrar)

        // Acción para editar
        btnEditar.setOnClickListener {
            // Aquí puedes abrir otro fragmento o actividad para editar los datos
            Toast.makeText(context, "Editar suscripción", Toast.LENGTH_SHORT).show()
        }

        // Acción para eliminar
        btnEliminar.setOnClickListener {
            // Aquí puedes implementar la lógica para eliminar el servicio
            Toast.makeText(context, "Eliminar suscripción", Toast.LENGTH_SHORT).show()
        }

        return view
    }

    // Método para recibir el servicio y mostrar sus detalles
    fun setServicio(servicio: Service) {
        this.servicio = servicio
    }
}