package com.example.submaster

import java.io.Serializable

data class Service (
    val nombre: String,
    val fechaAdquisicion: String,
    val fechaCobro: String,
    val frecuencia: String,
    val tipoDebito: String,
    val tipoServicio: String,
    val tarjeta: String) : Serializable{

    // Sobrescribir el método toString para que solo devuelva el nombre del servicio
    override fun toString(): String {
        return nombre  // Devuelve solo el nombre del servicio
    }
}

