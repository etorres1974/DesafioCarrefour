package com.example.desafiocarrefour.data

import com.example.desafiocarrefour.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response =
        chain.proceed(
            chain.request().newBuilder()
                .addHeader(authorization_key, auth_key)
                .build()
        )

    companion object{
        const val authorization_key = "Authorization"
        const val auth_key = BuildConfig.API_KEY
    }
}