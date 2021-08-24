package com.algebra.githubreposearch20.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.algebra.githubreposearch20.data.model.local.SearchRepo

@Database(entities = [SearchRepo::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun searchDao(): SearchDao
}
