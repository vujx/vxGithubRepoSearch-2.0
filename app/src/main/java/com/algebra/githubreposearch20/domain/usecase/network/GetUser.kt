package com.algebra.githubreposearch20.domain.usecase.network

import android.util.Log
import com.algebra.githubreposearch20.App
import com.algebra.githubreposearch20.R
import com.algebra.githubreposearch20.data.mapper.UserMapper
import com.algebra.githubreposearch20.data.network.connection.NoConnectivityException
import com.algebra.githubreposearch20.domain.model.User
import com.algebra.githubreposearch20.domain.repository.network.GitHubRepository
import com.algebra.githubreposearch20.domain.usecase.BaseUseCase
import java.net.UnknownHostException

class GetUser(private val gitHubRepoNetwork: GitHubRepository) : BaseUseCase<String, User> {

    private val userMapper = UserMapper()

    override suspend fun execute(params: String, callback: BaseUseCase.Callback<User>) {
        try {
            val response = gitHubRepoNetwork.getUser(params)
            Log.d("ipsi", response.code().toString())
            when (response.code()) {
                200 ->
                    {
                        Log.d("ispivo", response.body().toString())
                        response.body()?.let { callback.onSuccess(userMapper.mapFromEntity(it)) }
                            ?: callback.onError(App.getStringResource(R.string.unexpected_error))
                    }
                404 -> callback.onError(App.getStringResource(R.string.repo_not_found))
                else -> callback.onError(App.getStringResource(R.string.unexpected_error))
            }
        } catch (e: Exception) {
            Log.d("ispisv", e.toString())
            when (e) {
                is NoConnectivityException -> { callback.onError(App.getStringResource(R.string.check_internet)) }
                is UnknownHostException -> { callback.onError(App.getStringResource(R.string.unknown_host)) }
                else -> { callback.onError(App.getStringResource(R.string.unexpected_error)) }
            }
        }
    }
}
