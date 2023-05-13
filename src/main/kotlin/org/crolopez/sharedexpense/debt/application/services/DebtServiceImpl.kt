package org.crolopez.sharedexpense.debt.application.services

import jakarta.inject.Singleton
import org.crolopez.sharedexpense.debt.domain.entities.DebtEntity
import org.crolopez.sharedexpense.expense.domain.entities.ExpenseEntity
import org.crolopez.sharedexpense.user.domain.entities.UserEntity

@Singleton
class DebtServiceImpl: DebtService {

    override fun getDebtsFromUsers(expenses: List<ExpenseEntity>, users: List<UserEntity>): List<DebtEntity> {
        val total: Double = expenses.sumOf { x -> x.amount }
        return users.map { user ->
            val contributions: Double = expenses.filter{ x -> x.username == user.userName }.sumOf { x -> x.amount }
            DebtEntity(
                name = user.name,
                username = user.userName,
                amount = contributions - total / users.size
            )
        }
    }
}