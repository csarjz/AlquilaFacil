package com.unprg.alquilafacil.data.request.anuncio

import androidx.annotation.Keep

@Keep
data class ChatRequest (
    var idanuncio: Int,
    var mensaje: String
)