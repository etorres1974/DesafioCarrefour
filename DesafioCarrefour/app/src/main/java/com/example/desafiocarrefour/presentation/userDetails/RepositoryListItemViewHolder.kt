package com.example.desafiocarrefour.presentation.userDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiocarrefour.R
import com.example.desafiocarrefour.databinding.ItemRepoListBinding
import com.example.desafiocarrefour.domain.model.RepositoryListItem
import com.example.desafiocarrefour.presentation.addTextOrHide

class RepositoryListItemViewHolder(
    private val binding : ItemRepoListBinding
) : RecyclerView.ViewHolder(binding.root){

    fun bind(repo: RepositoryListItem)= with(binding){
        textViewTitle.addTextOrHide(repo.fullName)
        textViewDescription.addTextOrHide(repo.description)
        textViewLanguage.addTextOrHide(repo.language)
        textViewFork.addTextOrHide(repo.forksCount.toString())
        textViewStars.addTextOrHide(repo.starCount.toString())
        textViewWatch.addTextOrHide(repo.watchersCount.toString())
        val color = binding.root.context.getColor(getColorByLanguage(repo.language))
        textViewTitle.compoundDrawables.firstOrNull()?.let{
            it.setTint(color)
        }
        textViewLanguage.setTextColor(color)
    }

    private fun getColorByLanguage(language : String) = when(language){
        "Python" -> R.color.Python
        "Java" -> R.color.Java
        "JavaScript" -> R.color.JavaScript
        "C++" -> R.color.CPlusPlus
        "C" -> R.color.C
        "C#" -> R.color.CSharp
        "Ruby" -> R.color.Ruby
        "Swift" -> R.color.Swift
        "Go" -> R.color.Go
        "Rust" -> R.color.Rust
        "PHP" -> R.color.PHP
        "TypeScript" -> R.color.TypeScript
        "HTML" -> R.color.HTML
        "CSS" -> R.color.CSS
        "Kotlin" -> R.color.Kotlin
        "Scala" -> R.color.Scala
        "Perl" -> R.color.Perl
        "Haskell" -> R.color.Haskell
        "Lua" -> R.color.Lua
        "R" -> R.color.R
        else -> R.color.black
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