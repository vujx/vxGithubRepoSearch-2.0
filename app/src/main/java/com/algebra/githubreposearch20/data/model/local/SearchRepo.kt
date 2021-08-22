package com.algebra.githubreposearch20.data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.algebra.githubreposearch20.domain.model.GitHubRepoInfo

@Entity(tableName = "SearchRepos")
class SearchRepo(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "name")
    override val repoName: String,

    @ColumnInfo(name = "number_of_watchers")
    override val watchers: Int,

    @ColumnInfo(name = "number_of_forks")
    override val forks: Int,

    @ColumnInfo(name = "number_of_issues")
    override val issues: Int,

    @ColumnInfo(name = "number_of_stars")
    override val stars: Int,

    @ColumnInfo(name = "date_of_update")
    override val update: String,

    @ColumnInfo(name = "language")
    override val language: String,

    @ColumnInfo(name = "date_of_creation")
    override val dateCreation: String,

    @ColumnInfo(name = "date_of_last_modification")
    override val lastModification: String,

    @ColumnInfo(name = "repo_description")
    override val descriptionRepo: String,

    @ColumnInfo(name = "author")
    override val author: String,

    @ColumnInfo(name = "image_url")
    override val thumbnailImage: String,

    @ColumnInfo(name = "search_result")
    val searchResult: String

) : GitHubRepoInfo