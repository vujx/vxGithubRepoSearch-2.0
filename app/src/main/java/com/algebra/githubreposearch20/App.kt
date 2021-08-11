package com.algebra.githubreposearch20

import android.app.Application
import android.content.res.Resources

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        getResources = resources
    }

    companion object {
        lateinit var getResources: Resources
        fun getResource() = getResources
        fun getStringResource(id: Int) = getResource().getString(id)
    }
}
