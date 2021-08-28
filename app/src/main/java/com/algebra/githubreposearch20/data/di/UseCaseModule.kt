package com.algebra.githubreposearch20.data.di

import com.algebra.githubreposearch20.data.usecase.UseCaseFilter
import com.algebra.githubreposearch20.data.usecase.UseCaseNetwork
import com.algebra.githubreposearch20.domain.repository.db.SearchRepository
import com.algebra.githubreposearch20.domain.repository.network.GitHubRepository
import com.algebra.githubreposearch20.domain.usecase.db.GetFilterGitHubRepos
import com.algebra.githubreposearch20.domain.usecase.network.GetGitHubRepos
import com.algebra.githubreposearch20.domain.usecase.network.GetUser

object UseCaseModule {

    fun provideFilterUseCase(searchRepo: SearchRepository) =
        UseCaseFilter(
            GetFilterGitHubRepos(searchRepo)
        )

    fun provideNetworkUseCase(repoGitHub: GitHubRepository, searchRepo: SearchRepository) =
        UseCaseNetwork(
            GetGitHubRepos(repoGitHub, searchRepo),
            GetUser(repoGitHub)
        )
}
