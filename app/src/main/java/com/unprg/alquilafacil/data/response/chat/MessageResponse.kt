package com.unprg.alquilafacil.data.response.chat

import androidx.annotation.Keep

@Keep
data class MessageResponse (
    var idmensaje: Int? = null,
    var fecha: String? = null,
    var mensaje: String? = null,
    var idpersona: Int? = null,
    var idchat: Int? = null
)