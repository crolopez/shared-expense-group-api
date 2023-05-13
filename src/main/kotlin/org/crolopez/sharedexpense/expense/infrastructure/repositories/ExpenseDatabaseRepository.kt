package org.crolopez.sharedexpense.expense.infrastructure.repositories

import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import org.crolopez.sharedexpense.group.infrastructure.repositories.entities.ExpenseDbEntity
import org.crolopez.sharedexpense.group.infrastructure.repositories.entities.GroupDbEntity
import org.crolopez.sharedexpense.user.infrastructure.repositories.entities.UserDbEntity
import org.springframework.data.repository.query.Param

@Repository
interface ExpenseDatabaseRepository: CrudRepository<ExpenseDbEntity, Long>{
    @Query("SELECT h FROM ExpenseDbEntity h WHERE h.group.groupId = :groupId")
    fun findByUserId(@Param("groupId") groupId: Long): List<ExpenseDbEntity>
}
