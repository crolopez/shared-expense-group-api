package org.crolopez.sharedexpense.user.infrastructure.repositories

import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import org.crolopez.sharedexpense.shared.infrastructure.repositories.entities.GroupDbEntity
import org.crolopez.sharedexpense.shared.infrastructure.repositories.entities.UserDbEntity
import org.springframework.data.repository.query.Param

@Repository
interface UserDatabaseRepository: CrudRepository<UserDbEntity, String>{
    @Query("SELECT g FROM UserDbEntity g JOIN g.groups u WHERE u.groupId = :groupId")
    fun findByGroupId(@Param("groupId") groupId: Long): List<UserDbEntity>
    fun findByUsername(username: String): UserDbEntity?
}