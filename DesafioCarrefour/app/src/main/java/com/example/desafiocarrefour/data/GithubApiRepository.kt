package com.example.desafiocarrefour.data

import com.example.desafiocarrefour.data.model.GithubApiRepositoryListItem
import com.example.desafiocarrefour.data.model.GithubApiUserDetails
import com.example.desafiocarrefour.data.model.GithubApiUserSearchItem

class GithubApiRepository {

    private val retrofit = HttpClient().retrofit()
    private val service = retrofit.create(GitHubService::class.java)
    private val errorParser = GitHubApiErrorParser()

    suspend fun getUserListByQuery(query: String): Result<List<GithubApiUserSearchItem>> {
        val response = service.getUserListByQuery(query)
        return errorParser.parse(response) {
            val userList = response.body()?.users?.filterNotNull() ?: emptyList()
            Result.success(userList)
        }
    }

    suspend fun getUserList(since: Int): Result<List<GithubApiUserSearchItem>> {
        val response = service.getUserList(since)
        return errorParser.parse(response) {
            val userList = response.body()?.filterNotNull() ?: emptyList()
            Result.success(userList)
        }
    }

    suspend fun getUserDetails(user: String): Result<GithubApiUserDetails> {
        val response = service.getUserDetails(user)
        return errorParser.parse(response) {
            val details = response.body()!!
            Result.success(details)
        }
    }

    suspend fun getUserRepositories(user: String): Result<List<GithubApiRepositoryListItem>> {
        val response = service.getRepositoriesByUser(user)
        return errorParser.parse(response) {
            val repositoryList = response.body()?.filterNotNull() ?: emptyList()
            Result.success(repositoryList)
        }
    }
}