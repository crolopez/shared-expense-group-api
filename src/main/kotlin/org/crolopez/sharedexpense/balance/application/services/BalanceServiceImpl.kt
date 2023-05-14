package org.crolopez.sharedexpense.balance.application.services

import jakarta.inject.Singleton
import org.crolopez.sharedexpense.balance.domain.entities.BalanceEntity
import org.crolopez.sharedexpense.expense.domain.entities.ExpenseEntity
import org.crolopez.sharedexpense.user.domain.entities.UserEntity

@Singleton
class BalanceServiceImpl: BalanceService {

    override fun getBalanceFromUsers(expenses: List<ExpenseEntity>, users: List<UserEntity>): List<BalanceEntity> {
        val total: Double = expenses.sumOf { x -> x.amount }
        return users.map { user ->
            val contributions: Double = expenses.filter{ x -> x.username == user.userName }.sumOf { x -> x.amount }
            BalanceEntity(
                name = user.name,
                username = user.userName,
                amount = contributions - total / users.size
            )
        }
    }
}