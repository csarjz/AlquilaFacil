package com.unprg.alquilafacil.data.response.anuncio

import androidx.annotation.Keep

@Keep
data class ChatResponse (
    var idchat: Int? = null,
    var estado: Int? = null,
    var fecha: String? = null,
    var mensaje: String? = null,
    var idpersona: Int? = null,
    var idanuncio: Int? = null
)