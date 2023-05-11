package org.crolopez.sharedexpense.group.application.repositories

import org.crolopez.sharedexpense.group.domain.entities.GroupEntity

interface GroupRepository {
    fun getGroupsFromUser(userId: String): List<GroupEntity>
}