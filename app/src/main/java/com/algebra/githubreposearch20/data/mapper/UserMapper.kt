package com.algebra.githubreposearch20.data.mapper

import com.algebra.githubreposearch20.data.model.remote.user.UserEntity
import com.algebra.githubreposearch20.domain.model.User
import com.algebra.githubreposearch20.domain.util.checkIfNull
import com.algebra.githubreposearch20.domain.util.checkValue

class UserMapper : EntityMapper<UserEntity, User> {

    override fun mapFromEntity(entity: UserEntity): User =
        User(checkValue(entity.bio, 250), checkValue(entity.location))
}
