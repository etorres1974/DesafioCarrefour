package com.example.desafiocarrefour

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import java.io.InputStreamReader
import java.lang.IllegalArgumentException

class MockGithubService {

    fun successApi() = getApi(successDispatcher())

    fun errorApi() = getApi(errorDispatcher())

    private fun getApi(dispatcher: Dispatcher) =  MockWebServer(dispatcher).apply{
        start(8080)
    }


    private fun MockWebServer(dispatcher: Dispatcher) : MockWebServer = MockWebServer().apply {
        this.dispatcher = dispatcher
    }

    private fun successDispatcher() = object : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return when{
                request.path?.contains("/users/torvalds/repos") == true -> MockResponse().setResponseCode(200).setBody(fileReader(USER_REPOS))
                request.path?.contains("/users/torvalds") == true -> MockResponse().setResponseCode(200).setBody(fileReader(USER_DETAIL))
                request.path?.contains("/users") == true -> MockResponse().setResponseCode(200).setBody(fileReader(USER_LIST))
                request.path?.contains("/search") == true -> MockResponse().setResponseCode(200).setBody(fileReader(QUERY_USER))
                else -> throw IllegalArgumentException("Route ${request.path} is not implemented in mockwebserver")
            }
        }
    }

    private fun errorDispatcher() = object : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return when{
                request.path?.contains("/users") == true -> MockResponse().setResponseCode(404).setBody(fileReader(USER_NOT_FOUND ))
                else -> throw IllegalArgumentException("Route ${request.path} is not implemented in mockwebserver")
            }
        }
    }

    private fun Any.fileReader(filePath: String): String {
        val reader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream(filePath))
        return reader.readText().also { reader.close() }
    }

    companion object{
        private const val QUERY_USER = "queryUser.json"
        private const val USER_DETAIL = "userDetails.json"
        private const val USER_REPOS = "userRepos.json"
        private const val USER_LIST = "userList.json"

        private const val USER_NOT_FOUND = "userNotFound.json"

    }
}