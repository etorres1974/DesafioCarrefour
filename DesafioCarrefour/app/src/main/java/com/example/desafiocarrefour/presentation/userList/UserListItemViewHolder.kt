package com.example.desafiocarrefour.presentation.userList

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiocarrefour.R
import com.example.desafiocarrefour.databinding.ItemUserListBinding
import com.example.desafiocarrefour.domain.model.UserListItem
import com.squareup.picasso.Picasso

class UserListItemViewHolder(
    private val binding : ItemUserListBinding
) : RecyclerView.ViewHolder(binding.root){

    fun bind(user : UserListItem){
        binding.textViewName.text = user.login
        binding.imageViewUser.loadImageUrl(user.avatarUrl)
    }

    fun ImageView.loadImageUrl(url : String){
        Picasso.get()
            .load(url)
            .fit()
            .error(R.drawable.ic_baseline_error_outline_24_material_red)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(this)
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