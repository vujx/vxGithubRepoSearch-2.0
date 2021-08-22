package com.algebra.githubreposearch20.data.usecase

import com.algebra.githubreposearch20.domain.usecase.network.GetGitHubRepos
import com.algebra.githubreposearch20.domain.usecase.network.GetUser

data class UseCase(
    val getGitHubRepos: GetGitHubRepos,
    val getUser: GetUser
)