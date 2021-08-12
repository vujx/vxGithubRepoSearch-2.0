package com.algebra.githubreposearch20.domain.repository.db

import com.algebra.githubreposearch20.data.model.local.SearchRepo

interface SearchRepoDataSource {

    suspend fun getSearchRepos(searchValue: String): List<SearchRepo>
    suspend fun insertSearchRepo(searchRepo: SearchRepo)
    suspend fun deleteAllSearchRepos()
}
