package org.crolopez.sharedexpense.group.infrastructure.repositories

import jakarta.inject.Inject
import org.crolopez.sharedexpense.group.application.repositories.GroupRepository
import org.crolopez.sharedexpense.group.domain.entities.GroupEntity
import org.crolopez.sharedexpense.shared.infrastructure.mappers.Mapper
import org.crolopez.sharedexpense.group.infrastructure.repositories.entities.GroupDbEntity
import org.crolopez.sharedexpense.shared.infrastructure.repositories.relations.GroupUserRelationDbEntity
import org.crolopez.sharedexpense.shared.infrastructure.repositories.relations.GroupUserRelationId
import org.crolopez.sharedexpense.shared.infrastructure.repositories.relations.GroupUserRelationDatabaseRepository
import javax.transaction.Transactional

class GroupRepositoryImpl : GroupRepository {

    @Inject
    lateinit var groupRepository: GroupDatabaseRepository

    @Inject
    lateinit var groupUserRepository: GroupUserRelationDatabaseRepository

    @Inject
    lateinit var groupDbMapper: Mapper<GroupDbEntity, GroupEntity>

    override fun getGroupsFromUser(username: String): List<GroupEntity> {
        val groups: List<GroupDbEntity> = groupRepository.findByUsername(username)
        return groups.map { x -> groupDbMapper.convert(x) }
    }

    @Transactional
    override fun addUserToGroup(groupId: Long, username: String) {
        val newRelation = GroupUserRelationDbEntity(
            id = GroupUserRelationId(
                groupId = groupId,
                username = username
            )
        )
        groupUserRepository.save(newRelation)
    }
}