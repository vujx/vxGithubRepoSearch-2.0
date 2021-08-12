package com.algebra.githubreposearch20.domain.repository

class GitHubRepoRepository(private val dataSource: GitHubRepoNetworkDataSource) {

    suspend fun getGitHubRepos(searchRepo: String) = dataSource.getGitHubRepos(searchRepo)
    suspend fun getUser(userName: String) = dataSource.getUserInformation(userName)

}