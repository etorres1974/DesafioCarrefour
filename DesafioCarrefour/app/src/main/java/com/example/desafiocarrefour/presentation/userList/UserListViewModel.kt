package com.example.desafiocarrefour.presentation.userList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafiocarrefour.domain.model.UserListItem
import com.example.desafiocarrefour.domain.useCase.UserListUseCase
import kotlinx.coroutines.launch

class UserListViewModel : ViewModel() {

    private val userListUseCase = UserListUseCase()


    private val _usersLiveData = MutableLiveData<List<UserListItem>>()
    val usersLiveData : LiveData<List<UserListItem>> = _usersLiveData

    private val _usersLivedataLoading = MutableLiveData<Boolean>()
    val usersLivedataLoading : LiveData<Boolean> = _usersLivedataLoading

    init {
        getUsers()
    }

    private fun getUsers(){
        viewModelScope.launch {
            _usersLivedataLoading.postValue(true)
            val users = userListUseCase.getUsersList(0)
            _usersLiveData.postValue(users)
            _usersLivedataLoading.postValue(false)
        }
    }
}