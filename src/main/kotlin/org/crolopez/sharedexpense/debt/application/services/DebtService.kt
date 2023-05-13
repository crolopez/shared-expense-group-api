package org.crolopez.sharedexpense.debt.application.services

import org.crolopez.sharedexpense.debt.domain.entities.DebtEntity
import org.crolopez.sharedexpense.expense.domain.entities.ExpenseEntity
import org.crolopez.sharedexpense.user.domain.entities.UserEntity
import java.util.*

interface DebtService {
    fun getDebtsFromUsers(expenses: List<ExpenseEntity>, users: List<UserEntity>): List<DebtEntity>
}