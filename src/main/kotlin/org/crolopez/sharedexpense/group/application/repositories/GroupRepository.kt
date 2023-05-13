package org.crolopez.sharedexpense.group.application.repositories

import org.crolopez.sharedexpense.group.domain.entities.GroupEntity

interface GroupRepository {
    fun getGroupsFromUser(username: String): List<GroupEntity>
    fun addUserToGroup(groupId: Long, username: String)
}