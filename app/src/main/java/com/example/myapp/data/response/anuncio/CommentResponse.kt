package com.example.myapp.data.response.anuncio

import androidx.annotation.Keep

@Keep
data class CommentResponse (
    var idpersona: Int? = null,
    var idanuncio: Int? = null,
    var fecha: String? = null,
    var nombre: String? = null,
    var apellido: String? = null,
    var personaImage: String? = null,
    var texto: String? = null
)