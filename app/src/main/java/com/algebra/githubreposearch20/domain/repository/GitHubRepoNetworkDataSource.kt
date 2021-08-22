package com.algebra.githubreposearch20.domain.repository

import com.algebra.githubreposearch20.data.model.remote.github.GitHubRepoEntity
import com.algebra.githubreposearch20.data.model.remote.user.UserEntity
import retrofit2.Response

interface GitHubRepoNetworkDataSource {

    suspend fun getGitHubRepos(searchRepo: String) : Response<GitHubRepoEntity>
    suspend fun getUserInformation(userName: String) : Response<UserEntity>
}