package org.crolopez.sharedexpense.user.application.services

import org.crolopez.sharedexpense.user.domain.entities.UserEntity
import java.util.*

interface UserService {
    fun getUser(userName: String): Optional<UserEntity>
    fun getUsersFromGroup(groupId: Long): List<UserEntity>
}