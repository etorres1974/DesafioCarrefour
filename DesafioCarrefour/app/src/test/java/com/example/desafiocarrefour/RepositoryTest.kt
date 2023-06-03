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

    @Test
    fun `get user list`() = runBlocking {
        val response = repository.getUserList(0)
        assert(response.isSuccess)
        val users = response.getOrNull() ?: emptyList()
        assert(users.isNotEmpty())
            {"Was expecting user list, but got empty"}
    }

    @Test
    fun `get user details `() = runBlocking {
        val response = repository.getUserDetails("torvalds")
        assert(response.isSuccess)
        val userDetail = response.getOrNull()
        assert(userDetail != null)
            {"Was expecting user details, but got null"}
    }

    @Test
    fun `get user details throws Not Found error for invalid user`() = runBlocking {
        val response = repository.getUserDetails(" ")
        assert(response.isFailure)
        val exception = response.exceptionOrNull()
        assert(exception is GitHubApiException.NotFound)
            {"Was expecting NotFound exception, but got ${exception}"}
    }
}
