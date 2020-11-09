package com.unprg.alquilafacil.domain.model

import android.os.Parcelable
import com.unprg.alquilafacil.data.response.chat.ChatResponse
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Anuncio (
    var idanuncio: Int = 0,
    var fecha: String = String(),
    var estado: Int = -1,
    var precio: Float,
    var titulo: String,
    var descripcion: String,
    var telefono: String,
    var amueblado: Int,
    var habitaciones: Int,
    var banios: Int,
    var area: Float,
    var direccion: String,
    var direccionmapa: String = String(),
    var latitud: Float? = null,
    var longitud: Float? = null,
    var idcategoria: Int,
    var idpersona: Int = 0,
    var images: List<String> = ArrayList(),
    var categoryName: String = String(),
    var nombrePersona: String = String(),
    var telefonoPersona: String = String(),
    var person: Person? = null,
    var comments: List<Comment> = ArrayList(),
    var chat: Chat = Chat()
): Parcelable