package com.example.desafiocarrefour.domain.useCase

import com.example.desafiocarrefour.data.GithubApiRepository
import com.example.desafiocarrefour.domain.model.UserDetails
import com.example.desafiocarrefour.domain.transformation.UserDataToDomainUseCase

class UserDetailUseCase {
    private val repository = GithubApiRepository()
    private val userTransformer = UserDataToDomainUseCase()

    @kotlin.jvm.Throws
    suspend fun getUserDetails(login : String): UserDetails {
        val response = repository.getUserDetails(login)
        return userTransformer.userDetail(response.getOrThrow())
    }
}