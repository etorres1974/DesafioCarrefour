package com.example.desafiocarrefour.presentation.userList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafiocarrefour.domain.model.RepositoryListItem
import com.example.desafiocarrefour.domain.model.UserDetails
import com.example.desafiocarrefour.domain.model.UserListItem
import com.example.desafiocarrefour.domain.useCase.RepositoryListUseCase
import com.example.desafiocarrefour.domain.useCase.UserDetailUseCase
import com.example.desafiocarrefour.domain.useCase.UserListUseCase
import kotlinx.coroutines.launch

class UserListViewModel : ViewModel() {

    private val userListUseCase = UserListUseCase()
    private val userDetailsUseCase = UserDetailUseCase()
    private val repositoryListUseCase = RepositoryListUseCase()


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

    private val _repositoryListLivedata = MutableLiveData<List<RepositoryListItem>>()
    val repositoryListLivedata : LiveData<List<RepositoryListItem>> = _repositoryListLivedata

    private val _repositoryListLoading = MutableLiveData<Boolean>()
    val repositoryListLoading : LiveData<Boolean> = _repositoryListLoading



    fun getUsers(){
        viewModelScope.launch {
            _usersLivedataLoading.postValue(true)
            val users = userListUseCase.getUsersList(0)
            _usersLiveData.postValue(users)
            _usersLivedataLoading.postValue(false)
        }
    }

    fun getUserDetails(login : String){
        getRepositoryListByLogin(login)
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

    private fun getRepositoryListByLogin(login : String){
        viewModelScope.launch {
            _repositoryListLivedata.postValue(emptyList())
            _repositoryListLoading.postValue(true)
            val repositoryList = repositoryListUseCase.getUserRepository(login)
            _repositoryListLivedata.postValue(repositoryList)
            _repositoryListLoading.postValue(false)
        }
    }
}