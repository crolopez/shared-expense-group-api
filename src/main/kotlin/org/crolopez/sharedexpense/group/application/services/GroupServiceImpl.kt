package org.crolopez.sharedexpense.group.application.services

import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.crolopez.sharedexpense.group.application.repositories.GroupRepository
import org.crolopez.sharedexpense.group.domain.entities.GroupEntity

@Singleton
class GroupServiceImpl: GroupService {
    @Inject
    lateinit var groupRepository: GroupRepository

    override fun getGroupsFromUser(userId: String): List<GroupEntity> {
        return groupRepository.getGroupsFromUser(userId)
    }
}