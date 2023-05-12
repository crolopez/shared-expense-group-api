package org.crolopez.sharedexpense.user.infrastructure.mappers

import jakarta.inject.Singleton
import org.crolopez.sharedexpense.group.domain.entities.GroupEntity
import org.crolopez.sharedexpense.shared.infrastructure.api.dtos.DataDto
import org.crolopez.sharedexpense.group.infrastructure.api.dtos.GroupDto
import org.crolopez.sharedexpense.shared.infrastructure.mappers.Mapper
import org.crolopez.sharedexpense.user.domain.entities.UserEntity
import org.crolopez.sharedexpense.user.infrastructure.api.dtos.UserDto

@Singleton
class UserApiMapper: Mapper<UserEntity, DataDto<UserDto>> {

    private val dataType: String = "user"

    override fun convert(entity: UserEntity): DataDto<UserDto> {
        return DataDto(
            type = dataType,
            id = entity.userName,
            attributes = UserDto(
                name = entity.name
            )
        )
    }
}