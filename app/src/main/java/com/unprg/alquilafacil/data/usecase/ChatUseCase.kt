package com.unprg.alquilafacil.data.usecase

import android.content.Context
import com.unprg.alquilafacil.BuildConfig
import com.unprg.alquilafacil.data.apirestclient.RetrofitClient
import com.unprg.alquilafacil.data.mapper.toData
import com.unprg.alquilafacil.util.Error
import com.unprg.alquilafacil.util.ResponseType
import com.google.gson.stream.MalformedJsonException
import com.unprg.alquilafacil.data.mapper.toDomain
import com.unprg.alquilafacil.domain.model.Chat

class ChatUseCase(private val context: Context) {

    suspend fun saveChat(chat: Chat): ResponseType<String, Error> {
        return try {
            val response = RetrofitClient.getAPIService(context).saveChat(chat.toData())
            if (response.isSuccessful) {
                ResponseType.Success(response.body() ?: "Listo!")
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


    suspend fun listChats(idanuncio: Int, count: Int): ResponseType<List<Chat>, Error> {
        return try {
            val response = RetrofitClient.getAPIService(context).listChats(idanuncio, count)
            if (response.isSuccessful) {
                val listChat = response.body()
                if (listChat != null) {
                    ResponseType.Success(listChat.map { it.toDomain() })
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