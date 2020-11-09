package com.unprg.alquilafacil.data.request.chat

import androidx.annotation.Keep

@Keep
data class MessageRequest (
    var idchat: Int,
    var idanuncio: Int,
    var mensaje: String
)