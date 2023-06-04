package com.example.desafiocarrefour.domain.useCase

import com.example.desafiocarrefour.data.GithubApiRepository
import com.example.desafiocarrefour.domain.model.UserListItem
import com.example.desafiocarrefour.domain.transformation.UserDataToDomainUseCase

class UserListUseCase {
    private val repository = GithubApiRepository()
    private val userTransformer = UserDataToDomainUseCase()

    suspend fun getUsersByQuery(query: String): List<UserListItem> {
        val response = repository.getUserListByQuery(query)
        val dataUserList = response.getOrThrow()
        return dataUserList.map { userTransformer.userListItem(it) }
    }

    suspend fun getUsersList(since : Int) : List<UserListItem> {
        val response = repository.getUserList(since)
        val dataUserList = response.getOrThrow()
        return dataUserList.map { userTransformer.userListItem(it) }
    }
}