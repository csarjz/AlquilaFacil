package com.example.myapp.data.mapper

import com.example.myapp.data.request.anuncio.CommentRequest
import com.example.myapp.domain.model.Comment
import com.example.myapp.data.response.anuncio.CommentResponse

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