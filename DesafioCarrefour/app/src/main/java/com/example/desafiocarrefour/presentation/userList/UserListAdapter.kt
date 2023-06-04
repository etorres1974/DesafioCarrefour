package com.example.desafiocarrefour.presentation.userList

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.desafiocarrefour.domain.model.UserListItem

class UserListAdapter() :ListAdapter<UserListItem, UserListItemViewHolder>(USER_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserListItemViewHolder.inflate(parent)


    override fun onBindViewHolder(holder: UserListItemViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

    companion object {
        private val USER_COMPARATOR = object : DiffUtil.ItemCallback<UserListItem>() {
            override fun areItemsTheSame(oldItem: UserListItem, newItem: UserListItem) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: UserListItem, newItem: UserListItem) = oldItem.login == newItem.login
        }
    }
}