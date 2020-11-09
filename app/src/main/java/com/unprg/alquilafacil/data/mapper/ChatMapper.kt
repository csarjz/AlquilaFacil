package com.unprg.alquilafacil.data.mapper

import com.unprg.alquilafacil.data.response.chat.ChatResponse
import com.unprg.alquilafacil.domain.model.Chat

fun ChatResponse.toDomain() = Chat(
    idchat = idchat ?: 0,
    estado = estado ?: 0,
    fecha = fecha ?: "",
    idpersona = idpersona ?: 0,
    idanuncio = idanuncio ?: 0,
    nombrePersona = nombrePersona ?: ""
)