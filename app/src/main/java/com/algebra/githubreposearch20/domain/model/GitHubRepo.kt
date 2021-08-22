package com.algebra.githubreposearch20.domain.model

data class GitHubRepo(
    val repoName: String,
    val watchers: Int,
    val forks: Int,
    val issues: Int,
    val stars: Int,
    val update: String,
    val language: String,
    val dateCreation: String,
    val lastModification: String,
    val descriptionRepo: String,
    val author: String,
    val thumbnailImage: String
)