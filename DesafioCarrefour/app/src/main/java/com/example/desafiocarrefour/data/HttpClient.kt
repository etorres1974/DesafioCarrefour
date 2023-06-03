package com.example.desafiocarrefour.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HttpClient {

    private val baseUrl = "https://api.github.com/"

    fun retrofit(): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient())
        .baseUrl(baseUrl)
        .build()

    private fun httpClient() =
        OkHttpClient.Builder().addInterceptor(AuthenticationInterceptor()).build()
}