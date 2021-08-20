package com.algebra.githubreposearch20.data.di

import android.content.Context
import com.algebra.githubreposearch20.data.network.GitHubService
import com.algebra.githubreposearch20.data.network.connection.ConnectivityInterceptorImpl
import com.algebra.githubreposearch20.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiServiceModule {

    fun provideApiRepoSearchingService(retrofit: Retrofit): GitHubService {
        return retrofit.create(GitHubService::class.java)
    }

    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
    }

    fun provideHttpClient(context: Context): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            // .addInterceptor(ChuckInterceptor(context))
            .addInterceptor(logging)
            .addInterceptor(ConnectivityInterceptorImpl(context))
            .build()
    }
}
