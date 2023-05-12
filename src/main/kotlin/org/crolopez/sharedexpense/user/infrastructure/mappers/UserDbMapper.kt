package org.crolopez.sharedexpense.user.infrastructure.mappers

import jakarta.inject.Singleton
import org.crolopez.sharedexpense.shared.infrastructure.mappers.Mapper
import org.crolopez.sharedexpense.shared.infrastructure.repositories.entities.UserDbEntity
import org.crolopez.sharedexpense.user.domain.entities.UserEntity

@Singleton
class UserDbMapper: Mapper<UserDbEntity, UserEntity> {

    override fun convert(entity: UserDbEntity): UserEntity {
        return UserEntity(
            userName = entity.userName,
            password = entity.password,
            name = entity.name
        )
    }
}