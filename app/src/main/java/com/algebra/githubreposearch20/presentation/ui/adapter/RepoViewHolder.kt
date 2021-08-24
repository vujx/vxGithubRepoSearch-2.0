package com.algebra.githubreposearch20.presentation.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.algebra.githubreposearch20.App
import com.algebra.githubreposearch20.R
import com.algebra.githubreposearch20.databinding.ItemRepoBinding
import com.algebra.githubreposearch20.domain.model.GitHubRepo
import com.algebra.githubreposearch20.util.displayPic

class RepoViewHolder(
    private val itemRepo: ItemRepoBinding,
    private val listOfRepo: List<GitHubRepo>,
    private val listener: RepoAdapterListener
) : RecyclerView.ViewHolder(itemRepo.root) {

    init {
        itemRepo.imAuthor.setOnClickListener {
            listener.pictureAuthorClick(listOfRepo[layoutPosition].author)
        }
        itemView.setOnClickListener {
            listener.onItemClick(listOfRepo[layoutPosition])
        }
    }

    fun bind(repo: GitHubRepo) {
        itemRepo.tvRepoName.text = repo.repoName
        itemRepo.tvAuthorName.text = repo.author
        displayPic(itemView, repo.thumbnailImage, itemRepo.imAuthor)
        itemRepo.tvForks.text = String.format(App.getStringResource(R.string.displayForks), repo.forks)
        itemRepo.tvIssues.text = String.format(App.getStringResource(R.string.displayIssues), repo.issues)
        itemRepo.tvWatchers.text = String.format(App.getStringResource(R.string.displayWatchers), repo.watchers)
        itemRepo.tvStars.text = String.format(App.getStringResource(R.string.displayStars), repo.stars)
        itemRepo.tvUpdate.text = String.format(App.getStringResource(R.string.displayUpdate), repo.update)
    }
}
