package com.example.desafiocarrefour.data.model

import com.google.gson.annotations.SerializedName

data class GithubApiUserSearchResponse(
    val incomplete_results: Boolean?,
    @SerializedName("items") val users: List<GithubApiUserSearchItem?>?,
    val total_count: Int?
)