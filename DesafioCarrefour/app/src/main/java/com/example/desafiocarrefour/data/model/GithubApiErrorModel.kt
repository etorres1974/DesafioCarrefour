package com.example.desafiocarrefour.data.model

data class GithubApiErrorModel(
    val documentation_url: String?,
    val errors: List<Error?>?,
    val message: String?
){
    fun isSearchQueryMissing() : Boolean {
        val expectedMessage = this.message == "Validation Failed"
        val error = this.errors?.firstOrNull()
        val expectedError = error?.code == "missing" && error .field == "q" && error.resource == "Search"
        return expectedMessage && expectedError
    }
}