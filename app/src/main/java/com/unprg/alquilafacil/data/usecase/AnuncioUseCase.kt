package com.unprg.alquilafacil.data.usecase

import android.content.Context
import com.unprg.alquilafacil.BuildConfig
import com.unprg.alquilafacil.data.apirestclient.RetrofitClient
import com.unprg.alquilafacil.data.mapper.toData
import com.unprg.alquilafacil.data.mapper.toDomain
import com.unprg.alquilafacil.data.request.anuncio.AnuncioFilterRequest
import com.unprg.alquilafacil.domain.model.Anuncio
import com.unprg.alquilafacil.util.Error
import com.unprg.alquilafacil.util.ResponseType
import com.google.gson.Gson
import com.google.gson.stream.MalformedJsonException

class AnuncioUseCase(private val context: Context) {

    suspend fun saveAnuncio(anuncio: Anuncio): ResponseType<Anuncio, Error> {
        return try {
            val response = RetrofitClient.getAPIService(context).saveAnuncio(anuncio.toData())
            if (response.isSuccessful) {
                val anuncioResponse = response.body()
                if (anuncioResponse != null) {
                    ResponseType.Success(anuncioResponse.toDomain())
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

    suspend fun getAnuncio(idanuncio: Int): ResponseType<Anuncio, Error> {
        return try {
            val response = RetrofitClient.getAPIService(context).getAnuncio(idanuncio)
            if (response.isSuccessful) {
                val anuncioResponse = response.body()
                if (anuncioResponse != null) {
                    ResponseType.Success(anuncioResponse.toDomain())
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

    suspend fun listAnuncios(filtro: AnuncioFilterRequest): ResponseType<List<Anuncio>, Error> {
        return try {
            val response = RetrofitClient.getAPIService(context).listAnuncios(Gson().toJson(filtro))
            if (response.isSuccessful) {
                val listAnuncios = response.body()
                if (listAnuncios != null) {
                    ResponseType.Success(listAnuncios.map { it.toDomain() })
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

    suspend fun misAnuncios(): ResponseType<List<Anuncio>, Error> {
        return try {
            val response = RetrofitClient.getAPIService(context).misAnuncios()
            if (response.isSuccessful) {
                val listAnuncios = response.body()
                if (listAnuncios != null) {
                    ResponseType.Success(listAnuncios.map { it.toDomain() })
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