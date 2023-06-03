package com.example.desafiocarrefour

import com.example.desafiocarrefour.data.GitHubApiException
import com.example.desafiocarrefour.domain.useCase.RepositoryListUseCase
import com.example.desafiocarrefour.domain.useCase.UserDetailUseCase
import com.example.desafiocarrefour.domain.useCase.UserListUseCase
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Test

//TODO - Change to dependency injection and use the local api to use mockwebserver on "http://127.0.0.1:8080"
class UseCaseTest {

    private val userDetailUseCase = UserDetailUseCase()
    private val userListUseCase = UserListUseCase()
    private val reposiotryListUseCase = RepositoryListUseCase()
    private val mockGithubService = MockGithubService()
    private var mockWebServer : MockWebServer? = null

    @After
    fun teardown(){
        mockWebServer?.apply {
            dispatcher.shutdown()
            shutdown()
        }
    }

    @Test
    fun `get user detail return user successfully`() {
        runBlocking {
            mockWebServer = mockGithubService.successApi()
            val user = userDetailUseCase.getUserDetails("torvalds")
            assert(user.id == 1024025)
                { "Was expecting Linus Torvalds id, but gor ${user.id}"}
        }
    }

    @Test(expected = GitHubApiException.NotFound::class)
    fun `get user detail return user not found`() {
        runBlocking {
            mockWebServer = mockGithubService.errorApi()
            userDetailUseCase.getUserDetails(" ")
        }
    }

    @Test
    fun `get user list  return user successfully`() {
        runBlocking {
            mockWebServer = mockGithubService.successApi()
            val userList = userListUseCase.getUsersList(0)
            assert(userList?.first()?.id == 1)
                { "Was expecting User with id 1, but gor ${userList?.first()}"}
        }
    }

    @Test
    fun `get user repositories successfully`() {
        runBlocking {
            mockWebServer = mockGithubService.successApi()
            val repositoryList = reposiotryListUseCase.getUserRepository("torvalds")
            assert(repositoryList.first().id == 79171906)
                { "Was expecting Repo with id 79171906, but gor ${repositoryList.first()}"}
        }
    }
}