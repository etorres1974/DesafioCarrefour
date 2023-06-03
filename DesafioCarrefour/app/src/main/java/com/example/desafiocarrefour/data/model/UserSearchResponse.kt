package com.example.desafiocarrefour.data.model

import com.google.gson.annotations.SerializedName

data class UserSearchResponse(
    val incomplete_results: Boolean?,
    @SerializedName("items") val users: List<User?>?,
    val total_count: Int?
)