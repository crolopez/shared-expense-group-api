package org.crolopez.sharedexpense.group.infrastructure.repositories

import jakarta.inject.Inject
import org.crolopez.sharedexpense.group.application.repositories.GroupRepository
import org.crolopez.sharedexpense.group.domain.entities.GroupEntity
import org.crolopez.sharedexpense.shared.infrastructure.mappers.Mapper
import org.crolopez.sharedexpense.shared.infrastructure.repositories.entities.GroupDbEntity

class GroupRepositoryImpl : GroupRepository {

    @Inject
    lateinit var groupRepository: GroupDatabaseRepository

    @Inject
    lateinit var groupDbMapper: Mapper<GroupDbEntity, GroupEntity>

    override fun getGroupsFromUser(userId: String): List<GroupEntity> {
        return groupRepository.findAll().map { x -> groupDbMapper.convert(x) }
    }
}