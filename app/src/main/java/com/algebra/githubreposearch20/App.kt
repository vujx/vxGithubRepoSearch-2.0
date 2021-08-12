package com.algebra.githubreposearch20

import android.app.Application
import android.content.res.Resources
import com.algebra.githubreposearch20.data.di.ApiServiceModule.provideApiRepoSearchingService
import com.algebra.githubreposearch20.data.di.ApiServiceModule.provideHttpClient
import com.algebra.githubreposearch20.data.di.ApiServiceModule.provideRetrofit
import com.algebra.githubreposearch20.data.repository.DefaultGitHupRepoRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class App : Application() {

    private val appModule = module {
        single { provideHttpClient(applicationContext) }
        single { provideRetrofit(get()) }
        single { provideApiRepoSearchingService(get())}
    }

    private val repoModule = module {
        single {
            DefaultGitHupRepoRepository(get())
        }
    }

    override fun onCreate() {
        super.onCreate()
        getResources = resources
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, repoModule))
        }
    }

    companion object {
        lateinit var getResources: Resources
        private fun getResource() = getResources
        fun getStringResource(id: Int) = getResource().getString(id)
    }
}
