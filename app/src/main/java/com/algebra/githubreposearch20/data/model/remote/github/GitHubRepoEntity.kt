package com.algebra.githubreposearch20.data.model.remote.github

data class GitHubRepoEntity(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)
