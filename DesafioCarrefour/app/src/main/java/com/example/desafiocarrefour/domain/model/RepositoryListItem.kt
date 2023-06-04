package com.example.desafiocarrefour.domain.model

data class RepositoryListItem(
    val id: Int,
    val name: String,
    val fullName: String?,
    val description: String,
    val forksCount: Int,
    val watchersCount: Int,
    val starCount: Int,
    val language: String
)
