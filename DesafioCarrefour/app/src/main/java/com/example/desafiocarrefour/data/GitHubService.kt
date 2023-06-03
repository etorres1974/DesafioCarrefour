package com.example.desafiocarrefour.data

import com.example.desafiocarrefour.data.model.UserSearchResponse
import com.example.desafiocarrefour.domain.model.Repository
import com.example.desafiocarrefour.domain.model.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface GitHubService {

    @GET("users/{user}/repos")
    fun getRepositoriesByUser(@Path("user") user: String?): Call<List<Repository?>?>?

    @GET("users/users/{{user}}")
    fun getUserDetails(@Path("user") user : String?) : Call<User>

    @GET("search/users")
    suspend fun getUserListByQuery(@Query("q") query : String) : Response<UserSearchResponse>

    @GET("users")
    fun getUserList(@Query("since") since : Int) :  Call<List<User>>

}