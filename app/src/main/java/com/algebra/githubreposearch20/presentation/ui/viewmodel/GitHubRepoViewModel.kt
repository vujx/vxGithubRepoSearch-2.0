package com.algebra.githubreposearch20.presentation.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.algebra.githubreposearch20.App
import com.algebra.githubreposearch20.R
import com.algebra.githubreposearch20.data.usecase.UseCase
import com.algebra.githubreposearch20.domain.model.GitHubRepo
import com.algebra.githubreposearch20.domain.usecase.BaseUseCase
import com.algebra.githubreposearch20.util.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GitHubRepoViewModel(private val useCase: UseCase) : ViewModel(), BaseUseCase.Callback<List<GitHubRepo>> {

    private val _repos = MutableLiveData<Resource<List<GitHubRepo>>>()
    val repos: LiveData<Resource<List<GitHubRepo>>> = _repos

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        onError(App.getStringResource(R.string.unexpected_error))
    }

    fun getAllGitHubRepos(searchValue: String) = viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
        _repos.postValue(Resource.Loading())
        useCase.getGitHubRepos.execute(searchValue, this@GitHubRepoViewModel)
    }

    override fun onSuccess(result: List<GitHubRepo>) {
        if (result.isEmpty()) _repos.postValue(Resource.Empty())
        else _repos.postValue(Resource.Success(result))
    }

    override fun onError(errorMessage: String) {
        _repos.postValue(Resource.Failure(errorMessage))
    }
}
