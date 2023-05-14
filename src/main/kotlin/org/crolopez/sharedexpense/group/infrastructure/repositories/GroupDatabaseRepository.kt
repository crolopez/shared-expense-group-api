package org.crolopez.sharedexpense.group.infrastructure.repositories

import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import org.crolopez.sharedexpense.group.infrastructure.repositories.entities.GroupDbEntity
import org.springframework.data.repository.query.Param
import java.util.*

@Repository
interface GroupDatabaseRepository: CrudRepository<GroupDbEntity, Long>{
    @Query("SELECT g FROM GroupDbEntity g JOIN g.users u WHERE u.username = :username")
    fun findByUsername(@Param("username") username: String): List<GroupDbEntity>
}