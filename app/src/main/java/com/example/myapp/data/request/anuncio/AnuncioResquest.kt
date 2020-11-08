package com.example.myapp.data.request.anuncio

import androidx.annotation.Keep
import java.util.Date

@Keep
data class AnuncioResquest (
    var precio: Float? = null,
    var titulo: String? = null,
    var descripcion: String? = null,
    var telefono: String? = null,
    var amueblado: Int? = null,
    var habitaciones: Int? = null,
    var banios: Int? = null,
    var area: Float? = null,
    var direccion: String? = null,
    var direccionmapa: String? = null,
    var latitud: Float? = null,
    var longitud: Float? = null,
    var idcategoria: Int? = null,
    var images: List<String>? = null
)