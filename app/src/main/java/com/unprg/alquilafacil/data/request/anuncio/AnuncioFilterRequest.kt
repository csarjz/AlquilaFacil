package com.unprg.alquilafacil.data.request.anuncio

data class AnuncioFilterRequest (
    var search: String? = null,
    var idcategoria: Int? = null,
    var latitud: Float? = null,
    var longitud: Float? = null,
    var idpersona: Int? = null
)