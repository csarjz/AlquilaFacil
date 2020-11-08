package com.example.myapp.data.mapper

import com.example.myapp.data.request.person.PersonRequest
import com.example.myapp.data.response.persona.PersonResponse
import com.example.myapp.domain.model.Person

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
