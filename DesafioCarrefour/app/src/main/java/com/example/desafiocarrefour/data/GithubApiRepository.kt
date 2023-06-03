package com.example.desafiocarrefour.data

import com.example.desafiocarrefour.data.model.User

class GithubApiRepository {

    private val retrofit = HttpClient().retrofit()
    private val service = retrofit.create(GitHubService::class.java)
    private val errorParser = GitHubApiErrorParser()

    suspend fun getUserListByQuery(query : String) : Result<List<User>> {
        return try {
            val response = service.getUserListByQuery(query)
            if(response.isSuccessful) {
                val users = response.body()?.users?.filterNotNull() ?: emptyList()
                Result.success(users)
            } else {
                val error = errorParser.userSearchResponseToThrowable(response)
                Result.failure(error)
            }
        }catch (e : Exception){
            Result.failure(GitHubApiException.Unknown())
        }
    }
}