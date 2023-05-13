package org.crolopez.sharedexpense.group.application.services

import org.crolopez.sharedexpense.group.domain.entities.GroupEntity
import org.crolopez.sharedexpense.user.domain.entities.UserEntity

interface GroupService {
    fun getGroupsFromUser(username: String): List<GroupEntity>
    fun getUsersFromGroup(groupId: Long): List<UserEntity>
    fun addUserToGroup(groupId: Long, username: String)
}