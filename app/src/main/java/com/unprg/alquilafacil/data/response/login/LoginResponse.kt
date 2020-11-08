package com.unprg.alquilafacil.data.response.login

import com.unprg.alquilafacil.data.response.persona.PersonResponse

data class LoginResponse (
    var token: String? = null,
    var persona: PersonResponse? = null
)