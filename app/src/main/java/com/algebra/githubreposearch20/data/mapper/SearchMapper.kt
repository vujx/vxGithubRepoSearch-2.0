package com.algebra.githubreposearch20.data.mapper

import com.algebra.githubreposearch20.data.model.local.SearchRepo
import com.algebra.githubreposearch20.domain.model.GitHubRepo

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

    fun mapListFromEntity(list: List<SearchRepo>) = list.map { mapFromEntity(it) }
}
