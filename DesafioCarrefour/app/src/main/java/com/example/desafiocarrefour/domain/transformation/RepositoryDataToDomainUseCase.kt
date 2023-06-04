package com.example.desafiocarrefour.domain.transformation

import com.example.desafiocarrefour.data.model.GithubApiRepositoryListItem
import com.example.desafiocarrefour.domain.model.RepositoryListItem

class RepositoryDataToDomainUseCase {

    fun repositoryListItem(data : GithubApiRepositoryListItem) : RepositoryListItem{
        return RepositoryListItem(
            id = data.id ?: -1,
            name = data.name ?: "",
            fullName = data.full_name ?: "",
            description = data.description ?: "",
            forksCount = data.forks_count ?: 0,
            watchersCount = data.watchers_count ?: 0,
            starCount = data.stargazers_count ?: 0,
            language = data.language ?: ""
        )
    }
}