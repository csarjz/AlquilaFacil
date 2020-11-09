package com.unprg.alquilafacil.data.mapper

import com.unprg.alquilafacil.data.request.anuncio.ChatRequest
import com.unprg.alquilafacil.data.response.anuncio.ChatResponse
import com.unprg.alquilafacil.domain.model.Chat

fun ChatResponse.toDomain() = Chat(
    idchat = idchat ?: 0,
    fecha = fecha ?: "",
    mensaje = mensaje ?: "",
    idpersona = idpersona ?: 0,
    idanuncio = idanuncio ?: 0
)

fun Chat.toData() = ChatRequest(
    idanuncio = idanuncio,
    mensaje = mensaje
)