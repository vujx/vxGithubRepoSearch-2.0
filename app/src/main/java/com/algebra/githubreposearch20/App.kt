package com.algebra.githubreposearch20

import android.app.Application
import android.content.res.Resources
import com.algebra.githubreposearch20.data.di.ApiServiceModule.provideApiRepoSearchingService
import com.algebra.githubreposearch20.data.di.ApiServiceModule.provideHttpClient
import com.algebra.githubreposearch20.data.di.ApiServiceModule.provideRetrofit
import com.algebra.githubreposearch20.data.di.DatabaseModule.provideDatabase
import com.algebra.githubreposearch20.data.di.DatabaseModule.provideSearchDao
import com.algebra.githubreposearch20.data.repository.DefaultGitHupRepository
import com.algebra.githubreposearch20.data.repository.DefaultSearchRepository
import com.algebra.githubreposearch20.data.usecase.UseCase
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class App : Application() {

    private val appModule = module {
        single { provideHttpClient(applicationContext) }
        single { provideRetrofit(get()) }
        single { provideApiRepoSearchingService(get())}
    }

    private val dbModule = module {
        single { provideDatabase(applicationContext) }
        single { provideSearchDao(get()) }
    }

    private val repoModule = module {
        single { DefaultGitHupRepository(get()) }
        single { DefaultSearchRepository(get()) }
    }

    private val useCaseModule = module {
        factory { UseCase(get(), get()) }
    }

    override fun onCreate() {
        super.onCreate()
        getResources = resources
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, repoModule, dbModule, useCaseModule))
        }
    }

    companion object {
        lateinit var getResources: Resources
        private fun getResource() = getResources
        fun getStringResource(id: Int) = getResource().getString(id)
    }
}
