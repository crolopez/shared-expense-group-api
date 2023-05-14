package org.crolopez.sharedexpense.balance.application.services

import org.crolopez.sharedexpense.balance.domain.entities.BalanceEntity
import org.crolopez.sharedexpense.expense.domain.entities.ExpenseEntity
import org.crolopez.sharedexpense.user.domain.entities.UserEntity

interface BalanceService {
    fun getBalanceFromUsers(expenses: List<ExpenseEntity>, users: List<UserEntity>): List<BalanceEntity>
}