package com.example.desafiocarrefour

import com.example.desafiocarrefour.data.GitHubService
import com.example.desafiocarrefour.data.HttpClient
import kotlinx.coroutines.runBlocking
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


    @Test
    fun `get user list by query throw error on empty query`() = runBlocking {
        val response = service.getUserListByQuery("")

        assert(response.code() == 422)
    }

    @Test
    fun `get user list from start`()= runBlocking {
        val response = service.getUserList(0)

        response.assertIs200()
        val list = response.body()
        assert(list?.isNotEmpty() == true)
            {"Was not Expecting EMPTY list, but got : $list"}
    }

    @Test
    fun `get user details return user`()= runBlocking {
        val response = service.getUserDetails("torvalds")

        response.assertIs200()
        val user = response.body()
        assert(user?.name == "Linus Torvalds")
            {"Was Linus Torvalds but got : ${user?.name}"}
    }

    @Test
    fun `get user details on empty query returns 404 not found`()= runBlocking {
        val response = service.getUserDetails("")
        assert(response.code() == 404)
    }

    @Test
    fun `get user repository`() = runBlocking {
        val response = service.getRepositoriesByUser("torvalds")
        response.assertIs200()

        val repositories = response.body()

        assert(repositories?.isNotEmpty() == true)
            {"Was NOT expecting Empty repositories, but got : ${repositories}"}
    }

    @Test
    fun `get user repository without user name return 404`() = runBlocking {
        val response = service.getRepositoriesByUser(" ")
        assert(response.code() == 404)
    }

    private fun <T> Response<T>.assertIs200(){
        assert(this.isSuccessful)
            {"Was expecting 200 but got : ${this.code()} - ${this.errorBody()}"}
    }
}