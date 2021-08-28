package com.algebra.githubreposearch20.data.di

import android.content.Context
import androidx.room.Room
import com.algebra.githubreposearch20.data.db.AppDatabase

object DatabaseModule {

    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "GitHubRepo.db")
            .fallbackToDestructiveMigration().build()
    }

    fun provideSearchDao(database: AppDatabase) =
        database.searchDao()
}
