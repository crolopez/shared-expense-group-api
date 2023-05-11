package org.crolopez.sharedexpense.group.application.services

import org.crolopez.sharedexpense.group.domain.entities.GroupEntity

interface GroupService {
    fun getGroupsFromUser(userId: String): List<GroupEntity>
}