package com.example.myapp.data.request.anuncio

import androidx.annotation.Keep

@Keep
data class CommentRequest (
    var idanuncio: Int,
    var texto: String
)