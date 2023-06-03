package com.example.desafiocarrefour.domain.useCase

import com.example.desafiocarrefour.data.GithubApiRepository

class UserListUseCase {
    private val repository = GithubApiRepository()

    suspend fun getUsersByQuery(query : String) = repository.getUserListByQuery(query)

}