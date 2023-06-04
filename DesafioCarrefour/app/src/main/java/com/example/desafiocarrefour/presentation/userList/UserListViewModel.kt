package com.example.desafiocarrefour.presentation.userList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafiocarrefour.domain.model.UserDetails
import com.example.desafiocarrefour.domain.model.UserListItem
import com.example.desafiocarrefour.domain.useCase.UserDetailUseCase
import com.example.desafiocarrefour.domain.useCase.UserListUseCase
import kotlinx.coroutines.launch

class UserListViewModel : ViewModel() {

    private val userListUseCase = UserListUseCase()
    private val userDetailsUseCase = UserDetailUseCase()


    private val _usersLiveData = MutableLiveData<List<UserListItem>>()
    val usersLiveData : LiveData<List<UserListItem>> = _usersLiveData

    private val _queryUsersLiveData = MutableLiveData<List<UserListItem>>()
    val queryUsersLiveData : LiveData<List<UserListItem>> = _queryUsersLiveData

    private val _usersLivedataLoading = MutableLiveData<Boolean>()
    val usersLivedataLoading : LiveData<Boolean> = _usersLivedataLoading

    private val _userDetailsLiveData = MutableLiveData<UserDetails>()
    val usersDetailsLiveData : LiveData<UserDetails> = _userDetailsLiveData

    private val _usersDetailsLoading = MutableLiveData<Boolean>()
    val usersDetailsLoading : LiveData<Boolean> = _usersDetailsLoading



    fun getUsers(){
        viewModelScope.launch {
            _usersLivedataLoading.postValue(true)
            val users = userListUseCase.getUsersList(0)
            _usersLiveData.postValue(users)
            _usersLivedataLoading.postValue(false)
        }
    }

    fun getUserDetails(login : String){
        viewModelScope.launch {
            _usersDetailsLoading.postValue(true)
            val userDetails = userDetailsUseCase.getUserDetails(login)
            _userDetailsLiveData.postValue(userDetails)
            _usersDetailsLoading.postValue(false)
        }
    }

    fun searchUsersByQuery(query : String){
        viewModelScope.launch {
            val users = userListUseCase.getUsersByQuery(query)
            _queryUsersLiveData.postValue(users)
        }
    }
}