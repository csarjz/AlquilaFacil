package com.unprg.alquilafacil.data.apirestclient

import com.unprg.alquilafacil.BuildConfig

object APIUrl {
    val BASE_URL: String = when (BuildConfig.DEBUG) {
        true -> "http://192.168.0.30/unprg/api/v1/"
        false -> "http://192.168.0.30/unprg/api/v1/"
    }
    val BASE_IMAGE_URL: String = when (BuildConfig.DEBUG) {
        true -> "http://192.168.0.30/unprg/api/data/images/"
        false -> "http://192.168.0.30/unprg/api/data/images/"
    }
    const val LOGIN = "login/"
    const val LOGOUT = "logout/"
    const val PERSONA = "persona/"
    const val ANUNCIO = "anuncio/"
    const val MYS_ANUNCIOS = "anuncio/misanuncios.php"
    const val CHAT = "chat/"
}