package com.algebra.githubreposearch20.data.di

import android.content.Context
import android.content.SharedPreferences
import com.algebra.githubreposearch20.util.Constants

object SharePreferenceModule {

    fun provideSharePreference(context: Context): SharedPreferences {
        return context.getSharedPreferences(Constants.REPOS_PREFERENCE, Context.MODE_PRIVATE)
    }
}
