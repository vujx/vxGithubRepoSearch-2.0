package com.algebra.githubreposearch20.domain.usecase.network

import com.algebra.githubreposearch20.App
import com.algebra.githubreposearch20.R
import com.algebra.githubreposearch20.data.mapper.GitHubRepoMapper
import com.algebra.githubreposearch20.data.network.connection.NoConnectivityException
import com.algebra.githubreposearch20.domain.model.GitHubRepo
import com.algebra.githubreposearch20.domain.repository.GitHubRepoRepository
import com.algebra.githubreposearch20.domain.usecase.BaseUseCase
import java.net.UnknownHostException

class GetGitHubRepos(private val gitHubRepoNetwork: GitHubRepoRepository) : BaseUseCase<String, List<GitHubRepo>> {

    private val gitHubRepoMapper = GitHubRepoMapper()

    override suspend fun execute(params: String, callback: BaseUseCase.Callback<List<GitHubRepo>>) {
        try{
            val response = gitHubRepoNetwork.getGitHubRepos(params)
            when(response.code()) {
                200 -> response.body()?.let { callback.onSuccess(gitHubRepoMapper.mapListFromEntity(it)) } ?:
                callback.onError(App.getStringResource(R.string.unexpected_error))
                404 -> callback.onError(App.getStringResource(R.string.repo_not_found))
                else -> callback.onError(App.getStringResource(R.string.unexpected_error))
            }
        }catch (e: Exception){
            when(e){
                is NoConnectivityException -> { callback.onError(App.getStringResource(R.string.check_internet)) }
                is UnknownHostException -> { callback.onError(App.getStringResource(R.string.unexpected_error)) }
                else -> { callback.onError(App.getStringResource(R.string.unexpected_error)) }
            }
        }
    }
}