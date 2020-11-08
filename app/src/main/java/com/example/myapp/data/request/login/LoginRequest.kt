package com.example.myapp.data.request.login

import androidx.annotation.Keep

@Keep
data class LoginRequest (
    var email: String,
    var password : String
)