package com.algebra.githubreposearch20.data.mapper

import com.algebra.githubreposearch20.data.model.remote.github.GitHubRepoEntity
import com.algebra.githubreposearch20.data.model.remote.github.Item
import com.algebra.githubreposearch20.domain.model.GitHubRepo
import com.algebra.githubreposearch20.domain.util.checkIfNull
import com.algebra.githubreposearch20.domain.util.checkURL
import com.algebra.githubreposearch20.domain.util.checkValue
import com.algebra.githubreposearch20.domain.util.parseDate

class GitHubRepoMapper : EntityMapper<Item, GitHubRepo> {

    override fun mapFromEntity(entity: Item): GitHubRepo = GitHubRepo(
        checkValue(entity.name, 30),
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
        checkURL(entity.owner.avatar_url)
    )

    fun mapListFromEntity(entity: GitHubRepoEntity) =
        entity.items.map { mapFromEntity(it) }
}
