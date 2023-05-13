package org.crolopez.sharedexpense.expense.infrastructure.repositories

import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.crolopez.sharedexpense.expense.application.repositories.ExpenseRepository
import org.crolopez.sharedexpense.expense.domain.entities.ExpenseEntity
import org.crolopez.sharedexpense.group.infrastructure.repositories.entities.ExpenseDbEntity
import org.crolopez.sharedexpense.group.infrastructure.repositories.entities.GroupDbEntity
import org.crolopez.sharedexpense.shared.infrastructure.mappers.Mapper
import org.crolopez.sharedexpense.user.infrastructure.repositories.entities.UserDbEntity

@Singleton
class ExpenseRepositoryImpl : ExpenseRepository {

    @Inject
    lateinit var expenseRepository: ExpenseDatabaseRepository

    @Inject
    lateinit var expenseDbMapper: Mapper<ExpenseDbEntity, ExpenseEntity>

    override fun addExpense(groupId: Long, username: String, expenseEntity: ExpenseEntity) {
        val expenseDbEntity = ExpenseDbEntity(
            amount = expenseEntity.amount,
            currency = "EUR",
            description = expenseEntity.description,
            user = UserDbEntity(
                username = username
            ),
            group = GroupDbEntity(
                groupId = groupId
            )
        )

        expenseRepository.save(expenseDbEntity)
    }

    override fun getExpensesFromGroup(groupId: Long): List<ExpenseEntity> {
        val expenses: List<ExpenseDbEntity> = expenseRepository.findByUserId(groupId)
        return expenses.map { x -> expenseDbMapper.convert(x) }
    }
}
