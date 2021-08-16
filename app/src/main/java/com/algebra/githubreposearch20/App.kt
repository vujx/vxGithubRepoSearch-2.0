package com.algebra.githubreposearch20

import android.app.Application
import android.content.res.Resources
import com.algebra.githubreposearch20.data.di.ApiServiceModule.provideApiRepoSearchingService
import com.algebra.githubreposearch20.data.di.ApiServiceModule.provideHttpClient
import com.algebra.githubreposearch20.data.di.ApiServiceModule.provideRetrofit
import com.algebra.githubreposearch20.data.di.DatabaseModule.provideDatabase
import com.algebra.githubreposearch20.data.di.DatabaseModule.provideSearchDao
import com.algebra.githubreposearch20.data.di.SharePreferenceModule.provideSharePreference
import com.algebra.githubreposearch20.data.di.UseCaseModule.provideUseCase
import com.algebra.githubreposearch20.data.repository.DefaultGitHupRepository
import com.algebra.githubreposearch20.data.repository.DefaultSearchRepository
import com.algebra.githubreposearch20.domain.repository.db.SearchRepository
import com.algebra.githubreposearch20.domain.repository.network.GitHubRepository
import com.algebra.githubreposearch20.presentation.ui.viewmodel.GitHubRepoViewModel
import com.algebra.githubreposearch20.presentation.ui.viewmodel.UserViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {

    private val apiModule = module {
        single { provideHttpClient(applicationContext) }
        single { provideRetrofit(get()) }
        single { provideApiRepoSearchingService(get()) }
    }

    private val dbModule = module {
        single { provideDatabase(applicationContext) }
        single { provideSearchDao(get()) }
    }

    private val repoModule = module {
        single { DefaultGitHupRepository(get()) }
        single { DefaultSearchRepository(get()) }
        single { GitHubRepository(get<DefaultGitHupRepository>()) }
        single { SearchRepository(get<DefaultSearchRepository>()) }
    }

    private val useCaseModule = module {
        single { provideUseCase(get(), get()) }
    }

    private val viewModelModule = module {
        viewModel {
            GitHubRepoViewModel(get())
        }
        viewModel {
            UserViewModel(get())
        }
    }

    private val sharePreferencesModule = module {
        single { provideSharePreference(applicationContext) }
    }

    override fun onCreate() {
        super.onCreate()
        getResources = resources
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                listOf(
                    apiModule,
                    dbModule,
                    repoModule,
                    useCaseModule,
                    viewModelModule,
                    sharePreferencesModule
                )
            )
        }
    }

    companion object {
        lateinit var getResources: Resources
        private fun getResource() = getResources
        fun getStringResource(id: Int) = getResource().getString(id)
    }
}
