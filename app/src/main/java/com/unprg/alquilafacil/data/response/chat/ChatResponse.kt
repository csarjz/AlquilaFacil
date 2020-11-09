package com.unprg.alquilafacil.data.response.chat

data class ChatResponse (
    var idchat: Int? = null,
    var estado: Int? = null,
    var fecha: String? = null,
    var idpersona: Int? = null,
    var idanuncio: Int? = null,
    var nombrePersona: String? = null
)