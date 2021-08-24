package com.algebra.githubreposearch20.presentation.ui.helper

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.algebra.githubreposearch20.BR

class SearchResultHelper : BaseObservable() {

    private var searchResult: String = ""

    @Bindable
    fun getSearchResult() = searchResult

    fun setSearchResult(searchResult: String) {
        this.searchResult = searchResult
        notifyPropertyChanged(BR.searchResult)
    }
}
