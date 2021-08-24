package com.algebra.githubreposearch20.data.di

import com.algebra.githubreposearch20.data.usecase.UseCase
import com.algebra.githubreposearch20.domain.repository.db.SearchRepository
import com.algebra.githubreposearch20.domain.repository.network.GitHubRepository
import com.algebra.githubreposearch20.domain.usecase.db.GetFilterGitHubRepos
import com.algebra.githubreposearch20.domain.usecase.network.GetGitHubRepos
import com.algebra.githubreposearch20.domain.usecase.network.GetUser

object UseCaseModule {

    fun provideUseCase(repoGitHub: GitHubRepository, searchRepo: SearchRepository) =
        UseCase(
            GetGitHubRepos(repoGitHub, searchRepo),
            GetUser(repoGitHub),
            GetFilterGitHubRepos(searchRepo)
        )
}
