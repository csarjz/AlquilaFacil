package com.unprg.alquilafacil.util

sealed class ResponseType<T, U> {
    data class Success <T, U>(val value: T) : ResponseType<T, U>()
    data class Error <T, U>(val value: U) : ResponseType<T, U>()
}