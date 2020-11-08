package com.example.myapp.domain.model

data class Comment (
    var idpersona: Int = 0,
    var idanuncio: Int = 0,
    var fecha: String = String(),
    var nombre: String = String(),
    var apellido: String = String(),
    var personaImage: String = String(),
    var texto: String = String()
)