package org.crolopez.sharedexpense.group.infrastructure.mappers

import jakarta.inject.Singleton
import org.crolopez.sharedexpense.group.domain.entities.GroupEntity
import org.crolopez.sharedexpense.shared.infrastructure.api.dtos.DataDto
import org.crolopez.sharedexpense.group.infrastructure.api.dtos.GroupDto
import org.crolopez.sharedexpense.shared.infrastructure.mappers.Mapper

@Singleton
class GroupApiOutputMapper: Mapper<GroupEntity, DataDto<GroupDto>> {

    private val dataType: String = "group"

    override fun convert(entity: GroupEntity): DataDto<GroupDto> {
        return DataDto(
            type = dataType,
            id = entity.groupId.toString(),
            attributes = GroupDto(
                groupTitle = entity.groupTitle
            )
        )
    }
}