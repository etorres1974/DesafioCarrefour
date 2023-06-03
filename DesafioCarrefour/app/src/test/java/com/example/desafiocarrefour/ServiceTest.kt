package com.example.desafiocarrefour

import com.example.desafiocarrefour.data.GitHubService
import com.example.desafiocarrefour.data.HttpClient
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttp
import org.junit.Test
import retrofit2.Response

class ServiceTest {

    private val retrofit = HttpClient().retrofit()
    private val service = retrofit.create(GitHubService::class.java)

    @Test
    fun `get user list by query successfully`() = runBlocking {
        val response = service.getUserListByQuery("torvalds")

        response.assertIs200()

        val users = response.body()?.users ?: emptyList()
        assert(users.isNotEmpty())
            {"Was NOT expecting empty List but got ${users}"}
    }
    private fun <T> Response<T>.assertIs200(){
        assert(this.isSuccessful)
        {"Was expecting 200 but got : ${this.code()} - ${this.errorBody()}"}
    }

    @Test
    fun `get user list by query throw error on empty query`() = runBlocking {
        val response = service.getUserListByQuery("")

        assert(response.code() == 422)

        val users = response.body()?.users ?: emptyList()
        assert(users.isNotEmpty())
        {"Was NOT expecting empty List but got ${users}"}
    }
}