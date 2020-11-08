package com.example.myapp.data.apirestclient

import android.content.Context
import com.example.myapp.BuildConfig
import com.example.myapp.util.Token
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val BASE_URL: String = when (BuildConfig.DEBUG) {
        true -> "http://192.168.0.30/unprg/api/v1/"
        false -> "http://192.168.0.30/unprg/api/v1/"
    }

    fun getAPIService(context: Context) : APIService {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        httpClient.addInterceptor(HeaderInterceptor(context))

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(APIService::class.java)
    }

    private class HeaderInterceptor(private val context: Context) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val requestBuilder = chain.request().newBuilder()
            Token.getToken(context).let {
                requestBuilder.addHeader("Authorization", "Bearer $it")
            }
            return chain.proceed(requestBuilder.build())
        }
    }
}