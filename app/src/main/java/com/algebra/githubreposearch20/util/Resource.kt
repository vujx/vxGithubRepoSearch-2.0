package com.algebra.githubreposearch20.util

sealed class Resource<out T> {
    data class Success<out R>(val value: R) : Resource<R>()
    data class Failure(val throwable: Throwable?) : Resource<Nothing>()
    class Loading<out R>() : Resource<R>()
    class Empty<out R>() : Resource<R>()
}