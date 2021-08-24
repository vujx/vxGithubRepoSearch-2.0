package com.algebra.githubreposearch20.data.mapper

import com.algebra.githubreposearch20.data.model.remote.user.UserEntity
import com.algebra.githubreposearch20.domain.model.User

class UserMapper : EntityMapper<UserEntity, User> {

    override fun mapFromEntity(entity: UserEntity): User = User(entity.bio, entity.location)
}
