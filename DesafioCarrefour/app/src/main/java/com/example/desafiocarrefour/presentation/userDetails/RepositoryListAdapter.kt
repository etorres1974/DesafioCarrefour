package com.example.desafiocarrefour.presentation.userDetails

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.desafiocarrefour.domain.model.RepositoryListItem
import com.example.desafiocarrefour.domain.model.UserListItem

class RepositoryListAdapter() : ListAdapter<RepositoryListItem, RepositoryListItemViewHolder>(REPOSITORY_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RepositoryListItemViewHolder.inflate(parent)


    override fun onBindViewHolder(holder: RepositoryListItemViewHolder, position: Int) {
        val repo = getItem(position)
        holder.bind(repo)
    }

    companion object {
        private val REPOSITORY_COMPARATOR = object : DiffUtil.ItemCallback<RepositoryListItem>() {
            override fun areItemsTheSame(oldItem: RepositoryListItem, newItem: RepositoryListItem) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: RepositoryListItem, newItem: RepositoryListItem) = oldItem.name == newItem.name
        }
    }
}