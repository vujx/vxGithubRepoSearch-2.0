package com.algebra.githubreposearch20.data.db

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.algebra.githubreposearch20.data.model.local.SearchRepo

interface SearchDao {

    @Query("SELECT * FROM SearchRepos WHERE :searchValue LIKE search_result ORDER BY name")
    suspend fun getSearchRepos(searchValue: String): List<SearchRepo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchRepo(searchRepo: SearchRepo)

    @Query("DELETE FROM SearchRepos")
    suspend fun removeAllSearchRepos()
}