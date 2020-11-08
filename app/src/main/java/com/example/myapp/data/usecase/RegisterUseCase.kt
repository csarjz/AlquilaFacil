package com.example.myapp.data.usecase

import android.content.Context
import com.example.myapp.BuildConfig
import com.example.myapp.data.apirestclient.RetrofitClient
import com.example.myapp.data.mapper.toData
import com.example.myapp.data.response.login.LoginResponse
import com.example.myapp.domain.model.Person
import com.example.myapp.util.Error
import com.example.myapp.util.ResponseType
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