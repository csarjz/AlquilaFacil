package com.example.myapp.data.apirestclient

import com.example.myapp.BuildConfig

object APIUrl {
    val BASE_URL: String = when (BuildConfig.DEBUG) {
        true -> "http://192.168.0.30/unprg/api/v1/"
        false -> "http://192.168.0.30/unprg/api/v1/"
    }
    val IMAGE_ANUNCIO_URL: String = when (BuildConfig.DEBUG) {
        true -> "http://192.168.0.30/unprg/api/data/images/anuncio/"
        false -> "http://192.168.0.30/unprg/api/data/images/anuncio/"
    }
    const val LOGIN = "login/"
    const val LOGOUT = "logout/"
    const val PERSONA = "persona/"
    const val ANUNCIO = "anuncio/"
    const val MYS_ANUNCIOS = "anuncio/misanuncios.php"
}