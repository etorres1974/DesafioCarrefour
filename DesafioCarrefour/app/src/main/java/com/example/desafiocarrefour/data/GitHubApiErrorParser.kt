package com.example.desafiocarrefour.data

import com.example.desafiocarrefour.data.model.GithubApiErrorModel
import com.example.desafiocarrefour.data.model.GithubApiUserSearchResponse
import com.google.gson.Gson
import retrofit2.Response

class GitHubApiErrorParser {
    private val gson = Gson()

    fun userSearchResponseToThrowable(response : Response<GithubApiUserSearchResponse>): Throwable {
        return try {
            val error = gson.fromJson(response.errorBody()?.string(), GithubApiErrorModel::class.java)
            when{
                response.code() == 422 && error.isSearchQueryMissing() -> GitHubApiException.QueryMissing()
                else ->GitHubApiException.Unknown()
            }
        }catch (e :Exception){
            GitHubApiException.Unknown()
        }
    }

}

sealed class GitHubApiException : Throwable() {
    class QueryMissing : GitHubApiException()
    class Unknown : GitHubApiException()
}