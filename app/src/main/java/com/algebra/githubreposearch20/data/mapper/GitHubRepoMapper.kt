package com.algebra.githubreposearch20.data.mapper

import com.algebra.githubreposearch20.data.model.remote.github.GitHubRepoEntity
import com.algebra.githubreposearch20.data.model.remote.github.Item
import com.algebra.githubreposearch20.domain.model.GitHubRepo

class GitHubRepoMapper : EntityMapper<Item, GitHubRepo> {

    override fun mapFromEntity(entity: Item): GitHubRepo = GitHubRepo(
        entity.name,
        entity.watchers,
        entity.forks,
        entity.open_issues_count,
        entity.stargazers_count,
        entity.updated_at,
        entity.language,
        entity.created_at,
        entity.pushed_at,
        entity.description,
        entity.owner.login,
        entity.owner.avatar_url)

    fun mapListFromEntity(entity: GitHubRepoEntity) = entity.items.map { mapFromEntity(it) }

}