package com.algebra.githubreposearch20.data.repository

import com.algebra.githubreposearch20.data.model.remote.github.GitHubRepoEntity
import com.algebra.githubreposearch20.data.model.remote.user.UserEntity
import com.algebra.githubreposearch20.data.network.GitHubService
import com.algebra.githubreposearch20.domain.repository.network.GitHubRepoNetworkDataSource
import retrofit2.Response

class DefaultGitHupRepository(private val apiService: GitHubService) :
    GitHubRepoNetworkDataSource {

    override suspend fun getGitHubRepos(searchRepo: String): Response<GitHubRepoEntity> = apiService.getSearchRepo(searchRepo)

    override suspend fun getUserInformation(userName: String): Response<UserEntity> = apiService.getUserInfo(userName)
}