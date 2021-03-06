package com.unprg.alquilafacil.data.usecase

import android.content.Context
import com.unprg.alquilafacil.BuildConfig
import com.unprg.alquilafacil.data.apirestclient.RetrofitClient
import com.unprg.alquilafacil.data.mapper.toData
import com.unprg.alquilafacil.data.response.login.LoginResponse
import com.unprg.alquilafacil.domain.model.Person
import com.unprg.alquilafacil.util.Error
import com.unprg.alquilafacil.util.ResponseType
import com.google.gson.stream.MalformedJsonException

class RegisterUseCase(private val context: Context) {

    suspend fun createAccount(person: Person): ResponseType<LoginResponse, Error> {
        return try {
            val response = RetrofitClient.getAPIService(context).createAccount(person.toData())
            if (response.isSuccessful) {
                val loginResponse = response.body()
                if (loginResponse != null) {
                    ResponseType.Success(loginResponse)
                } else {
                    ResponseType.Error(Error("Ocurrió un error"))
                }
            } else {
                ResponseType.Error(Error(response.errorBody()?.string()))
            }
        } catch (e: java.net.ConnectException) {
            ResponseType.Error(Error("Asegúrate de estar conectado internet"))
        } catch (e: MalformedJsonException) {
            if (BuildConfig.DEBUG) {
                ResponseType.Error(Error("Error JSON"))
            } else {
                ResponseType.Error(Error("Ocurrió un error inesperado"))
            }
        } catch (e: Exception) {
            ResponseType.Error(Error("Ocurrió un error inesperado"))
        }
    }
}