package com.unprg.alquilafacil.data.mapper

import com.unprg.alquilafacil.data.request.person.PersonRequest
import com.unprg.alquilafacil.data.response.persona.PersonResponse
import com.unprg.alquilafacil.domain.model.Person

fun PersonResponse.toDomain() = Person(
    idpersona = idpersona ?: 0,
    estado = estado ?: 0,
    nombre = nombre ?: "",
    apellido = apellido ?: "",
    telefono = telefono ?: "",
    email = email ?: "",
    password = password ?: "",
    direccion = direccion ?: "",
    direccionmapa = direccionmapa ?: "",
    latitud = latitud ?: 0F,
    longitud = longitud ?: 0F,
    idrol = idrol ?: 0
)

fun Person.toData() = PersonRequest(
    idpersona = idpersona,
    nombre = nombre,
    apellido = apellido,
    telefono = telefono,
    email = email,
    password = password,
    direccion = direccion,
    direccionmapa = direccionmapa,
    latitud = latitud,
    longitud = longitud,
    idrol = idrol
)
