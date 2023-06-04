package com.example.desafiocarrefour

import com.example.desafiocarrefour.presentation.userList.UserListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { UserListViewModel() }
}