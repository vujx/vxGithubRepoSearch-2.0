package com.algebra.githubreposearch20.data.mapper

import com.algebra.githubreposearch20.data.model.local.SearchRepo
import com.algebra.githubreposearch20.data.model.remote.github.Item
import com.algebra.githubreposearch20.domain.model.GitHubRepo
import com.algebra.githubreposearch20.domain.util.checkIfNull
import com.algebra.githubreposearch20.domain.util.checkURL
import com.algebra.githubreposearch20.domain.util.checkValue
import com.algebra.githubreposearch20.domain.util.parseDate

class SearchMapper : EntityMapper<SearchRepo, GitHubRepo> {

    override fun mapFromEntity(entity: SearchRepo): GitHubRepo = GitHubRepo(
        entity.repoName,
        entity.watchers,
        entity.forks,
        entity.issues,
        entity.stars,
        entity.update,
        entity.language,
        entity.dateCreation,
        entity.lastModification,
        entity.descriptionRepo,
        entity.author,
        entity.thumbnailImage
    )

    fun mapListFromEntity(list: List<SearchRepo>) =
        list.map { mapFromEntity(it) }

    fun mapFromItemToSearchRepo(entity: Item, searchValue: String): SearchRepo = SearchRepo(
        0,
        checkValue(entity.name, 12),
        checkIfNull(entity.watchers),
        checkIfNull(entity.forks),
        checkIfNull(entity.open_issues_count),
        checkIfNull(entity.stargazers_count),
        parseDate(entity.updated_at),
        checkValue(entity.language),
        parseDate(entity.created_at),
        parseDate(entity.pushed_at),
        checkValue(entity.description, 250),
        checkValue(entity.owner.login),
        checkURL(entity.owner.avatar_url),
        searchValue
    )
}
