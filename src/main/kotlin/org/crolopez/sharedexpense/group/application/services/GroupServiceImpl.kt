package org.crolopez.sharedexpense.group.application.services

import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.crolopez.sharedexpense.group.application.repositories.GroupRepository
import org.crolopez.sharedexpense.group.domain.entities.GroupEntity
import org.crolopez.sharedexpense.user.application.services.UserService
import org.crolopez.sharedexpense.user.domain.entities.UserEntity

@Singleton
class GroupServiceImpl: GroupService {
    @Inject
    lateinit var groupRepository: GroupRepository

    @Inject
    lateinit var userService: UserService

    override fun getGroupsFromUser(username: String): List<GroupEntity> {
        return groupRepository.getGroupsFromUser(username)
    }

    override fun getUsersFromGroup(groupId: Long): List<UserEntity> {
        return userService.getUsersFromGroup(groupId)
    }

    override fun addUserToGroup(groupId: Long, username: String) {
        groupRepository.addUserToGroup(groupId, username)
    }
}