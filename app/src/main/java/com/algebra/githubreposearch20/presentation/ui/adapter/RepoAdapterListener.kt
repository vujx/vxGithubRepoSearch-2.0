package com.algebra.githubreposearch20.presentation.ui.adapter

import com.algebra.githubreposearch20.domain.model.GitHubRepo

interface RepoAdapterListener {

    fun onItemClick(gitHubRepo: GitHubRepo)
    fun pictureAuthorClick(author: String)
}
