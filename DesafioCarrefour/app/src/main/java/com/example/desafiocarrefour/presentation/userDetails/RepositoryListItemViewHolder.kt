package com.example.desafiocarrefour.presentation.userDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiocarrefour.databinding.ItemRepoListBinding
import com.example.desafiocarrefour.domain.model.RepositoryListItem

class RepositoryListItemViewHolder(
    private val binding : ItemRepoListBinding
) : RecyclerView.ViewHolder(binding.root){

    fun bind(repo: RepositoryListItem){
        binding.textViewTitle.text = repo.name
    }

    companion object {
        fun inflate(parent : ViewGroup) = RepositoryListItemViewHolder(
            ItemRepoListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}