package org.crolopez.sharedexpense.user.application.repositories

import org.crolopez.sharedexpense.user.domain.entities.UserEntity
import java.util.*

interface UserRepository {
    fun getUser(userName: String): Optional<UserEntity>
}