package com.algebra.githubreposearch20.presentation.ui.helper

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.algebra.githubreposearch20.BR

class ProgressBarHelper : BaseObservable() {

    private var isLoading = true

    @Bindable
    fun isLoading() = isLoading

    fun setLoading(loading: Boolean) {
        isLoading = loading
        notifyPropertyChanged(BR.loading)
    }
}
