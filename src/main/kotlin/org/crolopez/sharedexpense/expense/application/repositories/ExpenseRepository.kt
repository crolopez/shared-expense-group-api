package org.crolopez.sharedexpense.expense.application.repositories

import org.crolopez.sharedexpense.expense.domain.entities.ExpenseEntity
import org.crolopez.sharedexpense.user.domain.entities.UserEntity
import java.util.*

interface ExpenseRepository {
    fun addExpense(groupId: Long, username: String, expenseEntity: ExpenseEntity)
    fun getExpensesFromGroup(groupId: Long): List<ExpenseEntity>
}