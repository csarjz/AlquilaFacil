package com.unprg.alquilafacil.domain.model

data class Chat (
    var idchat: Int = 0,
    var fecha: String = String(),
    var mensaje: String = String(),
    var idpersona: Int = 0,
    var idanuncio: Int = 0
)