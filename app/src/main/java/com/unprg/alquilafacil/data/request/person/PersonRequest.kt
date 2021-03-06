package com.unprg.alquilafacil.data.request.person

import androidx.annotation.Keep

@Keep
data class PersonRequest(
    var idpersona: Int? = null,
    var estado: Int? = null,
    var nombre: String? = null,
    var apellido: String? = null,
    var telefono: String? = null,
    var email: String? = null,
    var password: String? = null,
    var direccion: String? = null,
    var direccionmapa: String? = null,
    var latitud: Float? = null,
    var longitud: Float? = null,
    var idrol: Int? = null
)