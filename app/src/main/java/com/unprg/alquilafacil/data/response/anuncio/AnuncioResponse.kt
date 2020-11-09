package com.unprg.alquilafacil.data.response.anuncio

import androidx.annotation.Keep
import com.unprg.alquilafacil.data.response.chat.ChatResponse
import com.unprg.alquilafacil.data.response.persona.PersonResponse

@Keep
data class AnuncioResponse (
    var idanuncio: Int? = null,
    var fecha: String? = null,
    var estado: Int? = null,
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
    var idpersona: Int? = null,
    var images: List<String>? = null,
    var categoryName: String?,
    var nombrePersona: String? = null,
    var telefonoPersona: String? = null,
    var person: PersonResponse? = null,
    var comments: List<CommentResponse>? = null,
    var chat: ChatResponse? = null
)