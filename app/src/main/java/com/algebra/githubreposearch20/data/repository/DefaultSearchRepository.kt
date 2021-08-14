package com.algebra.githubreposearch20.data.repository

import com.algebra.githubreposearch20.data.db.SearchDao
import com.algebra.githubreposearch20.data.model.local.SearchRepo
import com.algebra.githubreposearch20.domain.repository.db.SearchRepoDataSource

class DefaultSearchRepository(private val searchDao: SearchDao) : SearchRepoDataSource {

    override suspend fun getSearchRepos(searchValue: String): List<SearchRepo> = searchDao.getSearchRepos(searchValue)

    override suspend fun insertSearchRepo(searchRepo: SearchRepo) = searchDao.insertSearchRepo(searchRepo)

    override suspend fun deleteAllSearchRepos() = searchDao.removeAllSearchRepos()

    override suspend fun getSearchReposByStars(): List<SearchRepo> = searchDao.getReposByStars()

    override suspend fun getSearchReposByForks(): List<SearchRepo> = searchDao.getReposByForks()

    override suspend fun getSearchReposByDateUpdate(): List<SearchRepo> = searchDao.getReposByDateUpdate()
}
