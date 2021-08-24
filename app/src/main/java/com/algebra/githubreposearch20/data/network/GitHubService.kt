package com.algebra.githubreposearch20.data.network

import androidx.annotation.Keep
import com.algebra.githubreposearch20.data.model.remote.github.GitHubRepoEntity
import com.algebra.githubreposearch20.data.model.remote.user.UserEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubService {

    @Keep
    @GET("search/repositories")
    suspend fun getSearchRepo(
        @Query("q") q: String,
    ): Response<GitHubRepoEntity>

    @Keep
    @GET("users/{user}")
    suspend fun getUserInfo(@Path("user")user: String): Response<UserEntity>
}
