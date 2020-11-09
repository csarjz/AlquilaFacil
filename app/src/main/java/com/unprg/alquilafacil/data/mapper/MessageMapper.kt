package com.unprg.alquilafacil.data.mapper

import com.unprg.alquilafacil.data.request.chat.MessageRequest
import com.unprg.alquilafacil.data.response.chat.MessageResponse
import com.unprg.alquilafacil.domain.model.Message

fun MessageResponse.toDomain() = Message(
    idmensaje = idmensaje ?: 0,
    fecha = fecha ?: "",
    mensaje = mensaje ?: "",
    idpersona = idpersona ?: 0,
    idchat = idchat ?: 0
)

fun Message.toData() = MessageRequest(
    idchat = idchat,
    mensaje = mensaje,
    idanuncio = idanuncio
)