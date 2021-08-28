package com.algebra.githubreposearch20.presentation.ui.viewmodel

import androidx.lifecycle.*
import com.algebra.githubreposearch20.App
import com.algebra.githubreposearch20.R
import com.algebra.githubreposearch20.data.usecase.UseCaseNetwork
import com.algebra.githubreposearch20.domain.model.User
import com.algebra.githubreposearch20.domain.usecase.BaseUseCase
import com.algebra.githubreposearch20.util.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(private val useCaseNetwork: UseCaseNetwork) :
    ViewModel(),
    BaseUseCase.Callback<User> {

    val user = MutableLiveData<Resource<User>>()

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        onError(App.getStringResource(R.string.unexpected_error))
    }

    fun getUser(author: String) = viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
        user.postValue(Resource.Loading())
        useCaseNetwork.getUser.execute(author, this@UserViewModel)
    }

    override fun onSuccess(result: User) {
        user.postValue(Resource.Success(result))
    }

    override fun onError(errorMessage: String) {
        user.postValue(Resource.Loading())
        user.postValue(Resource.Failure(errorMessage))
    }
}
