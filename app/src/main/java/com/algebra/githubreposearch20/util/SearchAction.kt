package com.algebra.githubreposearch20.util

import android.content.Context
import androidx.appcompat.widget.SearchView
import com.algebra.githubreposearch20.App
import com.algebra.githubreposearch20.R
import com.algebra.githubreposearch20.presentation.ui.helper.RefreshHelper
import com.algebra.githubreposearch20.presentation.ui.viewmodel.GitHubRepoViewModel
import kotlinx.coroutines.*

var queryTextChangedJob: Job? = null

fun searchAction(searchView: SearchView, context: Context, viewModel: GitHubRepoViewModel, refreshHelper: RefreshHelper) {
    searchView.setOnQueryTextListener(
        object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { search ->
                    refreshHelper.searchResult = search
                    viewModel.getAllGitHubRepos(search)
                } ?: displayMessage(App.getStringResource(R.string.unexpected_error), context)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                queryTextChangedJob?.cancel()

                queryTextChangedJob = CoroutineScope(Dispatchers.Main).launch {
                    delay(2000)
                    newText?.let { search ->
                        when {
                            search.length >= 3 -> {
                                refreshHelper.searchResult = search
                                viewModel.getAllGitHubRepos(search)
                                searchView.clearFocus()
                            }
                            else -> refreshHelper.searchResult = search
                        }
                    }
                }
                return true
            }
        }
    )
}
