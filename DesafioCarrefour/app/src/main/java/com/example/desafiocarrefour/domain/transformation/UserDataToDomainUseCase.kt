package com.example.desafiocarrefour.domain.transformation

import com.example.desafiocarrefour.data.model.GithubApiUserDetails
import com.example.desafiocarrefour.data.model.GithubApiUserSearchItem
import com.example.desafiocarrefour.domain.model.UserDetails
import com.example.desafiocarrefour.domain.model.UserListItem

class UserDataToDomainUseCase {

    fun userListItem(data: GithubApiUserSearchItem): UserListItem {
        return UserListItem(
            id = data.id ?: -1,
            login = data.login ?: "",
            avatarUrl = data.avatar_url ?: "",
        )
    }

    fun userDetail(data : GithubApiUserDetails) : UserDetails{
        return UserDetails(
            id = data.id ?: -1,
        )
    }
}