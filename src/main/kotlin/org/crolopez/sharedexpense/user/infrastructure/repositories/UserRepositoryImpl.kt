package org.crolopez.sharedexpense.user.infrastructure.repositories

import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.crolopez.sharedexpense.shared.infrastructure.mappers.Mapper
import org.crolopez.sharedexpense.group.infrastructure.repositories.entities.GroupDbEntity
import org.crolopez.sharedexpense.user.infrastructure.repositories.entities.UserDbEntity
import org.crolopez.sharedexpense.user.application.repositories.UserRepository
import org.crolopez.sharedexpense.user.domain.entities.UserEntity
import java.util.*

class UserRepositoryImpl : UserRepository {

    @Inject
    lateinit var userRepository: UserDatabaseRepository

    @Inject
    lateinit var userDbMapper: Mapper<UserDbEntity, UserEntity>

    override fun getUser(userName: String): Optional<UserEntity> {
        var user = userRepository.findById(userName)
        return if (user.isPresent) {
            Optional.ofNullable(userDbMapper.convert(user.get()))
        } else {
            Optional.empty()
        }
    }

    override fun getUsersFromGroup(groupId: Long): List<UserEntity> {
        val users: List<UserDbEntity> = userRepository.findByGroupId(groupId)
        return users.map { x -> userDbMapper.convert(x) }
    }

    override fun userExists(username: String): Boolean {
        return userRepository.existsByUsername(username)
    }

    override fun userExistsInGroup(username: String, groupId: Long): Boolean {
        return userRepository.isUserInGroup(username, groupId)
    }

}