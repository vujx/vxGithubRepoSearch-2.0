package com.algebra.githubreposearch20.domain.usecase.db

import com.algebra.githubreposearch20.App
import com.algebra.githubreposearch20.R
import com.algebra.githubreposearch20.data.mapper.SearchMapper
import com.algebra.githubreposearch20.data.model.local.SearchRepo
import com.algebra.githubreposearch20.domain.model.GitHubRepo
import com.algebra.githubreposearch20.domain.repository.db.SearchRepository
import com.algebra.githubreposearch20.domain.usecase.BaseUseCase

class GetFilterGitHubRepos(private val searchRepo: SearchRepository) :
    BaseUseCase<Int, List<GitHubRepo>> {
    private val searchRepoMapper = SearchMapper()
    private val result = mutableListOf<SearchRepo>()

    override suspend fun execute(params: Int, callback: BaseUseCase.Callback<List<GitHubRepo>>) {
        try {
            result.clear()

            when (params) {
                0 -> result.addAll(searchRepo.getSearchReposByStars())
                1 -> result.addAll(searchRepo.getSearchReposByForks())
                else -> result.addAll(searchRepo.getSearchReposByDateUpdate())
            }

            callback.onSuccess(searchRepoMapper.mapListFromEntity(result))
        } catch (e: Exception) {
            callback.onError(App.getStringResource(R.string.unexpected_error))
        }
    }
}
