package com.example.desafiocarrefour.data

import com.example.desafiocarrefour.data.model.GithubApiErrorModel
import com.google.gson.Gson
import retrofit2.Response

class GitHubApiErrorParser {
    private val gson = Gson()

    fun <T, J> parse(response : Response<T>, result : () -> Result<J>) : Result<J>{
        return try {
            if(response.isSuccessful) {
                result()
            } else {
                val error = responseToThrowable(response)
                Result.failure(error)
            }
        }catch (e : Exception){
            Result.failure(GitHubApiException.Unknown())
        }
    }

    private fun <T> responseToThrowable(response : Response<T>): Throwable {
        return try {
            val error = gson.fromJson(response.errorBody()?.string(), GithubApiErrorModel::class.java)
            when{
                response.code() == 422 && error.isSearchQueryMissing() -> GitHubApiException.QueryMissing()
                response.code() == 404 && error.isNotFound() -> GitHubApiException.NotFound()
                else ->GitHubApiException.Unknown()
            }
        }catch (e :Exception){
            GitHubApiException.Unknown()
        }
    }

}

sealed class GitHubApiException : Throwable() {
    class QueryMissing : GitHubApiException()
    class NotFound :  GitHubApiException()
    class Unknown : GitHubApiException()
}