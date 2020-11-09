package com.unprg.alquilafacil.data.usecase

import android.content.Context
import com.unprg.alquilafacil.BuildConfig
import com.unprg.alquilafacil.data.apirestclient.RetrofitClient
import com.unprg.alquilafacil.data.mapper.toData
import com.unprg.alquilafacil.util.Error
import com.unprg.alquilafacil.util.ResponseType
import com.google.gson.stream.MalformedJsonException
import com.unprg.alquilafacil.data.mapper.toDomain
import com.unprg.alquilafacil.domain.model.Anuncio
import com.unprg.alquilafacil.domain.model.Message

class ChatUseCase(private val context: Context) {

    suspend fun saveMensaje(message: Message): ResponseType<Int, Error> {
        return try {
            val response = RetrofitClient.getAPIService(context).saveMessage(message.toData())
            if (response.isSuccessful) {
                ResponseType.Success(response.body() ?: 0)
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


    suspend fun listMensajes(idchat: Int, count: Int): ResponseType<List<Message>, Error> {
        return try {
            val response = RetrofitClient.getAPIService(context).listMessages(idchat, count)
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


    suspend fun listChats(): ResponseType<List<Anuncio>, Error> {
        return try {
            val response = RetrofitClient.getAPIService(context).listChats()
            if (response.isSuccessful) {
                val listAnuncio = response.body()
                if (listAnuncio != null) {
                    ResponseType.Success(listAnuncio.map { it.toDomain() })
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