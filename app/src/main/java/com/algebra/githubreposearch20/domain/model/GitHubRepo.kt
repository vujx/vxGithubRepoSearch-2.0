package com.algebra.githubreposearch20.domain.model

data class GitHubRepo(
    override val repoName: String,
    override val watchers: Int,
    override val forks: Int,
    override val issues: Int,
    override val stars: Int,
    override val update: String,
    override val language: String,
    override val dateCreation: String,
    override val lastModification: String,
    override val descriptionRepo: String,
    override val author: String,
    override val thumbnailImage: String
) : GitHubRepoInfo