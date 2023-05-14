package org.crolopez.sharedexpense.user.infrastructure.repositories

import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import org.crolopez.sharedexpense.group.infrastructure.repositories.entities.GroupDbEntity
import org.crolopez.sharedexpense.user.infrastructure.repositories.entities.UserDbEntity
import org.springframework.data.repository.query.Param

@Repository
interface UserDatabaseRepository: CrudRepository<UserDbEntity, String> {
    @Query("SELECT g FROM UserDbEntity g JOIN g.groups u WHERE u.groupId = :groupId")
    fun findByGroupId(@Param("groupId") groupId: Long): List<UserDbEntity>

    fun existsByUsername(username: String): Boolean

    @Query("SELECT COUNT(*) > 0 FROM UserDbEntity u JOIN u.groups g WHERE u.username = :username AND g.id = :groupId")
    fun isUserInGroup(@Param("username") username: String, @Param("groupId") groupId: Long): Boolean
}
