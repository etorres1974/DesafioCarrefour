package com.example.desafiocarrefour.data

import com.example.desafiocarrefour.data.model.GithubApiRepositoryListItem
import com.example.desafiocarrefour.data.model.GithubApiUserDetails
import com.example.desafiocarrefour.data.model.GithubApiUserSearchResponse
import com.example.desafiocarrefour.domain.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface GitHubService {

    @GET("users/{user}/repos")
    suspend fun getRepositoriesByUser(@Path("user") user: String?): Response<List<GithubApiRepositoryListItem?>?>

    @GET("users/{user}")
    suspend fun getUserDetails(@Path("user") user : String?) : Response<GithubApiUserDetails>

    @GET("search/users")
    suspend fun getUserListByQuery(@Query("q") query : String) : Response<GithubApiUserSearchResponse>

    @GET("users")
    suspend fun getUserList(@Query("since") since : Int) :  Response<List<User>>

}