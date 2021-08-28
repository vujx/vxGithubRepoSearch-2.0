package com.algebra.githubreposearch20.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.algebra.githubreposearch20.data.model.local.SearchRepo

@Dao
interface SearchDao {

    @Query("SELECT * FROM SearchRepos WHERE :searchValue LIKE search_result ORDER BY LOWER(name)")
    suspend fun getSearchRepos(searchValue: String): List<SearchRepo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchRepo(searchRepo: SearchRepo)

    @Query("DELETE FROM SearchRepos")
    suspend fun removeAllSearchRepos()

    @Query("SELECT * FROM SearchRepos ORDER BY number_of_stars")
    suspend fun getReposByStars(): List<SearchRepo>

    @Query("SELECT * FROM SearchRepos ORDER BY number_of_forks")
    suspend fun getReposByForks(): List<SearchRepo>

    @Query("SELECT * FROM SearchRepos ORDER BY date_of_update")
    suspend fun getReposByDateUpdate(): List<SearchRepo>
}
