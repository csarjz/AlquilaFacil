package com.unprg.alquilafacil.data.mapper

import com.unprg.alquilafacil.data.request.anuncio.CommentRequest
import com.unprg.alquilafacil.domain.model.Comment
import com.unprg.alquilafacil.data.response.anuncio.CommentResponse

fun CommentResponse.toDomain() = Comment(
    idpersona = idpersona ?: 0,
    idanuncio = idanuncio ?: 0,
    fecha = fecha ?: "",
    nombre = nombre ?: "",
    apellido = apellido ?: "",
    personaImage = personaImage ?: "",
    texto = texto ?: ""
)

fun Comment.toData() = CommentRequest(
    idanuncio = idanuncio,
    texto = texto
)