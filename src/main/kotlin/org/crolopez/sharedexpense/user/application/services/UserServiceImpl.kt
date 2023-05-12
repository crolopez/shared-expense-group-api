package org.crolopez.sharedexpense.user.application.services

import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.crolopez.sharedexpense.user.application.repositories.UserRepository
import org.crolopez.sharedexpense.user.domain.entities.UserEntity
import java.util.*

@Singleton
class UserServiceImpl: UserService {
    @Inject
    lateinit var userRepository: UserRepository

    override fun getUser(userName: String): Optional<UserEntity> {
        return userRepository.getUser(userName)
    }

    override fun getUsersFromGroup(groupId: Long): List<UserEntity> {
        return userRepository.getUsersFromGroup(groupId)
    }
}