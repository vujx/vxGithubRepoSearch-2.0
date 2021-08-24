package com.algebra.githubreposearch20.domain.repository.db

import com.algebra.githubreposearch20.data.model.local.SearchRepo

class SearchRepository(private val dataSource: SearchRepoDataSource) {

    suspend fun getSearchRepos(searchValue: String) =
        dataSource.getSearchRepos(searchValue)

    suspend fun insertSearchRepo(searchRepo: SearchRepo) =
        dataSource.insertSearchRepo(searchRepo)

    suspend fun getSearchReposByStars() =
        dataSource.getSearchReposByStars()

    suspend fun getSearchReposByForks() =
        dataSource.getSearchReposByForks()

    suspend fun getSearchReposByDateUpdate() =
        dataSource.getSearchReposByDateUpdate()
}
