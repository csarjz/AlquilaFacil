package com.example.myapp.data.apirestclient

import com.example.myapp.data.request.anuncio.AnuncioFilterRequest
import com.example.myapp.data.request.anuncio.AnuncioResquest
import com.example.myapp.data.request.login.LoginRequest
import com.example.myapp.data.request.person.PersonRequest
import com.example.myapp.data.response.anuncio.AnuncioResponse
import com.example.myapp.data.response.login.LoginResponse
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
    suspend fun getAnuncio(@Field("idanuncio") id: Int) : Response<AnuncioResponse>
}