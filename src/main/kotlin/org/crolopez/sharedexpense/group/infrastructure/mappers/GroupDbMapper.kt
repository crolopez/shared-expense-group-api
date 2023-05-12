package org.crolopez.sharedexpense.group.infrastructure.mappers

import jakarta.inject.Singleton
import org.crolopez.sharedexpense.group.domain.entities.GroupEntity
import org.crolopez.sharedexpense.shared.infrastructure.mappers.Mapper
import org.crolopez.sharedexpense.shared.infrastructure.repositories.entities.GroupDbEntity

@Singleton
class GroupDbMapper: Mapper<GroupDbEntity, GroupEntity> {

    override fun convert(entity: GroupDbEntity): GroupEntity {
        return GroupEntity(
            groupTitle = entity.groupTitle,
            groupId = entity.groupId
        )
    }
}