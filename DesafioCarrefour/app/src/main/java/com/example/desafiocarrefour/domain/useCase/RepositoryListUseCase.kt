package com.example.desafiocarrefour.domain.useCase

import com.example.desafiocarrefour.data.GithubApiRepository
import com.example.desafiocarrefour.domain.model.RepositoryListItem
import com.example.desafiocarrefour.domain.transformation.RepositoryDataToDomainUseCase

class RepositoryListUseCase {
    private val repository = GithubApiRepository()
    private val transformer = RepositoryDataToDomainUseCase()

    suspend fun getUserRepository(login : String): List<RepositoryListItem> {
        val response = repository.getUserRepositories(login)
        return response.getOrThrow().map { transformer.repositoryListItem(it) }
    }
}