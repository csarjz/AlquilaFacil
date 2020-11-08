package com.unprg.alquilafacil.data.mapper

import com.unprg.alquilafacil.data.request.anuncio.AnuncioResquest
import com.unprg.alquilafacil.data.response.anuncio.AnuncioResponse
import com.unprg.alquilafacil.domain.model.Anuncio
import kotlin.collections.ArrayList

fun AnuncioResponse.toDomain() = Anuncio(
    idanuncio = idanuncio ?: 0,
    estado = estado ?: -100,
    fecha = fecha ?: "",
    precio = precio ?: 0F,
    titulo = titulo ?: "",
    descripcion = descripcion ?: "",
    telefono = telefono ?: "",
    amueblado = amueblado ?: 0,
    habitaciones = habitaciones ?: 0,
    banios = banios ?: 0,
    area = area ?: 0F,
    direccion = direccion ?: "",
    direccionmapa = direccionmapa ?: "",
    latitud = latitud,
    longitud = longitud,
    idcategoria = idcategoria ?: 0,
    idpersona = idpersona ?: 0,
    images = images ?: ArrayList(),
    categoryName = categoryName ?: "",
    nombrePersona = nombrePersona ?: "",
    telefonoPersona = telefonoPersona ?: "",
    person = person?.toDomain(),
    comments = comments?.map { it.toDomain() } ?: ArrayList()
)

fun Anuncio.toData() = AnuncioResquest(
    precio = precio,
    titulo = titulo,
    descripcion = descripcion,
    telefono = telefono,
    amueblado = amueblado,
    habitaciones = habitaciones,
    banios = banios,
    area = area,
    direccion = direccion,
    direccionmapa = direccionmapa,
    latitud = latitud,
    longitud = longitud,
    idcategoria = idcategoria,
    images = images
)
