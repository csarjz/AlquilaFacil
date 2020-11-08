package com.example.myapp.data.response.login

import com.example.myapp.data.response.persona.PersonResponse

data class LoginResponse (
    var token: String? = null,
    var persona: PersonResponse? = null
)