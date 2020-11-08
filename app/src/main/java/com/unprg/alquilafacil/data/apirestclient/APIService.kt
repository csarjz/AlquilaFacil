package com.unprg.alquilafacil.data.apirestclient

import com.unprg.alquilafacil.data.request.anuncio.AnuncioResquest
import com.unprg.alquilafacil.data.request.login.LoginRequest
import com.unprg.alquilafacil.data.request.person.PersonRequest
import com.unprg.alquilafacil.data.response.anuncio.AnuncioResponse
import com.unprg.alquilafacil.data.response.login.LoginResponse
import retrofit2.Response
import retrofit2.http.*

interface APIService {

    @POST(APIUrl.LOGIN)
    suspend fun login(@Body loginRequest: LoginRequest) : Response<LoginResponse>

    @POST(APIUrl.LOGOUT)
    suspend fun logout() : Response<Any>

    @POST(APIUrl.PERSONA)
    suspend fun createAccount(@Body personRequest: PersonRequest) : Response<LoginResponse>

    @POST(APIUrl.ANUNCIO)
    suspend fun saveAnuncio(@Body anuncioResquest: AnuncioResquest) : Response<AnuncioResponse>

    @GET(APIUrl.ANUNCIO)
    suspend fun listAnuncios(@Query("filtro") filtro: String) : Response<List<AnuncioResponse>>

    @GET(APIUrl.MYS_ANUNCIOS)
    suspend fun misAnuncios() : Response<List<AnuncioResponse>>

    @GET(APIUrl.ANUNCIO)
    suspend fun getAnuncio(@Query("idanuncio") id: Int) : Response<AnuncioResponse>
}