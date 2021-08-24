package com.algebra.githubreposearch20.presentation.ui.helper

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.algebra.githubreposearch20.BR
import com.algebra.githubreposearch20.presentation.ui.viewmodel.GitHubRepoViewModel

class RefreshHelper(private val viewModel: GitHubRepoViewModel) : BaseObservable() {

    var searchResult = ""

    @Bindable
    private var isLoading = false


    fun isLoading() = isLoading

    private fun setLoading() {
        isLoading = false
        notifyPropertyChanged(BR.loading)
    }

    fun refreshList() {
        viewModel.getAllGitHubRepos(searchResult)
        setLoading()
    }
}