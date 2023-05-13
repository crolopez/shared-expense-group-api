package org.crolopez.sharedexpense.expense.application.services

import org.crolopez.sharedexpense.expense.domain.entities.ExpenseEntity
import org.crolopez.sharedexpense.user.domain.entities.UserEntity
import java.util.*

interface ExpenseService {
    fun addExpense(groupId: Long, username: String, expenseEntity: ExpenseEntity)
    fun getExpensesFromGroup(groupId: Long): List<ExpenseEntity>
}