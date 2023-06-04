package com.example.desafiocarrefour.presentation.userList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiocarrefour.databinding.ItemUserListBinding
import com.example.desafiocarrefour.domain.model.UserListItem
import com.example.desafiocarrefour.presentation.loadImageUrl

class UserListItemViewHolder(
    private val binding : ItemUserListBinding
) : RecyclerView.ViewHolder(binding.root){

    fun bind(user: UserListItem, openDetails: (UserListItem) -> Unit){
        binding.textViewName.text = user.login
        binding.imageViewUser.loadImageUrl(user.avatarUrl)
        binding.root.setOnClickListener {
            openDetails(user)
        }
    }

    companion object {
        fun inflate(parent : ViewGroup) = UserListItemViewHolder(
            ItemUserListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}