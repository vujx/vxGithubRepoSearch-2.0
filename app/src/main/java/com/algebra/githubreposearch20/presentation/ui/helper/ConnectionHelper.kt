package com.algebra.githubreposearch20.presentation.ui.helper

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.algebra.githubreposearch20.BR
import com.algebra.githubreposearch20.presentation.MainActivity
import com.algebra.githubreposearch20.util.ConnectionLiveData

class ConnectionHelper(
    private var connectionLiveData: ConnectionLiveData,
    private val activity: MainActivity
) : BaseObservable() {

    private var isLoading = false

    @Bindable
    fun isLoading() = isLoading

    private fun setLoading(loading: Boolean) {
        isLoading = loading
        notifyPropertyChanged(BR.loading)
    }

    fun observeInternetConnection() {
        connectionLiveData = ConnectionLiveData(context = activity.applicationContext)
        connectionLiveData.observe(
            activity,
            { isNetworkAvailable ->
                setLoading(isNetworkAvailable)
            }
        )
    }
}
