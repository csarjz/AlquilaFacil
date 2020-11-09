package com.unprg.alquilafacil.data.apirestclient

import com.unprg.alquilafacil.data.request.anuncio.AnuncioResquest
import com.unprg.alquilafacil.data.request.chat.MessageRequest
import com.unprg.alquilafacil.data.request.login.LoginRequest
import com.unprg.alquilafacil.data.request.person.PersonRequest
import com.unprg.alquilafacil.data.response.anuncio.AnuncioResponse
import com.unprg.alquilafacil.data.response.chat.MessageResponse
import com.unprg.alquilafacil.data.response.login.LoginResponse
import okhttp3.MultipartBody
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

    @Multipart
    @POST(APIUrl.ANUNCIO)
    suspend fun saveImageAnuncio(
        @Query("idanuncio") id: Int,
        @Part filePart: MultipartBody.Part,
        @Query("action") action: String = "saveimagen"
    ) : Response<String>

    @GET(APIUrl.ANUNCIO)
    suspend fun listAnuncios(@Query("filtro") filtro: String) : Response<List<AnuncioResponse>>

    @GET(APIUrl.MYS_ANUNCIOS)
    suspend fun misAnuncios() : Response<List<AnuncioResponse>>

    @GET(APIUrl.ANUNCIO)
    suspend fun getAnuncio(@Query("idanuncio") id: Int) : Response<AnuncioResponse>

    @GET(APIUrl.ANUNCIO)
    suspend fun deleteAnuncio(@Query("idanuncio") idanuncio: Int, @Query("force") force: Int = 0, @Query("action") action: String = "delete") : Response<String>

    @GET(APIUrl.CHAT)
    suspend fun listChats(@Query("action") action: String = "chats") : Response<List<AnuncioResponse>>

    @POST(APIUrl.CHAT)
    suspend fun saveMessage(@Body messageRequest: MessageRequest) : Response<Int>

    @GET(APIUrl.CHAT)
    suspend fun listMessages(
        @Query("idchat") idchat: Int,
        @Query("count") count: Int,
        @Query("action") action: String = "mensajes"
    ) : Response<List<MessageResponse>>
}