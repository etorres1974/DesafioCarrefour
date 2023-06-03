package com.example.desafiocarrefour

import com.example.desafiocarrefour.data.GitHubApiException
import com.example.desafiocarrefour.data.GitHubService
import com.example.desafiocarrefour.data.GithubApiRepository
import com.example.desafiocarrefour.data.HttpClient
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttp
import org.junit.Test
import retrofit2.Response

class RepositoryTest {

    private val repository = GithubApiRepository()

    @Test
    fun `get user list by query successfully`() = runBlocking {
        val response = repository.getUserListByQuery("torvalds")
        assert(response.isSuccess)
        val users = response.getOrNull() ?: emptyList()
        assert(users.isNotEmpty())
    }

    @Test
    fun `get user list by query throw query missing when query is empty string`() = runBlocking {
        val response = repository.getUserListByQuery("")
        assert(response.isFailure)
            {"Was expecting request to fail, but got : ${response.getOrNull()}"}
        val exception = response.exceptionOrNull()
        assert(exception is GitHubApiException.QueryMissing)
            {"Was expecting exception to be query missing, but got : $exception"}

    }
}
